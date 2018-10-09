package org.keedio.reflection;

import org.joor.Reflect;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReflectionCompiler {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ReflectionCompiler.class);

    private static String ruleClassCode = "";

    /**
     * Load the dynamic java code file
     * @throws IOException
     */
    private static void loadDynamicCodeFile() throws IOException {

        if (logger.isDebugEnabled()) {
            logger.debug("BEGIN loadDynamicCodeFile");
        }

        String path = "src/main/resources/MyRule.rule";

        StringBuffer sbRuleClassCode = new StringBuffer();

        try (BufferedReader r = Files.newBufferedReader(Paths.get(path), Charset.defaultCharset())) {
            r.lines().forEach(item->sbRuleClassCode.append(item).append("\n"));
        }
        ruleClassCode = sbRuleClassCode.toString();

        if (logger.isDebugEnabled()) {
            logger.debug("END loadPropertiesFile");
        }

    }

    public static void main(String[] args) throws Exception {
        loadDynamicCodeFile();

        Integer a = 10;
        Integer b = 20;
        String className = "org.keedio.MyRule";
        String methodName = "myCompute";
        Reflect.compile(className, ruleClassCode).create().call(methodName,a,b).get();


        //System.out.println(result);


    }
}
