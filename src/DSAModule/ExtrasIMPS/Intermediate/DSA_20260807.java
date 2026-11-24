package DSAModule.ExtrasIMPS.Intermediate;

public class DSA_20260807 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
//        System.out.println(sumOfAllSubArrays1(arr));
//        System.out.println(sumOfAllSubArraysContribution(arr));
//        System.out.println(maxSumOfSubarray(arr));
//        System.out.println(maxSumOfSubarrayKadnes(arr));
//        System.out.println(Arrays.toString(continousSumQuery()));
        int sum = sum(3);
        System.out.println(sum);

    }

    public int[] solve(int A, int[][] B) {
        int[] ans = new int[A];
        for(int j=0; j<B.length; j++) {
            int left = B[j][0];
            int right = B[j][1];
            int amount = B[j][2];

            ans[left-1] = ans[left-1] + amount;
            if(right<A) {
                ans[right] = ans[right] - amount;
            }
        }
        for(int i=1; i<A; i++) {
            ans[i] += ans[i-1];
        }
        return ans;


    }

    private static int[] continousSumQuery(int A, int[][] B) {
        int[] ans = new int[A];
        for(int j=0; j<B.length; j++) {
            int left = B[j][0];
            int right = B[j][1];
            int amount = B[j][2];

            for(int k=left-1; k<right; k++) {
                ans[k] = ans[k] + amount;
            }
        }
        return ans;
    }

    private static int maxSumOfSubarrayKadnes(int[] arr) {
        int currentMax = arr[0];
        int maxSum = arr[0];
        for(int i=1; i<arr.length; i++) {
            currentMax = Math.max(arr[i], currentMax+arr[i]);
            maxSum = Math.max(currentMax, maxSum);
        }
        return maxSum;
    }

    private static int maxSumOfSubarray(int[] arr) {
        int[] ps = new int[arr.length];
        ps[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            ps[i] = ps[i - 1] + arr[i];
        }

        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int currentSum = (i == 0) ? ps[j] : ps[j] - ps[i - 1];
                if(maxSum < currentSum) {
                    maxSum = currentSum;
                }
            }
        }
        return maxSum;
    }

    private int trappingRainWater_2pointer(int[] arr) {
        int n = arr.length;
        int left = 0;
        int right = n - 1;

        int leftMax = 0;
        int rightMax = 0;

        int waterTrapped = 0;

        while (left <= right) {
            if (arr[left] <= arr[right]) {
                if (arr[left] >= leftMax) {
                    leftMax = arr[left];
                } else {
                    waterTrapped = waterTrapped + (leftMax - arr[left]);
                }
                left++;
            } else {
                if (rightMax <= arr[right]) {
                    rightMax = arr[right];
                } else {
                    waterTrapped = waterTrapped + (rightMax - arr[right]);
                }
                right--;
            }
        }
        return waterTrapped;
    }

    private int subArrayWithLeastAverage(int[] arr, int k) {
        for (int i = 1; i < arr.length; i++) {
            arr[i] = arr[i - 1] + arr[i];
        }
        int minAvg = Integer.MIN_VALUE;
        int minIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + k - 1; j < arr.length; j++) {
                int sum = (i == 0) ? arr[j] : arr[j] - arr[i - 1];
                int avg = sum / k;
                if (avg < minAvg) {
                    minAvg = avg;
                    minIndex = i;
                }
            }
        }
        return minIndex;
    }

    public int equiIndex(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            arr[i] = arr[i - 1] + arr[i];
        }
        for (int i = 0; i < arr.length; i++) {
            int leftSum = arr[i];
            int rightSum = arr[arr.length - 1] - leftSum;
            if (leftSum == rightSum) {
                return i;
            }

        }
        return -1;
    }

    public int pickFromBothSides(int[] arr, int elem) {
        int maxSum = 0;
        int leftSum = 0;
        int rightSum = 0;

        for (int i = 0; i < elem; i++) {
            leftSum += arr[i];
        }
        maxSum = leftSum;
        for (int i = 1; i <= elem; i++) {
            rightSum = rightSum + arr[arr.length - i];
            leftSum = leftSum - arr[elem - i];
            int currentSum = leftSum + rightSum;
            maxSum = Math.max(maxSum, currentSum);
        }
        return maxSum;
    }

    private int solve(int[] arr, int len, int sum) {
        int[] prefixSum = new int[arr.length];
        prefixSum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + arr[i];
        }

        for (int i = 0; i <= arr.length - len; i++) {
            int j = i + len - 1;
            int currentSum = (i == 0) ? prefixSum[j] : prefixSum[j] - prefixSum[i - 1];
            if (currentSum == sum) {
                return 1;
            }
        }
        return -1;
    }

    // prefix sum
    private long[] rangeSum2(int[] A, int[][] B) {
        int[] prefixSum = new int[A.length];
        prefixSum[0] = A[0];
        for (int i = 1; i < A.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + A[i];
        }
        long[] ans = new long[B.length];
        for (int i = 0; i < B.length; i++) {
            int sum = 0;
            int left = B[i][0];
            int right = B[i][1];
            sum += (left == 0) ? prefixSum[right] : prefixSum[right] - prefixSum[left - 1];
            ans[i] = sum;
        }
        return ans;
    }


    //brute force
    private long[] rangeSum1(int[] A, int[][] B) {
        long[] ans = new long[B.length];
        for (int i = 0; i <= B.length; i++) {
            int left = B[i][0];
            int right = B[i][1];
            int sum = 0;
            for (int j = left; j < right; j++) {
                sum += A[j];
            }
            ans[i] = sum;
        }
        return ans;
    }


    // brute force
    private static long sumOfAllSubArrays1(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            int currentSum = 0;
            for (int j = i; j < arr.length; j++) {
                currentSum = currentSum + arr[j];
                sum = sum + currentSum;
            }
        }
        return sum;
    }

    // optimised -> contribution
    private static long sumOfAllSubArraysContribution(int[] arr) {
        // ith element present in nth subarrays
        // element i will comes in how many nuu,ber of subArrays -> (i+1) * (n-i)
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum = sum + arr[i] * ((i + 1) * (arr.length - i));
        }
        return sum;
    }

    private static int sum(int n) {
        if(n==1){
            return 1;
        }
        return sum(n-1)+n;
    }
}
