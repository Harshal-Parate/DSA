package DSAModule.Module2;

import java.util.Stack;

public class Nov18 {

    public int balancedParenthesis(String str) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == '(') {
                stack.push(')');
            } else if (ch == '[') {
                stack.push(']');
            } else if (ch == '{') {
                stack.push('}');
            } else {
                if (stack.isEmpty() || stack.pop() != ch) {
                    return 0;
                }
            }
        }
        return (stack.isEmpty()) ? 1 : 0;
    }

    public int evalRPN(String[] A) {
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < A.length; i++) {
            String str = A[i];

            if (str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/")) {

                int b = stack.pop();
                int a = stack.pop();

                switch (str) {
                    case "+":
                        stack.push(a + b);
                        break;

                    case "-":
                        stack.push(a - b);
                        break;

                    case "*":
                        stack.push(a * b);
                        break;

                    case "/":
                        stack.push(a / b);
                        break;
                }

            } else {
                stack.push(Integer.parseInt(str));
            }
        }
        return stack.pop();
    }

    // Monotonic stack questions
    private int[] nextSmaller(int[] A) {

        // A = [4, 5, 2, 10, 8]
        //     [2, 2, -1, 8, -1]

        int n = A.length;
        int[] ans = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = n - 1; i >= 0; i--) {

            while (!stack.isEmpty() && stack.peek() >= A[i]) {
                stack.pop();
            }

            ans[i] = stack.isEmpty() ? -1 : stack.peek();

            stack.push(A[i]);
        }
        return ans;
    }

    private int[] nextGreater(int[] A) {
        int n = A.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= A[i]) {
                stack.pop();
            }
            result[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(A[i]);
        }
        return result;
    }

    private int[] prevSmaller(int[] A) {
        int n = A.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && stack.peek() >= A[i]) {
                stack.pop();
            }
            result[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(A[i]);
        }
        return result;
    }

    private int[] prevGreater(int[] A) {
        int n = A.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && stack.peek() <= A[i]) {
                stack.pop();
            }
            result[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(A[i]);
        }
        return result;
    }

    private String removeEqualPairsOfConsecutiveChar(String str) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (!stack.isEmpty() && stack.peek() == ch) {
                stack.pop();
            } else {
                stack.push(ch);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (Character character : stack) {
            sb.append(character);
        }
        return sb.toString();
    }

    private int largestRectangleInHistogram(int[] A) {
        return 0;
    }



    public static void main(String[] args) {
        Nov18 clazz = new Nov18();

        String string = clazz.removeEqualPairsOfConsecutiveChar("abbc");
        System.out.println(string);

        int[] A = {2, 1, 5, 6, 2, 3};
        int i = clazz.largestRectangleInHistogram(A);
        System.out.println(i);
    }




}






















