package queue;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * method to generate binary numbers from 1 to any given number (n)
 * Sample input => n = 3
 * Sample output => "1","10","11"
 */
public class BinaryNumbersUpToN {

    @Test
    public void test() {
        Assertions.assertThat(findBin(3)).containsExactly("1", "10", "11");
        Assertions.assertThat(findBin(5)).containsExactly("1", "10", "11", "100", "101");
    }

    public String[] findBin(int number) {
        String ZERO = "0";
        String ONE = "1";
        String[] result = new String[number];

        Queue<String> queue = new ArrayDeque<>();
        queue.add(ONE);

        for (int i = 0; i < number; i++) {
            String value = queue.remove();
            result[i] = value;
            queue.add(value + ZERO);
            queue.add(value + ONE);
        }

        return result;
    }

}
