
public class MyTester {

	public static void main(String[] args) {
				
		Heap h = new Heap(10);
		System.out.println(h);
		
		// test add a few times
		h.add(7);
		System.out.println(h);
		
		
		
		// test remove a few times		
				
		
		// This is the same data from the heap handout.
		Integer[] data = { 25, 14, 8, 63, 27, 2, 9, 7, 4, 11 };
		
		h.fixHeapDown(2);
		
		// If you create an empty heap and then loop through the data calling
		// add(), you should get: 2 4 8 7 11 14 9 63 25 27
		// We called this Option 1 in the handout, 
		// which has time complexity O(n log n)
		
		
				
		
		// If you create a heap using the array constructor, and print the heap
		// then you should get: 2 4 8 7 11 25 9 14 63 27
		// We called this Option 2 in the handout, 
		// which has a better time complexity of O(n)
		
	}

}
