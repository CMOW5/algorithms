package array;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Given an array, return an array where each index stores the product of all numbers
 * except the number on the index itself.
 *
 * Sample input => arr = {1,2,3,4}
 * Sample output => arr = {24,12,8,6}
 */
public class ArrayOfProducts {

    @Test
    public void testFindProduct() {
        Assertions.assertThat(findProductNaive(new int[] {1,2,3,4})).containsExactly(24,12,8,6);
        Assertions.assertThat(findProductNaive(new int[] {2,5,9,3,6})).containsExactly(810,324,180,540,270);
        Assertions.assertThat(findProductNaive(new int[] {0,1,2})).containsExactly(2,0,0);
        Assertions.assertThat(findProductNaive(new int[] {11,2,5,7,2})).containsExactly(140,770,308,220,770);

        Assertions.assertThat(findProduct(new int[] {1,2,3,4})).containsExactly(24,12,8,6);
        Assertions.assertThat(findProduct(new int[] {2,5,9,3,6})).containsExactly(810,324,180,540,270);
        Assertions.assertThat(findProduct(new int[] {0,1,2})).containsExactly(2,0,0);
        Assertions.assertThat(findProduct(new int[] {11,2,5,7,2})).containsExactly(140,770,308,220,770);

    }

    public int[] findProductNaive(int[] arr) {
        int [] result = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            int product = 1;
            for (int j = 0; j < arr.length; j++) {
                if (i != j) {
                    product *= arr[j];
                }
            }
            result[i] = product;
        }

        return result;
    }

    public int[] findProduct(int[] arr) {
        int n = arr.length;
        int i, temp = 1;

        // Allocation of result array
        int[] result = new int[n];

        // Product of elements on left side excluding arr[i]
        for (i = 0; i < n; i++)
        {
            result[i] = temp;
            temp *= arr[i];
        }

        // Initializing temp to 1 for product on right side
        temp = 1;

        // Product of elements on right side excluding arr[i]
        for (i = n - 1; i >= 0; i--)
        {
            result[i] *= temp;
            temp *= arr[i];
        }

        return result;
    }
}
