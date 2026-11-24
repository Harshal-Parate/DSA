package DSAModule.ExtrasIMPS.Intermediate.Arrays;

import java.util.Arrays;

public class FirstMissingPositive {
    public static void main(String[] args) {
        int[] arr = {1, 2, 2, 3, 1, 5};

        System.out.println(firstMissingPositive(arr));

    }

    ///  Natural number -> Optimised
    private static int firstMissingPositiveOptimised(int[] arr) {
        // Step 1: Ignore non-positive numbers
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] <= 0) {
                arr[i] = arr.length + 10;
            }
        }

        // Step 2: Mark numbers that exist
        for (int i = 0; i < arr.length; i++) {
            int element = Math.abs(arr[i]);
            if (element <= arr.length) {
                int idx = element - 1;
                arr[idx] = -1 * Math.abs(arr[idx]);
            }
        }

        // Step 3: First positive index = missing positive number
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 0) {
                return i + 1;
            }
        }
        return arr.length + 1;
    }

    ///  Natural number
    private static int firstMissingPositive(int[] arr) {
        Arrays.sort(arr);
        int min = 1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == min) {
                min++;
            } else {
                return min++;
            }
        }
        return 1;
    }
}
