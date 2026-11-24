package DSAModule.PractiseProblems;

import java.util.ArrayList;

public class KadaneAlgorithm {

    private static long minSubarraySum(int[] nums) {
        int minSum = Integer.MAX_VALUE;
        int current = 0;

        for (int i = 0; i < nums.length; i++) {
            current += nums[i];

            if (current < minSum) {
                minSum = current;
            }

            if (current > 0) {
                current = 0;
            }
        }
        return minSum;
    }

    private static long maxSubarraySum(int[] nums) {
        int maxSum = Integer.MIN_VALUE;
        int current = 0;

        for (int i = 0; i < nums.length; i++) {
            current += nums[i];

            if (current > maxSum) {
                maxSum = current;
            }

            if (current < 0) {
                current = 1;
            }
        }
        return maxSum;
    }

    public long maxSubarraySumBrute(int[] nums) {
        int maxSum = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            int runningSum = 0;
            for (int j = i; j < nums.length; j++) {
                runningSum += nums[j];
                if (runningSum > maxSum) {
                    maxSum = runningSum;
                }
            }
        }
        return maxSum;
    }

    private static ArrayList<Integer> maxSubarrayWithIndices(int[] nums) {
        int maxSum = Integer.MIN_VALUE;
        int current = 0;
        int start = 0;
        int tempStart = 0;
        int end = 0;

        for (int i = 0; i < nums.length; i++) {
            current += nums[i];

            if (current > maxSum) {
                maxSum = current;
                start = tempStart;
                end = i;
            }

            if (current < 0) {
                current = 0;
                tempStart = i + 1;
            }
        }

        ArrayList<Integer> res = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            res.add(nums[i]);
        }

        return res;
    }

    public long maxCircularSubarraySum(int[] nums) {
        int maxSum = Integer.MIN_VALUE;
        int minSum = Integer.MAX_VALUE;
        int currMax = 0;
        int currMin = 0;
        int total = 0;

        for (int i = 0; i < nums.length; i++) {
            total += nums[i];

            currMax += nums[i];
            maxSum = Math.max(maxSum, currMax);
            if (currMax < 0) {
                currMax = 0;
            }

            currMin += nums[i];
            minSum = Math.min(minSum, currMin);
            if (currMin > 0) {
                currMin = 0;
            }
        }
        if (maxSum < 0) return maxSum;   // all elements negative: the answer is the largest element
        return Math.max(maxSum, total - minSum);
    }

    private static long maxProductSubarray(int[] nums) {
        long best = nums[0];
        long currentMax = nums[0];
        long currentMin = nums[0];

        for (int i = 1; i < nums.length; i++) {
            long x = nums[i];
            long a = currentMax * x;
            long b = currentMin * x;

            currentMax = Math.max(x, Math.max(a, b));
            currentMin = Math.min(x, Math.min(a, b));

            best = Math.max(best, currentMax);
        }
        return best;
    }

    private static long maxSumNonAdjacent(int[] nums) {
        long prev1 = 0;   // best up to index i-1
        long prev2 = 0;   // best up to index i-2

        for (int x : nums) {

            // int temp = a+b
            // a+b = c;
            // c = temp;
            long newBest = Math.max(prev1, prev2 + x);
            prev2 = prev1;
            prev1 = newBest;
        }
        return prev1;
    }

    // Maximum sum after deleting one element — Moderate
    private static int maximumSumBrute(int[] arr) {
        int oneDelete = arr[0];
        int noDelete = 0;
        int best = arr[0];

        for (int i = 1; i < arr.length; i++) {
            int current = arr[i];
            oneDelete = Math.max(oneDelete + current, noDelete);
            noDelete = Math.max(noDelete + current, current);
            best = Math.max(best, Math.max(noDelete, oneDelete));
        }
        return best;
    }

    public static void main(String[] args) {
        int[] arr = {-3, 4, -1, 2};
        System.out.println(maximumSumBrute(arr));

    }
}
