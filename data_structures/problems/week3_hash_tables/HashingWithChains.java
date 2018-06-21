package algorithms1;

import java.util.ArrayList;
import java.util.List;

public class HashingWithChains<E> {
	private long p = 1000000007;
	private int x = 263;
	private int m = 5;
	
	private ArrayList<E>[] list;
	
	public HashingWithChains() {
		this.list = new ArrayList[m];
		for (int i = 0; i < this.m; i++) {
			this.list[i] = new ArrayList<>();
		}
	}
	
	public int hash(String str) {
		long result = 0;
		for (int i = 0; i < str.length(); i++) {
			long si = str.charAt(i);
			long xi = (long) Math.pow(this.x, i); 
			result += si * xi;
		}
		return (int) ((result % this.p) % this.m);
	}
	
	// O(c + 1)
	public void add(E entry) {
		int index = hash((String) entry);
		ArrayList<E> sublist = list[index];
		
		if (hasEntry(sublist, entry)) {
			return;
		} else {
			sublist.add(0, entry); // O(n)
		}
	}
	
	public void del(E entry) {
		int index = hash((String) entry);
		ArrayList<E> sublist = list[index];
		
		if (hasEntry(sublist, (E) entry)) {
			sublist.remove(entry);
		}
	}
	 
	// O(c + 1) -> c = longest chain in the list
	public String find(E entry) {
		int index = hash((String) entry);
		ArrayList<E> sublist = list[index];
		
		// O(c)
		if (hasEntry(sublist, entry)) {
			return "yes";
		} else {
			return "no";
		}
	}
	
	public String check(int index) {
		ArrayList<E> sublist = list[index];
		StringBuilder output = new StringBuilder("");
	
		for (int i = 0; i < sublist.size(); i++) {
			E element = sublist.get(i);
			output.append(element + " "); 
		}
		
		return output.toString().trim();
		
	}
	
	// O(c) -> c = longest chain in the list  	
	private boolean hasEntry(ArrayList<E> sublist, E entry) {
		for (int i = 0; i < sublist.size(); i++) {
			E element = sublist.get(i);
			if (element.equals(entry)) {
				return true;
			}
		}
		return false;
	}
	
	private static void test() {
		HashingWithChains<String> hwc = new HashingWithChains<String>();
		hwc.add("world");
		hwc.add("HellO");
		System.out.println(hwc.check(4));
		System.out.println(hwc.find("World"));
		System.out.println(hwc.find("world"));
		hwc.del("world");
		System.out.println(hwc.check(4));
		hwc.del("HellO");
		hwc.add("luck");
		hwc.add("GooD");
		System.out.println(hwc.check(2));
		hwc.del("good");
	}

}
