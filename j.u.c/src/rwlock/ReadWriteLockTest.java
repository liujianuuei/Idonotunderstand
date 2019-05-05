package rwlock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockTest {
    private List<Integer> l = new ArrayList<>();

    public ReadWriteLockTest() {
        l.add(1);
    }

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);

    public int get(int i) {
        System.out.println("waiting for the read lock " + Thread.currentThread().getName());
        ReentrantReadWriteLock.ReadLock rlock = lock.readLock();
        rlock.lock();
        System.out.println("got the read lock " + Thread.currentThread().getName());
        try {
            try{Thread.currentThread().sleep(10000);}catch (InterruptedException e){}
            if (i < 0) {
                return l.get(l.size() - 1);
            }
            return l.get(i);
        } finally {
            rlock.unlock();
            System.out.println("released read lock");
        }
    }

    public void add(int v) {
        System.out.println("waiting for the write lock " + Thread.currentThread().getName());
        ReentrantReadWriteLock.WriteLock wlock = lock.writeLock();
        wlock.lock();
        try {
            System.out.println("write " + Thread.currentThread().getName() + " " + v);
            l.add(v);
            try{Thread.sleep(9000);}catch (InterruptedException e){}
        } finally {
            wlock.unlock();
            System.out.println("released write lock");
        }
    }

    public static void main(String[] args) {
        ReadWriteLockTest t = new ReadWriteLockTest();
//        new Thread(() -> {
//            while (true) {
//                t.add(t.get(-1) + 1);
//                //try{Thread.sleep(1);}catch (InterruptedException e){}
//            }
//        }).start();

//        try{Thread.currentThread().sleep(1000);}catch (InterruptedException e){}

        new Thread(()->{
            //while (true) {
                System.out.println("read "+t.get(-1));
                //try{Thread.sleep(1);}catch (InterruptedException e){}
            //}
        }).start();
        new Thread(()->{
            //while (true) {
                System.out.println("read "+t.get(-1));
                //try{Thread.sleep(1);}catch (InterruptedException e){}
            //}
        }).start();
        new Thread(()->{
            //while (true) {
                System.out.println("read "+t.get(-1));
                //try{Thread.sleep(1);}catch (InterruptedException e){}
            //}
        }).start();
        new Thread(()->{
           // while (true) {
                System.out.println("read "+t.get(-1));
                //try{Thread.sleep(1);}catch (InterruptedException e){}
            //}
        }).start();


        try{Thread.currentThread().sleep(1000);}catch (InterruptedException e){}

        new Thread(()->{
            //while (true) {
            t.add(2);
            //try{Thread.sleep(1);}catch (InterruptedException e){}
            //}
        }).start();

    }
}
