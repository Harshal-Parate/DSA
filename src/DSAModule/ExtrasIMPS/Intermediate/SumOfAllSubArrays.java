package DSAModule.ExtrasIMPS.Intermediate;

import java.util.Arrays;

public class SumOfAllSubArrays {

    // prefix sum method
    public long subarraySum1(int[] arr) {
        int[] prefixSum = new int[arr.length];
        prefixSum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + arr[i];
        }

        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int sum = (i == 0) ? prefixSum[j] : prefixSum[j] - prefixSum[i - 1];
                ans += sum;
            }
        }
        return (long) ans;
    }

    // 2 loops method
    public long subarraySum2(int[] arr) {
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            int sum = 0;
            for (int j = i; j < arr.length; j++) {
                sum = sum + arr[j];
                ans += sum;
            }
        }
        return (long) ans;
    }

    // contribution
    public long subarraySum3(int[] arr) {
        long n = arr.length;
        long ans = 0;
        for (int i = 0; i < arr.length; i++) {
            ans += arr[i] * (i + 1) * (n - i);
        }
        return ans;
    }

    // 0 -> i & i -> n-1
    // valid start * valid end
    // (i+1) * (n-i)


    //
    public int solve(int[] A, int B, int C) {
        int pointA = 0;
        int pointB = pointA + B;

        int[] prefixSum = new int[A.length];

        for (int i = 1; i < A.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + A[i];
        }

        int max = 0;

        while (pointB < A.length) {
            int sum = (pointA == 0) ? prefixSum[pointB] : prefixSum[pointB] - prefixSum[pointA];
            max = Math.max(sum, max);
            pointB++;
            pointA++;
        }
        return max;
    }

    public long solve(int[] A, int B) {
        int pointA = 0;
        int pointB = pointA + B;
        int[] prefixSum = new int[A.length];
        prefixSum[0] = A[0];
        for (int i = 1; i < A.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + A[i];
        }

        long max = Long.MIN_VALUE;

        while (pointB <= A.length) {
            long sum = (pointA == 0) ? prefixSum[pointB - 1] : prefixSum[pointB - 1] - prefixSum[pointA - 1];
            max = Math.max(sum, max);
            pointB++;
            pointA++;
        }
        return max;
    }

    public int solve1(int[] A, int B) {
        int pointerB = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] == B) {
                pointerB = i;
            }
        }
        return countSwaps(A, 0, pointerB);
    }

    private int countSwaps(int[] arr, int start, int end) {
        int count = 0;
        for (int i = start; i < end; i++) {
            for (int j = i + 1; j < end; j++) {
                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                    count++;
                }
            }
        }
        return count;
    }

    public static int solve(int[] A) {
        Arrays.sort(A);
        reverse(A);
        int totalSum = 0;

        for (int i = 0; i < A.length; i++) {
            int sum = 0;
            for (int j = i; j < A.length; j++) {
                sum = sum + A[j];
            }
            totalSum = totalSum + sum;
        }
        return totalSum;
    }

    static int[] reverse(int[] A) {
        int start = 0;
        int end = A.length - 1;
        while (start < end) {
            int temp = A[start];
            A[start] = A[end];
            A[end] = temp;
            start++;
            end--;
        }
        return A;
    }

    public static void main(String[] args) {
        int[] arr = {2, 1};
        int solve = solve(arr);
        System.out.println(solve);

//        for(int i=0; i<arr.length; i++) {
//            System.out.println(arr[][]);
//        }
//
//    }


    }
}

