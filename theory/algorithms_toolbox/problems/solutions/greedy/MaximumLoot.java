package algorithms1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/*
 * 		HashMap<Double, Double> items = new HashMap<>();
		//items.put((double)500,(double) 30);
		items.put((double)60,(double) 20);
		items.put((double)100,(double) 20);
		items.put((double)120,(double) 30);
		MaximumLoot maximumLoot = new MaximumLoot(60, items);
		double result = maximumLoot.calculateMaximumLoot(); 
		System.out.println(result);
 */

public class MaximumLoot {
	
	private double mTotalValue;
	private double mCurrentWeight;
	private ArrayList<Item> mItems;
	
	public MaximumLoot(double weight,HashMap<Double, Double> items) {
		mTotalValue = 0;
		mCurrentWeight = weight;
		mItems = new ArrayList<>();
		//System.arraycopy( items, 0, mItems, 0, items.length );
		//mItems = items;
		Iterator iterator = items.entrySet().iterator();
		while(iterator.hasNext()) {
			Map.Entry pair = (Map.Entry)iterator.next();
			Item item = new Item((double)pair.getKey(),(double)pair.getValue());
			mItems.add(item);
		}
		
		orderItems(mItems);
		printList();
	}
	
	public double calculateMaximumLoot() {
		
		for (int i = 0; i<mItems.size(); i++) {
			
			Item currentItem = mItems.get(i);	
			double wi = currentItem.getWeight();
			double vi = currentItem.getValue();
			double availableWeight = wi == 0 ? 0 : mCurrentWeight/wi;
			
			if(availableWeight == 0) {
				continue;
			}
			else if(availableWeight >= 1) {
				mTotalValue += vi;
				mCurrentWeight -= wi;
				currentItem.setWeight(0);
				mItems.set(i, currentItem);
			} else {
				mTotalValue += mCurrentWeight*vi/wi;
				mCurrentWeight -= mCurrentWeight;
				currentItem.setWeight(0);
				mItems.set(i, currentItem);
			}
			
			if (mCurrentWeight == 0) {
				return mTotalValue;
			}
			
		}
		
		return mTotalValue;
	}
	
	private void orderItems(ArrayList<Item> list) {
		
		boolean isOrdered = false;
		
		while(!isOrdered) {
			
			for(int i = 0; i < list.size(); i++) {		
				if (i == list.size() - 1) {
					isOrdered = true;
					break;
				}
				Item currentItem = list.get(i);
				Item nextItem = list.get(i+1);
				
				if(currentItem.getValuePerWeight() < nextItem.getValuePerWeight()) {
					list.set(i, nextItem);
					list.set(i+1, currentItem);
					break;
				}
			}
		}
	}
	
	private class Item {
		
		private double mWeight;
		private double mValue;
		private double mValuePerWeight;
		
		public Item(double value,double weigth) {
			mValue = value;
			mWeight = weigth;
			mValuePerWeight = mValue/mWeight;
		}

		public double getWeight() {
			return mWeight;
		}

		public void setWeight(double weight) {
			mWeight = weight;
		}
	
		public double getValue() {
			return mValue;
		}


		public double getValuePerWeight() {
			return mValuePerWeight;
		}
		
		private void updateValues() {
			mValuePerWeight = mWeight == 0 ? 0 : mValue/mWeight;
		}
	}
	
	public void printList() {
		for(Item item : mItems) {
			System.out.println("valuePerWeight = " + item.getValuePerWeight());
		}
	}
	
	
}
