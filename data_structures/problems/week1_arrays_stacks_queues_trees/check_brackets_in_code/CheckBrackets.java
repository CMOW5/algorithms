package algorithms1;

import java.util.*;

public class CheckBrackets {

	private String mSequence;
	private List<Character> openingSet = new ArrayList<>(Arrays.asList('{','[','('));
	private List<Character> closingSet = new ArrayList<>(Arrays.asList('}',']',')'));

	public CheckBrackets(String sequence) {
		mSequence = sequence;
	}
	
	public String check() {
		
		Stack<Bracket> stack = new Stack();
		
		for (int i = 0; i < mSequence.length(); i++) {
			
			char c = mSequence.charAt(i);
			
			if(openingSet.contains(c)) {
				
				Bracket openingBracket = new Bracket(c, i+1);
				
				stack.push(openingBracket);
			
			} else if (closingSet.contains(c)) {
				
				if (stack.isEmpty()) {
					return String.valueOf(i+1);
				} else {
					
					Bracket openingBracket = stack.pop();
					
					int openingIndex = openingSet.indexOf(openingBracket.getCharacter());
					int closingIndex = closingSet.indexOf(c);
					if (closingIndex != openingIndex) {
						return String.valueOf(i+1);
					}
				}
			} else {
				//normal character
			}
		}
		
		if (stack.isEmpty()) {
			return "SUCCESS";
		} else {
			int index = stack.pop().getIndex();
			return String.valueOf(index);
		}
		
	}
	
	
	private class Bracket {
		
		private char mCharacter;
		private int mIndex;
		
		public Bracket(char character, int index) {
			mCharacter = character;
			mIndex = index;
		}

		public char getCharacter() {
			return mCharacter;
		}

		public int getIndex() {
			return mIndex;
		}		
	}
	
	public void test() {
		//String s = "[]";
		//String s = "{}[]";
		//String s = "[()]";
		//String s = "(())";
		//String s = "{[]}()";
		//String s = "{";
		//String s = "{[}";
		//String s = "foo(bar);";
		//String s = "foo(bar[i);";
		/*
		CheckBrackets cb = new CheckBrackets(s);
		String result = cb.check();
		print(result);
		*/
	}
	
}
