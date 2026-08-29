
public class Tester {

	public static void main(String[] args) {
		// constructor, add, and toString
		TreeSet s = new TreeSet();
		s.add(3); // goes to left
		s.add(1); // goes to left
		s.add(7); // right of 3
		s.add(10);
		s.add(20); // right of root
		s.add(15); // left of 20
		System.out.println(s); // In-order traversal:1 3, 7, 10, 15, 20
		System.out.println(s.size());
		System.out.println(s.contains(15));
		System.out.println(s.contains(0));
		
		System.out.println(s.min());
		s.add(0);
		System.out.println(s.min());
		
		
		
	}

}
