package DSAModule.ExtrasIMPS.Advance;

public class recursion {

    public static void main(String[] args) {
        System.out.println(sumOfNaturalNumbers(4));
        System.out.println(factorial(5));
        printInIncreasingOrder(3);
        System.out.println();
        printInDecreasingOrder(3);
        int[] arr = {5,6,7};
        //printArrayUsingRecursion(arr);

    }

    private static void printInDecreasingOrder(int i) {
        if(i==0) {
            return;
        }
        System.out.print(i+", ");
        printInDecreasingOrder(i-1);
    }

    private static void printInIncreasingOrder(int n) {
        if(n==0) {
            return;
        }
        printInIncreasingOrder(n-1);
        System.out.print(n+", ");
    }

    private static int factorial(int n) {
        if (n == 0) {
            return 1;
        }
        return n * factorial(n - 1);
    }

    private static int sumOfNaturalNumbers(int n) {
        if (n == 1) {
            return 1;
        }
        return sumOfNaturalNumbers(n - 1) + n;
    }
}
