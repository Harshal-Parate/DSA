package DSAModule.Module2;

import java.util.Arrays;

public class Oct28 {
    private static Oct28 clazz = new Oct28();

    private boolean searchNumber(final int[] A, int B) {
        int left = 0;
        int right = A.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (A[mid] == B) {
                return true;
            }

            if (A[mid] < B) {
                left = mid + 1;
            }

            if (A[mid] > B) {
                right = mid - 1;
            }
        }
        return false;
    }

    private int[] searchRange(final int[] A, int B) {
        int first = getMatchIndex(A, B, true);
        int last = getMatchIndex(A, B, false);
        return new int[]{first, last};
    }

    private int getMatchIndex(int[] A, int B, boolean findFirst) {
        int left = 0;
        int right = A.length - 1;
        int ans = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (A[mid] == B) {
                ans = mid;

                if (findFirst) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

            if (A[mid] < B) {
                left = mid + 1;
            }

            if (A[mid] > B) {
                right = mid - 1;
            }
        }
        return ans;
    }

    private int localMinima(int[] A) {
        int left = 0;
        int right = A.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            boolean isLeftLarger = mid == 0 || A[mid - 1] < A[mid];
            boolean isRightLarger = mid == A.length - 1 || A[mid] < A[mid + 1];

            if (isLeftLarger && isRightLarger) {
                return mid;
            }

            if (isLeftLarger) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    private int sqrt(int A) {
        int left = 0;
        int right = A;
        int ans = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (mid * mid == A) {
                return mid;
            }

            if (mid * mid < A) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] A = {5, 7, 7, 8, 8, 10};
        boolean b = clazz.searchNumber(A, 7);
        System.out.println(b);

        int[] ints = clazz.searchRange(A, 8);
        System.out.println(Arrays.toString(ints));

        int sqrt = clazz.sqrt(25);
        System.out.println(sqrt);


    }
}
