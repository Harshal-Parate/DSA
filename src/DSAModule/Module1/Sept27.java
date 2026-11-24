package DSAModule.Module1;

import java.util.Arrays;

public class Sept27 {
    public static void main(String[] args) {

        //lest significant bit
        // for even number is always '0' and for odd its '1'

        // anything AND 0 is 0
        // anything OR 0 is 0
        // anything XOR 0 is 0 -> any number XOR with itself is '0'

        /*

            &  → AND → both must be 1
            |  → OR  → at least one is 1
            ^  → XOR → exactly one is 1
            ~  → NOT → flips the bit



            | A | B | `A & B` | `A  | B` | `A ^ B` |
            | - | - | ------: | -------: | ------: |
            | 0 | 0 |   **0** |    **0** |   **0** |
            | 0 | 1 |   **0** |    **1** |   **1** |
            | 1 | 0 |   **0** |    **1** |   **1** |
            | 1 | 1 |   **1** |    **1** |   **0** |



         */
    }

    // find unique number
    public int singleNumber(final int[] A) {
        int result = 0;
        for (int num : A) {
            result ^= num;
        }
        return result;
    }

    // find 3 unique numbers
    private int solveFrom3(int[] A) {
        int max = Integer.MIN_VALUE;
        for (int num : A) {
            max = Math.max(num, max);
        }

        int[] arr = new int[max+1];
        for (int j : A) {
            arr[j] += 1;
        }

        // finding unique number
        int unique = 0;
        for (int i=0; i<arr.length; i++) {
            if (arr[i] % 3 != 0) {
                unique = i;
            }
        }

        return unique;
    }


    // find 2 unique numbers
    public int[] solve(int[] A) {
        int[] countBits = new int[32];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < 32; j++) {
                if ((A[i] & (1 << j)) > 0) {
                    countBits[j]++;
                }
            }
        }
        int firstOddIdx = -1;
        for (int i = 0; i < countBits.length; i++) {
            if (countBits[i] % 2 != 0) {
                firstOddIdx = i;
                break;
            }
        }

        int group1 = 0;
        int group2 = 0;

        for (int i = 0; i < A.length; i++) {
            if ((A[i] & (1 << firstOddIdx)) > 0) {
                group1 ^= A[i];
            } else {
                group2 ^= A[i];
            }
        }

        int[] ans = new int[2];
        ans[0] = group1;
        ans[1] = group2;
        Arrays.sort(ans);

        return ans;
    }


}
