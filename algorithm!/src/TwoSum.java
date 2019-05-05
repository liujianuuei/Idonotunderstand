import java.util.HashMap;

public class TwoSum {
    public static void main(String[] args) {
        int[] arr = {3, 7, 2, 1, 6, 4};
        int sum = 5;

        HashMap m = new HashMap();
        for (int i = 0; i < arr.length; i++) {
            m.put(arr[i], i);
        }

        for (int i = 0; i < arr.length; i++) {
            int d = sum - arr[i];
            Object k = m.get(d);
            if (k != null && Integer.valueOf(k.toString()) > i) {
                System.out.print("[" + i + "," + k + "]");
            }
        }
    }
}
