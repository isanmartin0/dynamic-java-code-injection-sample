package org.keedio.javascript;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.FileReader;

public class DynamicJavascriptCaller {


    private static final String NASHORN_ENGINE_NAME = "nashorn";

    public static void main(String[] args) throws Exception {

        String fileJsPath = "src/main/resources/script.js";
        String functionName = "myCompute";
        Integer a = 10;
        Integer b = 20;

        ScriptEngine engine = new ScriptEngineManager().getEngineByName(NASHORN_ENGINE_NAME);
        engine.eval(new FileReader(fileJsPath));
        Invocable invocable = (Invocable) engine;

        Object result = invocable.invokeFunction(functionName, a, b);
        System.out.println(result);
        System.out.println(result.getClass());



    }
}
