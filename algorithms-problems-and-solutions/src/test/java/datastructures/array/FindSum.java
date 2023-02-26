package datastructures.array;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Given an array and a number "n", find two numbers from the array that sums up to "n".
 *
 * Sample input => arr = {1, 21, 3, 14, 5, 60, 7, 6}, value = 27
 * Sample output => arr = {21, 6} or {6, 21}
 */
public class FindSum {

    @Test
    public void findSumTest() {
        Assertions.assertThat(findSumNaive(new int[] {1, 21, 3, 14, 5, 60, 7, 6}, 27)).contains(21, 6);
        Assertions.assertThat(findSumNaive(new int[] {9, 4, 7, 2, 6}, 17)).contains(9, 4, 7, 2, 6);
        Assertions.assertThat(findSumNaive(new int[] {5, 11, 4, 6}, 10)).contains(4, 6);

        Assertions.assertThat(findSumSorted(new int[] {1, 21, 3, 14, 5, 60, 7, 6}, 27)).contains(21, 6);
        Assertions.assertThat(findSumSorted(new int[] {9, 4, 7, 2, 6}, 17)).contains(9, 4, 7, 2, 6);
        Assertions.assertThat(findSumSorted(new int[] {5, 11, 4, 6}, 10)).contains(4, 6);

        Assertions.assertThat(findSum(new int[] {1, 21, 3, 14, 5, 60, 7, 6}, 27)).contains(21, 6);
        Assertions.assertThat(findSum(new int[] {9, 4, 7, 2, 6}, 17)).contains(9, 4, 7, 2, 6);
        Assertions.assertThat(findSum(new int[] {5, 11, 4, 6}, 10)).contains(4, 6);
    }

    // O(n)
    public int[] findSum(int[] arr, int n) {
        Set<Integer> seenNumbers = new HashSet<>();

        for (int val: arr) {
            int r = Math.abs(n - val);

            if (seenNumbers.contains(r)) {
                return new int[] {val, r};
            }

            seenNumbers.add(val);
        }
        return arr;
    }

    // O(n^2)
    public int[] findSumNaive(int[] arr, int n) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] + arr[j] == n) {
                    return new int[] {arr[i], arr[j]};
                }
            }
        }
        return arr;
    }

    // O(nlog(n))
    public int[] findSumSorted(int[] arr, int n) {
        Arrays.sort(arr);
        int i = 0;
        int j = arr.length - 1;

        while (i != j) {
            if (arr[i] + arr[j] == n) {
                return new int[] {arr[i], arr[j]};
            } else if (arr[i] + arr[j] > n) {
                j--;
            } else if (arr[i] + arr[j] < n) {
                i++;
            }
        }

        return arr;
    }
}