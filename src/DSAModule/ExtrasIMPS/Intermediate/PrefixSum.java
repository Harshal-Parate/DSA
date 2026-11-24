package DSAModule.ExtrasIMPS.Intermediate;

public class PrefixSum {
    public static void main(String[] args) {
        int[] arr = {2, 4, 1, 6, 8, 10};
        int[][] queries = {{1, 3}, {0, 4}, {2, 2}};
        rangeSumQuery(queries, arr);
        System.out.println("-----------------------------------------");

        countEvenNumberInRange(arr, queries);
        System.out.println("-----------------------------------------");

        countOddNumberInRange(arr, queries);
        System.out.println("-----------------------------------------");

        sumOfOddIndexElements(arr, queries);
        System.out.println("-----------------------------------------");

        sumOfEvenIndexElements(arr, queries);
        System.out.println("-----------------------------------------");

        sumOfMultipleOf5(arr, queries);
        System.out.println("-----------------------------------------");

        sumOfMultipleOf7(arr, queries);
        System.out.println("-----------------------------------------");

        specialIndex(arr);
        System.out.println("-----------------------------------------");
    }

    private static void specialIndex(int[] arr) {

    }

    private static void sumOfMultipleOf7(int[] arr, int[][] queries) {
        int[] prefixSum = new int[arr.length];
        prefixSum[0] = (arr[0] % 7 == 0) ? arr[0] : 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] % 7 == 0) {
                prefixSum[i] = prefixSum[i - 1] + arr[i];
            } else {
                prefixSum[i] = prefixSum[i - 1];
            }
        }
        for (int i = 0; i < queries.length; i++) {
            int left = queries[i][0];
            int right = queries[i][1];
            System.out.println("Sum of multiple of 7 from " + left + " to " + right + " is " + getRangeSum(prefixSum, left, right));
        }
    }

    private static void sumOfMultipleOf5(int[] arr, int[][] queries) {
        int[] prefixSum = new int[arr.length];
        prefixSum[0] = (arr[0] % 5 == 0) ? arr[0] : 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] % 5 == 0) {
                prefixSum[i] = prefixSum[i - 1] + arr[i];
            } else {
                prefixSum[i] = prefixSum[i - 1];
            }
        }
        for (int i = 0; i < queries.length; i++) {
            int left = queries[i][0];
            int right = queries[i][1];
            System.out.println("Sum of multiple of 5 from " + left + " to " + right + " is " + getRangeSum(prefixSum, left, right));
        }
    }

    private static void sumOfEvenIndexElements(int[] arr, int[][] queries) {
        int[] evenIndexSum = new int[arr.length];
        evenIndexSum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (i % 2 == 0) {
                evenIndexSum[i] = evenIndexSum[i - 1] + arr[i];
            } else {
                evenIndexSum[i] = evenIndexSum[i - 1];
            }
        }
        for (int i = 0; i < queries.length; i++) {
            int left = queries[i][0];
            int right = queries[i][1];
            System.out.println("Sum of Even Index numbers from " + left + " to " + right + " is " + getRangeSum(evenIndexSum, left, right));
        }
    }

    private static void sumOfOddIndexElements(int[] arr, int[][] queries) {
        int[] oddIndexSum = new int[arr.length];
        oddIndexSum[0] = 0;
        for (int i = 1; i < arr.length; i++) {
            if (i % 2 != 0) {
                oddIndexSum[i] = oddIndexSum[i - 1] + arr[i];
            } else {
                oddIndexSum[i] = oddIndexSum[i - 1];
            }
        }
        for (int i = 0; i < queries.length; i++) {
            int left = queries[i][0];
            int right = queries[i][1];
            System.out.println("Sum of Odd Index numbers from " + left + " to " + right + " is " + getRangeSum(oddIndexSum, left, right));
        }
    }

    private static void countOddNumberInRange(int[] arr, int[][] queries) {
        int[] oddCount = new int[arr.length];
        oddCount[0] = (arr[0] % 2 != 0) ? 1 : 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] % 2 != 0) {
                oddCount[i] = oddCount[i - 1] + 1;
            } else {
                oddCount[i] = oddCount[i - 1];
            }
        }
        for (int i = 0; i < queries.length; i++) {
            int left = queries[i][0];
            int right = queries[i][1];
            System.out.println("Count of Odd numbers from " + left + " to " + right + " is " + getRangeSum(oddCount, left, right));
        }
    }

    private static void countEvenNumberInRange(int[] arr, int[][] queries) {
        int[] prefixEvenCount = new int[arr.length];
        prefixEvenCount[0] = (arr[0] % 2 == 0) ? 1 : 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] % 2 == 0) {
                prefixEvenCount[i] = prefixEvenCount[i - 1] + 1;
            } else {
                prefixEvenCount[i] = prefixEvenCount[i - 1];
            }
        }

        for (int i = 0; i < queries.length; i++) {
            int left = queries[i][0];
            int right = queries[i][1];
            System.out.println("Count of Even numbers from " + left + " to " + right + " is " + getRangeSum(prefixEvenCount, left, right));
        }
    }

    private static void rangeSumQuery(int[][] queries, int[] arr) {
        int[] prefixSum = new int[arr.length];
        prefixSum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + arr[i];
        }

        for (int i = 0; i < queries.length; i++) {
            int left = queries[i][0];
            int right = queries[i][1];
            System.out.println("Sum from " + left + " to " + right + " is " + getRangeSum(prefixSum, left, right));
        }
    }

    private static int getRangeSum(int[] arr, int left, int right) {
        return (left == 0) ? arr[right] : arr[right] - arr[left - 1];
    }
}
