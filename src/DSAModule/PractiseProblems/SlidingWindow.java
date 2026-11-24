package DSAModule.PractiseProblems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class SlidingWindow {

    private static long maxSumSubarrayOfSizeK(int[] nums, int k) {
        int maxSum = 0;
        int left = 0;
        int right = k;
        int kSum = 0;

        for (int i = 0; i < k; i++) {
            kSum += nums[i];
        }
        maxSum = kSum;

        while (right < nums.length) {
            int curSum = kSum - nums[left] + nums[right];
            maxSum = Math.max(curSum, maxSum);
            left++;
            right++;
            kSum = curSum;
        }
        return maxSum;
    }

    private static long maxSumSubArrayOfKClean(int[] nums, int k) {
        long kSum = 0;

        for (int i = 0; i < k; i++) {
            kSum += nums[i];
        }

        long maxSum = kSum;

        for (int i = k; i < nums.length; i++) {
            kSum += nums[i] - nums[i - k];
            maxSum = Math.max(maxSum, kSum);
        }
        return maxSum;
    }

    private static long minSumSubarrayOfSizeK(int[] nums, int k) {
        long kSum = 0;

        for (int i = 0; i < k; i++) {
            kSum += nums[i];
        }

        long minSum = kSum;

        for (int i = k; i < nums.length; i++) {
            kSum += nums[i] - nums[i - k];
            minSum = Math.min(minSum, kSum);
        }
        return minSum;
    }

    private static double findMaxAverage(int[] nums, int k) {
        return (double) maxSumSubArrayOfKClean(nums, k) / k;
    }


    // swaps = (total ones) - (max ones in any window of that length)
    private static int minSwaps(int[] nums) {
        int window = 0;

        for (int i : nums) {
            if (i == 1) {
                window++;
            }
        }

        int currentOne = 0;
        for (int i = 0; i < window; i++) {
            if (nums[i] == 1) {
                currentOne++;
            }
        }

        int minSwaps = window - currentOne;

        for (int i = window; i < nums.length; i++) {
            currentOne += nums[i] - nums[i - window];
            minSwaps = Math.min(minSwaps, window - currentOne);
        }
        return minSwaps;
    }

    private int lengthOfLongestSubstring(String s) {
        HashSet<Character> set = new HashSet<>();

        int start = 0;
        int end = 0;
        int len = 0;

        while (end < s.length()) {
            char character = s.charAt(end);

            if (set.contains(character)) {
                set.remove(s.charAt(start));
                start++;
            } else {
                set.add(character);
                end++;
                len = Math.max(len, end - start);
            }
        }
        return len;
    }

    // 0, 1, 0, 1, 1, 0, 1
    private int longestOnesWithOneSwap(int[] nums) {
        int totalOnes = 0;
        for (int x : nums) {
            if (x == 1) totalOnes++;
        }

        int start = 0;
        int zeroCount = 0;
        int maxLen = 0;

        for (int end = 0; end < nums.length; end++) {
            if (nums[end] == 0) zeroCount++;

            while (zeroCount > 1) {          // window has two zeros, so shrink from the left
                if (nums[start] == 0) zeroCount--;
                start++;
            }

            maxLen = Math.max(maxLen, end - start + 1);
        }
        return Math.min(maxLen, totalOnes);  // can't build a block longer than the number of 1s
    }


    // count vowels in window brute force
    private int maxVowelsBrute(String s, int k) {
        ArrayList<String> strings = getAllSubstrings(s, k);
        int maxCount = 0;
        for (String str : strings) {
            int count = getVowelCount(str);
            maxCount = Math.max(count, maxCount);
        }
        return maxCount;
    }

    private int getVowelCount(String str) {
        int count = 0;
        for (Character ch : str.toCharArray()) {
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                count++;
            }
        }
        return count;
    }

    private ArrayList<String> getAllSubstrings(String s, int k) {
        int left = 0;
        int right = left + k;
        ArrayList<String> strings = new ArrayList<>();

        while (right <= s.length()) {
            strings.add(s.substring(left, right));
            left++;
            right++;
        }
        return strings;
    }

    // count vowel in window opt
    // abciiidef -> 3
    private static int countVowels(String s, int k) {
        int initialCount = 0;

        for (int i = 0; i < k; i++) {
            if (vowelCheck(s.charAt(i))) {
                initialCount++;
            }
        }
        int maxCount = initialCount;

        for (int i = k; i < s.length(); i++) {
            if (vowelCheck(s.charAt(i))) {
                initialCount++;
            }
            if (vowelCheck(s.charAt(i - k))) {
                initialCount--;
            }
            maxCount = Math.max(maxCount, initialCount);
        }
        return maxCount;
    }

    private static boolean vowelCheck(char ch) {
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }

    private static int countAnagramsBrute(String txt, String pat) {
        // "forxxorfxdofr"
        // "for"

        int windowSize = pat.length();
        int maxCount = 0;
        int initCount = 0;

        for (int i = 0; i + windowSize <= txt.length(); i++) {
            String subString = txt.substring(i, i + windowSize);
            if (anagramCheck(subString, pat)) {
                initCount++;
            }
            maxCount = Math.max(maxCount, initCount);
        }
        return maxCount;
    }

    private static boolean anagramCheck(String str, String pat) {
        if (str.length() != pat.length()) return false;

        int[] arr = new int[256];

        for (int i = 0; i < str.length(); i++) {
            arr[str.charAt(i)]++;
            arr[pat.charAt(i)]--;
        }

        for (int i : arr) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }

    private static int countAnagramOpt(String str, String pat) {
        int k = pat.length();

        if (k > str.length()) {
            return 0;
        }

        int[] need = new int[25];
        int[] window = new int[25];

        for (int i = 0; i < k; i++) {
            need[pat.charAt(i) - 'a']++;
            window[str.charAt(i) - 'a']++;
        }

        int count = (Arrays.equals(need, window)) ? 1 : 0;

        for (int i = k; i < str.length(); i++) {
            window[str.charAt(i) - 'a']++;
            window[str.charAt(i - k) - 'a']--;
            if (Arrays.equals(need, window)) {
                count++;
            }
        }
        return count;

    }

    private static int lengthOfLongestSubstringTwoDistinct(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int start = 0;
        int maxLen = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            map.put(ch, map.getOrDefault(ch, 0) + 1);

            while (map.size() > 2) {
                char c = s.charAt(start);
                Integer value = map.get(c);
                if (value == 1) {
                    map.remove(c);
                } else {
                    map.put(c, value - 1);
                }
                start++;
            }
            maxLen = Math.max(maxLen, i - start + 1);
        }
        return maxLen;
    }

    private static int minSubArrayLenBrute(int[] nums, int k) {
        int minLen = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length; i++) {
            int runningSum = 0;
            for (int j = i; j < nums.length; j++) {
                runningSum += nums[j];
                if (runningSum >= k) {
                    minLen = Math.min(minLen, j - i + 1);
                    break;
                }
            }
        }
        return (minLen == Integer.MAX_VALUE) ? minLen : 0;
    }

    // for to expand and while to shrink
    private static int minSubArrayLenOpt(int[] nums, int k) {
        int minLen = Integer.MAX_VALUE;
        int sum = 0;
        int left = 0;

        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];

            while (sum >= k) {
                minLen = Math.min(minLen, right - left + 1);
                sum -= nums[left];
                left++;
            }
        }
        return (minLen == Integer.MAX_VALUE) ? 0 : minLen;
    }

    private static int longestOnes(int[] nums) {
        int zeros = 0;
        int maxLen = 0;
        int left = 0;

        for (int right = 0; right < nums.length; right++) {
            if (nums[right] == 0) {
                zeros++;
            }
            while (zeros > 1) {
                if (nums[left] == 0) {
                    zeros--;
                }
                left++;
            }
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }

    private static long maximumSubarraySumOfSizeK(int[] nums, int k) {
        // [1, 5, 4, 2, 9, 9, 9] , k=3 , o/p = 15
        HashSet<Integer> set = new HashSet<>();
        int sum = 0;
        int left = 0;
        int maxSum = 0;


        for (int right = 0; right < nums.length; right++) {
            while (set.contains(nums[right])) {
                set.remove(nums[left]);
                sum -= nums[left];
                left++;
            }
            set.add(nums[right]);
            sum += nums[right];

            int currWindowSize = right - left + 1;

            if (currWindowSize > k) {
                set.remove(nums[left]);
                sum -= nums[left];
                left++;
            }
            if (currWindowSize == k) {
                maxSum = Math.max(maxSum, sum);
            }
        }
        return maxSum;
    }


    public static void main(String[] args) {
        int[] nums = {1,2,1,3};
        int k = 3;
//        System.out.println(minSwaps(nums));

        //System.out.println(countAnagramOpt("forxxorfxdofr", "for"));
        //System.out.println(lengthOfLongestSubstringTwoDistinct("eceba"));
        //System.out.println(minSubArrayLenOpt(nums, k));
        //System.out.println(longestOnes(nums));
        System.out.println(maximumSubarraySumOfSizeK(nums, k));

    }
}