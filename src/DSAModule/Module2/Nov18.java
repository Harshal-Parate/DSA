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

}
