package datastructures.array;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Given an array, can you rotate its elements once from right to left.
 * The function takes an arr and rotate it right by 1. This means that the right-most elements will appear
 * at the left-most position in the array.
 *
 * Sample input => arr = {1, 2, 3, 4, 5}
 * Sample output => arr = {5, 1, 2, 3, 4}
 */
public class RotateArrayToRight {

    @Test
    public void testRotateArray() {
        int[] arr = new int[] {1, 2, 3, 4, 5};
        rotateArray(arr);
        Assertions.assertThat(arr).isEqualTo(new int[] {5,1,2,3,4});

        arr = new int[] {1, 2};
        rotateArray(arr);
        Assertions.assertThat(arr).isEqualTo(new int[] {2,1});

        arr = new int[] {1};
        rotateArray(arr);
        Assertions.assertThat(arr).isEqualTo(new int[] {1});
    }

    public void rotateArray(int[] arr) {
        if (arr.length <= 1)
            return;

        //Store last element of Array.
        //Start from the Second last and Right Shift the Array by one.
        //Store the last element saved on the first index of the Array.
        int lastElement = arr[arr.length - 1];

        for (int i = arr.length - 1; i > 0; i--) {
            arr[i] = arr[i - 1];
        }

        arr[0] = lastElement;

    }
}
