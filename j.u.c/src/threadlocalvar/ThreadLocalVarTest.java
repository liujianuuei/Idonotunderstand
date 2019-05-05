package threadlocalvar;

public class ThreadLocalVarTest implements Runnable {

    private final ThreadLocal<String> threadLocal = new ThreadLocal<String>() {
        @Override
        protected String initialValue() {
            return "乘风";
        }
    };

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " " + threadLocal.get());
        threadLocal.set(Thread.currentThread().getName());
        System.out.println(Thread.currentThread().getName() + " " + threadLocal.get());
    }

    public static void main(String[] args) {
        Runnable runnable = new ThreadLocalVarTest();

        new Thread(runnable, "ABC").start();
        new Thread(runnable,"BCD").start();
        new Thread(runnable, "DEF").start();
    }
}
