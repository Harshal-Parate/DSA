package DSAModule.ExtrasIMPS.Intermediate.Arrays;

public class RowWiseAndColWiseSort {

    public static void main(String[] args) {
        int[][] arr = {
                {-5, -2, 1, 13},
                {-4, 0, 3, 14},
                {-3, 2, 6, 18}
        };

        int[][] arr1 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        int k = 2;
        System.out.println(searchForElementK(arr, k));

        System.out.println(searchForElementKOptimised(arr1, k));
    }

    private static boolean searchForElementK(int[][] arr, int k) {
        boolean isExists = false;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] == k) {
                    isExists = true;
                    System.out.println(i + ", " + j);
                    break;
                }
            }
        }
        return isExists;
    }

    // if the matrix is sorted
    private static boolean searchForElementKOptimised(int[][] arr, int k) {
        int row = 0;
        int col = arr[row].length - 1;
        while (row < arr.length && col >=0) {

            int current = arr[row][col];
            if (current > k) {
                col--;
            }
            if (current < k) {
                row++;
            }
            if (current == k) {
                return true;
            }

        }
        return false;
    }

}
