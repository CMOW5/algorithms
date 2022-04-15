package algorithms1;

import java.util.*;

public class MinHeap {
	private int[] data;
	private List<Swap> swaps;
	
	public MinHeap(int data[]) {
		this.data = data;
		this.swaps = new ArrayList<>();
	}
	
	public String solveNaive() {
		for (int i = 0; i < data.length; i++) {
			for (int j = i + 1; j < data.length; j++) {
				if (data[i] > data[j]) {
					swaps.add(new Swap(i, j));
					int temp = data[i];
					data[i] = data[j];
					data[j] = temp;
				}
			}
		}
		return response();
	}
	
	public String solve() {
		buildTree();
		return response();
	}
	
	private void buildTree() {
		for (int i = data.length/2; i >= 0; i--) { // O(n)
			siftDown(i); // O(log(n))
		}
	}
	
	private void siftDown(int index) {
		int siftChildIndex = getSiftChildIndex(index);
		
		if (siftChildIndex < 0) return;
		
		swaps.add(new Swap(index,siftChildIndex));
		int temp = data[index];
		data[index] = data[siftChildIndex]; 
		data[siftChildIndex] = temp;
	
		siftDown(siftChildIndex);
	}
	
	private int getLeftChild(int index) {
		return data[2*index + 1];
	}
	
	private int getLeftChildIndex(int index) {
		return 2*index + 1;
	}
	
	private int getRightChild(int index) {
		return data[2*index + 2];
	}
	
	private int getRightChildIndex(int index) {
		return 2*index + 2;
	}
	
	private int getSiftChildIndex(int index) {
		
		int currentNode = data[index];
		
		try {
			int leftChild = getLeftChild(index);
			int rightChild = getRightChild(index);
			
			if (currentNode <= leftChild && currentNode <= rightChild) return -1;
			
			if(leftChild  < rightChild) {
				return getLeftChildIndex(index);
			} else {
				return getRightChildIndex(index);
			}
		} catch (IndexOutOfBoundsException e) { }
		
		try {
			int leftChild = getLeftChild(index);
			if (currentNode <= leftChild) return -1;
			return getLeftChildIndex(index);
		} catch (IndexOutOfBoundsException e) { }
		
		try {
			int rightChild = getLeftChild(index);
			if (currentNode <= rightChild) return -1;
			return getRightChildIndex(index);
		} catch (IndexOutOfBoundsException e) { }
		
		return -1;
	}
	

	private String response() {
		StringBuilder response = new StringBuilder();
		response.append(swaps.size());
		response.append("\n");
		for (Swap swap: swaps) {
			response.append(swap.index1 + " " + swap.index2);
			response.append("\n");
		}
		return response.toString();
	}
	
	private class Swap {
		private int index1;
		private int index2;
		
		public Swap(int index1, int index2) {
			this.index1 = index1;
			this.index2 = index2;
		}
	}
	
}
