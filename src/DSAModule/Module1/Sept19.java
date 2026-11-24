package DSAModule.Module1;

public class Sept19 {
    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 4, 5};
        int[] arr2 = {1, 2, 3, 4, 5};
        int k = 3;
        maxSumSubArray1(arr1, k);
        maxSumSubArrayOptimised(arr2, k);

    }

    public static int maxSumOfAllSubArray(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            int temp = 0;
            for (int j = i; j < arr.length; j++) {
                temp += arr[j];
                if (max < temp) {
                    max = temp;
                }
            }
        }
        return max;
    }

    public static int sumOfAllSubArray(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            int temp = 0;
            for (int j = i; j < arr.length; j++) {
                temp += arr[j];
                sum += temp;
            }
        }
        return sum;
    }

    public long subarraySum(int[] A) {
        // contribution
        int sum = 0;
        for (int i = 0; i < A.length; i++) {
            sum = A[i] * ((i + 1) * (A.length - i));
        }
        return sum;
    }

    public static void maxSumSubArrayOptimised(int[] arr, int k) {
        int maxSum = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += arr[i];
        }
        maxSum = sum;

        int start = 0;
        int end = k;

        while (end < arr.length) {
            int currentSum = sum - arr[start] + arr[end];
            sum = currentSum;
            maxSum = Math.max(currentSum, maxSum);
            start++;
            end++;
        }
        System.out.println("Max sum: " + maxSum + ", for size of subarray k: " + k);
    }

    public static void maxSumSubArray1(int[] arr, int k) {
        int maxSum = Integer.MIN_VALUE;
        int n = arr.length;

        for (int i = 0; i <= n - k; i++) {
            int temp = 0;
            for (int j = 0; j < k; j++) {
                temp += arr[i + j];
            }
            maxSum = Math.max(maxSum, temp);
        }
        System.out.println("Max sum: " + maxSum + ", for size of subarray k: " + k);
    }

    public long[] rangeSum(int[] A, int[][] B) {
        long[] res = new long[B.length];
        long[] ps = new long[A.length];

        ps[0] = A[0];
        for (int i = 1; i < A.length; i++) {
            ps[i] = ps[i - 1] + A[i];
        }

        for (int i = 0; i < B.length; i++) {
            int left = B[i][0];
            int right = B[i][1];

            res[i] = (left == 0) ? ps[right] : ps[right] - ps[left - 1];
        }
        return res;
    }

    public long[] rangeSumEvenIndex(int[] A, int[][] B) {
        long[] res = new long[B.length];
        long[] ps = new long[A.length];

        ps[0] = A[0];
        for (int i = 1; i < A.length; i++) {
            if (i % 2 == 0) {
                ps[i] = ps[i - 1] + A[i];
            } else {
                ps[i] = ps[i - 1];
            }
        }

        for (int i = 0; i < B.length; i++) {
            int left = B[i][0];
            int right = B[i][1];

            res[i] = (left == 0) ? ps[right] : ps[right] - ps[left - 1];
        }
        return res;
    }

    public long[] rangeSumOddIndex(int[] A, int[][] B) {
        long[] res = new long[B.length];
        long[] ps = new long[A.length];

        ps[0] = 0;
        for (int i = 1; i < A.length; i++) {
            if (i % 2 != 0) {
                ps[i] = ps[i - 1] + A[i];
            } else {
                ps[i] = ps[i - 1];
            }
        }

        for (int i = 0; i < B.length; i++) {
            int left = B[i][0];
            int right = B[i][1];

            res[i] = (left == 0) ? ps[right] : ps[right] - ps[left - 1];
        }
        return res;
    }
}
