package algorithms1;

import java.util.ArrayList;

public class RabinKarpAlgorithm {
	private long prime = 1000000007;
	private long multiplier = 10; // random number between 0 and prime
	// private int m = 5;
	
	public int[] RabinKarp(String text, String pattern) {
		ArrayList<Integer> ocurrences = new ArrayList<>();
		
		long pHash = polyHash(pattern, prime, multiplier);
		
		long[] hashes = precomputeHashes(text, pattern.length(), prime, multiplier);
		
		for (int i = 0; i <= text.length() - pattern.length(); i++) {
			if (pHash != hashes[i]) {
				continue;
			}
			if (pattern.equals(text.substring(i, i + pattern.length()))) {
				ocurrences.add(i);
			}
		}
		return ocurrences.stream().mapToInt(i -> i).toArray();
	}
	
	private long[] precomputeHashes(String text, int patternLength, long prime, long multiplier) {
		int textLength = text.length();
		int hashLength = (textLength - patternLength + 1);
		
		long[] hashes = new long[hashLength];
		
		String s = text.substring(textLength - patternLength);
		
		hashes[hashLength - 1] = polyHash(s, prime, multiplier);
		
		long y = 1; 
		for (int i = 0; i < patternLength; i++) {
			y = (y * multiplier) % prime;
		}
		
		for (int i = text.length() - patternLength - 1; i >= 0; i--) {
			long preHash = multiplier * hashes[i+1] + text.charAt(i) - 
							y * text.charAt(i+patternLength);
			hashes[i] = ((preHash % prime) + prime) % prime;
		}
		
		return hashes;
	}
	
	private long polyHash(String str, long prime, long multiplier) {
		long hash = 0;
		for (int i = str.length() - 1; i >= 0; --i)
			hash = (hash * multiplier + str.charAt(i)) % prime;
		return hash;
	}
	
	public static void test() {
		RabinKarpAlgorithm rka = new RabinKarpAlgorithm();
		int[] result = rka.RabinKarp("abacaba", "aba");
		// int[] result = rka.RabinKarp("baaaaaaa", "aaaaa");
	}

}
