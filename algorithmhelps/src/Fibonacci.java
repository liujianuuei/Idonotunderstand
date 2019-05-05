public class Fibonacci {

    public static void main(String[] args) {
//        for (int i = 1; i <= 10; i++) {
//            System.out.print(fib(i) + "\t");
//        }
        int a = 0;
        int b = 0;
        boolean on = true;

        for (int i = 1; i <= 5; i++) {
            if (i == 1) {
                a = 1;
            }
            if (i == 2) {
                b = 1;
            }
            if (on) {
                a = a + b;
                on = !on;
            } else {
                b = a + b;
                on = !on;
            }
        }

        System.out.print(b);
    }

    public static int fib(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        return fib(n - 1) + fib(n - 2);
    }
}
