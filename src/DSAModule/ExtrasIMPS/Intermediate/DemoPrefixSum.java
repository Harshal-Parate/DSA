package DSAModule.ExtrasIMPS.Intermediate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class DemoPrefixSum {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6};
        System.out.println(Arrays.toString(arr));

        int[] prefixSum = new int[arr.length];
        prefixSum[0] = arr[0];

//        for (int i = 1; i < arr.length; i++) {
//            if (i % 2 == 0) {
//                prefixSum[i] = prefixSum[i - 1];
//            } else {
//                prefixSum[i] = prefixSum[i - 1] + arr[i];
//            }
//        }
//
//        System.out.println(Arrays.toString(prefixSum));

        for (int i = 1; i < arr.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + arr[i];
        }

        System.out.println(Arrays.toString(prefixSum));

        int left = 1;
        int right = 4;
        int sum = prefixSum[right] - prefixSum[left - 1];
        System.out.println(sum);


        // Building sum array

        int[] arr1 = {2, 4, 1, 7, 3};
        int[] prefixSum1 = new int[arr1.length];

        prefixSum1[0] = arr1[0];

        for (int i = 1; i < arr1.length; i++) {
            prefixSum1[i] = prefixSum1[i - 1] + arr1[i];
        }

        System.out.println(Arrays.toString(prefixSum1));

        int left1 = 1;
        int right1 = 3;

        int sum1 = prefixSum1[right1] - prefixSum1[left1 - 1];
        System.out.println("sum: " + sum1);

        // print running sum
        int[] arr2 = {1, 2, 3, 4};
        int sum2 = 0;
        for (int i = 0; i < arr2.length; i++) {
            sum2 = sum2 + arr2[i];
            System.out.println(sum2);
        }

        // total array sum using prefix sum
        int[] arr3 = {5, 3, 2, 1};
        int[] prefixSum3 = new int[arr3.length];
        prefixSum3[0] = arr3[0];
        for (int i = 1; i < arr3.length; i++) {
            prefixSum3[i] = prefixSum3[i - 1] + arr3[i];
        }
        System.out.println(prefixSum3[arr3.length - 1]);
        System.out.println("-------");

        // multiple queries
        int[][] q = {
                {0, 2},
                {1, 3},
                {2, 4}
        };
        int[] arr4 = {2, 4, 1, 7, 3};
        int[] prefixSum4 = new int[arr1.length];

        prefixSum4[0] = arr4[0];

        for (int i = 1; i < arr4.length; i++) {
            prefixSum4[i] = prefixSum4[i - 1] + arr4[i];
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < q.length; i++) {
            int l = q[i][0];
            int r = q[i][1];
            int ans;
            if (l == 0) {
                ans = prefixSum4[r];
            } else {
                ans = (prefixSum4[r] - prefixSum4[l - 1]);
            }
            if (max < ans) {
                max = ans;
            }
            System.out.println(ans);
        }
        System.out.println(max);

        System.out.println("====================");

        countEven();
        countOdd();
        countPosNumberInRange();
        sumOfOddIndexElement();
        leadersInArray();
        System.out.println("====================");
        printAllSubarray();
        System.out.println("====================");
        specialIndex();


    }

    static void countEven() {
        int[] arr = {2, 5, 8, 7, 4};
        int[] prefixEven = new int[arr.length];

        prefixEven[0] = (arr[0] % 2 == 0) ? 1 : 0; // if even -> 1 else 0

        for (int i = 1; i < arr.length; i++) {
            prefixEven[i] = prefixEven[i - 1];
            if (arr[i] % 2 == 0) {
                prefixEven[i]++;
            }
        }

        System.out.println(Arrays.toString(prefixEven));

        int l = 1;
        int r = 4;
        int count = prefixEven[r] - prefixEven[l - 1];
        System.out.println(count);
    }

    static void countOdd() {
        int[] arr = {2, 5, 8, 7, 4};
        int[] prefixOdd = new int[arr.length];

        prefixOdd[0] = (arr[0] % 2 != 0) ? 1 : 0;

        for (int i = 1; i < arr.length; i++) {
            prefixOdd[i] = prefixOdd[i - 1];
            if (arr[i] % 2 != 0) {
                prefixOdd[i]++;
            }
        }
        System.out.println(Arrays.toString(prefixOdd));

        int l = 0;
        int r = 4;
        int count = (l == 0) ? prefixOdd[r] : prefixOdd[r] - prefixOdd[l - 1];
        System.out.println(count);

    }

    static void countPosNumberInRange() {
        int[] arr = {1, -2, -2, 3, 4, -234, 235, -6};
        int[] prefixPos = new int[arr.length];
        prefixPos[0] = (arr[0] > 0) ? 1 : 0;

        for (int i = 1; i < arr.length; i++) {
            prefixPos[i] = prefixPos[i - 1];
            if (arr[i] > 0) {
                prefixPos[i]++;
            }
        }

        int l = 0;
        int r = 4;
        int count = (l == 0) ? prefixPos[r] : prefixPos[r] - prefixPos[l - 1];
        System.out.println(count);


    }

    static void sumOfOddIndexElement() {
        int[] arr = {2, 5, 8, 7, 4};
        int[] prefixOddSum = new int[arr.length];

        prefixOddSum[0] = 0;

        for (int i = 1; i < arr.length; i++) {
            if (i % 2 != 0) {
                prefixOddSum[i] = prefixOddSum[i - 1] + arr[i];
            } else {
                prefixOddSum[i] = prefixOddSum[i - 1];
            }
        }

        int l = 1;
        int r = 3;
        int count = (l == 0) ? prefixOddSum[r] : prefixOddSum[r] - prefixOddSum[l - 1];
        System.out.println(count);

    }

    static void specialIndex() {
        int[] arr = {1,2,3,4,5,6,13,1,2,3,4,5,6};

        int n = arr.length;

        int[] prefixEvenSum = new int[n];
        int[] prefixOddSum = new int[n];

        prefixEvenSum[0] = arr[0];
        prefixOddSum[0] = 0;

        for (int i = 1; i < n; i++) {

            prefixEvenSum[i] = prefixEvenSum[i - 1];
            prefixOddSum[i] = prefixOddSum[i - 1];

            if (i % 2 == 0) {
                prefixEvenSum[i] += arr[i];
            } else {
                prefixOddSum[i] += arr[i];
            }
        }

        int totalEven = prefixEvenSum[n - 1];
        int totalOdd = prefixOddSum[n - 1];

        int count = 0;

        for (int i = 0; i < n; i++) {

            int leftEven = (i == 0) ? 0 : prefixEvenSum[i - 1];
            int leftOdd = (i == 0) ? 0 : prefixOddSum[i - 1];

            int rightEven = totalEven - prefixEvenSum[i];
            int rightOdd = totalOdd - prefixOddSum[i];

            int newEven = leftEven + rightOdd;
            int newOdd = leftOdd + rightEven;

            if (newEven == newOdd) {
                count++;
                System.out.println("Special Index = " + i);
            }
        }

        System.out.println("Count = " + count);
    }

    static void leadersInArray() {
        int[] arr = {9, 5, 8, 4};
        int max = Integer.MIN_VALUE;
        int count = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            if (max < arr[i]) {
                max = arr[i];
                count++;
            }
        }
        System.out.println(count);
    }

    static void printAllSubarray() {
        int[] arr = {9, 5, 8, 4};

        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                for (int k = i; k <= j; k++) {
                    System.out.print(arr[k]);
                }
                System.out.println();
            }
        }

        ArrayList<Integer> objects = new ArrayList<>();
        objects.sort(Comparator.naturalOrder());


    }
}
