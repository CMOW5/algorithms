package algorithms1;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class OrganizingLotteries {
	
	private List<Segment> mSegments;
	private List<Point> mPoints;
	
	public OrganizingLotteries(int[][] segments, int[] points) {
		mSegments = new ArrayList<>();
		mPoints = new ArrayList<>();
		for (int[] segment : segments) {
			Segment newSegment = new Segment(segment);
			mSegments.add(newSegment);
		}
		for (int point : points) {
			Point newPoint = new Point(point);
			mPoints.add(newPoint);
		}
		orderPoints();
		orderSegments();
	}
	
	public int[] organizeNaive() {
		orderPoints();
		for (int i = 0; i < mPoints.size(); i++) {
			Point currentPoint = mPoints.get(i);
			for (int j = 0; j < mSegments.size(); j++) {
				Segment currentSegment = mSegments.get(j);
				if(currentSegment.containsPoint(currentPoint.getPoint())) {
					currentPoint.incrementCount();
				}
			}
		}
		
		int[] counts = new int[mPoints.size()]; 
		for (int i = 0; i < counts.length; i++) {
			counts[i] = mPoints.get(i).getCount();
		}
		
		return counts;
	}
	
	public int[] organize() {
		
		orderPoints();
		orderSegments();
		
		for (int i = 0; i < mPoints.size(); i++) {
			Point currentPoint = mPoints.get(i);
			for (int j = 0; j < mSegments.size(); j++) {
				Segment currentSegment = mSegments.get(j);
				if (currentSegment.containsPoint(currentPoint.getPoint())) {
					currentPoint.incrementCount();
				} else if(currentSegment.isPointHigher(currentPoint.getPoint())) {
					continue;
				} else {
					break;
				}
			}
		}
		
		int[] counts = new int[mPoints.size()]; 
		for (int i = 0; i < counts.length; i++) {
			counts[i] = mPoints.get(i).getCount();
		}
		
		return counts;
	}
	
	public int[] organize2() {
		
		orderPoints();
		orderSegments();
		
		for (int i = 0; i < mSegments.size(); i++) { // O(n)
			Segment currentSegment = mSegments.get(i); // O(1)
			int[] indexes = getPointsInsideSegment(currentSegment); // O(log(n))
			if (indexes != null) {
				for (int j = indexes[0]; j <= indexes[1]; j++) { // O(n)
					mPoints.get(j).incrementCount();
				}
			}
		}
		
		int[] counts = new int[mPoints.size()]; 
		for (int i = 0; i < counts.length; i++) {
			counts[i] = mPoints.get(i).getCount();
		}
		
		return counts;
	}
	
	private int[] getPointsInsideSegment(Segment segment) {
		
		int lowPoint = segment.getLow();
		int highPoint = segment.getHigh();
		
		int lowIndex = searchFirstPoint(mPoints, lowPoint, 0, mPoints.size());
		
		if(lowIndex < 0)
			return null;
		
		int highIndex = searchLastPoint(mPoints, highPoint, 0, mPoints.size()-1);
		
		if (highIndex < lowIndex || highIndex < 0 || lowIndex < 0) {
			return null;
		} else {
			return new int[] {lowIndex,highIndex};
		}
	}
	
	private int searchFirstPoint(List<Point> points, int key, int low, int high) {
		
		//safe in lower bound	
		if (high < low)
			return -1;
		
		int mid = low + (high-low)/2;	
		int currentPoint = 0;
		
		try {
			currentPoint = points.get(mid).getPoint();
		} catch (Exception e) {
			return -1;
		}
		
		if (currentPoint < key) {
			return searchFirstPoint(points, key, mid+1, high);
		} else if (low != mid) { //Equal but range is not fully scanned
			return searchFirstPoint(points,key,low,mid);
		} else {
			return mid;
		}
		
	}
	
	private int searchLastPoint(List<Point> points, int key, int low, int high) {
		
		if (high < low)
			return -1;
		
		int mid = low + (high-low)/2;
		
		int currentPoint = points.get(mid).getPoint();
		
		if (currentPoint > key) {
			return searchLastPoint(points, key, low, mid-1);
		} else if (high != mid) { //Equal but range is not fully scanned
			if (Math.abs(high - low) == 1) {
				int firstPoint = points.get(low).getPoint();
				int lastPoint =  points.get(high).getPoint();
				if (lastPoint <= key )
					return high;
				else
					return low;
			}
			else
				return searchLastPoint(points,key,mid,high);
		} else {
			return mid;
		}
		
	}
	
	private void orderPoints() {
		Collections.sort(mPoints, new Comparator<Point>() {
		    public int compare(Point point1, Point point2) {
		        return point1.compareTo(point2);
		    }
		});
	}
	
	private void orderSegments() {
		Collections.sort(mSegments, new Comparator<Segment>() {
		    public int compare(Segment segment1, Segment segment2) {
		        return segment1.compareTo(segment2);
		    }
		});
	}
	
	private int searchInSegment(Segment segment) {
		
		int low = segment.getLow();
		int high = segment.getHigh();
		
		
		return 0;
	}
	
	private class Point{
		
		private int mPoint;
		private int mCount = 0;
		
		public Point(int point) {
			mPoint = point;
		}
		
		public void incrementCount() {
			mCount++;
		}
		
		public int getPoint() {
			return mPoint;
		}
		
		public int getCount() {
			return mCount;
		}
		
		public int compareTo(Point point) {
			if (mPoint > point.getPoint())
				return 1;
			else if (mPoint < point.getPoint())
				return -1;
			else
				return 0;
		}
	}
	
	private class Segment {
		
		private int mLow;
		private int mHigh;
		
		public Segment(int low, int high) {
			mLow = low;
			mHigh = high;
		}
		
		public Segment(int[] segment) {
			mLow = segment[0];
			mHigh = segment[1];
		}
		
		public int getLow() {
			return mLow;
		}

		public int getHigh() {
			return mHigh;
		}
		
		public boolean containsPoint(int point) {
			return point >= mLow && point <= mHigh;
		}
		
		public boolean isPointHigher(int point) {
			return point > mHigh;
		}

		public int compareTo(Segment segment) {
			
			if (mLow > segment.getLow())
				return 1;
			else if (mLow < segment.getLow())
				return -1;
			else
				return 0;
		}
		
	}
	
	public static void test(boolean forever) {
		
		do {
			
			int s = 50000;//ThreadLocalRandom.current().nextInt(1,50000);
			int p = 50000;//ThreadLocalRandom.current().nextInt(1,50000);
			
			int[][] segments = new int[s][];
			int[] points = new int[p];
			
			for(int i = 0; i < s; i++) {
				int[] segment = new int[2];
				segment[0] = ThreadLocalRandom.current().nextInt(-(int)Math.pow(10,8),(int)Math.pow(10,8));
				segment[1] = ThreadLocalRandom.current().nextInt(segment[0],(int)Math.pow(10,8));
				//segment[0] = ThreadLocalRandom.current().nextInt(-10,10);
				//segment[1] = ThreadLocalRandom.current().nextInt(segment[0],10);
				segments[i] = segment;
			}
			
			for(int i = 0; i < p; i++) {
				int point = ThreadLocalRandom.current().nextInt(-(int)Math.pow(10,8),(int)Math.pow(10,8));
				//point = ThreadLocalRandom.current().nextInt(-20,20);
				points[i] = point;
			}
			
			OrganizingLotteries ol1 = new OrganizingLotteries(segments, points);
			OrganizingLotteries ol2 = new OrganizingLotteries(segments, points);
			
			System.out.println("calculating naive...");
			int[] result1 = ol1.organizeNaive();
			System.out.println("calculating efficient...");
			int[] result2;
			try {
				result2 = ol2.organize2();
				System.out.println("done efficient...");
				if(compareArrays(result1, result2)) {
					System.out.println("OK");
				} else {
					System.out.println("Wrong answer!!!!!");
					System.out.println("segments = ");
					printArray(segments);
					System.out.println("points = ");
					printArray(points);
					System.out.println("result 1 = ");
					printArray(result1);
					System.out.println("result2 = ");
					printArray(result2);
					
					int[][] newSegments = segments;
					int[] newPoints = points;
					OrganizingLotteries ol3 = new OrganizingLotteries(newSegments, newPoints);
					
					int[] result3 = ol3.organize2();
					
					return;
				}	
			} catch (Exception e) {
				System.out.println("exception !!!!!");
				System.out.println("segments = ");
				printArray(segments);
				System.out.println("points = ");
				printArray(points);
				System.out.println("result 1 = ");
				printArray(result1);
				
				int[][] newSegments = segments;
				int[] newPoints = points;
				OrganizingLotteries ol3 = new OrganizingLotteries(newSegments, newPoints);
				
				int[] result3 = ol3.organize2();
			
				//System.out.println("result2 = ");
				//printArray(result2);
				return;
			}
			
			
		} while(forever);
		
	}
	
	private static boolean compareArrays(int[] arr1, int[] arr2) {
		for (int i = 0; i < arr1.length; i++) {
			if(arr1[i] != arr2[i]) {
				return false;
			}
		}
		return true;
	}
	
	private static <T> void printArray(int[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println("");
	}
	
	private static <T> void printArray(int[][] a) {
		for (int i = 0; i < a.length; i++) {
			int[] element = a[i];
			System.out.println(element[0] + "," + element[1]);
		}
		System.out.println("");
	}
}
