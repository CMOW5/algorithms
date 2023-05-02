package datastructures.stack;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Stack;

/**
 *  implement the isBalanced() method, which will take a string containing only curly {}, square [],
 *  and round () parentheses. The method will tell us whether all the parentheses in the string are balanced or not.
 *
 *  sample input => exp = "{[({})]}"
 *  sample output  => True
 */
public class BalancedParentheses {

    private static List<Character> OPEN_ELEMENTS = List.of('{', '[', '(');

    @Test
    public void test() {
        Assertions.assertThat(isBalanced("{}{}{}")).isTrue();
        Assertions.assertThat(isBalanced("{()}[{}]")).isTrue();
        Assertions.assertThat(isBalanced("{{[[}}}]")).isFalse();
        Assertions.assertThat(isBalanced("{[}]()")).isFalse();
    }

    public boolean isBalanced(String exp) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < exp.length(); i++) {
            Character element = exp.charAt(i);
            if (isOpenElement(element)) {
                stack.push(element);
            } else if (!stack.isEmpty()) {
                Character openElement = stack.pop();
                if (!isMatch(openElement, element)) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    private boolean isOpenElement(Character element) {
        return OPEN_ELEMENTS.contains(element);
    }

    private boolean isMatch(Character openElement, Character closingElement) {
        if (openElement.equals('{') && closingElement.equals('}')) {
            return true;
        } else if (openElement.equals('(') && closingElement.equals(')')) {
            return true;
        } else return openElement.equals('[') && closingElement.equals(']');
    }


}
