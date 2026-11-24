package DSAModule.PractiseProblems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class HashSetHashMapQuestions {

    private static HashSetHashMapQuestions clazz = new HashSetHashMapQuestions();

    private int countPairsWithTargetSumAllPairs(int[] nums, int k) {
        int count = 0;
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i : nums) {
            int target = k - i;

            if (map.containsKey(target) && map.get(target) > 0) {
                count += map.get(target);
            }
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        return count;
    }

    private int countPairsWithTargetSumUniquePairs(int[] nums, int k) {
        int count = 0;
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i : nums) {
            int target = k - i;

            if (map.containsKey(target) && map.get(target) > 0) {
                map.put(target, map.get(target) - 1);
                count++;
            }
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        return count;
    }

    private ArrayList<ArrayList<Integer>> findAllIndexPairs(int[] nums, int target) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();

        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int partner = target - nums[i];

            if (map.containsKey(partner)) {
                for (int index : map.get(partner)) {
                    ArrayList<Integer> pair = new ArrayList<>();
                    pair.add(index);
                    pair.add(i);
                    list.add(pair);
                }
            }

            if (map.containsKey(num)) {
                map.get(num).add(i);
            } else {
                ArrayList<Integer> index = new ArrayList<>();
                index.add(i);
                map.put(num, index);
            }
        }
        return list;

    }

    private int countUniqueValuePairs(int[] nums, int target) {
        HashSet<Integer> set = new HashSet<>();
        int count = 0;

        for (int i : nums) {
            int partner = target - i;
            if (set.contains(partner)) {
                set.remove(partner);
                count++;
            } else {
                set.add(i);
            }
        }
        return count;
    }

    private int findSubArrayWithSumK(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int sum = 0;
        int count = 0;

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

    private int longestConsecutiveSequence(int[] nums) {
        // 9, 1, 4, 7, 3, -1, 0, 5, 8, -1, 6
        HashSet<Integer> set = new HashSet<>();
        for (int i : nums) {
            set.add(i);
        }

        int count = 0;
        for (int i : nums) {
            int previous = i - 1;
            if (!set.contains(previous)) {
                int num = i;
                int curCount = 0;
                while (set.contains(num)) {
                    curCount++;
                    num++;
                }
                count = Math.max(count, curCount);
            }
        }
        return count;
    }

    private int firstRepeatingElement(int[] nums) {
        HashMap<Integer, Integer> frequencyMap = new HashMap<>();

        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        for (int num : nums) {
            if (frequencyMap.get(num) > 1) {
                return num;
            }
        }

        return -1;
    }

    private int firstNonRepeatingElement(int[] A) {
        HashMap<Integer, Integer> frequencyMap = new HashMap<>();

        for (int num : A) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        for (int num : A) {
            if (frequencyMap.get(num) == 1) {
                return num;
            }
        }

        return -1;
    }

    private ArrayList<Integer> commonElementsBtwArray(int[] A, int[] B) {
        HashSet<Integer> seen = new HashSet<>();
        ArrayList<Integer> list = new ArrayList<>();
        for (int i : A) {
            seen.add(i);
        }
        for (int i : B) {
            if (seen.contains(i)) {
                list.add(i);
            }
        }
        return list;
    }

    private ArrayList<Integer> intersectionOfArray(int[] A, int[] B) {
        HashMap<Integer, Integer> map = new HashMap<>();
        ArrayList<Integer> ans = new ArrayList<>();

        for (int i : A) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        for (int i = 0; i < B.length; i++) {
            int curNum = B[i];

            if (map.containsKey(curNum) && map.get(curNum) > 0) {
                map.put(curNum, map.get(curNum) - 1);
                ans.add(curNum);
            }
        }
        return ans;
    }

    private int longestSubarrayWithSumZero(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int sum = 0;
        int maxLen = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            int target = sum;

            if (map.containsKey(target)) {
                maxLen = Math.max(maxLen, i - map.get(target));
            }
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }
        return maxLen;
    }

    /*
    a - b == k, so b = a - k
    b - a == k, so b = a + k
    */
    private int countPairsWithDiffK(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int count = 0;

        for (int i : nums) {
            int posTarget = i - k;
            int negTarget = i + k;

            if (map.containsKey(posTarget)) {
                count += map.get(posTarget);
            }

            if (k != 0) {
                if (map.containsKey(negTarget)) {
                    count += map.get(negTarget);
                }
            }

            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        return count;
    }

    public ArrayList<Integer> countDistinctInWindows(int[] nums, int k) {
        ArrayList<Integer> res = new ArrayList<>();
        HashMap<Integer, Integer> countMap = new HashMap<>();

        for (int i = 0; i < k; i++) {
            countMap.put(nums[i], countMap.getOrDefault(nums[i], 0) + 1);
        }

        res.add(countMap.size());

        for (int i = k; i < nums.length; i++) {
            int enter = nums[i];
            countMap.put(enter, countMap.getOrDefault(enter, 0) + 1);

            int leave = nums[i - k];
            if (countMap.containsKey(leave)) {
                if (countMap.get(leave) == 1) {
                    countMap.remove(leave);
                }
                else {
                    countMap.put(leave, countMap.get(leave) - 1);
                }
            }
            res.add(countMap.size());
        }
        return res;
    }

//    public List<List<String>> groupAnagrams(String[] str) {
//
//    }

    public static void main(String[] args) {
        int[] nums = {1, 5, 7, -1, 5};
        int i = clazz.countPairsWithTargetSumAllPairs(nums, 6);
        System.out.println(i);

        int i1 = clazz.countPairsWithTargetSumUniquePairs(nums, 6);
        System.out.println(i1);

        ArrayList<ArrayList<Integer>> allIndexPairs = clazz.findAllIndexPairs(nums, 6);
        System.out.println(allIndexPairs);

        int i2 = clazz.countUniqueValuePairs(nums, 6);
        System.out.println(i2);

        int[] nums2 = {3, 4, 7, 2, -3, 1, 4, 2};
        int i3 = clazz.findSubArrayWithSumK(nums2, 7);
        System.out.println(i3);

        int[] nums3 = {9, 1, 4, 7, 3, -1, 0, 5, 8, -1, 6, 3};
        int i4 = clazz.longestConsecutiveSequence(nums3);
        System.out.println(i4);

        int i5 = clazz.firstRepeatingElement(nums3);
        System.out.println(i5);

        int[] nums4 = {4, 9, 5};
        int[] nums5 = {9, 4, 9, 8, 4};
        ArrayList<Integer> list = clazz.intersectionOfArray(nums4, nums5);
        System.out.println(list);

        int[] nums6 = {15, -2, 2, -8, 1, 7, 10, 23};
        int i6 = clazz.longestSubarrayWithSumZero(nums6);
        System.out.println(i6);

        int[] nums7 = {1, 5, 3, 4, 2};
        int i7 = clazz.countPairsWithDiffK(nums7, 2);
        System.out.println(i7);


    }


}
