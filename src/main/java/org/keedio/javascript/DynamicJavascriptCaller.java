package org.keedio.javascript;

import org.keedio.beans.MetricsBean;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

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

        System.out.println("\nExample function call with single parameters");
        Object result = invocable.invokeFunction(functionName, a, b);
        System.out.println(result);
        System.out.println(result.getClass());

        System.out.println("\nExample function call with array");
        int[] arrayValues = {1,2,3,4,5};
        result = invocable.invokeFunction("myComputeArray", arrayValues);
        System.out.println(result);
        System.out.println(result.getClass());

        System.out.println("\nExample function call with map");
        Map<String, Integer> valuesMap = new HashMap<>();
        valuesMap.put("par1", new Integer(a));
        valuesMap.put("par2", new Integer(b));
        result = invocable.invokeFunction("fun2", valuesMap);
        System.out.println(result);
        System.out.println(result.getClass());

        System.out.println("\nExample function call with map with objects");
        Map<String, MetricsBean> valuesMapObject = new HashMap<>();
        MetricsBean mb1 = new MetricsBean("par1", "MB" , "2018-10-09T13:17:28.000Z", a);
        MetricsBean mb2 = new MetricsBean("par2", "MB" , "2018-10-09T13:17:28.000Z", b);
        valuesMapObject.put("par1", mb1);
        valuesMapObject.put("par2", mb2);

        result = invocable.invokeFunction("fun3", valuesMapObject);
        System.out.println(result);
        System.out.println(result.getClass());
    }
}
