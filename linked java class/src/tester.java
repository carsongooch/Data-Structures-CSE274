
public class tester {

	public static void main(String[] args) {
		//Standard start: constructor, add, toString, tested
		LinkedSet s = new LinkedSet();
		System.out.println(s.add("cat")); // true;
		System.out.println(s.add("dog")); // true;
		System.out.println(s.add("cat")); // false;
		System.out.println(s.add("tree")); // true;
		
		
		s.add("house");
		s.add("car");
		
		System.out.println(s);
		
		System.out.println(s.remove("tree")); // true
		System.out.println(s.remove("cat")); // true
		System.out.println(s.remove("bird")); // false
		System.out.println(s);
		System.out.println(s.size());
		System.out.println(s.remove()); // car
		System.out.println(s); // dog house
		System.out.println(s.remove()); // dog
		
		System.out.println(s.add("dog")); // true;
		System.out.println(s.add("cat")); // false;
		System.out.println(s.add("tree")); // true;
		System.out.println(s);
		System.out.println(s.remove()); // true
		System.out.println(s);
		

	}

}
