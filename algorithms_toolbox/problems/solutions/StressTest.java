import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import algorithms1.LCM;

public class StressTest {

	public void run() {
		
		while(true) {
			
			//Random random = new Random();
			long a = ThreadLocalRandom.current().nextLong(1,(long)(1+2*Math.pow(10, 9)));
			long b = ThreadLocalRandom.current().nextLong(1,(long)(1+2*Math.pow(10, 9)));
			long result1, result2;
			
			System.out.println("a = " + a);
			System.out.println("b = " + b);
			
			result1 = LCM.leastCommonMultipleNaive(a, b);
			result2 = LCM.leastCommonMultiple(a, b);
			
			if(result1 == result2) {
				System.out.println("OK");
			} else {
				System.out.println("Wrong answer!!!!!");
				System.out.println("result1 = " + result1);
				System.out.println("result2 = " + result2);
				return;
			}
				
		}
		
	}

}
