package DSAModule.Module1;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;

public class Oct14 {
    public static void main(String[] args) {
        System.out.println(longestSubWithoutRepChar("pwwkew"));

        int[] arr = {1, 0, 1};
        int k = 1;

        int[] arr1 = {100, 4, 200, 1, 3};

//        System.out.println(countSubArraysSumK(arr, k));
        System.out.println(countSubArraysSumKOpt(arr, k));
        System.out.println(longestIncreasingContiguousSubarray(arr1));
    }

    // longest sub string without repeating characters
    private static int longestSubWithoutRepChar(String str) {
        HashSet<Character> seen = new HashSet<>();
        int length = 0;
        int start = 0;
        int end = 0;

        while (end < str.length()) {
            char ch = str.charAt(end);

            if (seen.contains(ch)) {
                seen.remove(str.charAt(start));
                start++;
            } else {
                seen.add(ch);
                end++;
                length = Math.max(length, end - start);
            }
        }

        return length;
    }

    // frequency
    public int solve(int[] A) {
        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>();
        for (int i : A) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        for (Integer i : map.keySet()) {
            if (map.get(i) == 1) {
                return 1;
            }
        }

        return -1;
    }

    // subArray sum = 0
    private boolean subArraySum(int[] arr) {
        HashSet<Long> set = new HashSet<>();
        long totalSum = 0;
        for (int num : arr) {
            totalSum += num;
            if (totalSum == 0 || set.contains(totalSum)) {
                return true;
            }
            set.add(totalSum);
        }
        return false;
    }

    // subArray sum == k and count the number of sub arrays
    // 0  1 -1  3 4 5
    // |     |
    private static int countSubArraysSumK1(int[] arr, int z) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            int sum = 0;
            for (int j = i; j < arr.length; j++) {
                sum += arr[j];
                if (sum == z) {
                    count++;
                }
            }
        }
        return count;
    }

    // optimised
    private static int countSubArraysSumKOpt(int[] arr, int z) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int count = 0;
        int sum = 0;
        map.put(0, 1);

        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            int target = sum - z;

            if (map.containsKey(target)) {
                count += map.get(target);
            }

            if (map.containsKey(sum)) {
                map.put(sum, map.get(sum) + 1);
            } else {
                map.put(sum, 1);
            }
        }
        return count;
    }


    // brute force
    public static int longestIncreasingContiguousSubarray(final int[] A) {
        int current = 1;
        int result = 0;
        for (int i = 1; i < A.length; i++) {
            if (A[i - 1] < A[i]) {
                current++;
                result = Math.max(result, current);
            } else {
                current = 1;
            }
        }
        return result;
    }

    public int longestConsecutive(final int[] A) {
        HashSet<Integer> set = new HashSet<>();
        for (int n : A) {
            set.add(n);
        }
        int total = 0;

        for (int i = 0; i < A.length; i++) {
            int num = A[i];

            if (set.contains(num - 1)) {
                continue;
            } else {
                int current = num;
                int count = 0;
                while (set.contains(current)) {
                    count++;
                    current++;

                    total = Math.max(total, count);
                }
            }
        }
        return total;
    }


}
