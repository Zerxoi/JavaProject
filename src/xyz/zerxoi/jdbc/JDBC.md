# JDBC

## JDBC 驱动注册

[JDBC驱动注册讲解](https://blog.csdn.net/justloveyou_/article/details/72231425)

[Java虚拟机笔记-JDBC驱动加载机制](https://greenhathg.github.io/2019/06/21/Java%E8%99%9A%E6%8B%9F%E6%9C%BA%E7%AC%94%E8%AE%B0-JDBC%E9%A9%B1%E5%8A%A8%E5%8A%A0%E8%BD%BD%E6%9C%BA%E5%88%B6/)

版本：

- java version "11.0.9" 2020-10-20 LTS
- mysql-connector-java-8.0.22.jar

### Class.forName("com.mysql.cj.jdbc.Driver")

```java
try {
    Class.forName("com.mysql.cj.jdbc.Driver");
    connection = (Connection) DriverManager.getConnection(url, username, password);
} catch (Exception e) {
    System.out.println(e);
}
```

使用该方式注册的关键在于 `Class.forName(driver)`，这句话的作用是加载并初始化指定驱动。`com.mysql.cj.jdbc.Driver` 正是在Driver初始化的时候完成注册：

```java
public class Driver extends NonRegisteringDriver implements java.sql.Driver {
    //
    // Register ourselves with the DriverManager
    //
    static {
        try {
            java.sql.DriverManager.registerDriver(new Driver());
        } catch (SQLException E) {
            throw new RuntimeException("Can't register driver!");
        }
    }

    /**
     * Construct a new driver and register it with DriverManager
     * 
     * @throws SQLException
     *             if a database error occurs.
     */
    public Driver() throws SQLException {
        // Required for Class.forName().newInstance()
    }
}
```

### System.setProperty

```java
try {
    System.setProperty("jdbc.drivers", "com.mysql.cj.jdbc.Driver");
    connection = (Connection) DriverManager.getConnection(url, username, password);
} catch (Exception e) {
    System.out.println(e);
}
```

在 `DriverManager.getConnection` 中调用了 `ensureDriversInitialized` 来加载初始的JDBC驱动。

```java
private static void ensureDriversInitialized() {
    if (driversInitialized) {
        return;
    }

    synchronized (lockForInitDrivers) {
        if (driversInitialized) {
            return;
        }
        String drivers;
        try {
            drivers = AccessController.doPrivileged(new PrivilegedAction<String>() {
                public String run() {
                    return System.getProperty(JDBC_DRIVERS_PROPERTY);
                }
            });
        } catch (Exception ex) {
            drivers = null;
        }
        // If the driver is packaged as a Service Provider, load it.
        // Get all the drivers through the classloader
        // exposed as a java.sql.Driver.class service.
        // ServiceLoader.load() replaces the sun.misc.Providers()

        AccessController.doPrivileged(new PrivilegedAction<Void>() {
            public Void run() {

                ServiceLoader<Driver> loadedDrivers = ServiceLoader.load(Driver.class);
                Iterator<Driver> driversIterator = loadedDrivers.iterator();

                /* Load these drivers, so that they can be instantiated.
                    * It may be the case that the driver class may not be there
                    * i.e. there may be a packaged driver with the service class
                    * as implementation of java.sql.Driver but the actual class
                    * may be missing. In that case a java.util.ServiceConfigurationError
                    * will be thrown at runtime by the VM trying to locate
                    * and load the service.
                    *
                    * Adding a try catch block to catch those runtime errors
                    * if driver not available in classpath but it's
                    * packaged as service and that service is there in classpath.
                    */
                try {
                    while (driversIterator.hasNext()) {
                        driversIterator.next();
                    }
                } catch (Throwable t) {
                    // Do nothing
                }
                return null;
            }
        });

        println("DriverManager.initialize: jdbc.drivers = " + drivers);

        if (drivers != null && !drivers.equals("")) {
            String[] driversList = drivers.split(":");
            println("number of Drivers:" + driversList.length);
            for (String aDriver : driversList) {
                try {
                    println("DriverManager.Initialize: loading " + aDriver);
                    Class.forName(aDriver, true,
                            ClassLoader.getSystemClassLoader());
                } catch (Exception ex) {
                    println("DriverManager.Initialize: load failed: " + ex);
                }
            }
        }

        driversInitialized = true;
        println("JDBC DriverManager initialized");
    }
}
```

`ensureDriversInitialized` 中会获取 `jdbc.drivers` 系统属性，并使用使用系统默认的类加载器来加载获取到的 JDBC驱动。

```java
String drivers;
try {
    drivers = AccessController.doPrivileged(new PrivilegedAction<String>() {
        public String run() {
            return System.getProperty(JDBC_DRIVERS_PROPERTY);
        }
    });
} catch (Exception ex) {
    drivers = null;
}
if (drivers != null && !drivers.equals("")) {
    String[] driversList = drivers.split(":");
    println("number of Drivers:" + driversList.length);
    for (String aDriver : driversList) {
        try {
            println("DriverManager.Initialize: loading " + aDriver);
            Class.forName(aDriver, true,
                    ClassLoader.getSystemClassLoader());
        } catch (Exception ex) {
            println("DriverManager.Initialize: load failed: " + ex);
        }
    }
}
```

### DriverManager.getConnection

```java
try {
    connection = (Connection) DriverManager.getConnection(url, username, password);
} catch (Exception e) {
    System.out.println(e);
}
```

`DriverManager.getConnection` 会调用 `ensureDriversInitialized` 来加载初始的JDBC 驱动。其中包含如下代码：

```java
AccessController.doPrivileged(new PrivilegedAction<Void>() {
    public Void run() {

        ServiceLoader<Driver> loadedDrivers = ServiceLoader.load(Driver.class);
        Iterator<Driver> driversIterator = loadedDrivers.iterator();
        try {
            while (driversIterator.hasNext()) {
                driversIterator.next();
            }
        } catch (Throwable t) {
            // Do nothing
        }
        return null;
    }
});
```

`ServiceLoader<Driver> loadedDrivers = ServiceLoader.load(Driver.class)` 会加载所有在 `META-INF/services/java.sql.Driver` 文件里面的类到JVM内存（例如`mysql-connector-java-8.0.22.jar`中`META-INF/services/java.sql.Driver` 的内容为`com.mysql.cj.jdbc.Driver`），完成驱动的自动加载。

看以看到加载 JDBC驱动使用的是当前线程的类加载器 `Thread.currentThread().getContextClassLoader()`。

```java
@CallerSensitive
public static <S> ServiceLoader<S> load(Class<S> service) {
    ClassLoader cl = Thread.currentThread().getContextClassLoader();
    return new ServiceLoader<>(Reflection.getCallerClass(), service, cl);
}
```

在 `driversIterator.next()` 中用于将实例化的驱动保存在一个`List`中。

```java
@Override
public S next() {
    checkReloadCount();
    S next;
    if (index < instantiatedProviders.size()) {
        next = instantiatedProviders.get(index);
    } else {
        next = lookupIterator1.next().get();
        instantiatedProviders.add(next);
    }
    index++;
    return next;
}
```

## 数据库连接池配置

[DBCP 配置](https://commons.apache.org/proper/commons-dbcp/configuration.html)

[c3p0 配置](https://www.mchange.com/projects/c3p0/#configuration)