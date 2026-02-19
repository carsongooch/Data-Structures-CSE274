import java.util.Arrays;

public class tester {

	public static void main(String[] args) {
		//MUCH MORE TESTING TO DO THAN THIS HERE.

		System.out.println("**** int constructor and basics:");
		FractionSet fs = new FractionSet(5); // initial capacity 5
		System.out.println("empty set: " + fs); // tests toString() for an empty set

		System.out.println("add 4/6 true? " + fs.add(new Fraction(4, 6)));
		System.out.println(fs);
		System.out.println("add 3/5 true? " + fs.add(3, 5));
		System.out.println(fs);
		System.out.println("add 20/30 false? " + fs.add(20, 30));
		System.out.println(fs);
		System.out.println("size is 2? " + fs.size());
		System.out.println("isEmpty is false? " + fs.isEmpty());
		System.out.println("add 1/3 true? " + fs.add(1, 3));
		System.out.println("contains 12/18 true? " + fs.contains(new Fraction(12, 18)));

		System.out.println("\n**** removing");
		System.out.println("before remove: " + fs);
		System.out.println("removing 6/10 true? " + fs.remove(new Fraction(6, 10)));
		System.out.println("after remove: " + fs);
		
		
		System.out.println("\n**** toArray() vs peekAtUnderlyingArray()");
		System.out.println("toArray: " + Arrays.toString(fs.toArray()));
		System.out.println("peekAtUnderlyingArray: "
		        + Arrays.toString(fs.peekAtUnderlyingArray()));

		System.out.println("\n**** some math:");
		System.out.println("fs: " + fs);
		System.out.println("min: " + fs.min());
		System.out.println("sum: " + fs.sum());

		System.out.println("\n**** methods that return new sets:");
		System.out.println("original: " + fs);
		FractionSet allTheSums = fs.allSums();
		System.out.println("allSums: " + allTheSums);

		System.out.println("\n**** methods that combine 2 sets to make new sets:");
		
		FractionSet fs2 = new FractionSet();
		fs2.add(1, 3);
		fs2.add(2, 3);
		fs2.add(3, 4);
		System.out.println("fs: " + fs);
		System.out.println("fs2: " + fs2);
		System.out.println("intersection: " + fs.intersection(fs2));
		System.out.println("union: " + fs.intersection(fs2));

		System.out.println("\n**** hasAllReciprocals:");
		System.out.println("fs: " + fs);
		System.out.println("false? " + fs.hasAllReciprocals());
		fs.add(3, 2);
		
		

	}

}
