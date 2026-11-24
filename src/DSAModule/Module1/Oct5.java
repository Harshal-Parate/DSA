package DSAModule.Module1;

import java.util.Arrays;

public class Oct5 {

    public static void main(String[] args) {
        System.out.println(fact(5));

        printNumbersInDecreasing(8);

        System.out.println();

        printNumbersInIncreasing(8);

        System.out.println();

        int[] arr = {1, 2, 3, 4};
        printArray1(arr);

        System.out.println();

        printArray2(arr);

        System.out.println();

        System.out.println(fib(5));

        System.out.println();

    }

    private static int fact(int n) {
        if (n == 0) {
            return 1;
        }
        return n * fact(n - 1);
    }

    private static void printNumbersInDecreasing(int n) {
        if (n == 1) {
            System.out.print(1);
            return;
        }
        System.out.print(n);
        printNumbersInDecreasing(n - 1);
    }

    private static void printNumbersInIncreasing(int n) {
        if (n == 1) {
            System.out.print(1);
            return;
        }
        printNumbersInIncreasing(n - 1);
        System.out.print(n);
    }

    private static void printArray1(int[] A) {
        if (A.length == 0) {
            return;
        }
        printArray1(Arrays.copyOf(A, A.length - 1));
        System.out.print(A[A.length - 1]);

    }

    private static void printArray2(int[] A) {
        printHelper(A, 0);
    }

    private static void printHelper(int[] A, int i) {
        if (i == A.length) {
            return;
        }
        printHelper(A, i + 1);
        System.out.print(A[i]);
    }

    private static int fib(int n) {
        if (n <= 1) {
            return n;
        }
        return fib(n - 1) + fib(n - 2);
    }
}
