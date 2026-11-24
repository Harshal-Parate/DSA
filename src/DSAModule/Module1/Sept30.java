package DSAModule.Module1;

public class Sept30 {

    // brute force
    public int solve1(int[] A) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A.length; j++) {
                if (i != j) {
                    int temp = A[i] & A[j];
                    max = Math.max(max, temp);
                }
            }
        }
        return max;
    }

    // optimised
    public int solve2(int[] A) {
        int ans = 0;
        // Try every bit from highest to lowest
        for (int bit = 31; bit >= 0; bit--) {
            // Try adding this bit to our current answer
            int candidate = ans | (1 << bit);
            int count = 0;
            // Count numbers containing all candidate bits
            for (int num : A) {
                if ((num & candidate) == candidate) {
                    count++;
                }
            }

            // Need at least 2 different numbers
            if (count >= 2) {
                ans = candidate;
            }
        }
        return ans;
    }

    // Q1. Number of 1 Bits
    public int numSetBits(int A) {
        int[] bit = new int[32];
        int count = 0;
        for (int i = 0; i < bit.length; i++) {
            if ((A & (1 << i)) > 0) {
                count++;
            }
        }
        return count;
    }

    //Q2. Single Number II
    // chat gpt solution
    public int singleNumber1(final int[] A) {
        int ones = 0;
        int twos = 0;

        for (int num : A) {
            twos |= ones & num;
            ones ^= num;

            int common = ones & twos;

            ones &= ~common;
            twos &= ~common;
        }

        return ones;
    }

    //Q2. Single Number II
    // Sir solution
    public int singleNumber2(final int[] A) {
        int ones = 0;
        int twos = 0;

        for (int num : A) {
            twos |= ones & num;
            ones ^= num;

            int common = ones & twos;

            ones &= ~common;
            twos &= ~common;
        }

        return ones;
    }


    //Q3. Finding Good Days
    public int solve(int A) {
        int[] goodDays = new int[32];
        int days = 0;
        for (int i = 0; i < goodDays.length; i++) {
            if ((A & (1 << i)) > 0) {
                days++;
            }
        }
        return days;
    }

    private static int ORSumCountSubArrays(int[] A) {
        int start = 0;
        int end = 0;
        int totalSubs = 0;

        while (end < A.length) {
            if (A[start] == 0) {
                if (A[end] == 0) {
                    end++;
                } else {
                    int temp = end - start;
                    int currentSub = temp * (temp + 1) / 2;
                    totalSubs += currentSub;
                    start = end;
                }
            } else {
                start++;
                end = start;
            }
        }
        // Handle trailing zeros
        int temp = end - start;
        totalSubs += temp * (temp + 1) / 2;
        return totalSubs;
    }

    public static void main(String[] args) {
        int[] arr = {0, 0, 1, 0, 0, 1, 0, 0, 0};
        System.out.println(ORSumCountSubArrays(arr));
    }
}
