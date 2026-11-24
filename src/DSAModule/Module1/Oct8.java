package DSAModule.Module1;

import java.util.ArrayList;

public class Oct8 {

    public static void main(String[] args) {
        System.out.println(power(2, 3));
        int[] A = {8, 9, 5, 6, 5, 5};
        System.out.println(findTheIndices(A, 5));

    }

    private static long power(int A, int B) {
        if (B == 0) {
            return 1;
        }
        return A * power(A, B - 1);
    }

    // Fast Power
    private long fastPower(int A, int B) {
        if (B == 0) {
            return 1;
        }

        if (B == 1) {
            return A;
        }

        long half = fastPower(A, B / 2);
        long result = half * half;

        if (B % 2 != 0) {
            return result * A;
        }
        return result;
    }

    private static ArrayList<Integer> findTheIndices(int[] A, int B) {
        ArrayList<Integer> ans = new ArrayList<>();
        return helperIndices(A, B, 0, ans);
    }

    private static ArrayList<Integer> helperIndices(int[] a, int b, int i, ArrayList<Integer> ans) {
        if (a.length == i) {
            return ans;
        }
        if (a[i] == b) {
            ans.add(i);
        }
        return helperIndices(a, b, i + 1, ans);
    }

    ArrayList<ArrayList<Integer>> res = new ArrayList<>();

    public ArrayList<ArrayList<Integer>> towerOfHanoi(int A) {
        res.clear();
        helper(A, 1, 2, 3);
        return res;
    }

    private void helper(int A, int source, int aux, int dest) {
        if(A == 1) {
            ArrayList<Integer> move = new ArrayList<Integer>();
            move.add(A);
            move.add(source);
            move.add(dest);

            res.add(move);
            return;
        }
        // 1st
        helper(A-1, source, dest, aux);

        ArrayList<Integer> move = new ArrayList<Integer>();
        move.add(A);
        move.add(source);
        move.add(dest);

        res.add(move);

        //2nd
        helper(A-1, aux, source, dest);
    }
}


