import java.io.*;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Main {

	public static void main(String[] args) {
		Tn tn = new Tn();
		int fibr = fibList(46);
		System.out.println(fibr);
		System.out.println("lines of code: "+ tn.get());
		
		tn.reset();
		fibr = fibRecur(46);
		System.out.println(fibr);
		System.out.println("lines of code: "+ tn.get());
	}
	
	public static int fibRecur(int n) {
		Tn tn = new Tn();
		tn.add();
		if (n <= 1) {
			tn.add();
			return n;
		} else {
			tn.add();
			tn.add();
			return fibRecur(n-1) + fibRecur(n-2);
		}
	}
	
	public static int fibList(int n) {
		Tn tn = new Tn();
		int[] fibArray = new int[n+1];
		fibArray[0] = 0;
		fibArray[1] = 1;
		for(int i = 2; i <= n; i++) {
			fibArray[i] = fibArray[i-1] + fibArray[i-2];
		}
		return fibArray[n];
	}
	
	public static class Tn {
		static int n = 0;
		
		public static void add() {
			n++;
		}
		public static int get() {
			return n;
		}
		public static void reset() {
			n = 0;
		}
	}
}
