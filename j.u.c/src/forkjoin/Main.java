package forkjoin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        List<Update> l = new ArrayList<>();
        String text = "";
        final Update.Builder builder = new Update.Builder();
        for (int i = 0; i < 256; i++) {
            text = text + "X";
            long now = System.currentTimeMillis();
            l.add(builder.author("JL").updateText(text).createTime(now).build());
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
            }
        }
        Collections.shuffle(l);
        Update[] updates = l.toArray(new Update[0]);

        MicroBlogUpdateSorter sorter = new MicroBlogUpdateSorter(updates);
        ForkJoinPool pool = new ForkJoinPool(4);
        pool.invoke(sorter);
        for (Update u : sorter.getResults()) {
            System.out.println(u);
        }
    }
}
