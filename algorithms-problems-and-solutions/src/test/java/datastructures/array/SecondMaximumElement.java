package datastructures.array;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Given an array of size n, can you find the second maximum element in the array?
 * Assumption: Array should contain at least two unique elements.
 *
 * Sample input => arr = {9,2,3,6}
 * Sample output => 6
 */
public class SecondMaximumElement {

    @Test
    public void testFindSecondMaximum() {
        Assertions.assertThat(findSecondMaximumTraversingTwice(new int[] {9,2,3,6})).isEqualTo(6);
        Assertions.assertThat(findSecondMaximumTraversingTwice(new int[] {4,2,1,5,0})).isEqualTo(4);

        Assertions.assertThat(findSecondMaximumTraversingOnce(new int[] {9,2,3,6})).isEqualTo(6);
        Assertions.assertThat(findSecondMaximumTraversingOnce(new int[] {4,2,1,5,0})).isEqualTo(4);
    }

    // O(n + n) = O(n)
    public int findSecondMaximumTraversingTwice(int[] arr) {
        int max = Integer.MIN_VALUE;;
        int secondMax = Integer.MIN_VALUE;

        // Find the maximum value in the array by comparing each index with max
        // If an element is greater than max then update max to be equal to it
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        // Find the second maximum value by comparing each index with secondmax
        // If an element is greater than secondmax and not equal to previously
        // calculated max, then update secondmax to be equal to that element.
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > secondMax && arr[i] < max) {
                secondMax = arr[i];
            }
        }

        return secondMax;
    }

    // O(n)
    private int findSecondMaximumTraversingOnce(int [] arr) {
        int max = Integer.MIN_VALUE;;
        int secondMax = Integer.MIN_VALUE;

        // Keep track of Maximum value, whenever the value at an array index is greater
        // than current Maximum value then make that max value 2nd max value and
        // make that index value maximum value
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                secondMax = max;
                max = arr[i];
            }
            else if (arr[i] > secondMax && arr[i] != max) {
                secondMax = arr[i];
            }
        }

        return secondMax;
    }
}
