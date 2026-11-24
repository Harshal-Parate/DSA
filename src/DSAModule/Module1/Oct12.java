package DSAModule.Module1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Oct12 {

    public static void main(String[] args) {
        //int[] arr = {3, 5, 1, 2, 1, 2};
        int[] arr = {6, 3, 3, 6, 7, 8, 7, 3, 7};
        int k = 8;
        System.out.println(countPairsOfTargetSum(arr, k));

        int[] arr1 = {10, 9, 8};
        System.out.println(Arrays.toString(solve(arr, arr1)));

        int[] A = {2, 4, 2, 4};
        int B = 6;
        System.out.println(returnAllTheIndexOfTargetSum(A, B));


    }

    // unique pairs
    private static int countPairsOfTargetSum(int[] arr, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int count = 0;

        for (int i = 0; i < arr.length; i++) {
            int target = k - arr[i];
            int num = arr[i];

            if (map.containsKey(target) && map.get(target) > 0) {
                map.put(target, map.get(target) - 1);
                count++;
            } else {
                map.put(num, map.getOrDefault(num, 0) + 1);
            }
        }
        return count;
    }

    // all pairs
    private static int countPairsAllOfTargetSum(int[] arr, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int count = 0;

        for (int i = 0; i < arr.length; i++) {
            int target = k - arr[i];
            int num = arr[i];

            if (map.containsKey(target) && map.get(target) > 0) {
                count = count + map.get(target);
            } else {
                map.put(num, map.getOrDefault(num, 0) + 1);
            }
        }
        return count;
    }

    public static int[] solve(int[] A, int[] B) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int num : A) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        int[] res = new int[B.length];
        for (int i = 0; i < B.length; i++) {
            res[i] = map.getOrDefault(B[i], 0);
        }
        return res;
    }


    /*
    // unique pairs
    private int countPairForTarget(int[] arr, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int count = 0;

        for (int i = 0; i < arr.length; i++) {
            int x = k - arr[i];

            if (map.containsKey(x) && map.get(x) > 0) {
                map.put(x, map.get(x) - 1);
                count++;
            } else {
                map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
            }
        }
        return count;
    }

    // all pairs
    private int countAllTargetPairs(int[] arr, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int count = 0;

        for (int i = 0; i < arr.length; i++) {
            int target = k - arr[i];

            if (map.containsKey(target)) {
                count += map.get(target);
            } else {
                map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
            }
        }
        return count;
    }

     */

    /*
    private static ArrayList<ArrayList<Integer>> returnAllTheIndexOfTargetSum(int[] arr, int k) {
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            int target = k - arr[i];

            if (map.containsKey(target)) {
                for (int index : map.get(target)) {
                    ArrayList<Integer> pair = new ArrayList<>();
                    pair.add(index);
                    pair.add(i);
                    result.add(pair);
                }

            }

            if (map.containsKey(arr[i])) {
                map.get(arr[i]).add(i);
            } else {
                ArrayList<Integer> indices = new ArrayList<>();
                indices.add(i);
                map.put(arr[i], indices);
            }
        }
        return result;

    }


     */

    private static ArrayList<ArrayList<Integer>> returnAllTheIndexOfTargetSum(int[] arr, int k) {
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];
            int target = k - num;

            if (map.containsKey(target)) {
                for (int index : map.get(target)) {
                    ArrayList<Integer> pair = new ArrayList<>();
                    pair.add(index);
                    pair.add(i);
                    list.add(pair);
                }
            }

            if (map.containsKey(num)) {
                map.get(num).add(i);
            } else {
                ArrayList<Integer> index = new ArrayList<>();
                index.add(i);
                map.put(num, index);
            }
        }
        // if i want to return list of indexes
        return list;
        // if count of pairs
        //return list.size();
    }


}
