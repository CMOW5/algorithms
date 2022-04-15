package algorithms1;

public class Fibonacci {

	public static int[] fibonacci(int n) {

		int[] fibArray = new int[n + 1];
		
		if (n == 0) {
			fibArray[0] = 0;
			return fibArray;
		}
		if (n == 1) {
			fibArray[0] = 0;
			fibArray[1] = 1;
			return fibArray;
		}
		
		fibArray[0] = 0;
		fibArray[1] = 1;

		for (int i = 2; i <= n; i++) {
			fibArray[i] = fibArray[i-1] + fibArray[i-2];
		}

		return fibArray;

	}

	public static long lastFibonacciNumber(long n) {

		if (n <= 1)
			return n;

		long fiboN = 0;
		long n1 = 0;
		long n2 = 1;

		for (long i = 2; i <= n; i++) {
			long aux = n2;
			fiboN = n1 + n2;
			n2 = fiboN;
			n1 = aux;
		}

		return fiboN;

	}

}
