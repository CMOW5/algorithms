package algorithms1;

/*
 * The goal in this problem is to find the minimum number of 
 * coins needed to change the input value (an integer) into 
 * coins with denominations 1, 5, and 10.
 */

public class MoneyChange {
	
	private int[] denominations = {10,5,1};
	private int mChange;
	
	public MoneyChange(int change) {
		mChange = change;
	}
	
	public int calculateChange() {
		int remainChange = mChange;
		int coins = 0;
		
		for (int i = 0; i < denominations.length; i++) {
			coins += remainChange / denominations[i];
			remainChange = remainChange % denominations[i];
			if(remainChange == 0) {
				break;
			}
		}
		return coins;
	}
	
}
