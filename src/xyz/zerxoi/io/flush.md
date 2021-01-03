# flush 方法

## flush 与 OutputStream

`flush` 方法在 `OutputStream` 抽象类中的实现是空函数。

```java
public abstract class OutputStream implements Closeable, Flushable {
    public void flush() throws IOException {
    }
}
```

`OutputStream` 的子类有：`ByteArrayOutputStream`, `FileOutputStream`, `FilterOutputStream`(子类有`BufferedOutputStream`, `DataOutputStream`, `PrintStream`), `ObjectOutputStream`, `PipedOutputStream` 等几个类.

`ByteArrayOutputStream`, `FileOutputStream` 类的 `flush` 方法均是从父类继承的.

`FilterOutputStream` 类重写了 `flush` 方法,但是实质还是调用父类的 `flush` 方法. 并且会在 `close` 方法调用过程中执行 `flush`.

```java
public class FilterOutputStream extends OutputStream {
    @Override
    public void flush() throws IOException {
        out.flush();
    }

    @Override
    public void close() throws IOException {
        if (closed) {
            return;
        }
        synchronized (closeLock) {
            if (closed) {
                return;
            }
            closed = true;
        }

        Throwable flushException = null;
        try {
            flush();
        } catch (Throwable e) {
            flushException = e;
            throw e;
        } finally {
            if (flushException == null) {
                out.close();
            } else {
                try {
                    out.close();
                } catch (Throwable closeException) {
                   // evaluate possible precedence of flushException over closeException
                   if ((flushException instanceof ThreadDeath) &&
                       !(closeException instanceof ThreadDeath)) {
                       flushException.addSuppressed(closeException);
                       throw (ThreadDeath) flushException;
                   }

                    if (flushException != closeException) {
                        closeException.addSuppressed(flushException);
                    }

                    throw closeException;
                }
            }
        }
    }
}
```

`ObjectOutputStream` 类重写了 `flush` 方法, 并且在 `close` 中同样调用了 `flush` 方法.

```java
public class ObjectOutputStream
    extends OutputStream implements ObjectOutput, ObjectStreamConstants
{
    private final BlockDataOutputStream bout;

    public void flush() throws IOException {
        bout.flush();
    }

    public void close() throws IOException {
        flush();
        clear();
        bout.close();
    }

    private static class BlockDataOutputStream
        extends OutputStream implements DataOutput
    {
        public void flush() throws IOException {
            drain();
            out.flush();
        }

        public void close() throws IOException {
            flush();
            out.close();
        }

        void drain() throws IOException {
            if (pos == 0) {
                return;
            }
            if (blkmode) {
                writeBlockHeader(pos);
            }
            out.write(buf, 0, pos);
            pos = 0;
        }
    }
}
```

`PipedOutputStream` 类也重写了 `flush` 方法.

```java
class PipedOutputStream extends OutputStream {
    private PipedInputStream sink;

    public synchronized void flush() throws IOException {
        if (sink != null) {
            synchronized (sink) {
                sink.notifyAll();
            }
        }
    }

    public void close()  throws IOException {
        if (sink != null) {
            sink.receivedLast();
        }
    }
}
```

以 `BufferedOutputStream` 类为例

```java
public class BufferedOutputStream extends FilterOutputStream {
    protected byte buf[]; // 缓存字节数组, 默认长度是 8192B=8KB
    protected int count; // 记录 buf 的字节数

    // out 是父类 FilterOutputStream 中的成员变量
    // 将 buf 中的数据刷入 out 流中
    private void flushBuffer() throws IOException {
        if (count > 0) {
            out.write(buf, 0, count);
            count = 0;
        }
    }

    @Override
    public synchronized void write(byte b[], int off, int len) throws IOException {
        if (len >= buf.length) {
            /* If the request length exceeds the size of the output buffer,
               flush the output buffer and then write the data directly.
               In this way buffered streams will cascade harmlessly. */
            flushBuffer();
            out.write(b, off, len);
            return;
        }
        if (len > buf.length - count) {
            flushBuffer();
        }
        System.arraycopy(b, off, buf, count, len);
        count += len;
    }

    @Override
    public synchronized void flush() throws IOException {
        flushBuffer();
        out.flush();
    }
}
```

当调用 `write` 方法写入 `buf` 时, `buf` 未满是不会像向`out`流中写入数据, 数据仅仅是在 `buf` 字节数组中. 如果这是想强制向 `out` 流写入数据, 可以调用 `flush` 方法(如果数据正好写完, 调用 `close` 方法也可以, 因为 `close` 方法会调用 `flush` 方法).

所以说通常只有包含缓存的流才需要 `flush` 方法, 像 `ByteArrayOutputStream`, `FileOutputStream` 这样直接写入的流并不需要 `flush` 方法.

```java
public class Test {
    public static void main(String[] args) throws Exception {
        File file = new File("text.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
        bos.write("too short".getBytes());
        // bos.flush();
        // bos.close();
    }
}
```

上述示例中, `BufferedOutputStream` 不会将字符串 `"too short"` 写入文件, 因为缓存未满, 如果调用 `flush` 方法或者 `close` 方法就会将上述数据写入文件中.

## flush 与 Writer

`Writer` 的子类有 `BufferedWriter`, `CharArrayWriter`, `FileWriter`(`OutputStreamWriter`), `FilterWriter`, `PipedWriter`, `PrintWriter` `StringWriter`. 大体和 `OuputStream` 类似, 不再赘述.
