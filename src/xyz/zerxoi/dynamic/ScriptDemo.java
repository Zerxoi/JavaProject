package xyz.zerxoi.dynamic;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ScriptDemo {
    public static void main(String[] args) throws ScriptException, NoSuchMethodException, FileNotFoundException {
        // Java 脚本API是联通 Java 平台和脚本的桥梁
        // Java 应用程序可以通过一套固定的接口与各种脚本引擎交互,
        // 从而达到再 Java 平台上调用各种脚本语言的目的
        // 可以把一些复杂异变的业务逻辑交给脚本语言处理
        // 有时可以大大提高开发效率

        ScriptEngineManager sem = new ScriptEngineManager();
        ScriptEngine se = sem.getEngineByName("javascript");
        se.put("msg", "kaguya yes!");
        se.eval("print(msg);");
        se.eval("msg = 'thoru yes!'");
        System.out.println(se.get("msg"));

        // 定义函数
        se.eval("function add(a, b) { var sum = a + b; return sum;}");
        // 获取调用接口
        Invocable jsInvocable = (Invocable) se;
        // 执行脚本中定义的方法
        Object res = jsInvocable.invokeFunction("add", 13, 20);
        System.out.println(res);

        // 导入 Java 包, 使用其他包中的 Java 类
        String jsCode = "var Arrays = java.util.Arrays; var list = Arrays.asList([\"kaguya\", \"tohru\"]);";
        se.eval(jsCode);

        List<?> list = null;
        Object obj = se.get("list");
        if (obj instanceof List) {
            list = (List<?>) obj;
        }
        for (Object e : list) {
            System.out.println(e);
        }

        // 执行 JS 文件
        Reader reader = new FileReader("src/xyz/zerxoi/dynamic/scriptDemo.js");
        // Reader reader = new
        // InputStreamReader(ScriptDemo.class.getResourceAsStream("scriptDemo.js"));
        // Reader reader = new
        // InputStreamReader(ScriptDemo.class.getClassLoader().getResourceAsStream("xyz/zerxoi/dynamic/scriptDemo.js"));
        se.eval(reader);

        try {
            if (reader != null)
                reader.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
