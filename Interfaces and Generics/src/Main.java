import java.awt.Point;

public class Main {

	public static void main(String[] args) {
		list<Point> myList = new ArrayList<>(); // If LinkedList were implemented I can change ArrayList to LinkedList to change entire code
		// List<String> <String> can be changed to anything since we have generics implemented the T variable
		myList.add(new Point(2, 3));
		myList.add(new Point(4, 5));
		System.out.println(myList);

	}

}
