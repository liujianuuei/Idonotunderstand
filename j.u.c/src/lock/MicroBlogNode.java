package lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MicroBlogNode {

    private final Lock lock = new ReentrantLock();

    private final String id;

    public MicroBlogNode(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void propagateUpdate(Update upd, MicroBlogNode backup) {
        boolean acquired = false;
        boolean done = false;
        while (!done) {
            int wait = (int) (Math.random() * 10);
            try {
                acquired = lock.tryLock(wait, TimeUnit.MILLISECONDS);
                if (acquired) {
                    System.out.println(id + ": received: " + upd.getUpdateText() + "; backup: " + backup.getId());
                    Thread.sleep(3000);
                    System.out.println("try lock " + backup.getId());
                    done = backup.tryConfirmUpdate(this, upd);
                }
            } catch (InterruptedException e) {
            } finally {
                if (acquired) {
                    lock.unlock();
                }
            }
            if (!done) {
                System.out.println("waiting for " + backup.getId());
                try {
                    Thread.sleep(wait);
                } catch (InterruptedException e) {
                }
            }
        }
    }

    private boolean tryConfirmUpdate(MicroBlogNode other, Update upd) {
        boolean acquired = false;
        try {
            int wait = (int) (Math.random() * 10);
            acquired = lock.tryLock(wait, TimeUnit.MILLISECONDS);
            if (acquired) {
                System.out.println(id + ": received conformation: " + upd.getUpdateText() + " from " + other.getId());
                return true;
            }
        } catch (InterruptedException e) {
        } finally {
            if (acquired) {
                lock.unlock();
            }
        }
        return false;
    }

    public static void main(String[] args) {
        final MicroBlogNode local = new MicroBlogNode("localhost:8080");
        final MicroBlogNode other = new MicroBlogNode("localhost:8988");

        final Update first = new Update("1");
        final Update second = new Update("2");

        new Thread(() -> {
            local.propagateUpdate(first, other);
        }).start();
        new Thread(() -> {
            other.propagateUpdate(second, local);
        }).start();
    }
}

class Update {
    private String text;

    public Update(String t) {
        this.text = t;
    }

    public String getUpdateText() {
        return text;
    }
}