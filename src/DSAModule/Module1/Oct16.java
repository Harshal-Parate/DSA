package DSAModule.Module1;

public class Oct16 {

    public int[] mergeTwoSortedArray(final int[] A, final int[] B) {
        int m = A.length;
        int n = B.length;
        int[] res = new int[m + n];
        int i = 0, j = 0, k = 0;

        while (i < m && j < n) {
            if (A[i] <= B[j]) {
                res[k] = A[i];
                i++;
            } else {
                res[k] = B[j];
                j++;
            }
            k++;
        }
        while (i < m) {
            res[k] = A[i];
            k++;
            i++;
        }

        while (j < n) {
            res[k] = B[j];
            k++;
            j++;
        }

        return res;
    }

    public int[] mergeSort(int[] A) {

        int[] arr1 = new int[1];
        int[] arr2 = new int[A.length - 1];

        arr1[0] = A[0];

        for (int i = 1; i < A.length; i++) {
            arr2[i - 1] = A[i];
        }

        countSort(arr1);
        countSort(arr2);

        return mergeArray(arr1, arr2);
    }

    private int[] mergeArray(final int[] A, final int[] B) {
        int m = A.length;
        int n = B.length;
        int[] res = new int[m + n];
        int i = 0, j = 0, k = 0;

        while (i < m && j < n) {
            if (A[i] <= B[j]) {
                res[k] = A[i];
                i++;
            } else {
                res[k] = B[j];
                j++;
            }
            k++;
        }
        while (i < m) {
            res[k] = A[i];
            k++;
            i++;
        }

        while (j < n) {
            res[k] = B[j];
            k++;
            j++;
        }
        return res;
    }


    // count sort
    public static void countSort(int[] A) {

        int max = A[0];

        for (int num : A) {
            max = Math.max(max, num);
        }

        int[] freq = new int[max + 1];

        // Count frequency
        for (int num : A) {
            freq[num]++;
        }

        // Put values back into array
        int index = 0;

        for (int num = 0; num <= max; num++) {
            while (freq[num] > 0) {
                A[index++] = num;
                freq[num]--;
            }
        }
    }

    // real merge sort - recursion
    public static int[] mergeSortReal(int[] A) {
        solve(A, 0, A.length - 1);
        return A;
    }

    private static void solve(int[] A, int left, int right) {

        // Base case
        if (left >= right) {
            return;
        }

        int mid = left + (right - left) / 2;

        // Sort left half
        solve(A, left, mid);

        // Sort right half
        solve(A, mid + 1, right);

        // Merge both sorted halves
        merge(A, left, mid, right);
    }

    private static void merge(int[] A, int left, int mid, int right) {

        int[] temp = new int[right - left + 1];

        int i = left;
        int j = mid + 1;
        int k = 0;

        while (i <= mid && j <= right) {

            if (A[i] <= A[j]) {
                temp[k++] = A[i++];
            } else {
                temp[k++] = A[j++];
            }
        }

        while (i <= mid) {
            temp[k++] = A[i++];
        }

        while (j <= right) {
            temp[k++] = A[j++];
        }

        // Copy sorted elements back
        for (int x = 0; x < temp.length; x++) {
            A[left + x] = temp[x];
        }
    }
}
