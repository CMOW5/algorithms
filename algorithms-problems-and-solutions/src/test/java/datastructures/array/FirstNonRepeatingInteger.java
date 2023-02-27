package datastructures.array;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Given an array, find the first integer, which is unique in the array. Unique means the number does
 * not repeat and appears only once in the whole array. The function returns -1 if no unique number is found.
 *
 * Sample input => arr = {9, 2, 3, 2, 6, 6}
 * Sample output => 9
 */
public class FirstNonRepeatingInteger {

    private final static int NOT_FOUND = -1;

    @Test
    public void testFindFirstUnique() {
        Assertions.assertThat(findFirstUniqueNaive(new int[] {9, 2, 3, 2, 6, 6})).isEqualTo(9);
        Assertions.assertThat(findFirstUniqueNaive(new int[] {4, 5, 1, 2, 0, 4})).isEqualTo(5);
        Assertions.assertThat(findFirstUniqueNaive(new int[] {4, 4, 4, 3})).isEqualTo(3);
        Assertions.assertThat(findFirstUniqueNaive(new int[] {4, 5, 1, 2, 0, 4, 5 ,0, 2, 1})).isEqualTo(-1);
        Assertions.assertThat(findFirstUniqueNaive(new int[] {})).isEqualTo(-1);


        Assertions.assertThat(findFirstUnique(new int[] {9, 2, 3, 2, 6, 6})).isEqualTo(9);
        Assertions.assertThat(findFirstUnique(new int[] {4, 5, 1, 2, 0, 4})).isEqualTo(5);
        Assertions.assertThat(findFirstUniqueNaive(new int[] {4, 4, 4, 3})).isEqualTo(3);
        Assertions.assertThat(findFirstUnique(new int[] {4, 5, 1, 2, 0, 4, 5 ,0, 2, 1})).isEqualTo(-1);
        Assertions.assertThat(findFirstUniqueNaive(new int[] {})).isEqualTo(-1);
    }

    // O(n^2)
    public int findFirstUniqueNaive(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            boolean hasDuplicates = false;
            for (int j = 0; j < arr.length; j++) {
                hasDuplicates = (j != i) && (arr[i] == arr[j]);
                if (hasDuplicates) {
                    break;
                }
            }
            if (!hasDuplicates) {
                return arr[i];
            }
        }

        return NOT_FOUND;
    }

    // O(n) but using a hashTable
    public int findFirstUnique(int[] arr) {
        Map<Integer, Integer> numbersCount = new LinkedHashMap<>();

        for (int value : arr) { // O(n)
            Integer count = numbersCount.getOrDefault(value, 0) + 1; // O(1)
            numbersCount.put(value, count); // O(1)
        }

        // O(n)
        return numbersCount.entrySet().stream()
                           .filter(entrySet -> entrySet.getValue() == 1)
                           .findFirst()
                           .map(Map.Entry::getKey)
                           .orElse(NOT_FOUND);
    }
}
