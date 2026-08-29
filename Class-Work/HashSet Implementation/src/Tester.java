
public class Tester {

	public static void main(String[] args) {
		
		HashSet s = new HashSet(11);
		s.add("cat");
		s.add("dog");
		s.add("tree");
		s.add("house");
		s.add("chair");
		s.add("cat"); // shouldn't get added
		
		for (char letter = 'A'; letter <= 'z'; letter++) {
			s.add(letter + "");
		}
		
		System.out.println(s.toDeluxeString());
		
	}

}
