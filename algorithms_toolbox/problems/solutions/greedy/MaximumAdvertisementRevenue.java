package algorithms1;

public class MaximumAdvertisementRevenue {

	private long[] profitsPerClick;
	private long[] clicksPerDay;

	public MaximumAdvertisementRevenue(long[] profitsPerClick, long[] clicksPerDay) {
		this.profitsPerClick = new long[profitsPerClick.length];
		this.clicksPerDay = new long[clicksPerDay.length];
		System.arraycopy(profitsPerClick, 0, this.profitsPerClick, 0, profitsPerClick.length);
		System.arraycopy(clicksPerDay, 0, this.clicksPerDay, 0, clicksPerDay.length);
		sortArray(this.profitsPerClick);
		sortArray(this.clicksPerDay);
	}
	
	public long maximize() {
		long total = 0;
		
		for (int i = 0; i < this.profitsPerClick.length; i++) {
			total += this.profitsPerClick[i]*this.clicksPerDay[i];
		}
	
		return total;
	}
	
	private void sortArray(long[] array) {
		boolean isOrdered = false;

		while (!isOrdered) {

			for (int i = 0; i < array.length; i++) {
				if (i == array.length - 1) {
					isOrdered = true;
					break;
				}
				long currentItem = array[i];
				long nextItem = array[i+1];

				if (currentItem < nextItem) {
					array[i] = nextItem;
					array[i+1] = currentItem;
					break;
				}
			}
		}
	}

}
