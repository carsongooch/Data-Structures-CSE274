import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class LinkedNodeProblemsTester {

	
	@Test
	void testSumFirstTwo() {
		int[] data = new int[] { 5, 17, 11, 10 };
		Node head = LinkedNodeProblems.buildChain(data);
		assertEquals(22, LinkedNodeProblems.sumFirstTwo(head));
		
		data = new int[] { 3, 5 };
		head = LinkedNodeProblems.buildChain(data);
		assertEquals(8, LinkedNodeProblems.sumFirstTwo(head));
		
		data = new int[] { 53 };
		head = LinkedNodeProblems.buildChain(data);
		assertEquals(53, LinkedNodeProblems.sumFirstTwo(head));
	}
	
	
	@Test
	void testBuildChain() {
		int[] data = { 5, 17, 11, 10 };
		Node head = LinkedNodeProblems.buildChain(data);

		Node curr = head;
		for (int n : data) {
			assertEquals(n, curr.data);
			curr = curr.next;
		}

		assertNull(curr);
		
		head = LinkedNodeProblems.buildChain(new int[0]);
		assertNull(head);		
	}
	
	@Test
	void testDuplicateChain() {
		int[] data = { 5, 17, 11, 10 };
		Node head = LinkedNodeProblems.buildChain(data);
		LinkedNodeProblems.duplicateChain(head);
		assertEquals("5 5 17 17 11 11 10 10", LinkedNodeProblems.dataToString(head));
		
		data = new int[]{ 3 };
		head = LinkedNodeProblems.buildChain(data);
		LinkedNodeProblems.duplicateChain(head);
		assertEquals("3 3", LinkedNodeProblems.dataToString(head));
		
		data = new int[]{ 5, 5 };
		head = LinkedNodeProblems.buildChain(data);
		LinkedNodeProblems.duplicateChain(head);
		assertEquals("5 5 5 5", LinkedNodeProblems.dataToString(head));
		
		data = new int[]{ 10, 4 };
		head = LinkedNodeProblems.buildChain(data);
		LinkedNodeProblems.duplicateChain(head);
		assertEquals("10 10 4 4", LinkedNodeProblems.dataToString(head));
		
		data = new int[]{ };
		head = LinkedNodeProblems.buildChain(data);
		LinkedNodeProblems.duplicateChain(head);
		assertEquals("EMPTY", LinkedNodeProblems.dataToString(head));	
	}
	
	@Test
	void testRemoveDuplicates() {
		int[] data = { 1, 1, 1 };
		Node head = LinkedNodeProblems.buildChain(data);
		LinkedNodeProblems.removeDuplicates(head);
		assertEquals("1", LinkedNodeProblems.dataToString(head));
		
		data = new int[]{ 1, 1, 2, 3, 5, 5, 5, 3 };
		head = LinkedNodeProblems.buildChain(data);
		LinkedNodeProblems.removeDuplicates(head);
		assertEquals("1 2 3 5 3", LinkedNodeProblems.dataToString(head));
		
		data = new int[]{ 1, 1, 2, 3, 5, 5, 5, 3, 3 };
		head = LinkedNodeProblems.buildChain(data);
		LinkedNodeProblems.removeDuplicates(head);
		assertEquals("1 2 3 5 3", LinkedNodeProblems.dataToString(head));
		
		data = new int[]{ 8 };
		head = LinkedNodeProblems.buildChain(data);
		LinkedNodeProblems.removeDuplicates(head);
		assertEquals("8", LinkedNodeProblems.dataToString(head));
		
		data = new int[]{  };
		head = LinkedNodeProblems.buildChain(data);
		LinkedNodeProblems.removeDuplicates(head);
		assertEquals("EMPTY", LinkedNodeProblems.dataToString(head));
		
		data = new int[]{ 8, 6, 4, 2 };
		head = LinkedNodeProblems.buildChain(data);
		LinkedNodeProblems.removeDuplicates(head);
		assertEquals("8 6 4 2", LinkedNodeProblems.dataToString(head));
		
		data = new int[]{ 8, 6, 6, 4, 4, 4, 4, 4, 4, 2 };
		head = LinkedNodeProblems.buildChain(data);
		LinkedNodeProblems.removeDuplicates(head);
		assertEquals("8 6 4 2", LinkedNodeProblems.dataToString(head));
		
	}

	@Test
	void testDataToString() {
		int[] data = { 5, 17, 11, 10 };
		Node head = LinkedNodeProblems.buildChain(data);

		assertEquals("5 17 11 10", LinkedNodeProblems.dataToString(head));

		data = new int[] { -9 };
		head = LinkedNodeProblems.buildChain(data);

		assertEquals("-9", LinkedNodeProblems.dataToString(head));
		
		assertEquals("EMPTY", LinkedNodeProblems.dataToString(null));
	}
	
	@Test
	void testSumLastTwo() {
		int[] data = new int[] { 5, 17, 11, 10 };
		Node head = LinkedNodeProblems.buildChain(data);
		assertEquals(21, LinkedNodeProblems.sumLastTwo(head));
		
		data = new int[] { 3, 5 };
		head = LinkedNodeProblems.buildChain(data);
		assertEquals(8, LinkedNodeProblems.sumLastTwo(head));
		
		data = new int[] { 53 };
		head = LinkedNodeProblems.buildChain(data);
		assertEquals(53, LinkedNodeProblems.sumLastTwo(head));
		
		data = new int[0];
		head = LinkedNodeProblems.buildChain(data);
		assertEquals(0, LinkedNodeProblems.sumLastTwo(head));
	}

	@Test
	void testLength() {
		int[] data = { 5, 17, 11, 10 };
		Node head = LinkedNodeProblems.buildChain(data);

		assertEquals(4, LinkedNodeProblems.length(head));
		assertEquals(3, LinkedNodeProblems.length(head.next));
		assertEquals(2, LinkedNodeProblems.length(head.next.next));
		assertEquals(1, LinkedNodeProblems.length(head.next.next.next));
		assertEquals(0, LinkedNodeProblems.length(null));
	}
	
	@Test
	void testSum() {
		int[] data = { 5, 17, 11, 10 };
		Node head = LinkedNodeProblems.buildChain(data);

		assertEquals(43, LinkedNodeProblems.sum(head));
		assertEquals(38, LinkedNodeProblems.sum(head.next));
		assertEquals(21, LinkedNodeProblems.sum(head.next.next));
		assertEquals(10, LinkedNodeProblems.sum(head.next.next.next));
		assertEquals(0, LinkedNodeProblems.sum(null));
	}

	@Test
	void testSwapFirstLast() {
		int[] data = { 5, 17, 11, 10 };
		Node head = LinkedNodeProblems.buildChain(data);
		LinkedNodeProblems.swapFirstLast(head);

		assertEquals("10 17 11 5", LinkedNodeProblems.dataToString(head));

		data = new int[] { 42 };
		head = LinkedNodeProblems.buildChain(data);
		LinkedNodeProblems.swapFirstLast(head);

		assertEquals("42", LinkedNodeProblems.dataToString(head));
	}

	@Test
	void testContains() {
		int[] data1 = { 3, 9, 2, 3, 3, 4, 5, 6, 8, 13 };
		int[] data2 = { 13 };
		Node head1 = LinkedNodeProblems.buildChain(data1);
		Node head2 = LinkedNodeProblems.buildChain(data2);

		assertTrue(LinkedNodeProblems.contains(head1, 3));
		assertTrue(LinkedNodeProblems.contains(head1, 13));
		assertTrue(LinkedNodeProblems.contains(head1, 4));
		assertTrue(LinkedNodeProblems.contains(head1, 2));
		assertFalse(LinkedNodeProblems.contains(head1, 99));
		
		assertTrue(LinkedNodeProblems.contains(head2, 13));
		assertFalse(LinkedNodeProblems.contains(head2, -13));
		assertFalse(LinkedNodeProblems.contains(null, -13));
	}
	
	@Test
	void testIndexOf() {
		int[] data1 = { 3, 9, 2, 3, 3, 4, 5, 6, 8, 13 };
		int[] data2 = { 13 };
		Node head1 = LinkedNodeProblems.buildChain(data1);
		Node head2 = LinkedNodeProblems.buildChain(data2);

		assertEquals(1, LinkedNodeProblems.indexOf(head1, 9));
		assertEquals(0, LinkedNodeProblems.indexOf(head1, 3));
		assertEquals(9, LinkedNodeProblems.indexOf(head1, 13));
		assertEquals(8, LinkedNodeProblems.indexOf(head1, 8));
		assertEquals(-1, LinkedNodeProblems.indexOf(head1, 20));
		
		assertEquals(-1, LinkedNodeProblems.indexOf(head2, -13));
		assertEquals(0, LinkedNodeProblems.indexOf(head2, 13));
		
		assertEquals(-1, LinkedNodeProblems.indexOf(null, 13));
	}
	
	@Test
	void testGetFrequency() {
		int[] data1 = { 3, 9, 2, 3, 3, 4, 5, 6, 3, 3 };
		int[] data2 = { -13 };
		Node head1 = LinkedNodeProblems.buildChain(data1);
		Node head2 = LinkedNodeProblems.buildChain(data2);

		assertEquals(1, LinkedNodeProblems.getFrequency(head1, 9));
		assertEquals(5, LinkedNodeProblems.getFrequency(head1, 3));
		assertEquals(0, LinkedNodeProblems.getFrequency(head1, 99));

		assertEquals(1, LinkedNodeProblems.getFrequency(head2, -13));
		assertEquals(0, LinkedNodeProblems.getFrequency(head2, 9));
		
		assertEquals(0, LinkedNodeProblems.getFrequency(null, 9));
	}

	@Test
	void testAppend() {
		int[] data1 = { 5, 6, 7 };
		int[] data2 = { 4, 3 };
		int[] data3 = { 6 };
		int[] data4 = { 15, 22, 11, 86 };
		int[] data5 = { 15, 22, 11, 86 };
		int[] data6 = { 6 };

		Node head1 = LinkedNodeProblems.buildChain(data1);
		Node head2 = LinkedNodeProblems.buildChain(data2);
		Node head3 = LinkedNodeProblems.buildChain(data3);
		Node head4 = LinkedNodeProblems.buildChain(data4);
		Node head5 = LinkedNodeProblems.buildChain(data5);
		Node head6 = LinkedNodeProblems.buildChain(data6);

		LinkedNodeProblems.append(head1, head2);
		LinkedNodeProblems.append(head3, head4);
		LinkedNodeProblems.append(head5, head6);

		assertEquals("5 6 7 4 3", LinkedNodeProblems.dataToString(head1));
		assertEquals("6 15 22 11 86", LinkedNodeProblems.dataToString(head3));
		assertEquals("15 22 11 86 6", LinkedNodeProblems.dataToString(head5));

	}

	@Test
	void testGetLast() {
		int[] data = { 5 };
		Node head = LinkedNodeProblems.buildChain(data);
		assertEquals(5, LinkedNodeProblems.getLast(head));

		data = new int[] { 8, 6, 7, 5, 3, 0, 9 };
		head = LinkedNodeProblems.buildChain(data);
		assertEquals(9, LinkedNodeProblems.getLast(head));
	}



	@Test
	void testRemoveFirst() {
		int[] data = { 8, 6, 7, 5, 3, 0, 9 };
		Node head = LinkedNodeProblems.buildChain(data);
		head = LinkedNodeProblems.removeFirst(head);
		assertEquals("6 7 5 3 0 9", LinkedNodeProblems.dataToString(head));
		head = LinkedNodeProblems.removeFirst(head);
		assertEquals("7 5 3 0 9", LinkedNodeProblems.dataToString(head));
		head = LinkedNodeProblems.removeFirst(head);
		assertEquals("5 3 0 9", LinkedNodeProblems.dataToString(head));
		head = LinkedNodeProblems.removeFirst(head);
		assertEquals("3 0 9", LinkedNodeProblems.dataToString(head));
		head = LinkedNodeProblems.removeFirst(head);
		assertEquals("0 9", LinkedNodeProblems.dataToString(head));
		head = LinkedNodeProblems.removeFirst(head);
		assertEquals("9", LinkedNodeProblems.dataToString(head));
		head = LinkedNodeProblems.removeFirst(head);
		assertEquals("EMPTY", LinkedNodeProblems.dataToString(head));
		head = LinkedNodeProblems.removeFirst(head);
		assertEquals("EMPTY", LinkedNodeProblems.dataToString(head));	
	}
	
	@Test
	void testRemoveLast() {
		int[] data = { 8, 6, 7, 5, 3, 0, 9 };
		Node head = LinkedNodeProblems.buildChain(data);
		
		head = LinkedNodeProblems.removeLast(head);
		assertEquals("8 6 7 5 3 0", LinkedNodeProblems.dataToString(head));
		head = LinkedNodeProblems.removeLast(head);
		assertEquals("8 6 7 5 3", LinkedNodeProblems.dataToString(head));
		head = LinkedNodeProblems.removeLast(head);
		assertEquals("8 6 7 5", LinkedNodeProblems.dataToString(head));
		head = LinkedNodeProblems.removeLast(head);
		assertEquals("8 6 7", LinkedNodeProblems.dataToString(head));
		head = LinkedNodeProblems.removeLast(head);
		assertEquals("8 6", LinkedNodeProblems.dataToString(head));
		head = LinkedNodeProblems.removeLast(head);
		assertEquals("8", LinkedNodeProblems.dataToString(head));
		head = LinkedNodeProblems.removeLast(head);
		assertEquals("EMPTY", LinkedNodeProblems.dataToString(head));
		head = LinkedNodeProblems.removeLast(head);
		assertEquals("EMPTY", LinkedNodeProblems.dataToString(head));
	}
	
	
	@Test
	void testJoinChains() {
		int[] data1 = new int[]{ 8, 6, 7, 5, 3, 0, 9 };
		int[] data2 = new int[]{10, 11};
		Node head1 = LinkedNodeProblems.buildChain(data1);
		Node head2 = LinkedNodeProblems.buildChain(data2);
		Node result = LinkedNodeProblems.joinChains(head1, head2);
		assertEquals("8 6 7 5 3 0 9 10 11", LinkedNodeProblems.dataToString(result));
		
		data1 = new int[]{10, 11, 12};
		data2 = new int[]{ 8, 6, 7, 5, 3, 0, 9 };
		head1 = LinkedNodeProblems.buildChain(data1);
		head2 = LinkedNodeProblems.buildChain(data2);
		result = LinkedNodeProblems.joinChains(head1, head2);
		assertEquals("10 11 12 8 6 7 5 3 0 9", LinkedNodeProblems.dataToString(result));
		
		data1 = new int[]{10};
		data2 = new int[]{ 8, 6, 7, 5, 3, 0, 9 };
		head1 = LinkedNodeProblems.buildChain(data1);
		head2 = LinkedNodeProblems.buildChain(data2);
		result = LinkedNodeProblems.joinChains(head1, head2);
		assertEquals("10 8 6 7 5 3 0 9", LinkedNodeProblems.dataToString(result));
		
		data1 = new int[]{};
		data2 = new int[]{ 8, 6, 7, 5, 3, 0, 9 };
		head1 = LinkedNodeProblems.buildChain(data1);
		head2 = LinkedNodeProblems.buildChain(data2);
		result = LinkedNodeProblems.joinChains(head1, head2);
		assertEquals("8 6 7 5 3 0 9", LinkedNodeProblems.dataToString(result));
		
		data1 = new int[]{10, 11, 12};
		data2 = new int[]{ 8};
		head1 = LinkedNodeProblems.buildChain(data1);
		head2 = LinkedNodeProblems.buildChain(data2);
		result = LinkedNodeProblems.joinChains(head1, head2);
		assertEquals("10 11 12 8", LinkedNodeProblems.dataToString(result));
		
		data1 = new int[]{10, 11, 12};
		data2 = new int[]{};
		head1 = LinkedNodeProblems.buildChain(data1);
		head2 = LinkedNodeProblems.buildChain(data2);
		result = LinkedNodeProblems.joinChains(head1, head2);
		assertEquals("10 11 12", LinkedNodeProblems.dataToString(result));
		
		data1 = new int[]{};
		data2 = new int[]{};
		head1 = LinkedNodeProblems.buildChain(data1);
		head2 = LinkedNodeProblems.buildChain(data2);
		result = LinkedNodeProblems.joinChains(head1, head2);
		assertEquals("EMPTY", LinkedNodeProblems.dataToString(result));
	}

	@Test
	void testHas10TotalAdjacent() {
		int[] data = { 8, 6, 7, 5, 3, 0, 9 };
		Node head = LinkedNodeProblems.buildChain(data);
		assertFalse(LinkedNodeProblems.has10TotalAdjacent(head));
		
		data = new int[] { 8, 2, 7, 5, 3, 0, 9 };
		head = LinkedNodeProblems.buildChain(data);
		assertTrue(LinkedNodeProblems.has10TotalAdjacent(head));
		
		data = new int[] { 8, 6, 7, 3, 5, 0, 9 };
		head = LinkedNodeProblems.buildChain(data);
		assertTrue(LinkedNodeProblems.has10TotalAdjacent(head));
		
		data = new int[] { 8, 6, 7, 5, 3, 1, 9 };
		head = LinkedNodeProblems.buildChain(data);
		assertTrue(LinkedNodeProblems.has10TotalAdjacent(head));
		
		data = new int[] { 11, -1 };
		head = LinkedNodeProblems.buildChain(data);
		assertTrue(LinkedNodeProblems.has10TotalAdjacent(head));
		
		data = new int[] { 11, 1 };
		head = LinkedNodeProblems.buildChain(data);
		assertFalse(LinkedNodeProblems.has10TotalAdjacent(head));
		
		data = new int[] { 8 };
		head = LinkedNodeProblems.buildChain(data);
		assertFalse(LinkedNodeProblems.has10TotalAdjacent(head));
		
		data = new int[] { 10 };
		head = LinkedNodeProblems.buildChain(data);
		assertFalse(LinkedNodeProblems.has10TotalAdjacent(head));
		
		data = new int[] { };
		head = LinkedNodeProblems.buildChain(data);
		assertFalse(LinkedNodeProblems.has10TotalAdjacent(head));	
	}
	
	@Test
	void testHas10TotalSomewhere() {
		int[] data = { 8, 6, 7, 5, 3, 0, 9 };
		Node head = LinkedNodeProblems.buildChain(data);
		assertTrue(LinkedNodeProblems.has10TotalSomewhere(head));
		
		data = new int[] { 8, 2, 1, 1, 1, 1};
		head = LinkedNodeProblems.buildChain(data);
		assertTrue(LinkedNodeProblems.has10TotalSomewhere(head));
		
		data = new int[] { 3, 4, 5, 0, 10 };
		head = LinkedNodeProblems.buildChain(data);
		assertTrue(LinkedNodeProblems.has10TotalSomewhere(head));
		
		data = new int[] { 2, 7, 6, 7, 6, 8 };
		head = LinkedNodeProblems.buildChain(data);
		assertTrue(LinkedNodeProblems.has10TotalSomewhere(head));
		
		data = new int[] { 7, 2, 6, 7, 6, 8 };
		head = LinkedNodeProblems.buildChain(data);
		assertTrue(LinkedNodeProblems.has10TotalSomewhere(head));
		
		data = new int[] { 11, -1 };
		head = LinkedNodeProblems.buildChain(data);
		assertTrue(LinkedNodeProblems.has10TotalSomewhere(head));
		
		data = new int[] { 11, 0, -1 };
		head = LinkedNodeProblems.buildChain(data);
		assertTrue(LinkedNodeProblems.has10TotalSomewhere(head));
		
		data = new int[] { 11, 0, 1 };
		head = LinkedNodeProblems.buildChain(data);
		assertFalse(LinkedNodeProblems.has10TotalSomewhere(head));
		
		data = new int[] { 3, 5, 8 };
		head = LinkedNodeProblems.buildChain(data);
		assertFalse(LinkedNodeProblems.has10TotalSomewhere(head));
		
		data = new int[] { 5, 3, 5 };
		head = LinkedNodeProblems.buildChain(data);
		assertTrue(LinkedNodeProblems.has10TotalSomewhere(head));
		
		data = new int[] { 11, 1 };
		head = LinkedNodeProblems.buildChain(data);
		assertFalse(LinkedNodeProblems.has10TotalSomewhere(head));
		
		data = new int[] { 8 };
		head = LinkedNodeProblems.buildChain(data);
		assertFalse(LinkedNodeProblems.has10TotalSomewhere(head));
		
		data = new int[] { 10 };
		head = LinkedNodeProblems.buildChain(data);
		assertFalse(LinkedNodeProblems.has10TotalSomewhere(head));
		
		data = new int[] { };
		head = LinkedNodeProblems.buildChain(data);
		assertFalse(LinkedNodeProblems.has10TotalSomewhere(head));	
	}

	@Test
	void testSumDataBefore10() {
		int[] data = { 8, 6, 7, 5, 3, 0, 9 };
		Node head = LinkedNodeProblems.buildChain(data);
		assertEquals(0, LinkedNodeProblems.sumDataBefore10(head));
		
		data = new int[] { 10 };
		head = LinkedNodeProblems.buildChain(data);
		assertEquals(0, LinkedNodeProblems.sumDataBefore10(head));
		
		data = new int[] { 1, 10, 2, 10, 3, 10 };
		head = LinkedNodeProblems.buildChain(data);
		assertEquals(6, LinkedNodeProblems.sumDataBefore10(head));
		
		data = new int[] { 10, 2, 10, 3, 10, 4 };
		head = LinkedNodeProblems.buildChain(data);
		assertEquals(5, LinkedNodeProblems.sumDataBefore10(head));
		
		data = new int[] { 10, 10, 10, 10, 10 };
		head = LinkedNodeProblems.buildChain(data);
		assertEquals(40, LinkedNodeProblems.sumDataBefore10(head));
		
		data = new int[] { 8, 10 };
		head = LinkedNodeProblems.buildChain(data);
		assertEquals(8, LinkedNodeProblems.sumDataBefore10(head));
		
		data = new int[] { 10, 8 };
		head = LinkedNodeProblems.buildChain(data);
		assertEquals(0, LinkedNodeProblems.sumDataBefore10(head));
		
		data = new int[] { 8 };
		head = LinkedNodeProblems.buildChain(data);
		assertEquals(0, LinkedNodeProblems.sumDataBefore10(head));
		
		data = new int[] { 10 };
		head = LinkedNodeProblems.buildChain(data);
		assertEquals(0, LinkedNodeProblems.sumDataBefore10(head));
		
		data = new int[] {  };
		head = LinkedNodeProblems.buildChain(data);
		assertEquals(0, LinkedNodeProblems.sumDataBefore10(head));
	}
	
	@Test
	void testToArray() {
		int[] data = { 5, 17, 11, 10 };
		Node head = LinkedNodeProblems.buildChain(data);
		assertArrayEquals(data, LinkedNodeProblems.toArray(head));

		data = new int[] { -9 };
		head = LinkedNodeProblems.buildChain(data);
		assertArrayEquals(data, LinkedNodeProblems.toArray(head));
		
		data = new int[] {  };
		head = LinkedNodeProblems.buildChain(data);
		assertArrayEquals(data, LinkedNodeProblems.toArray(head));
	}

	@Test
	void testRemoveFirstOccurrence() {
		int[] data = { 8, 6, 7, 5, 3, 0, 9, 7, 99 };
		Node head = LinkedNodeProblems.buildChain(data);
		head = LinkedNodeProblems.removeFirstOccurrence(head, 7);		
		assertEquals("8 6 5 3 0 9 7 99", LinkedNodeProblems.dataToString(head));
		
		head = LinkedNodeProblems.buildChain(data);
		head = LinkedNodeProblems.removeFirstOccurrence(head, 4);		
		assertEquals("8 6 7 5 3 0 9 7 99", LinkedNodeProblems.dataToString(head));
		
		head = LinkedNodeProblems.buildChain(data);
		head = LinkedNodeProblems.removeFirstOccurrence(head, 8);		
		assertEquals("6 7 5 3 0 9 7 99", LinkedNodeProblems.dataToString(head));
		
		head = LinkedNodeProblems.buildChain(data);
		head = LinkedNodeProblems.removeFirstOccurrence(head, 99);		
		assertEquals("8 6 7 5 3 0 9 7", LinkedNodeProblems.dataToString(head));
		
		data = new int[] { 7, 11 };
		head = LinkedNodeProblems.buildChain(data);
		head = LinkedNodeProblems.removeFirstOccurrence(head, 7);		
		assertEquals("11", LinkedNodeProblems.dataToString(head));
		
		head = LinkedNodeProblems.buildChain(data);
		head = LinkedNodeProblems.removeFirstOccurrence(head, 11);		
		assertEquals("7", LinkedNodeProblems.dataToString(head));
		
		head = LinkedNodeProblems.buildChain(data);
		head = LinkedNodeProblems.removeFirstOccurrence(head, 8);		
		assertEquals("7 11", LinkedNodeProblems.dataToString(head));
		
		data = new int[] { 8 };
		head = LinkedNodeProblems.buildChain(data);
		head = LinkedNodeProblems.removeFirstOccurrence(head, 8);		
		assertEquals("EMPTY", LinkedNodeProblems.dataToString(head));
		
		head = LinkedNodeProblems.removeFirstOccurrence(head, 8);		
		assertEquals("EMPTY", LinkedNodeProblems.dataToString(head));
	}
	
	@Test
	void testJustTheEvens() {
		int[] data = { 8, 6, 7, 5, 3, 0, 9, 7, 99, 4 };
		Node head = LinkedNodeProblems.buildChain(data);
		Node result = LinkedNodeProblems.justTheEvens(head);	
		assertEquals("8 6 7 5 3 0 9 7 99 4", LinkedNodeProblems.dataToString(head));
		assertEquals("8 6 0 4", LinkedNodeProblems.dataToString(result));	
		
		data = new int[] { 42 };
		head = LinkedNodeProblems.buildChain(data);
		result = LinkedNodeProblems.justTheEvens(head);	
		assertEquals("42", LinkedNodeProblems.dataToString(head));
		assertEquals("42", LinkedNodeProblems.dataToString(result));	
		
		data = new int[] { 43 };
		head = LinkedNodeProblems.buildChain(data);
		result = LinkedNodeProblems.justTheEvens(head);	
		assertEquals("43", LinkedNodeProblems.dataToString(head));
		assertEquals("EMPTY", LinkedNodeProblems.dataToString(result));	
		
		
		data = new int[] {  };
		head = LinkedNodeProblems.buildChain(data);
		result = LinkedNodeProblems.justTheEvens(head);	
		assertEquals("EMPTY", LinkedNodeProblems.dataToString(head));
		assertEquals("EMPTY", LinkedNodeProblems.dataToString(result));	
		
		
		data = new int[] {1, 3, 5, 7, 9};
		head = LinkedNodeProblems.buildChain(data);
		result = LinkedNodeProblems.justTheEvens(head);	
		assertEquals("1 3 5 7 9", LinkedNodeProblems.dataToString(head));
		assertEquals("EMPTY", LinkedNodeProblems.dataToString(result));	
	}

}
