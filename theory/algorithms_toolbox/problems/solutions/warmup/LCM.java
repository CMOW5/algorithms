package algorithms1;

public class LCM {

	public static long leastCommonMultiple(long a, long b) {
		return (a * b) / smallestDivider(a, b);
	}

	public static long smallestDivider(long a, long b) {
		long max = Math.max(a, b);
		long min = Math.min(a, b);
		long mod = max % min;

		if (mod != 0) {
			max = min;
			min = mod;
			mod = smallestDivider(min, max);
		} else {
			mod = min;
		}
		
		return mod;
	}

	public static long leastCommonMultipleNaive(long a, long b) {
		long factor = 0;
		boolean calculateNextFactor = false;

		if (a == 1 && b == 1) {
			return 1;
		}
		if (a == 0 || b == 0) {
			return 0;
		}
		for (long i = 2; i <= Math.max(a, b); i++) {
			if (a % i == 0) {
				a = a / i;
				calculateNextFactor = true;
				factor = i;
			}
			if (b % i == 0) {
				b = b / i;
				calculateNextFactor = true;
				factor = i;
			}
			if (calculateNextFactor) {
				break;
			}
		}

		return factor * leastCommonMultipleNaive(a, b);
	}
}
