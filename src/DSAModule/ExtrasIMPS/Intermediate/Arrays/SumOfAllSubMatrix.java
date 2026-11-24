package DSAModule.ExtrasIMPS.Intermediate.Arrays;

import java.util.ArrayList;
import java.util.List;

public class SumOfAllSubMatrix {

    // m(m+1) n(n+1)/4 ->  total number of submatrixes

    public static void main(String[] args) {
        int[][] arr = {
                {-5, -2, 1, 13},
                {-4, 0, 3, 14},
                {-3, 2, 6, 18}
        };

        System.out.println(sumOfAllSubMatrix(arr));

    }

    /// Optimised
    ///  Contribution
    ///  [(i+1)*(j+1) ] * [(n-i)*(m-j)]

    public class Solution {
        public long solve(int[][] A) {
            int m = A.length;
            int n = A[0].length;
            long ans = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    long contribution = (long)
                            (i + 1) * (j + 1)
                            * (m - i) * (n - j);

                    ans += contribution * A[i][j];
                }
            }
            return ans;
        }
    }

    private static int sumOfAllSubMatrix(int[][] arr) {
        List<int[][]> allSubMatrix = getAllSubMatrix(arr);
        int total = 0;
        for (int i = 0; i < allSubMatrix.size(); i++) {
            int currentSubSum = getSumOfMatrix(allSubMatrix.get(i));
            total += currentSubSum;
        }
        return total;
    }

    private static int getSumOfMatrix(int[][] matrix) {
        int sum = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                sum += matrix[i][j];
            }
        }
        return sum;
    }

    private static List<int[][]> getAllSubMatrix(int[][] arr) {
        List<int[][]> result = new ArrayList<>();

        int rows = arr.length;
        int cols = arr[0].length;

        for (int top = 0; top < rows; top++) {
            for (int left = 0; left < cols; left++) {
                for (int bottom = top; bottom < rows; bottom++) {
                    for (int right = left; right < cols; right++) {
                        int[][] subMatrix = new int[bottom - top + 1][right - left + 1];
                        for (int i = top; i <= bottom; i++) {
                            for (int j = left; j <= right; j++) {
                                subMatrix[i - top][j - left] = arr[i][j];
                            }
                        }
                        result.add(subMatrix);
                    }
                }
            }
        }

        return result;
    }


}
