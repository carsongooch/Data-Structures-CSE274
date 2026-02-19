/**
 * A basic tester for our FixedArraySet
 */
public class Tester {
	
	public static void main(String[] args) {
		
		//As quickly as possible we want to start testing.
		//So, write a constructor, an add(), and toString()
		// you can replace your tester code with the code below for additional testing
		// *** Gradually uncomment parts of the code when you are ready to test them.
		// Eventually the whole tester should be uncommented.
				
				int CAPACITY = 10;
				FixedArraySet s = new FixedArraySet(CAPACITY);
				s.add("tree");
				s.add("cat");
				s.add("house");
				s.add("computer");
				s.add("dog");
				s.add("top");
				//System.out.println(s);
				
				FixedArraySet copy = s.copyOf();
				System.out.println(copy);
				
				s.remove("cat");
				System.out.println(s);
				s.clear();
		
	}
	
}
