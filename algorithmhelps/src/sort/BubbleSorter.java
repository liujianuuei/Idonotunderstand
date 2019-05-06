package sort;

public class BubbleSorter {
    public static void bubble(int[] numbers) {
        for (int i = 0; i < numbers.length - 1; i++) {
            for (int j = 0; j < numbers.length - i - 1; j++) {
                if (numbers[j] > numbers[j + 1]) {
                    int temp = numbers[j];
                    numbers[j] = numbers[j + 1];
                    numbers[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr={
            4, 8, 1, 3, 2, 9, 6, 5, 7
        } ;
        bubble(arr);
        for(int i : arr) {
            System.out.print(i +" ");
        }
    }
}
