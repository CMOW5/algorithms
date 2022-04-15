package algorithms1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 * @author cristian
 *
 * 
 * segments = new long[][] {
			{1,6},{2,3},{1,4},
			{3,9},{4,7},{5,6},
			{10,20},{12,22},{13,18}
		
		};	
	CollectingSignatures cs = new CollectingSignatures(segments);
	Long[] result = cs.minimunNumberOfPoints();
	System.out.println("t points = " + result.length);
	printArray(result);
 * 
 *
 */

public class CollectingSignatures {
	
	long[][] mSegments;
	Set<Long> mPoints;
	long mNumberOfPoints;
	
	public CollectingSignatures(long[][] segments) {
		mSegments = new long[segments.length][segments.length];
		System.arraycopy(segments, 0, mSegments, 0, segments.length);
		mPoints = new HashSet<Long>();
		sortArray(mSegments);
		printArray();
	}
	
	public Long[] minimunNumberOfPoints() {
		
		long[] newSegment = mSegments[0]; 
		
		for(int i = 0; i < mSegments.length - 1; i++) {			
			long[] currentSegment = mSegments[i];
			long[] nextSegment = mSegments[i+1];
			long a1 = currentSegment[0];
			long b1 = currentSegment[1];
			long a2 = nextSegment[0];
			long b2 = nextSegment[1];
			if (a2 >= newSegment[0] && a2 <= newSegment[1]) {
				if (b2 > b1) {
					newSegment = new long[] {a2,b1};
				} else {
					newSegment = new long[] {a2,b2};
				}
			} else {
				mPoints.add(newSegment[1]);
				newSegment = new long[] {a2,b2};
			}
		}
		mPoints.add(newSegment[0]);
		Long[] arr = mPoints.toArray(new Long[mPoints.size()]);
		return arr;
	}
	
	private void sortArray(long[][] array) {
		
		boolean isOrdered = false;

		while (!isOrdered) {

			for (int i = 0; i < array.length; i++) {
				if (i == array.length - 1) {
					isOrdered = true;
					break;
				}
	
				long[] currentItem = array[i];
				long[] nextItem = array[i+1];
					
				if (currentItem[0] > nextItem[0]) {
					array[i] = nextItem;
					array[i+1] = currentItem;
					break;
				}	
			}
		}
		
	}
	
	private void printArray() {
		for(int i = 0; i < mSegments.length; i++) {
			for(int j = 0; j < mSegments[i].length; j++) {
				System.out.print(mSegments[i][j]);
			}
			System.out.println("");
		}
		
	}

}
