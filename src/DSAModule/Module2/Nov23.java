package DSAModule.Module2;

import java.util.Stack;

public class Nov23 {

    public static void main(String[] args) {

        String string = doubleTroubleCharacter("abba");
        System.out.println(string);

    }

    private static String doubleTroubleCharacter(String str) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            if (!stack.isEmpty() && stack.peek() == ch) {
                stack.pop();
            } else {
                stack.push(ch);
            }
        }

        StringBuilder result = new StringBuilder();

        for (char ch : stack) {
            result.append(ch);
        }

        return result.toString();
    }


}
