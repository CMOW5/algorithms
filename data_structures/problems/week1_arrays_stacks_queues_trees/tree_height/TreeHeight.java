package algorithms1;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class TreeHeight {
	
	private Node[] nodes;
	private int[] _nodes;
	private int rootIndex = -1;
	
	public TreeHeight(int[] nodes) {
		int size = nodes.length;
		this._nodes = nodes;
		this.nodes = new Node[size];
		allocateNodes(nodes); //O(n)
		createTree(); //O(n)
	}
	
	public int heightNaive() {
		
		int maxHeight = 0;
		int[] parent = this._nodes;
		
		for (int vertex = 0; vertex < this._nodes.length; vertex++) {
			int height = 0;
			for (int i = vertex; i != -1; i = parent[i]) {
				height++;
			}
			maxHeight = Math.max(maxHeight,height);
		}
	
		return maxHeight;
	}
	
	public int height() {
		Node rootNode = this.nodes[this.rootIndex]; // O(1)
		return dfsHeight(rootNode);
	}
	
	private int bfsHeight() {
		
		Node rootNode = this.nodes[this.rootIndex]; // O(1)
		rootNode.setDepth(1);
		Queue<Node> queue = new LinkedList();
		queue.add(rootNode);
		
		int height = 0;
		
		while(!queue.isEmpty()) {
			
			Node currentNode = queue.remove();
			Node[] childNodes = currentNode.childs(); // O(n)
			
			for (Node childNode : childNodes) {
				childNode.setDepth(currentNode.getDepth() + 1);
				queue.add(childNode);
			}
			
			height = currentNode.getDepth();
			
		}

		return height;
	}
	
	private int dfsHeight(Node node) {
		
		
		int currentHeight = 1; // O(1)
		int maxHeight = 0; // O(1)
		Node[] childNodes = node.childs(); // O(n)
		
		if (childNodes.length == 0) {
			return currentHeight;
		}
		
		for (Node childNode : childNodes) {
			int height = dfsHeight(childNode);
			maxHeight = Math.max(height, maxHeight);
		}
		
		return maxHeight + currentHeight;
	}
	
	private void allocateNodes(int[] nodes) {
		for (int key = 0; key < nodes.length; key++) {
			int parent = nodes[key];
			Node node = new Node(key,parent);
			this.nodes[key] = node;
		}
	}
	
	private void createTree() {
		
		for (int childIndex = 0; childIndex < this.nodes.length; childIndex++) {
			
			Node currentNode = this.nodes[childIndex];
			if (currentNode.isRoot()) {
				this.rootIndex = childIndex;
			}
			else {
				int parentIndex = currentNode.getParent();
				this.nodes[parentIndex].addChild(currentNode);
			}
		}
	}
	
	private class Node {
		
		private int key;
		private int parent;
		private int depth = 0;
		private boolean isRoot = false;
		private List<Node> child_nodes;
		
		public Node(int key, int parent) {
			this.key = key;
			this.parent = parent;
			if (parent == -1) {
				this.isRoot = true;
			}
			child_nodes = new ArrayList<>();
		}
		
		public void addChild(Node childNode) {
			this.child_nodes.add(childNode);
		}
		
		public void setRoot(boolean isRoot) {
			this.isRoot = isRoot;
		}
		
		public boolean isRoot() {
			return this.isRoot;
		}
		
		public int getKey() {
			return this.key;
		}
		
		public int getParent() {
			return this.parent;
		}
		
		public void setDepth(int depth) {
			this.depth = depth;
		}
		
		public int getDepth() {
			return this.depth;
		}
		
		public Node[] childs() {
			return this.child_nodes.toArray(new Node[this.child_nodes.size()]);
		}
		
		
	}
	
	public static void test(boolean forever) {
		
		do {
			
			int n = 100000;//100000;//ThreadLocalRandom.current().nextInt(1,50000);
			
			int[] nodes = new int[n]; // {-1, 0, 4, 0, 3};
			nodes[0] = -1;
			
			for(int i = 1; i < n; i++) {
				nodes[i] = i - 1;
			}
			
			TreeHeight th = new TreeHeight(nodes);
			
			System.out.println("calculating naive...");
			int result1 = th.heightNaive();
			System.out.println("calculating efficient...");
			int result2 = th.bfsHeight();//th.height();
			
			if (result1 == result2) {
				System.out.println("OK");
			} else {
				System.out.println("Wrong answer!!!!!");
				System.out.println("tree = ");
				printArray(nodes);
				System.out.println("result 1 = ");
				print(result1);
				System.out.println("result2 = ");
				print(result2);
				
				return;
			}	
		
			
		} while(forever);
		
	}
	
	public static <T> void print(T str) {
		System.out.println(str);
	}
	
	private static <T> void printArray(int[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println("");
	}
	
	
	
	/*
	 
	 public int compareTo(Point point) {
			if (mPoint > point.getPoint())
				return 1;
			else if (mPoint < point.getPoint())
				return -1;
			else
				return 0;
		}
	 
	 private void orderPoints() {
		Collections.sort(mPoints, new Comparator<Point>() {
		    public int compare(Point point1, Point point2) {
		        return point1.compareTo(point2);
		    }
		});
	}
	 */
	
}
