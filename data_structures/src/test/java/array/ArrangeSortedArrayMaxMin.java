package array;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Given an array, can you re-arrange the elements such that the first position will have the largest number,
 * the second will have the smallest, the third will have the second-largest, and so on.
 *
 * This will re-arrange the elements of a sorted array in such a way that the first position will have the largest
 * number, the second will have the smallest, the third will have the second-largest, and so on.
 *
 * Sample input => arr = {1, 2, 3, 4, 5}
 * Sample output => arr = {5, 1, 4, 2, 3}
 */
public class ArrangeSortedArrayMaxMin {

    @Test
    public void testMaxMin() {
        int[] arr = new int[] {1, 2, 3, 4, 5};
        maxMin(arr);
        Assertions.assertThat(arr).containsExactly(5, 1, 4, 2, 3);

        arr = new int[] {1, 2, 3, 4, 5, 6, 7};
        maxMin(arr);
        Assertions.assertThat(arr).containsExactly(7, 1, 6, 2, 5, 3, 4);
    }

    // O(n/2 + n/2) = O(n)
    public void maxMin(int[] arr) {
        int[] result = new int[arr.length];

        // store the max numbers first
        for (int i = 0, j = arr.length - 1; i < arr.length; i+=2, j--) {
            result[i] = arr[j];
        }

        // then store the min numbers
        for (int i = 1, j = 0; i < arr.length; i+=2, j++) {
            result[i] = arr[j];
        }

        System.arraycopy(result, 0, arr, 0, result.length);
    }
}
