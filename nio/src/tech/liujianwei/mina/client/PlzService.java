package tech.liujianwei.mina.client;

public class PlzService implements Runnable {

    public void start() {
        new Thread(this).start();
    }

    @Override
    public void run() {
        new PlzClient().start();
    }
}
