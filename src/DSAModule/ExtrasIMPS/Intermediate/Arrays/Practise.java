package DSAModule.ExtrasIMPS.Intermediate.Arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Practise {
    public static void main(String[] args) {
        /// Generate all subarrays
        int[] arr1 = {1, 2, 3, 4};
        generateAllSubArrays1(arr1); // n^3

        ///  Range Sum
        int[] arr2 = {1, 2, 3, 4};
        int left1 = 0;
        int right1 = 3;
        rangeSum(arr2, left1, right1);

        ///  Count even in range
        int[] arr3 = {2, 2, 3, 4};
        int left2 = 0;
        int right2 = 3;
        evenInRange(arr3, left2, right2);

        ///  Count odd in range
        int[] arr4 = {1, 2, 3, 4};
        int left3 = 0;
        int right3 = 3;
        oddInRange(arr4, left3, right3);

        ///  sum of odd index
        int[] arr5 = {1, 2, 3, 4};
        int left4 = 2;
        int right4 = 3;
        oddIndexSum(arr5, left4, right4);

        ///  sum of even index
        int[] arr6 = {1, 2, 3, 4};
        int left5 = 2;
        int right5 = 2;
        evenIndexSum(arr6, left5, right5);

        ///  Special Index
        int[] arr7 = {4, 2, 6, 3, 8};
        specialIndex1(arr7);
        specialIndexOptimised(arr7);

        /// Count AG
        /// A = "AGAG" -> 3
        String str1 = "AGAG";
        countAG(str1);
        countAGOptimised(str1);

        /// Count Increasing Triplets
        int[] arr8 = {1, 2, 4, 3};
        countIncreasingTriplets(arr8);
        /// For every middle element: (# smaller on left) × (# greater on right).
        countIncreasingTripletsOptimised(arr8);

        /// Min swaps required to bring all together less than B
        /// minimum swaps = window size - maximum good elements in window
        int[] arr9 = {1, 12, 10, 3, 14, 10, 5};
        int b1 = 8;
        minSwaps(arr9, b1);
        minSwapsOptimised(arr9, b1);

        ///  pick from both sides
        int[] arr10 = {5, -2, 3, 1, 2};
        int b2 = 3;
        pickFromBothSides(arr10, b2);

        /// Max Profit
        int[] arr11 = {1, 4, 2, 7, 5};
        maxProfit(arr11);
        maxProfitOptimised(arr11);

        // Maximum Subarray ≤ B
        int[] arr12 = {2, 1, 3, 4, 5};
        int b3 = 7;
        maximumSubarraySumLessThanEqualB(arr12, b3);


    }

    private static void maximumSubarraySumLessThanEqualB(int[] arr, int b) {
        int maxSum = Integer.MIN_VALUE;
        int start = 0;
        int end = 0;
        for (int i = 0; i < arr.length; i++) {
            int curSum = 0;
            for (int j = i; j < arr.length; j++) {
                curSum += arr[j];
                if (curSum <= b) {
                    if (maxSum < curSum) {
                        maxSum = curSum;
                        start = i;
                        end = j;
                    }
                }
            }
        }
        System.out.println(maxSum);
        System.out.println(Arrays.toString(Arrays.copyOfRange(arr, start, end + 1)));
    }

    private static void maxProfitOptimised(int[] arr) {
        // find best buying price -> min
        // track max profit;
        int buy = arr[0];
        int profit = 0;
        for (int i = 1; i < arr.length; i++) {
            buy = Math.min(buy, arr[i]);
            profit = Math.max(profit, arr[i] - buy);
        }
        System.out.println(profit);
    }

    private static void maxProfit(int[] arr) {
        int profit = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] < arr[j]) {
                    profit = Math.max(profit, arr[j] - arr[i]);
                }
            }
        }
        System.out.println(profit);
    }

    private static void pickFromBothSides(int[] arr, int b) {
        int sum = 0;
        for (int i = 0; i < b; i++) {
            sum += arr[i];
        }

        int lp = b - 1;
        int rp = arr.length - 1;
        int maxSum = sum;

        while (lp >= 0) {
            sum -= arr[lp];
            sum += arr[rp];
            maxSum = Math.max(maxSum, sum);
            lp--;
            rp--;
        }
        System.out.println(maxSum);
    }

    private static void minSwapsOptimised(int[] arr, int b) {
        int windowSize = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] <= b) {
                windowSize++;
            }
        }

        if (windowSize == 0) {
            System.out.println("No good elements");
            return;
        }

        int good = 0;
        // finding good elements in first window
        for (int i = 0; i < windowSize; i++) {
            if (arr[i] <= b) {
                good++;
            }
        }

        int maxGood = good;
        int i = 0;
        int j = windowSize;

        while (j < arr.length) {
            if (arr[i] <= b) {
                good++;
            }

            if (arr[i] >= b) {
                good--;
            }

            maxGood = Math.max(maxGood, good);
            i++;
            j++;
        }
        int minSwaps = windowSize - maxGood;
        System.out.println(minSwaps);
    }

    private static void minSwaps(int[] arr, int b) {
        int windowSize = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] <= b) {
                windowSize++;
            }
        }

        int maxGood = 0;
        int i = 0;
        int j = windowSize;
        while (i < arr.length && j == arr.length) {
            int goodElem = getGoodElements(arr, i, j, b);
            if (maxGood < goodElem) {
                maxGood = goodElem;
            }
            i++;
            j++;
        }
        int minSwap = windowSize - maxGood;
        System.out.println(minSwap);
    }

    private static int getGoodElements(int[] arr, int i, int j, int b) {
        int elem = 0;
        for (int k = i; k < j; k++) {
            if (arr[k] <= b) {
                elem++;
            }
        }
        return elem;
    }

    private static void countIncreasingTripletsOptimised(int[] arr) {
        /// For every middle element: (# smaller on left) × (# greater on right).
        int count = 0;
        for (int j = 0; j < arr.length; j++) {
            int smaller = 0;
            int greater = 0;

            for (int i = 0; i < j; i++) {
                if (arr[i] < arr[j]) {
                    smaller++;
                }
            }

            for (int k = j + 1; k < arr.length; k++) {
                if (arr[k] > arr[j]) {
                    greater++;
                }
            }

            count = count + (smaller * greater);
        }
        System.out.println(count);
    }

    private static void countIncreasingTriplets(int[] arr) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                for (int k = j + 1; k < arr.length; k++) {
                    if (arr[i] < arr[j] && arr[j] < arr[k]) {
                        count++;
                        //System.out.println(arr[i] + " " + arr[j] + " " + arr[k]);
                    }
                }
            }
        }
        System.out.println(count);
    }

    private static void countAGOptimised(String str) {
        int countA = 0;
        int countAG = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'A') {
                countA++;
            }
            if (str.charAt(i) == 'G') {
                countAG = countA + countAG;
            }
        }
        System.out.println(countAG);
    }

    private static void countAG(String str) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            for (int j = i; j < str.length(); j++) {
                if (str.charAt(i) == 'A' && str.charAt(j) == 'G') {
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    private static void specialIndexOptimised(int[] arr) {
        int[] evenPrefix = new int[arr.length];
        int[] oddPrefix = new int[arr.length];

        evenPrefix[0] = arr[0];

        for (int i = 1; i < arr.length; i++) {
            evenPrefix[i] = evenPrefix[i - 1];
            oddPrefix[i] = oddPrefix[i - 1];
            if (i % 2 != 0) {
                oddPrefix[i] = arr[i] + oddPrefix[i];
            } else {
                evenPrefix[i] = arr[i] + evenPrefix[i];
            }
        }

        for (int i = 0; i < arr.length; i++) {
            int evenLeft = (i == 0) ? 0 : evenPrefix[i - 1];
            int oddLeft = (i == 0) ? 0 : oddPrefix[i - 1];

            int evenRight = evenPrefix[arr.length - 1] - evenPrefix[i];
            int oddRight = oddPrefix[arr.length - 1] - oddPrefix[i];

            int newEvenSum = evenLeft + oddRight;
            int newOddSum = oddLeft + evenRight;

            if (newEvenSum == newOddSum) {
                System.out.println("Special Index: " + i + ", ");
            }
        }
    }

    private static void specialIndex1(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < arr.length; j++) {
                if (j != i) {
                    list.add(arr[j]);
                }
            }

            int evenSum = getCurrentEvenIndexSum(list);
            int oddSum = getCurrentOddIndexSum(list);

            if (evenSum == oddSum) {
                System.out.println("Special index: " + i);
            }
        }
    }

    private static int getCurrentOddIndexSum(List<Integer> list) {
        int sum = 0;
        for (int i = 0; i < list.size(); i++) {
            if (i % 2 != 0) {
                sum += list.get(i);
            }
        }
        return sum;
    }

    private static int getCurrentEvenIndexSum(List<Integer> list) {
        int sum = 0;
        for (int i = 0; i < list.size(); i++) {
            if (i % 2 == 0) {
                sum += list.get(i);
            }
        }
        return sum;
    }

    private static void evenIndexSum(int[] arr, int left, int right) {
        arr[0] = 0;
        for (int i = 1; i < arr.length; i++) {
            if (i % 2 == 0) {
                arr[i] = arr[i - 1] + arr[i];
            } else {
                arr[i] = arr[i - 1];
            }
        }
        int ans = (left == 0) ? arr[right] : arr[right] - arr[left - 1];
        System.out.println(ans);
    }

    private static void oddIndexSum(int[] arr, int left, int right) {
        arr[0] = 0;
        for (int i = 1; i < arr.length; i++) {
            if (i % 2 != 0) {
                arr[i] = arr[i - 1] + arr[i];
            } else {
                arr[i] = arr[i - 1];
            }
        }
        int ans = (left == 0) ? arr[right] : arr[right] - arr[left - 1];
        System.out.println(ans);
    }

    private static void oddInRange(int[] arr, int left, int right) {
        if (arr[0] % 2 != 0) {
            arr[0] = 1;
        } else {
            arr[0] = 0;
        }

        for (int i = 1; i < arr.length; i++) {
            arr[i] = (arr[i] % 2 != 0) ? arr[i - 1] + 1 : arr[i - 1];
        }
        int ans = (left == 0) ? arr[right] : arr[right] - arr[left - 1];
        System.out.println(ans);
    }

    private static void evenInRange(int[] arr, int left, int right) {
        if (arr[0] % 2 == 0) {
            arr[0] = 1;
        } else {
            arr[0] = 0;
        }

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] % 2 == 0) {
                arr[i] = arr[i - 1] + 1;
            } else {
                arr[i] = arr[i - 1];
            }
        }
        int ans = (left == 0) ? arr[right] : arr[right] - arr[left - 1];
        System.out.println(ans);
    }

    private static void rangeSum(int[] arr, int left, int right) {
        for (int i = 1; i < arr.length; i++) {
            arr[i] = arr[i - 1] + arr[i];
        }
        int ans = (left == 0) ? arr[right] : arr[right] - arr[left - 1];
        System.out.println(ans);
    }


    private static void generateAllSubArrays1(int[] arr) {
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                List<Integer> list = new ArrayList<>();
                for (int k = i; k <= j; k++) {
                    list.add(arr[k]);
                }
                ans.add(list);
            }
        }

        System.out.println(ans);
    }
}
