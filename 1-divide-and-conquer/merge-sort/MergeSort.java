/**
 * implementation of the merge sort algorithm
 *
 * -> there are log2(n) + 1 levels. The input size is being decreased by 2 in each level.
 * the number of levels of the recursion tree is exactly the number of times you need to divide
 * n by 2 until you get down to a number that is at most one == log2(n)
 * 
 * -> at a given level j, there are 2^j subproblems
 * 
 * -> at a given level j, the input size is n/(2^j)
 * 
 * -> work at a given level j = 0,1,2,3..log2(n) = 2^j * (n/2^j)
 * -> total work = 2^j * (n/2^j) * log2(n) = n*log2(n) 
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] A = {5, 2, 4, 7, 1, 3, 2, 6};
        printArray(A);
        mergeSort(A, 0, 7);
        printArray(A);
    }

    /**
     * sorts the elements in the subarray A[p....r]
     * runs O[nlog(n)]
     */
    public static void mergeSort(int[] A, int p, int r) {
        if (p < r) {
            // divide O(1)
            int q = ((p + r) / 2); 
            // conquer (recursively solve two subproblems, each of size n/2)
            // 2*T(n/2) when T(n) is 
            mergeSort(A, p, q); 
            mergeSort(A, q + 1, r);
            // combine O(n)
            merge(A, p, q, r);
        }
    }

    /**
    * merges the two subarrays A[p...q] and A[q+1...r]
    * runs O(r - p) => O(n)
    */
    public static void merge(int[] A, int p, int q, int r) {
        // copy subarray A[p...q] to new array R
        int n1 = q - p + 1;
        int[] R = new int[n1 + 1];

        // source, from source, dest, from dest, # elements to copy
        System.arraycopy(A, p, R, 0, n1);
        R[n1] = Integer.MAX_VALUE; // sentinel, so we don't worry about getting to the end of the array
                                   // and potentially getting IndexOutOfBounds 

        // copy subarray A[q+1...r] to new array L    
        int n2 = r - q; 
        int[] L = new int[n2 + 1];
        System.arraycopy(A, q+1, L, 0, n2);
        L[n2] = Integer.MAX_VALUE; // sentinel, so we don't worry about getting to the end of the array
                                   // and potentially getting IndexOutOfBounds 

        // we assume R and L are sorted, so we just compare the values and stores them in the original array A
        for (int k = p, i = 0, j = 0; k <= r; k++) {
            int r_value = R[i];
            int l_value = L[j];
            if (r_value <= l_value) {
                A[k] = r_value;
                i++;
            } else {
                A[k] = l_value;
                j++;
            }
        } 
    }
    
    public static void printArray(int[] A) {
        for (int v : A) {
          String value = v == Integer.MAX_VALUE ? "INF": String.valueOf(v);
          System.out.print(value + ", ");
        }
        System.out.println();  
    }
}