package DSAModule.PractiseProblems;

import java.util.Arrays;

public class DifferenceArray {

    private static long[] continuousSumQuery(int n, int[][] updates) {
        long[] diff = new long[n + 1];          // extra slot for r + 1

        for (int i = 0; i < updates.length; i++) {
            int left = updates[i][0];
            int right = updates[i][1];
            int value = updates[i][2];

            diff[left] += value;
            diff[right + 1] -= value;

        }

        long[] res = new long[n];
        res[0] = diff[0];
        for (int i = 1; i < n; i++) {
            res[i] = res[i - 1] + diff[i];      // running sum rebuilds the array
        }

        return res;
    }

    private static int[] rangeIncrementQueries(int[] nums, int[][] queries) {
        int n = nums.length;
        int[] result = new int[n];
        if (n == 0) return result;

        int[] diff = new int[n + 1];

        for (int[] q : queries) {
            int left = q[0];
            int right = q[1];
            int inc = q[2];

            diff[left] += inc;
            diff[right + 1] -= inc;
        }

        int running = 0;

        for (int i = 0; i < n; i++) {
            running += diff[i];
            result[i] = nums[i] + running;
        }
        return result;
    }

    private static int[] multipleRangeAdditions(int n, int[][] updates) {
        int[] diff = new int[n + 1];
        if (n == 0) return diff;

        for (int[] q : updates) {
            int left = q[0];
            int right = q[1];
            int value = q[2];

            diff[left] += value;
            diff[right + 1] -= value;
        }

        int[] res = new int[n];
        res[0] = diff[0];
        for (int i = 1; i < n; i++) {
            res[i] = res[i - 1] + diff[i];
        }
        return res;
    }

    private static int[] rangeIncrementFinalArray(int[] nums, int[][] updates) {
        int n = nums.length;
        int[] diff = new int[n + 1];

        for (int[] i : updates) {
            int left = i[0];
            int right = i[1];
            int value = i[2];

            diff[left] += value;
            diff[right + 1] -= value;
        }

        int[] res = new int[n];
        int runningSum = 0;

        for (int i = 0; i < n; i++) {
            runningSum += diff[i];
            res[i] = nums[i] + runningSum;
        }
        return res;
    }

    private static int[] corpFlightBookings(int[][] bookings, int n) {
        int[] diff = new int[n + 2];

        for (int[] i : bookings) {
            int left = i[0];
            int right = i[1];
            int values = i[2];

            diff[left] += values;
            diff[right + 1] -= values;
        }

        int[] res = new int[n];
        int running = 0;
        for (int i = 0; i < n; i++) {
            running += diff[i + 1];
            res[i] = running;
        }
        return res;
    }

    private static int[] rangeAdditionWithNegatives(int n, int[][] updates) {
        int[] diff = new int[n + 1];

        for (int[] i : updates) {
            int left = i[0];
            int right = i[1];
            int values = i[2];

            diff[left] += values;
            diff[right + 1] -= values;
        }

        int[] res = new int[n];
        int running = 0;

        for (int i = 0; i < n; i++) {
            running += diff[i];
            res[i] = running;
        }
        return res;
    }

    private static int[] diffArrayPrefixQuery(int n, int[][] updates, int[][] queries) {
        int[] arr = rangeAdditionWithNegatives(n, updates);

        for (int i = 1; i < arr.length; i++) {
            arr[i] = arr[i - 1] + arr[i];
        }

        int[] res = new int[queries.length];
        int index = 0;
        for(int[] i : queries) {
            int left = i[0];
            int right = i[1];
            res[index] = (left == 0) ? arr[right] : arr[right] - arr[left-1];
            index++;
        }
        return res;
    }


    public static void main(String[] args) {
        int[][] arr = {{0, 2, -3}, {1, 4, 5}, {2, 3, -4}};
        int n = 5;
        System.out.println(Arrays.toString(rangeAdditionWithNegatives(5, arr)));
    }
}
