package tech.liujianwei.xinhua08.client;

public class PlzSystem {

    public void start() {
        new PlzService().start();
    }

    public static void main(String[] args) {
        new PlzSystem().start();
    }
}
