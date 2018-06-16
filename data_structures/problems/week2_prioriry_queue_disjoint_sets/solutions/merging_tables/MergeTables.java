package algorithms1;

import java.util.ArrayList;

public class MergeTables {
	private Table[] tables;
	private int [][] transactions;
	private int maxSize;
	
	
	public MergeTables(int[] sizes,int [][] transactions) {
		this.maxSize = 0;
		this.tables = new Table[sizes.length];
		this.transactions = transactions;
		// build the tree
		for (int i = 0; i < this.tables.length; i++) {
			this.tables[i] = new Table(sizes[i], i);
			this.maxSize = Math.max(this.maxSize, sizes[i]);
		}
	}
	
	public void merge() {
		for (int[] transaction : this.transactions) { // O(n)
			int src =  transaction[1] - 1; // converting to 0 based index array
			int dest =  transaction[0] - 1; // converting to 0 based index array
			copyTable(src, dest); // O(tree height)
		}
	}
	
	private int find(int i) {
		if (i != this.tables[i].parent) {
			// path compresion
			this.tables[i].parent = find(this.tables[i].parent);
		}
		return this.tables[i].parent;
		
		/*
		while (i != this.tables[i].parent) {
			i = this.tables[i].parent;
		}
		return i;
		*/
	}
	
	private void copyTable(int src, int dest) {
		src = find(src); // pointer to the real data to copy
		dest = find(dest); // pointer to the real table to copy the data
		if (src != dest) {
			Table srcTable = this.tables[src];
			Table destTable = this.tables[dest]; 
			destTable.size += srcTable.size;
			// simlink the src table to the dest table
			srcTable.size = 0;
			srcTable.parent = dest;
			this.maxSize = Math.max(this.maxSize, destTable.size);
		}
		System.out.println(this.maxSize);
	}
	
	private class Table {
		public int size;
		public int parent;
		
		public Table(int size, int parent) {
			this.size = size;
			this.parent = parent;
		}
	}
	
	public void Test() {
		// int[] sizes = {1,1,1,1,1};
		// int[][] transactions = {{3,5},{2,4},{1,4},{5,4},{5,3}};
		int[] sizes = {10,0,5,0,3,3};
		int[][] transactions = {{6,6},{6,5},{5,4},{4,3}};
		MergeTables mt = new MergeTables(sizes, transactions);
		mt.merge();
	}

}
