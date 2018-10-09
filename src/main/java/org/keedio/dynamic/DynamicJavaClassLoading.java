package org.keedio.dynamic;

import net.openhft.compiler.CachedCompiler;

/**
 * Loads the addingStrategy and then after 3s replaces it with the
 * subtractingStrategy.
 */
public class DynamicJavaClassLoading {


    private final static String className = "org.keedio.dynamic.MyClass";
    private final static String addingStrategyRule = "package org.keedio.dynamic;\n" +
            "import org.keedio.dynamic.DynamicJavaClassLoading.Strategy;\n" +
            "public class MyClass implements Strategy {\n" +
            "    public int compute(int a, int b) {\n" +
            "        return a+b;\n" +
            "    }\n" +
            "}\n";

    private final static String subtractingStrategyRule = "package org.keedio.dynamic;\n" +
            "import org.keedio.dynamic.DynamicJavaClassLoading.Strategy;\n" +
            "public class MyClass implements Strategy {\n" +
            "    public int compute(int a, int b) {\n" +
            "        return a-b;\n" +
            "    }\n" +
            "}\n";

    public static void main(String[] args) throws Exception {
        StrategyProxy strategy = new StrategyProxy();

        //Thread calling the strategy once a second
        Thread t = new Thread(() -> {
            while (true) {
                System.out.println(strategy.compute(10,20));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();

        {
            ClassLoader cl = new ClassLoader() {
            };
            CachedCompiler cc = new CachedCompiler(null, null);
            Class aClass = cc.loadFromJava(cl, className, addingStrategyRule);

            Strategy runner = (Strategy) aClass.newInstance();
            strategy.setStratgey(runner);
        }

        Thread.sleep(3000);

        {
            ClassLoader cl = new ClassLoader() {
            };
            CachedCompiler cc = new CachedCompiler(null, null);
            Class aClass = cc.loadFromJava(cl, className, subtractingStrategyRule);

            Strategy runner = (Strategy) aClass.newInstance();
            strategy.setStratgey(runner);
        }
    }

    public interface Strategy{
        int compute(int a, int b);
    }

    public static class StrategyProxy implements Strategy{
        private volatile Strategy underlying;

        public void setStratgey(Strategy underlying){
            this.underlying = underlying;
        }

        public int compute(int a, int b){
            Strategy underlying = this.underlying;
            return underlying == null ? Integer.MIN_VALUE : underlying.compute(a, b);
        }
    }
}


