package callable;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class CallableTask {

    public static void main(String[] args) {
        new Thread(new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "Done";
            }
        })).start();
    }
}
