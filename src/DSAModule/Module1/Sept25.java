package DSAModule.Module1;

import java.util.ArrayList;
import java.util.List;

public class Sept25 {
    public static void main(String[] args) {
        List<List<Integer>> list = mergeSortedOverlappingIntervals();
        System.out.println(list);
        printBoundary();

    }

    private static void printBoundary() {
        int[][] arr = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };

        int top = 0;
        int bottom = arr.length - 1;
        int left = 0;
        int right = arr[0].length - 1;

        //while (top <= bottom && left <= right) {

            for (int i = left; i <= right; i++) {
                System.out.print(arr[top][i] + ", ");
            }
            top++;

            for (int i = top; i <= bottom; i++) {
                System.out.print(arr[i][right] + ", ");
            }
            right--;

            for (int i = right; i >= left; i--) {
                System.out.print(arr[bottom][i] + ", ");
            }
            bottom--;

            for (int i = bottom; i >= top; i--) {
                System.out.print(arr[i][left] + ", ");
            }
            left++;
        //}

    }

    private static List<List<Integer>> mergeSortedOverlappingIntervals() {
        int[][] arr = {
                {1, 3},
                {2, 6},
                {5, 10},
                {15, 18}
        };

        List<List<Integer>> ans = new ArrayList<>();
        int i = 1;
        int n = arr.length;
        int listIndex = 0;

        List<Integer> list1 = new ArrayList<>();
        list1.add(0, arr[0][0]);
        list1.add(1, arr[0][1]);
        ans.add(list1);


        while (i < n) {
            int previousEnd = ans.get(listIndex).get(1);
            int currentStart = arr[i][0];
            int currentEnd = arr[i][1];

            if (currentStart <= previousEnd) {
                ans.get(listIndex).set(1, Math.max(currentEnd, previousEnd));
                i++;
            } else {
                List<Integer> list2 = new ArrayList<>();
                list2.add(0, currentStart);
                list2.add(1, currentEnd);
                ans.add(list2);
                i++;
                listIndex++;
            }
        }
        return ans;
    }
}
