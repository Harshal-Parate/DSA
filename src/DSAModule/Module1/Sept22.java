package DSAModule.Module1;

public class Sept22 {

    public static void main(String[] args) {
        findFirstMissingNumber();
        System.out.println(findFirstMissingNumberOptimised());
    }

    // while loop

    private static int findFirstMissingNumberOptimisedWhile() {
        int[] arr = {3, -2, 1, 2, 7, 0};
        int n = arr.length;

        int i = 0;

        while (i < n) {
            int correctIdx = arr[i] - 1;

            if (arr[i] <= 0 || arr[i] > n || arr[i] == i + 1) {
                i++;
            }
            else if (arr[correctIdx] == arr[i]) {
                i++;
            }
            else {
                int temp = arr[i];
                arr[i] = arr[correctIdx];
                arr[correctIdx] = temp;
            }
        }

        for (i = 0; i < n; i++) {
            if (arr[i] != i + 1) {
                return i + 1;
            }
        }

        return n + 1;
    }


//    Time: O(N)
//    Extra Space: O(1)
    private static int findFirstMissingNumberOptimised() {
        int[] arr = {3, -2, 1, 2, 7, 0};
        int n = arr.length;

        for (int i = 0; i < n; i++) {
            if (arr[i] <= 0 || arr[i] > n || arr[i] == i + 1) {
                continue;
            }

            int correctIdx = arr[i] - 1;

            if (arr[correctIdx] == correctIdx + 1) {
                continue;
            }

            int temp = arr[correctIdx];
            arr[correctIdx] = arr[i];
            arr[i] = temp;
        }

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != i + 1) {
                return i + 1;
            }
        }

        return n + 1;

    }

    // time: O(N), Space: O(N)
    private static void findFirstMissingNumber() {
        int[] arr = {3, -2, 1, 2, 7, 0};
        boolean[] visited = new boolean[arr.length];

        for (int i = 0; i < arr.length; i++) {
            int index = arr[i];
            if (index >= 1 && index <= arr.length) {
                visited[index - 1] = true;
            }
        }
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                System.out.println(i + 1);
                break;
            }
        }
    }

    private static int sumOfAllSubMatrix(int[][] arr) {
        int sum = 0;
        int m = arr.length;
        int n = arr[0].length;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                sum += (arr[i][j]) * ((i + 1) * (j + 1) * (m - i) * (n - j));
            }
        }
        return sum;
    }

    private static boolean searchInSortedMatrix(int[][] arr, int k) {
        int row = arr.length;
        int col = arr[0].length;

        // choosing start point as bottom left point
        int i = row - 1;
        int j = 0;

        while (j < col && i >= 0) {
            if (arr[i][j] == k) {
                return true;
            }
            if (arr[i][j] > k) {
                i--;
            } else {
                j++;
            }
        }
        return false;
    }
}
