import java.util.HashSet;
import java.util.Set;

/**
 * Implement an algorithm to determine if a string has all unique characters. What if you
 * cannot use additional data structures? (Bit vector)
 */
public class IsUnique {

	public static void main(String[] args) {
		String str = "abcdefghijklmnopqrstuvwxyz";
		boolean result = hasDuplicates(str);
		System.out.println(result);
	}

	private static boolean hasDuplicates(String str) {
		Set<Character> foundCharacters = new HashSet<Character>();
		for (int i = 0; i < str.length(); i++) {
			char character = str.charAt(i);
			if (foundCharacters.contains(character)) {
				return true;
			} else {
				foundCharacters.add(character);
			}
		}
		return false;
	}
}
