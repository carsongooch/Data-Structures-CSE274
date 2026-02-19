import static org.junit.Assert.*;
import org.junit.Test;

public class HeapTest {

	@Test
	public void testEmptyHeap() {
		Heap h = new Heap();
		assertTrue(h.isEmpty());
		assertEquals(0, h.size());
		assertNull(h.peek());
		assertNull(h.remove());
	}

	@Test
	public void testAddSingleElement() {
		Heap h = new Heap();
		h.add(5);
		assertFalse(h.isEmpty());
		assertEquals(1, h.size());
		assertEquals(Integer.valueOf(5), h.peek());
	}

	@Test
	public void testAddMultipleElements() {
		Heap h = new Heap();
		h.add(5);
		h.add(3);
		h.add(7);
		h.add(1);
		
		assertEquals(4, h.size());
		assertEquals(Integer.valueOf(1), h.peek());
	}

	@Test
	public void testRemoveSingleElement() {
		Heap h = new Heap();
		h.add(5);
		assertEquals(Integer.valueOf(5), h.remove());
		assertTrue(h.isEmpty());
		assertEquals(0, h.size());
	}

	@Test
	public void testRemoveMultipleElements() {
		Heap h = new Heap();
		h.add(5);
		h.add(3);
		h.add(7);
		h.add(1);
		h.add(9);
		
		assertEquals(Integer.valueOf(1), h.remove());
		assertEquals(Integer.valueOf(3), h.remove());
		assertEquals(Integer.valueOf(5), h.remove());
		assertEquals(Integer.valueOf(7), h.remove());
		assertEquals(Integer.valueOf(9), h.remove());
		assertTrue(h.isEmpty());
	}

	@Test
	public void testRemoveInSortedOrder() {
		Heap h = new Heap();
		h.add(50);
		h.add(30);
		h.add(70);
		h.add(10);
		h.add(90);
		h.add(20);
		h.add(60);
		
		int prev = h.remove();
		while (!h.isEmpty()) {
			int curr = h.remove();
			assertTrue(prev <= curr);
			prev = curr;
		}
	}

	@Test
	public void testPeekDoesNotRemove() {
		Heap h = new Heap();
		h.add(5);
		h.add(3);
		h.add(7);
		
		assertEquals(Integer.valueOf(3), h.peek());
		assertEquals(3, h.size());
		assertEquals(Integer.valueOf(3), h.peek());
		assertEquals(3, h.size());
	}

	@Test
	public void testArrayConstructor() {
		Integer[] arr = {9, 5, 6, 2, 3, 7, 1, 4, 8};
		Heap h = new Heap(arr);
		
		assertEquals(9, h.size());
		assertEquals(Integer.valueOf(1), h.peek());
	}

	@Test
	public void testArrayConstructorRemoveAll() {
		Integer[] arr = {9, 5, 6, 2, 3, 7, 1, 4, 8};
		Heap h = new Heap(arr);
		
		assertEquals(Integer.valueOf(1), h.remove());
		assertEquals(Integer.valueOf(2), h.remove());
		assertEquals(Integer.valueOf(3), h.remove());
		assertEquals(Integer.valueOf(4), h.remove());
		assertEquals(Integer.valueOf(5), h.remove());
		assertEquals(Integer.valueOf(6), h.remove());
		assertEquals(Integer.valueOf(7), h.remove());
		assertEquals(Integer.valueOf(8), h.remove());
		assertEquals(Integer.valueOf(9), h.remove());
		assertTrue(h.isEmpty());
	}

	@Test
	public void testIndexOfLastNonLeaf() {
		Heap h = new Heap();
		assertEquals(-1, h.indexOfLastNonLeaf());
		
		h.add(1);
		assertEquals(-1, h.indexOfLastNonLeaf());
		
		h.add(2);
		assertEquals(1, h.indexOfLastNonLeaf());
		
		h.add(3);
		assertEquals(1, h.indexOfLastNonLeaf());
		
		h.add(4);
		assertEquals(2, h.indexOfLastNonLeaf());
		
		h.add(5);
		assertEquals(2, h.indexOfLastNonLeaf());
		
		h.add(6);
		assertEquals(3, h.indexOfLastNonLeaf());
	}

	@Test
	public void testDuplicateValues() {
		Heap h = new Heap();
		h.add(5);
		h.add(3);
		h.add(5);
		h.add(3);
		h.add(5);
		
		assertEquals(Integer.valueOf(3), h.remove());
		assertEquals(Integer.valueOf(3), h.remove());
		assertEquals(Integer.valueOf(5), h.remove());
		assertEquals(Integer.valueOf(5), h.remove());
		assertEquals(Integer.valueOf(5), h.remove());
	}

	@Test
	public void testLargeHeap() {
		Heap h = new Heap();
		for (int i = 100; i > 0; i--) {
			h.add(i);
		}
		
		assertEquals(100, h.size());
		
		for (int i = 1; i <= 100; i++) {
			assertEquals(Integer.valueOf(i), h.remove());
		}
		
		assertTrue(h.isEmpty());
	}

	@Test
	public void testCapacityExpansion() {
		Heap h = new Heap(3); // Small initial capacity
		for (int i = 0; i < 20; i++) {
			h.add(i);
		}
		
		assertEquals(20, h.size());
		assertEquals(Integer.valueOf(0), h.peek());
	}

	@Test
	public void testMixedOperations() {
		Heap h = new Heap();
		h.add(10);
		h.add(5);
		assertEquals(Integer.valueOf(5), h.remove());
		h.add(3);
		h.add(8);
		assertEquals(Integer.valueOf(3), h.peek());
		h.add(1);
		assertEquals(Integer.valueOf(1), h.remove());
		assertEquals(Integer.valueOf(3), h.remove());
		h.add(2);
		assertEquals(Integer.valueOf(2), h.remove());
		assertEquals(Integer.valueOf(8), h.remove());
		assertEquals(Integer.valueOf(10), h.remove());
	}

	@Test
	public void testNegativeNumbers() {
		Heap h = new Heap();
		h.add(-5);
		h.add(-10);
		h.add(-3);
		h.add(-1);
		
		assertEquals(Integer.valueOf(-10), h.remove());
		assertEquals(Integer.valueOf(-5), h.remove());
		assertEquals(Integer.valueOf(-3), h.remove());
		assertEquals(Integer.valueOf(-1), h.remove());
	}

	@Test
	public void testSingleElementArray() {
		Integer[] arr = {42};
		Heap h = new Heap(arr);
		
		assertEquals(1, h.size());
		assertEquals(Integer.valueOf(42), h.peek());
		assertEquals(Integer.valueOf(42), h.remove());
		assertTrue(h.isEmpty());
	}

	@Test
	public void testAlreadySortedArray() {
		Integer[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
		Heap h = new Heap(arr);
		
		for (int i = 1; i <= 9; i++) {
			assertEquals(Integer.valueOf(i), h.remove());
		}
	}

	@Test
	public void testReverseSortedArray() {
		Integer[] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1};
		Heap h = new Heap(arr);
		
		for (int i = 1; i <= 9; i++) {
			assertEquals(Integer.valueOf(i), h.remove());
		}
	}
}