import java.util.Arrays;
import java.util.Scanner;


public class LongestCommonSubstring {

    private String getCommonStrLength(String str1, String str2) {
        int len1 = str1.length();
        int len2 = str2.length();
        String[][] matrix = new String[len1][len2];

        // Core Alg
        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                if (str1.charAt(i) == str2.charAt(j)) {
                    matrix[i][j] = toString(matrix[i - 1 < 0 ? 0 : i - 1][j - 1 < 0 ? 0 : j - 1]) + str1.charAt(i);
                }
            }
        }
        // End

        String max = "";
        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                max = maxString(matrix[i][j], max);
            }
        }
        return max;
    }

    private String toString(Object o) {
        return o != null ? o.toString() : "";
    }

    private String maxString(String str1, String str2) {
        if (str1 == null) {
            if (str2 == null) {
                return "";
            } else {
                return str2;
            }
        } else {
            if (str2 == null) {
                return str1;
            } else {
                return (str1.length() > str2.length()) ? str1 : str2;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new LongestCommonSubstring().getCommonStrLength("feferabcdefkxywz", "htyhtyabcdefxywz"));
    }
}
