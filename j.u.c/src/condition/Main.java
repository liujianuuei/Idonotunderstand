package condition;

public class Main {
    public static void main(String[] args) {
        QueueWorker worker = new QueueWorker(new MarketDataService());
        new Thread(worker).start();
        new Thread(()->{
            while (true) {
                worker.addAndNotify("hello".getBytes());
                try{Thread.sleep(10);}catch (InterruptedException e){}
            }
        }).start();
    }
}
