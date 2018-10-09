package org.keedio.dynamic;

import net.openhft.compiler.CompilerUtils;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Loads the addingStrategy and then after 3s replaces it with the
 * subtractingStrategy.
 */
public class DynamicJavaClassLoadingFile1Rule {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(DynamicJavaClassLoadingFile1Rule.class);

    private final static String className = "org.keedio.dynamic.MyClass";
    private static String addingStrategyRule = "";
    private static String subtractingStrategyRule = "";
    /**
     * Load the dynamic java code file
     * @throws IOException
     */
    private static void loadDynamicCodeFile() throws IOException {

        if (logger.isDebugEnabled()) {
            logger.debug("BEGIN loadDynamicCodeFile");
        }

        String path = "src/main/resources/AddingStrategy.rule";

        StringBuffer sbAddingStrategy = new StringBuffer();

        try (BufferedReader r = Files.newBufferedReader(Paths.get(path), Charset.defaultCharset())) {
            r.lines().forEach(item->sbAddingStrategy.append(item).append("\n"));
        }
        addingStrategyRule = sbAddingStrategy.toString();

        path = "src/main/resources/SubtractingStrategy.rule";

        StringBuffer sbSubtractingStrategy = new StringBuffer();

        try (BufferedReader r = Files.newBufferedReader(Paths.get(path), Charset.defaultCharset())) {
            r.lines().forEach(item->sbSubtractingStrategy.append(item).append("\n"));
        }
        subtractingStrategyRule = sbSubtractingStrategy.toString();

        if (logger.isDebugEnabled()) {
            logger.debug("END loadPropertiesFile");
        }

    }

    public static void main(String[] args) throws Exception {
        loadDynamicCodeFile();
        DynamicJavaClassLoading.StrategyProxy strategy = new DynamicJavaClassLoading.StrategyProxy();


        //Thread calling the strategy once a second
        Thread t = new Thread(() -> {
            while (true) {
                int result = strategy.compute(10,20);
                if (result != Integer.MIN_VALUE) {
                    System.out.println(strategy.compute(10,20));
                    break;
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();

        {
            Class aClass = CompilerUtils.CACHED_COMPILER.loadFromJava(className, addingStrategyRule);

            DynamicJavaClassLoading.Strategy runner = (DynamicJavaClassLoading.Strategy) aClass.newInstance();
            strategy.setStratgey(runner);
        }



    }

    public interface Strategy{
        int compute(int a, int b);
    }

    public static class StrategyProxy implements DynamicJavaClassLoading.Strategy {
        private volatile DynamicJavaClassLoading.Strategy underlying;

        public void setStratgey(DynamicJavaClassLoading.Strategy underlying){
            this.underlying = underlying;
        }

        public int compute(int a, int b){
            DynamicJavaClassLoading.Strategy underlying = this.underlying;
            return underlying == null ? Integer.MIN_VALUE : underlying.compute(a, b);
        }
    }

}


