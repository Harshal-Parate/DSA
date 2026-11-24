package DSAModule.Module2;

public class Nov2 {

    private static Nov2 clazz = new Nov2();

    private int findUniqueInPairs(int[] A) {
        int l = 0;
        int r = A.length - 1;
        int ans = -1;

        while (l <= r) {
            int m = l + (r - l) / 2;

            if (m % 2 != 0) {
                m--;
            }

            if (m + 1 < A.length && A[m] == A[m + 1]) {
                l = m + 2;
            } else {
                ans = m;
                r = m - 1;
            }
        }
        return A[ans];
    }

    private int rotatedSortedArrays(final int[] A, int target) {
        int n = A.length;
        int low = 0;
        int high = n - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (A[mid] == target) {
                return mid;
            }

            if (A[mid] > A[low]) {
                if (A[mid] > target && A[low] <= target) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                if (A[mid] < target && A[high] >= target) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    private int medianOfTwoSortedArrayUsingMergeSortedArrays(int[] A, int[] B) {
        int[] arr = mergeTwoSortedArray(A, B);
        int n = arr.length;

        if (n % 2 == 0) {
            return (arr[n / 2 - 1] + arr[n / 2]) / 2;
        }

        return arr[n / 2];
    }

    private int[] mergeTwoSortedArray(int[] A, int[] B) {
        int i = 0;
        int j = 0;
        int k = 0;

        int[] ans = new int[A.length + B.length];

        while (i < A.length && j < B.length) {
            if (A[i] < B[j]) {
                ans[k] = A[i];
                i++;
            } else {
                ans[k] = B[j];
                j++;
            }
            k++;
        }

        while (i < A.length) {
            ans[k] = A[i];
            i++;
            k++;
        }

        while (j < B.length) {
            ans[k] = B[j];
            j++;
            k++;
        }
        return ans;
    }


    /*
        Our target is:
        - Left half should contain half of the total elements (rounded up).
        - Right half contains the remaining elements.
        - Every element on the left must be smaller than or equal to every element on the right.
     */
    private int medianOfTwoSortedArray(int[] A, int[] B) {
        if (A.length > B.length) {
            return medianOfTwoSortedArray(B, A);
        }

        int m = A.length, n = B.length;
        int halfLength = (m + n + 1) / 2;
        int low = 0, high = m;

        while (low <= high) {
            int i = low + (high - low) / 2;
            int j = halfLength - i;

            int aLeft = Integer.MIN_VALUE, aRight = Integer.MAX_VALUE;
            int bLeft = Integer.MIN_VALUE, bRight = Integer.MAX_VALUE;

            if (i > 0) {
                aLeft = A[i - 1];
            }

            if (i < m) {
                aRight = A[i];
            }

            if (j > 0) {
                bLeft = B[j - 1];
            }

            if (j < n) {
                bRight = B[j];
            }

            if (aLeft > bRight) {
                high = i - 1;
            } else if (bLeft > aRight) {
                low = i + 1;
            } else {
                if ((m + n) % 2 == 0) {
                    return (Math.max(aLeft, bLeft) + Math.min(aRight, bRight)) / 2;
                } else {
                    return Math.max(aLeft, bLeft);
                }
            }
        }
        return -1;
    }

}
