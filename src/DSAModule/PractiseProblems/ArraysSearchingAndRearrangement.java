package DSAModule.PractiseProblems;

import java.util.*;

public class ArraysSearchingAndRearrangement {

    // i need to place the number at i-1 index
    // ignore -ve, 0 and number greater than length of array or the number which is at correct index already
    private static int firstMissingPositive(int[] nums) {
        int i = 0;
        int n = nums.length;

        while (i < n) {
            if (nums[i] <= 0 || nums[i] > n || nums[i] == i + 1) {
                i++;
                continue;
            }

            int correctIndex = nums[i] - 1;

            if (nums[correctIndex] == correctIndex + 1) {
                i++;
                continue;
            }

            int temp = nums[i];
            nums[i] = nums[correctIndex];
            nums[correctIndex] = temp;
        }

        for (int j = 0; j < n; j++) {
            if (nums[j] != j + 1) {
                return j + 1;
            }
        }
        return n + 1;
    }

    private static int maxProfit(int[] prices) {
        // 7, 1, 5, 3, 6, 4
        // buy at 1 and sell at 6
        // track min and subtract and update the maxProfit
        int minSoFar = Integer.MAX_VALUE;
        int maxProfit = 0;

        for (int price : prices) {
            if (price < minSoFar) {
                minSoFar = price;
            }
            maxProfit = Math.max(maxProfit, price - minSoFar);
        }
        return maxProfit;
    }

    private static int[] mergeSortedArrays(int[] a, int[] b) {
        int m = a.length;
        int n = b.length;
        int[] arr = new int[m + n];
        int i = 0, j = 0, k = 0;

        while (i < m && j < n) {
            if (a[i] < b[j]) {
                arr[k] = a[i];
                i++;
            } else {
                arr[k] = b[j];
                j++;
            }
            k++;
        }

        while (i < m) {
            arr[k] = a[i];
            i++;
            k++;
        }

        while (j < n) {
            arr[k] = b[j];
            j++;
            k++;
        }

        return arr;
    }

    // sort using map
    private static int[] countSort(int[] nums) {
        /*
        Hint (the idea):
         - Find max.
         - Create int[] count = new int[max + 1] and do count[x]++ for each element.
         - Walk v from 0 to max, and write v into the output count[v] times.
        */
        TreeMap<Integer, Integer> map = new TreeMap<>();

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        int[] res = new int[nums.length];

        int index = 0;
        for (int i : map.keySet()) {
            Integer iteration = map.get(i);
            for (int j = 0; j < iteration; j++) {
                res[index] = i;
                index++;
            }
        }

        return res;
    }

    private static int[] countSortReal(int[] A) {
        int max = A[0];

        for (int i : A) {
            max = Math.max(max, i);
        }

        // build frequency array -> index me frequency store kardo of element from A
        int[] frequency = new int[max + 1];

        for (int j : A) {
            frequency[j]++;
        }

        int index = 0;

        // frequency kamm kar and number to add karo A me he
        for (int i = 0; i < frequency.length; i++) {
            while (frequency[i] > 0) {
                A[index++] = i;
                frequency[i]--;
            }
        }

        return A;
    }

    // divide and sort
    // the merge the sorted
    private static int[] mergeSort(int[] nums) {
        mergeSolveSol(nums, 0, nums.length - 1);
        return nums;
    }

    private static void mergeSolveSol(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSolveSol(nums, left, mid);
        mergeSolveSol(nums, mid + 1, right);

        mergeArray(nums, left, mid, right);
    }

    private static void mergeArray(int[] nums, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left;
        int j = mid + 1;
        int k = 0;

        while (i <= mid && j <= right) {
            if (nums[i] <= nums[j]) {
                temp[k++] = nums[i++];
            } else {
                temp[k++] = nums[j++];
            }
        }

        while (i <= mid) {
            temp[k++] = nums[i++];
        }

        while (j <= right) {
            temp[k++] = nums[j++];
        }

        for (int x = 0; x < temp.length; x++) {
            nums[left + x] = temp[x];
        }
    }

    private static int minSwapsToSort(int[] nums) {
        // [4, 3, 2, 1]
        // [0, 1, 2, 3]
        ArrayList<ArrayList<Integer>> pair = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            ArrayList<Integer> list = new ArrayList<>();
            list.add(i);
            list.add(nums[i]);
            pair.add(list);
        }

        pair.sort((a, b) -> a.get(1) - b.get(1));

        // [[0, 4], [1, 3], [2, 2], [3, 1]]
        // [[3, 1], [2, 2], [1, 3], [0, 4]]
        // 0,       1,      2     , 3

        boolean[] visited = new boolean[nums.length];
        int swaps = 0;

        for (int i = 0; i < nums.length; i++) {
            if (visited[i] || pair.get(i).get(0) == i) {
                continue;
            }

            int cycleLength = 0;
            int j = i;

            while (!visited[j]) {
                visited[j] = true;
                j = pair.get(j).get(0);
                cycleLength++;
            }

            swaps += cycleLength - 1;

        }
        return swaps;
    }

    private static int[] productExceptSelfBrute(int[] nums) {
        int[] productArray = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int runningProduct = 1;
            for (int j = 0; j < nums.length; j++) {
                if (i != j) {
                    runningProduct *= nums[j];
                    productArray[i] = runningProduct;
                }
            }
        }
        return productArray;
    }

    private static int[] productExceptSelfOpt(int[] nums) {
        int[] productArray = new int[nums.length];
        int n = nums.length;

        productArray[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            productArray[i] = productArray[i - 1] * nums[i - 1];
        }

        int right = 1;
        for (int i = n - 1; i >= 0; i--) {
            productArray[i] *= right;
            right *= nums[i];
        }

        return productArray;
    }

    // HashMap
    private int[] findTwoUnique(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        int[] res = new int[2];
        int index = 0;
        for (Integer i : map.keySet()) {
            if (map.get(i) == 1) {
                res[index++] = i;
            }
        }
        return res;
    }

    private static int[] moveZeroes(int[] nums) {
        int[] res = new int[nums.length];
        int start = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                res[start++] = nums[i];
            }
        }

        while (start < res.length) {
            res[start++] = 0;
        }

        return res;
    }

    // dutch national flag
    private static int[] sortColors(int[] nums) {
        int start = 0;
        int right = nums.length - 1;
        int pointer = 0;

        while (pointer <= right) {
            int curNum = nums[pointer];
            if (curNum == 0) {
                swap(nums, start, pointer);
                start++;
                pointer++;
            } else if (curNum == 2) {
                swap(nums, right, pointer);
                right--;
            } else {
                pointer++;
            }
        }
        return nums;
    }

    private static int[] rearrangeAlternate(int[] nums) {
        // 3, 1, -2, -5, 2, -4
        int[] res = new int[nums.length];
        int pos = 0;
        int neg = 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                res[pos] = nums[i];
                pos += 2;
            } else {
                res[neg] = nums[i];
                neg += 2;
            }
        }
        return res;
    }

    private static int findDuplicate(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int i : nums) {
            if (set.contains(i)) {
                return i;
            }
            set.add(i);
        }
        return -1;
    }

    private static void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }

    private static int[] findMissingAndRepeating(int[] nums) {
        int n = nums.length;
        int i = 0;

        while (i < n) {
            int correctIndex = nums[i] - 1;
            if (nums[i] == i + 1 || nums[i] == nums[correctIndex]) {
                i++;
            } else {
                int temp = nums[correctIndex];
                nums[correctIndex] = nums[i];
                nums[i] = temp;
            }
        }

        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != j + 1) {
                return new int[]{nums[j], j + 1};
            }
        }
        return new int[]{-1, -1};
    }

    private static int maxDifference(int[] nums) {
        int best = 0;
        int start = nums[0];
        for (int i = 0; i < nums.length; i++) {
            start = Math.min(start, nums[i]);
            best = Math.max(best, nums[i] - start);
        }
        return best;
    }

    private static int[] findLeadersBrute(int[] nums) {
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            int j = i + 1;
            boolean isLeader = greaterCheck(nums, i, j);
            if (isLeader) {
                list.add(nums[i]);
            }
        }

        int[] res = new int[list.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    private static boolean greaterCheck(int[] nums, int i, int j) {
        int current = nums[i];
        for (int k = j; k < nums.length; k++) {
            if (current <= nums[k]) {
                return false;
            }
        }
        return true;
    }

    private static int[] findLeaders(int[] nums) {
        ArrayList<Integer> list = new ArrayList<>();
        int max = Integer.MIN_VALUE;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (max < nums[i]) {
                list.add(nums[i]);
                max = nums[i];
            }
        }

        Collections.reverse(list);
        int[] res = new int[list.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = list.get(i);
        }
        return res;
    }


    // elements appearing more than n/2 times
    private static int majorityElement(int[] nums) {
        int candidate = 0;
        int count = 0;

        for (int i : nums) {
            if (count == 0) candidate = i;
            if (candidate == i) count++;
            count--;
        }

        int total = 0;
        for (int i : nums) {
            if (i == candidate) total++;
        }

        return total > nums.length / 2 ? candidate : -1;
    }

    // elements appearing more than n/3 times
    private static List<Integer> majorityElementII(int[] nums) {
        List<Integer> res = new ArrayList<>();

        int count1 = 0, count2 = 0;
        int candidate1 = 0, candidate2 = 1;

        for (int i : nums) {
            if (i == candidate1) {
                count1++;
            } else if (i == candidate2) {
                count2++;
            } else if (count1 == 0) {
                candidate1 = i;
                count1 = 1;
            } else if (count2 == 0) {
                candidate2 = i;
                count2 = 1;
            } else {
                count1--;
                count2--;
            }
        }

        int total1 = 0;
        int total2 = 0;

        for (int i : nums) {
            if (i == candidate1) total1++;
            if (i == candidate2) total2++;
        }

        res.add(total1 > nums.length / 3 ? candidate1 : -1);
        res.add(total2 > nums.length / 3 ? candidate2 : -1);

        return res;
    }

    private static List<Integer> majorityElementIIHashMap(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        List<Integer> res = new ArrayList<>();

        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        for(int i : map.keySet()) {
            Integer value = map.get(i);
            if(value > nums.length/3) res.add(i);
        }

        return res;
    }

    private static int findLongestPalindrome(String str) {
        char[] charArray = str.toCharArray();
        int maxLength = 0;

        for (int i = 0; i < charArray.length; i++) {
            int oddLen = expand(charArray, i, i);
            int eveLen = expand(charArray, i, i + 1);
            maxLength = Math.max(Math.max(oddLen, eveLen), maxLength);
        }
        return maxLength;
    }

    private static int expand(char[] charArray, int left, int right) {
        while (left >= 0 && right < charArray.length
                && charArray[left] == charArray[right]) {
            left--;
            right++;
        }
        return right - left - 1;
    }

    public static void main(String[] args) {
        int[] nums1 = {16, 17, 4, 3, 5, 2};
        int[] nums2 = {1, 1, 1, 3, 3, 2, 2, 2};
        System.out.println(Arrays.toString(findLeaders(nums1)));
        System.out.println(majorityElementIIHashMap(nums2));
    }
}
