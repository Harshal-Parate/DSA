package DSAModule.Module1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Oct19 {

    public static void main(String[] args) {
        int[] arr = {2, 1, 3, 4, 3, 5};
        quickSort(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println(partition(arr));

    }

    /*

    private static int[] solve(int[] A) {
        quickSort(A, 0, A.length - 1);
        return A;
    }

    private static void quickSort(int[] A, int l, int r) {
        if (l < r) {
            int pIndex = partition(A, l, r);
            quickSort(A, l, pIndex - 1);
            quickSort(A, pIndex + 1, r);
        }
    }

    private static int partition(int[] A, int l, int r) {
        int pivot = A[l];
        int i = l + 1;
        int j = r;

        // i <= j -> updating i and j at multiple places it can cross anywhere so this condition will valid on every loop
        while (i <= j) {
            while (i <= j && A[i] <= pivot) i++;
            while (i <= j && A[j] >= pivot) j--;

            if (i <= j) {
                swap(A, i, j);
                i++;
                j--;
            }
        }
        swap(A, l, j);
        return j;
    }

    private static void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

     */

    private static void quickSort(int[] nums) {
        qsa(nums, 0, nums.length - 1);
    }

    private static void qsa(int[] A, int left, int right) {
        if (left < right) {
            int pIndex = getPivotIndex(A, left, right);
            qsa(A, left, pIndex - 1);
            qsa(A, pIndex + 1, right);
        }
    }

    private static int getPivotIndex(int[] A, int left, int right) {
        int pivot = A[left];
        int i = left + 1;
        int j = right;

        while (i <= j) {
            while (i <= j && A[i] <= pivot) {
                i++;
            }
            while (i <= j && A[j] >= pivot) {
                j--;
            }

            if (i <= j) {
                swap(A, i, j);
                i++;
                j--;
            }
        }
        swap(A, left, j);
        return j;
    }

    private static void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }


    public static int partition(int[] arr) {
        int pivot = arr[arr.length - 1];
        int i = arr.length - 2;
        int j = 0;

        while (i >= j) {
            while (i >= j && arr[j] <= pivot) j++;
            while (i >= j && arr[i] >= pivot) i--;

            if (i >= j) {
                swap(arr, j, i);
                i--;
                j++;
            }
        }
        swap(arr, arr.length - 1, j);
        return i;
    }

    int countFactors(int n) {
        int count = 0;
        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                if (i == n / i) {
                    count++;
                } else {
                    count += 2;
                }
            }
        }
        return count;
    }

    public ArrayList<Integer> solve(ArrayList<Integer> A) {
        A.sort(Comparator.comparing(this::countFactors).thenComparing(Integer::intValue));
        return A;
    }

    public String largestNumber(ArrayList<Integer> A) {
        ArrayList<String> strings = new ArrayList<>();

        for (int i = 0; i < A.size(); i++) {
            strings.add(i, A.get(i).toString());
        }

        strings.sort((a, b) -> (b + a).compareTo(a + b));

        if (strings.get(0).equals("0")) return "0";

        String res = "";

        for (String str : strings) {
            res += str;
        }
        return res;
    }

}
