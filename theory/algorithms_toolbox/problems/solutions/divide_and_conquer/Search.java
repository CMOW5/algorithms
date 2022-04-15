package algorithms1;

import java.util.concurrent.ThreadLocalRandom;

public class Search {
	
	public static int linearSearch(int[] A, int key) {
		for (int i = 0; i < A.length; i++) {
			if (A[i] == key)
				return i;
		}
		return -1;
	}
	
	
	public static int binarySearch(int[] A, int low, int high, int key) {
		if (high < low) {
			//return low - 1;
			return -1;
		}
		int mid = low + (high - low)/2;
		if (key == A[mid]) {
			return mid;
		} else if (key < A[mid]) {
			return binarySearch(A, low, mid-1, key);
		} else {
			return binarySearch(A, mid+1, high, key);
		}
	}
	
	public static int binarySearchFirst(int[] A, int low, int high, int key) {
		if (high < low) {
			//return low - 1;
			return -1;
		}
		int mid = low + (high - low)/2;
		
		if (A[mid] < key)
			return binarySearchFirst(A, mid+1, high, key);
	    else if (A[mid] > key)
	    	return binarySearchFirst(A, low, mid-1, key);
	    else if (low != mid) //Equal but range is not fully scanned
	    	return binarySearchFirst(A, low, mid, key); //Set upper bound to current number and rescan
	    else //Equal and full range is scanned
	        return mid;
		
	}
	
	public static void test() {
		
		while(true) {
			//Random random = new Random();
			int n = ThreadLocalRandom.current().nextInt(1,20);
			int[] arr = new int[n];
			int key = Integer.MAX_VALUE/2;
			
			for (int i = 0; i < n; i++) {
				arr[i] = ThreadLocalRandom.current().nextInt(1,Integer.MAX_VALUE);
				if (i == n-1);
					//key = arr[i];
			}
			
			arr = Sort.mergeSort(arr);
			
			int result1 =  linearSearch(arr, key);
			int result2 =  binarySearch(arr, 0,arr.length-1, key);
			
			if ( result1 == result2 ) {
				System.out.println("OK");
			}
			else {
				System.out.println("Wrong answer!!!!!");
				System.out.println("key = " + key);
				System.out.println("inital array = ");
				printArray(arr);
				System.out.println("result1 = " + result1);
				System.out.println("result2 index  = " + result2);
				System.out.println("result2 value at index = " + arr[result2]);
				return;
			}
		
		}
		
	}
	
	private static <T> void print(T str) {
		System.out.println(str);
	}
	
	private static <T> void printArray(int[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println("");
	}
	
}
