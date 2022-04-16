

public class BinarySearch {

    private static final int NOT_FOUND_INDEX = -1; 

    public static void main(String[] args) {
        int[] A = {1, 2, 3, 4, 6, 7, 8 , 9, 10, 11};
        int index = binarySearch(A, 7, 0, A.length - 1);
        System.out.println("index = " + index);
        index = binarySearch(A, 5, 0, A.length - 1);
        System.out.println("index = " + index);
    }

    public static int binarySearch(int[] A, int valueToFind, int start, int end) {
        if (start > end) {
            return NOT_FOUND_INDEX;
        }
        
        int middle = (end + start) / 2;
        int value = A[middle];

        if (value == valueToFind) {
            return middle;
        } else if (value < valueToFind) {
            start = middle + 1;
        } else {
            end = middle - 1;
        }

        return binarySearch(A, valueToFind, start, end);
    }

}