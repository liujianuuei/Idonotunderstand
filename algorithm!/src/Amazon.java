import java.util.*;

public class Amazon {
    List<List<Integer>> optimalUtilization(int deviceCapacity, List<List<Integer>> foregroundAppList, List<List<Integer>> backgroudAppList) {
        List<List<Integer>> results = new ArrayList<>();
        Map<Integer, Integer> map = new TreeMap<>();
        Map<Integer, Integer> map1 = new TreeMap<>();
        for (List<Integer> app : foregroundAppList) {
            Integer findex = app.get(0);
            Integer mem = app.get(1);
            map.put(mem, findex);
        }
        for(List<Integer> app : backgroudAppList) {
            Integer bindex = app.get(0);
            Integer mem = app.get(1);

            Integer diff = deviceCapacity - mem;
            Integer findex = map.get(diff);

            if(findex != null) {
                results.add(Arrays.asList(findex, bindex));
                map.remove(diff);
            } else {
                findex = map.get(diff -1);
                if(findex != null) {
                    results.add(Arrays.asList(findex, bindex));
                    map.remove(diff);
                }
            }
        }
        return results;
    }

    public static void main(String[] args) {
        List<List<Integer>> foregroundAppList = Arrays.asList(Arrays.asList(1, 8), Arrays.asList(2, 15), Arrays.asList(3, 9));
        List<List<Integer>> backgroundAppList = Arrays.asList(Arrays.asList(1, 8), Arrays.asList(2, 11), Arrays.asList(3, 12));

        List<List<Integer>> results = new Amazon().optimalUtilization(20, foregroundAppList, backgroundAppList);

        for (List<Integer> r : results) {
            System.out.print("[" + r.get(0) + ", " + r.get(1) + "]");
        }

        int[] A = {1,2,3,4,5};
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        int tempSum=0, maxSum=0;
        for(int i=0; i<A.length; i++) {
            if(map.containsKey(A[i])) {
                map.put(A[i], map.get(A[i])+1);
                //If the number is repeated only twice then always tempSum = A[i]*2
                tempSum = A[i]*(map.get(A[i])+1);
                if(tempSum > maxSum) {
                    maxSum = tempSum;
                }
            }
            else {
                map.put(A[i], 1);
            }
        }
        System.out.println(maxSum);

    }
}
