package datastructures.array;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Given an array, re-arrange its elements in such a way that the negative elements
 * appear at one side and positive elements appear at the other.
 * Note: Consider 0 as a positive number.
 * Note: Order of the numbers doesn't matter.
 *
 * Sample input => arr = {10, -1, 20, 4, 5, -9, -6}
 * Sample output => arr = {-1, -9, -6, 10, 20, 4, 5}
 */
public class ArrangePositiveAndNegativeValues {

    @Test
    public void testReArrange() {
        int[] arr = new int[] {10, -1, 20, 4, 5, -9, -6};
        reArrange(arr);
        Assertions.assertThat(arr).containsExactly(-1, -9, -6, 10, 20, 4, 5);
    }

    public void reArrange(int[] arr) {
        int[] result = new int[arr.length];
        int resultIndex = 0;

        // find negative numbers first
        for (int value : arr) {
            if (value < 0) {
                result[resultIndex++] = value;
            }
        }

        // find positive numbers
        for (int value : arr) {
            if (value >= 0) {
                result[resultIndex++] = value;
            }
        }

        System.arraycopy(result, 0, arr, 0, result.length);
    }
}
