package DSAModule.Module1;

import java.util.Arrays;

public class Sept21 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, -4, 5, 6};
        System.out.println(printMaxSubArraySum(arr));

        int[][] B = {{1, 2, 10}, {2, 3, 20}, {2, 5, 25}};
        int A = 5;
        System.out.println(Arrays.toString(continuousSumQuery(A, B)));

        int[] rain = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(rainWaterTrapping(rain));
    }

    private static int rainWaterTrapping(int[] A) {
        int maxHeightIdx = 0;
        int maxHeight = Integer.MIN_VALUE;
        for (int i = 0; i < A.length; i++) {
            if (maxHeight < A[i]) {
                maxHeight = A[i];
                maxHeightIdx = i;
            }
        }

        int totalWater = 0;
        int maxL = Integer.MIN_VALUE;
        for (int i = 0; i < maxHeightIdx; i++) {
            if (maxL < A[i]) {
                maxL = A[i];
            }
            totalWater += (maxL - A[i]);
        }

        int maxR = Integer.MIN_VALUE;
        for (int i = A.length - 1; i > maxHeightIdx; i--) {
            if (maxR < A[i]) {
                maxR = A[i];
            }
            totalWater += maxR - A[i];
        }
        return totalWater;
    }

    private static int[] continuousSumQuery(int A, int[][] B) {
        int[] res = new int[A];

        for (int i = 0; i < B.length; i++) {
            int start = B[i][0] - 1;
            int end = B[i][1] - 1;
            int amount = B[i][2];

            res[start] += amount;

            if (end < res.length - 1) {
                res[end + 1] -= amount;
            }
        }

        for (int i = 1; i < res.length; i++) {
            res[i] = res[i - 1] + res[i];
        }
        return res;
    }

    private static int maxSubArraySum(int[] arr) {
        int max = Integer.MIN_VALUE;
        int current = 0;
        for (int i = 0; i < arr.length; i++) {
            current += arr[i];
            if (current > max) {
                max = current;
            }
            if (current < 0) {
                current = 0;
            }
        }
        return max;
    }

    private static int printMaxSubArraySum(int[] arr) {
        int max = Integer.MIN_VALUE;
        int current = 0;
        int start = 0;
        int end = 0;
        int tempStart = 0;

        for (int i = 0; i < arr.length; i++) {
            current += arr[i];

            if (current > max) {
                max = current;
                start = tempStart;
                end = i;
            }

            if (current < 0) {
                current = 0;
                tempStart = i + 1;
            }
        }


        for (int i = start; i <= end; i++) {
            System.out.print(arr[i]);
        }
        System.out.println();
        return max;
    }
}
