package algorithms1;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import javax.print.attribute.IntegerSyntax;

public class Sort {
	
	public static void selectionSort(int[] A) {
		for (int i = 0; i < A.length; i++) {
			int minIndex = i;
			for (int j = i+1; j < A.length; j++) {
				if (A[j] < A[minIndex]) {
					minIndex = j;
				}
			}
			swap(A, i, minIndex);
		}
	}
	
	public static int[] mergeSort(int[] A) {
		int n =  A.length;
		int sizeB;
		int sizeC;
		if (n == 1) 
			return A;
		
		if ( n % 2 == 0 )
			sizeB = sizeC = n/2;
		else { 
			sizeB = n/2;
			sizeC = sizeB + 1;
		}
		
		int[] A1 = new int[sizeB];
		int[] A2 = new int[sizeC];
		System.arraycopy(A,0,A1,0,sizeB);
		System.arraycopy(A,sizeB,A2,0,sizeC);
		
		int[] b = mergeSort(A1);
		int[] c = mergeSort(A2);
		return merge(b, c);
	}
	
	
	public static void quickSort(int[] A, int l, int r) {
		if (l >= r)
			return;
		//random pivot
		int pivot = pivotHeuristic(A, l, r);
		swap(A, l, pivot);
		int[] m = partition(A, l, r);
		quickSort(A, l, m[0]-1);
		quickSort(A, m[1]+1, r);
	}
	
	private static int[] merge(int[] B, int[] C) {
		
		int size = C.length + B.length;
		int[] D = new int[size];
		int i = 0;
		int j = 0;
		int z = 0;
		
		while (i < B.length && j < C.length) {
			int b = B[i];
			int c = C[j];
			if (b <= c) {
				D[z] = b;
				i++;
				z++;
			} else {
				D[z] = c;
				j++;
				z++;
			}
		}
		
		for(; i < B.length; i++, z++) {
			D[z] = B[i];
		}
		
		for(; j < C.length; j++, z++) {
			D[z] = C[j];
		}
		
		return D;
	}
	
	private static int[] partition(int[] A, int l, int r) {
		int x = A[l];
		int j = l;
		int k = l;
		
		for (int i = l+1; i <= r; i++ ) {
			if (A[i] < x) {
				j+=1;
				k+=1;
				swap(A, i, j);
				if (k > j) {
					swap(A, i, k);
				}
			} else if (A[i] == x) {
				k+=1;
				swap(A, i, k);
			}
		}
		swap(A, l, j);
		return new int[] {j,k};
	}
	
	private static void swap(int[] A, int i, int j) {
		int aux = A[i];
		A[i] = A[j];
		A[j] = aux;
	}
	
	/* this heuristic select the middle value of 3 elements */
	private static int pivotHeuristic(int[] A, int l, int r) {
		int pivot;
		//4282
		if (l >= r)
			pivot = l;
		else if (Math.abs(l-r) < 2)
			pivot = l;
		else {	
			int[] probablesPivots = {A[l],A[r],A[(r-l)/2]};
			//int[] indexes = {l,r,(r-l)/2};
			Map<Integer, Integer> indexes = new HashMap<Integer, Integer>();
			indexes.put(A[l], l);
			indexes.put(A[r], r);
			indexes.put(A[(r-l)/2], (r-l)/2);
			selectionSort(probablesPivots);
			pivot = indexes.get(probablesPivots[1]);
		}
		return pivot; 	
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
	
	private static boolean compareArrays(int[] arr1, int[] arr2) {
		for (int i = 0; i < arr1.length; i++) {
			if(arr1[i] != arr2[i]) {
				return false;
			}
		}
		return true;
	}
	
	private static boolean compareArrays(int[] arr1, int[] arr2, int[] arr3) {
		for (int i = 0; i < arr1.length; i++) {
			if(arr1[i] != arr2[i] || arr1[i] != arr3[i] || arr2[i] != arr3[i]) {
				return false;
			}
		}
		return true;
	}
	
	public static void test() {
		
		while(true) {
			//Random random = new Random();
			int n = ThreadLocalRandom.current().nextInt(1,Integer.MAX_VALUE/100000);
			int[] arr = new int[n];
			
			for (int i = 0; i < n; i++) {
				arr[i] = ThreadLocalRandom.current().nextInt(1,Integer.MAX_VALUE);
			}
			
			int[] selectionArray = new int[n];
			int[] mergeArray = new int[n];
			int[] quickArray = new int[n];
		
			System.arraycopy(arr, 0, selectionArray, 0, n);
			System.arraycopy(arr, 0, mergeArray, 0, n);
			System.arraycopy(arr, 0, quickArray, 0, n);
			
			selectionSort(selectionArray);
			quickSort(quickArray, 0, quickArray.length-1);
			mergeArray = mergeSort(mergeArray);
			
			if (compareArrays(selectionArray, mergeArray, quickArray)) {
				System.out.println("OK");
			}
			else {
				System.out.println("Wrong answer!!!!!");
				System.out.println("inital array = ");
				printArray(arr);
				System.out.println("selection array = ");
				printArray(selectionArray);
				System.out.println("merge array = ");
				printArray(mergeArray);
				System.out.println("quick array = ");
				printArray(quickArray);
				return;
			}
		
		}
		
		
		
	}
}
