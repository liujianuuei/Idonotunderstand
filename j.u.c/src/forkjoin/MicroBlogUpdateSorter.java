package forkjoin;

import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

public class MicroBlogUpdateSorter extends RecursiveAction {

    private static final int SMALL_ENOUGH = 32;
    private final Update[] updates;
    private final int start, end;
    private final Update[] results;

    public MicroBlogUpdateSorter(Update[] upds) {
        this(upds, 0, upds.length);
    }

    public MicroBlogUpdateSorter(Update[] upds, int startPos, int endPos) {
        this.start = startPos;
        this.end = endPos;
        updates = upds;
        results = new Update[updates.length];
    }

    private void merge(MicroBlogUpdateSorter left, MicroBlogUpdateSorter right) {
        int i = 0;
        int lCt = 0;
        int rCt = 0;
        while (lCt < left.size() && rCt < right.size()) {
            results[i++] = (left.results[lCt].compareTo(right.results[rCt]) < 0) ? left.results[lCt++] : right.results[rCt++];
        }
        while (lCt < left.size()) {
            results[i++] = left.results[lCt++];
        }
        while (rCt < right.size()) {
            results[i++] = right.results[rCt++];
        }
    }

    public int size() {
        return end - start;
    }

    public Update[] getResults() {
        return results;
    }

    @Override
    protected void compute() {
        if (size() < SMALL_ENOUGH) {
            System.arraycopy(updates, start, results, 0, size());
            Arrays.sort(results, 0, size());
        } else {
            int mid = size() / 2;
            MicroBlogUpdateSorter left = new MicroBlogUpdateSorter(updates, start, start + mid);
            MicroBlogUpdateSorter right = new MicroBlogUpdateSorter(updates, start + mid, end);
            invokeAll(left, right);
            merge(left, right);
        }
    }
}
