package datastructures.array;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Given two sorted arrays, merge them into one array, which should also be sorted
 *
 * Sample input => arr1 = {1, 3, 4, 5}, arr2 = {2, 6, 7, 8}
 * Sample output => arr = {1, 2, 3, 4, 5, 6, 7, 8}
 */
public class MergeSortedArrays {

    @Test
    public void mergeArraysTest() {

        Assertions.assertArrayEquals(
                mergeArrays(new int[] {1,3,4,5}, new int[] {2,6,7,8}),
                new int[] {1,2,3,4,5,6,7,8});

        Assertions.assertArrayEquals(
                mergeArrays(new int[] {1,1,1,1}, new int[] {2,6,7,8}),
                new int[] {1,1,1,1,2,6,7,8});

        Assertions.assertArrayEquals(
                mergeArrays(new int[] {2,6,7,8}, new int[] {1,1,1,1}),
                new int[] {1,1,1,1,2,6,7,8});
    }

    // The time complexity for this algorithm is O(n+m) where n and are the sizes of arr1 and arr2
    public int[] mergeArrays(int[] arr1, int[] arr2) {
        int s1 = arr1.length;
        int s2 = arr2.length;
        int[] resultantArray = new int[s1+s2];
        int i = 0, j = 0, k = 0;

        // Traverse both array
        while (i < s1 && j < s2) {
            // Check if current element of first
            // array is smaller than current element
            // of second array. If yes, store first
            // array element and increment first array
            // index. Otherwise, do same with second array
            if (arr1[i] < arr2[j])
                resultantArray[k++] = arr1[i++];
            else
                resultantArray[k++] = arr2[j++];
        }

        // Store remaining elements of first array
        while (i < s1)
            resultantArray[k++] = arr1[i++];

        // Store remaining elements of second array
        while (j < s2)
            resultantArray[k++] = arr2[j++];

        return resultantArray;
    }
}