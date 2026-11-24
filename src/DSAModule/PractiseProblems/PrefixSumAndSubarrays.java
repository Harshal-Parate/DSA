package DSAModule.PractiseProblems;

import java.util.HashMap;

public class PrefixSumAndSubarrays {

    private static int rangeSum(int[] nums, int left, int right) {
        for (int i = 1; i < nums.length; i++) {
            nums[i] = nums[i] + nums[i - 1];
        }
        return (left == 0) ? nums[right] : nums[right] - nums[left - 1];
    }

    private static int evenIndexRangeSum(int[] nums, int left, int right) {
        if (left > right) {
            return 0;
        }
        int[] eps = new int[nums.length];

        eps[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (i % 2 == 0) {
                eps[i] = nums[i] + eps[i - 1];
            } else {
                eps[i] = eps[i - 1];
            }
        }

        return (left == 0) ? eps[right] : eps[right] - eps[left - 1];
    }

    private static int oddIndexRangeSum(int[] nums, int left, int right) {
        if (left > right) {
            return 0;
        }
        int[] ops = new int[nums.length];

        for (int i = 1; i < nums.length; i++) {
            if (i % 2 != 0) {
                ops[i] = ops[i - 1] + nums[i];
            } else {
                ops[i] = ops[i - 1];
            }
        }

        return (left == 0) ? ops[right] : ops[right] - ops[left - 1];
    }

    private static int specialIndex(int[] nums) {
        int count = 0;
        int n = nums.length;

        if (nums.length == 1) {
            return 1;
        }

        for (int i = 0; i < nums.length; i++) {

            int evenSum = evenIndexRangeSum(nums, 0, i - 1) + oddIndexRangeSum(nums, i + 1, n - 1);
            int oddSum = oddIndexRangeSum(nums, 0, i - 1) + evenIndexRangeSum(nums, i + 1, n - 1);

            if (evenSum == oddSum) {
                count++;
            }
        }
        return count;
    }

    private static int maximumSumSubarray(int[] nums) {
        int maxSum = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            int runningSum = 0;
            for (int j = i; j < nums.length; j++) {
                runningSum += nums[j];
                maxSum = Math.max(maxSum, runningSum);
            }
        }
        return maxSum;
    }

    private static int sumOfAllSubarrays(int[] nums) {
        int totalSum = 0;

        for (int i = 0; i < nums.length; i++) {
            int runningSum = 0;
            for (int j = i; j < nums.length; j++) {
                runningSum += nums[j];
                totalSum += runningSum;
            }
        }
        return totalSum;
    }

    private static long sumOfAllSubarraysContribution(int[] nums) {
        //nums = [1, 2, 3, 4]
        // contribution of ith element -> (i+1) * (n-i)
        int n = nums.length;
        long totalSum = 0;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            long contribution = num * ((long) (i + 1) * (n - i));
            totalSum += contribution;
        }
        return totalSum;
    }

    private static long countAG(String s) {
        //s = "ABCGAG"
        //3
        int aCount = 0;
        int pairs = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'A') {
                aCount++;
            }
            if (s.charAt(i) == 'G') {
                pairs += aCount;
            }
        }
        return pairs;
    }

    private static int pickFromBothSides(int[] nums, int B) {
        if (B == 0) {
            return 0;
        }
        int windowSum = 0;

        for (int i = 0; i < B; i++) {
            windowSum += nums[i];
        }

        int n = nums.length;
        int maxSum = windowSum;

        for (int i = 0; i < B; i++) {
            windowSum = windowSum - nums[B - 1 - i] + nums[n - 1 - i];
            maxSum = Math.max(maxSum, windowSum);
        }

        return maxSum;
    }

    public long countZeroOrSubarrays(int[] nums) {
        // zeros must be together for |
        int count = 0;
        int consecutiveZeros = 0;
        for (int i : nums) {
            if (i == 0) {
                consecutiveZeros++;
                count += consecutiveZeros;
            } else {
                consecutiveZeros = 0;
            }
        }
        return count;
    }

    public int subarraySumBruteForce(int[] nums, int k) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int runningSum = 0;
            for (int j = i; j < nums.length; j++) {
                runningSum += nums[j];
                if (runningSum == k) {
                    count++;
                }
            }
        }
        return count;
    }

    public static int subarraySumOpt(int[] nums, int k) {
        int count = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        int sum = 0;

        map.put(0, 1);

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            int target = sum - k;

            if (map.containsKey(target)) {
                count += map.get(target);
            }

            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

    public static int countZeroSumSubarrays(int[] nums) {
        int count = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        int sum = 0;

        map.put(0, 1);

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            int target = sum;

            if (map.containsKey(target)) {
                count += map.get(target);
            }

            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

    // not understood
    private static int countEvenSumSubarrays(int[] nums) {

        /*
        sub array sum == k
        i - 1 -> target, j -> sum
        prefix[j] - prefix[i-1] == k
        prefix[i-1] == prefix[j] - k

        sub array sum even
        prefix[j] - prefix[i-1] % 2 == 0
        prefix[i-1] % 2 == prefix[j] % 2


         */

        int count = 0;
        int sum = 0;
        int evenCount = 1;  // the empty prefix (sum 0) is even
        int oddCount = 0;

        for (int num : nums) {
            sum += num;

            if ((sum & 1) == 0) {      // current prefix is even
                count += evenCount;    // pair with every earlier even prefix
                evenCount++;
            } else {                   // current prefix is odd
                count += oddCount;     // pair with every earlier odd prefix
                oddCount++;
            }
        }
        return count;
    }

    private static int findEquilibriumIndex(int[] nums) {
        long total = 0;

        for (int x : nums) {
            total += x;
        }

        long leftSum = 0;

        for (int i = 0; i < nums.length; i++) {
            if (leftSum == total - leftSum - nums[i]) {
                return i;
            }
            leftSum += nums[i];
        }
        return -1;
    }

    public int findMaxEquilibriumIndex(int[] nums) {
        long total = 0;
        int maxIdx = -1;

        for (int x : nums) {
            total += x;
        }

        long leftSum = 0;

        // total = leftSum + nums[i] + rightSum
        // rightSum = total - leftSum - nums[i]
        // leftSum == total - leftSum - nums[i]

        for (int i = 0; i < nums.length; i++) {
            if (leftSum == total - leftSum - nums[i]) {
                maxIdx = Math.max(maxIdx, i);
            }
            leftSum += nums[i];
        }
        return maxIdx;
    }

    // not understood
    public int subarraysDivByK(int[] nums, int k) {
        int count = 0;
        long sum = 0;
        int[] remCount = new int[k];
        remCount[0] = 1;  // the empty prefix (sum 0) has remainder 0

        for (int num : nums) {
            sum += num;
            int rem = Math.floorMod(sum, k);  // always in 0..k-1, even for negative sums

            count += remCount[rem];  // pair with every earlier prefix of the same remainder
            remCount[rem]++;         // record the current prefix for later indices
        }
        return count;
    }

    public int longestSubarrayWithSumKBruteForce(int[] nums, int k) {
        int maxLen = 0;
        for (int i = 0; i < nums.length; i++) {
            int runningSum = 0;
            for (int j = i; j < nums.length; j++) {
                runningSum += nums[j];
                if (runningSum == k) {
                    int curLen = j - i + 1;
                    maxLen = Math.max(curLen, maxLen);
                }
            }
        }
        return maxLen;
    }

    public static int longestSubarrayWithSumK(int[] nums, int k) {

        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int maxLen = 0;
        int sum = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            int target = sum - k;

            if (map.containsKey(target)) {
                maxLen = Math.max(maxLen, i - map.get(target));
            }

            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }
        return maxLen;
    }

    public int pivotIndex(int[] nums) {
        int leftSum = 0;
        int totalSum = 0;

        for (int num : nums) {
            totalSum += num;
        }

        for (int i = 0; i < nums.length; i++) {
            if (leftSum == totalSum - leftSum - nums[i]) {
                return i;
            }
            leftSum += nums[i];
        }
        return -1;
    }

    public long[] rangeSumQueries(int[] nums, int[][] queries) {
        long[] res = new long[queries.length];

        for (int i = 1; i < nums.length; i++) {
            nums[i] = nums[i - 1] + nums[i];
        }

        for (int i = 0; i < queries.length; i++) {
            int left = queries[i][0];
            int right = queries[i][1];
            res[i] = (left == 0) ? nums[right] : nums[right] - nums[left - 1];
        }
        return res;
    }

    public long sumOfSubarrayProductsBrute(int[] nums) {
        int totalSum = 0;

        for (int i = 0; i < nums.length; i++) {
            int rProduct = 1;
            for (int j = i; j < nums.length; j++) {
                rProduct *= nums[j];
                totalSum += rProduct;
            }
        }
        return totalSum;
    }

    // ending = num * (1 + ending);
    public long sumOfSubarrayProductsOpt(int[] nums) {
        long ending = 0;
        long total = 0;
        for (int num : nums) {
            ending = num * (1 + ending);
            total += ending;
        }
        return total;
    }

    public long sumSubarrayMins(int[] nums) {
        int n = nums.length;
        int total = 0;

        for (int i = 0; i < n; i++) {
            int min = nums[i];
            for (int j = i; j < n; j++) {
                min = Math.min(min, nums[j]);
                total += min;
            }
        }
        return total;
    }

    public long sumSubarrayMaxs(int[] nums) {
        int n = nums.length;
        int total = 0;

        for (int i = 0; i < n; i++) {
            int max = nums[i];
            for (int j = i; j < n; j++) {
                max = Math.max(max, nums[j]);
                total += max;
            }
        }
        return total;
    }


    public static void main(String[] args) {

        int[] nums = {1, 3, 5, 2, 2};
        int k = 10;
        System.out.println(longestSubarrayWithSumK(nums, k));


        /*
        question 1
        int[] nums = {-2, 0, 3, -5, 2, -1};
        int left = 0;
        int right = 5;
        System.out.println(rangeSum(nums, left, right));
         */

        /*
        question 2
        int[] nums = {7};
        int left = 0;
        int right = 0;
        System.out.println(evenIndexRangeSum(nums, left, right));
         */

        /*
        question 3
        int[] nums = {1, 2, 3, 4, 5, 6};
        int left = 0;
        int right = 5;
        System.out.println(oddIndexRangeSum(nums, left, right));
         */

        /*
        question 4
        int[] nums = {1, 1, 1};
        System.out.println(specialIndex(nums));
        */

        /*
        question 5
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maximumSumSubarray(nums));
        */

        /*
        String s = "ABCGAG";
        System.out.println(countAG(s));
         */
    }

}
