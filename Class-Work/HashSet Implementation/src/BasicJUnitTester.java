import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BasicJUnitTester {

	//////// THIS DOES NOT TEST EVERYTHING. SEE BELOW. \\\\\\\\\
	
	/*
	 * For the lab, this tester is enough.
	 * 1. LAB_TESTS must pass. 
	 */
	
	/*
	 * For the homework, this tester is NOT enough. 
	 * 1. LAB_TESTS must still pass (if it doesn't, your highest score on the homework 
	 *    would be 10 points out of 50 points).
	 * 2. All other tests in this JUnit test file must pass AND...
	 * 3. The rest of your HashSet methods must work (including those not tested here).
	 */
	
	@Test
	public void LAB_TESTS() {
		testIntConstructor();
		//testAdd();
		//testToString();
		testNoParamConstructor();
		//testContains();
		//testGetCurrentLoad();
		//testSize();
		//testIsEmpty();
		//testRemove();
		testIntConstructorExceptionHandling();
	}

	// makes sure your hash set is correctly created
	@Test
	public void testNoParamConstructor() {
		HashSet s = new HashSet();
		assertEquals(0, s.size());
		s.add("a");
	}

	// Tests adding but does not check whether resizing
	// happens.
	@Test
	public void testAdd() {
		HashSet s = new HashSet();

		assertTrue(s.add("A"));
		assertEquals(1, s.size());
		// duplicate
		assertFalse(s.add("A"));
		assertEquals(1, s.size());
		// these should both work
		assertTrue(s.add("C"));
		assertTrue(s.add("D"));
		assertEquals(3, s.size());
	}

	@Test
	public void testToString() {
		HashSet s = new HashSet();
		assertEquals("", s.toString(), "toString() should return empty string");

		String[] toBeAdded = { "1", "2", "3" };

		for (String str : toBeAdded) {
			s.add(str);
		}

		assertEquals("1 2 3", s.toString());
	}

	@Test
	public void testContains() {
		HashSet s = new HashSet();
		assertFalse(s.contains(new String("A")));
		s.add("A");
		assertTrue(s.contains(new String("A")));
		s.add("H");
		assertTrue(s.contains(new String("A")));
		assertTrue(s.contains(new String("H")));
		assertFalse(s.contains(new String("B")));
	}

	@Test
	public void testSize() {
		HashSet s = new HashSet();
		// size of an empty set
		assertEquals(0, s.size());
		// add 4 items
		for (int i = 1; i <= 4; i++) {
			s.add("" + i);
			assertEquals(i, s.size());
		}
	}

	// tests both an empty and non-empty set
	@Test
	public void testIsEmpty() {
		HashSet s = new HashSet();
		assertTrue(s.isEmpty());
		s.add("A");
		assertFalse(s.isEmpty());
	}

	// this isn't checking your exception handling
	@Test
	public void testIntConstructor() {
		HashSet s = new HashSet(5);
		assertEquals(0, s.size());
		s.add("a");
		assertEquals(1.0 / 5.0, s.getCurrentLoad(), 0.0001);
	}

	/*
	 * JUnit lets us test whether code throws exceptions correctly. In HashSet, we
	 * want the number of buckets to be a prime number. Creating a HashSet with a
	 * non- prime should throw an IllegalArgumentException. This test below will
	 * PASS if the correct exception is thrown.
	 */
	@Test
	public void testIntConstructorExceptionHandling() {
		assertThrows(IllegalArgumentException.class, () -> {
			new HashSet(10);
		});
	}

	/*
	 * Tests whether remove() is removing the first string it finds by traversing
	 * the array until it finds the first non-empty bucket
	 */
	@Test
	public void testRemove() {
		HashSet s = new HashSet();
		String[] toBeAdded = { "cat", "dog", "house", "mouse", "tree", "ant", "book" };

		for (String str : toBeAdded) {
			s.add(str);
		}

		String[] result = { "house", "dog", "book", "mouse", "ant", "tree", "cat" };
		for (int i = 0; i < result.length; i++) {
			assertEquals(result[i], s.remove());
			assertEquals(6 - i, s.size());
		}

		// Now set is empty. remove() should return null:
		assertNull(s.remove());
		assertEquals(0, s.size());
	}
	
	/*************************************************************
	 These remaining tests must pass for the homework (but not the
	 lab). 
	 AND you will have to do your own testing of any methods that
	 are not tested below.
	 ************************************************************/
	
	@Test
	public void testGetCurrentLoad() {
		// When comparing doubles for equality, we need
		// to allow for a certain amount of rounding error.
		double epsilon = 0.00001;

		HashSet s = new HashSet();
		assertEquals(0.0, s.getCurrentLoad(), epsilon);

		s.add("A");
		assertEquals(1.0 / 11.0, s.getCurrentLoad(), epsilon);
		s.add("B");
		assertEquals(2.0 / 11.0, s.getCurrentLoad(), epsilon);
		s.add("C");
		assertEquals(3.0 / 11.0, s.getCurrentLoad(), epsilon);
		s.add("D");
		assertEquals(4.0 / 11.0, s.getCurrentLoad(), epsilon);
		s.add("E");
		assertEquals(5.0 / 11.0, s.getCurrentLoad(), epsilon);
		s.add("F");
		assertEquals(6.0 / 11.0, s.getCurrentLoad(), epsilon);
		s.add("G");
		assertEquals(7.0 / 11.0, s.getCurrentLoad(), epsilon);
	}
	
	@Test
	public void testAddWithResize() {
		HashSet s = new HashSet();

		// These adds won't trigger resizing
		for (int i = 1; i <= 7; i++) {
			assertTrue(s.add("" + i));
			assertFalse(s.add("" + i));
			assertEquals(i, s.size());
			assertTrue(s.contains(new String("" + i)));
		}

		assertEquals("7 1 2 3 4 5 6", s.toString());
		assertEquals(7.0 / 11.0, s.getCurrentLoad(), 0.0001);

		// the load now exceeds the max load factor, and so
		// before we perform the next add, a resizing should
		// happen and then the item should be added.

		// This will trigger one resizing. Array length
		// should now be 23.
		String word = "pencil";
		assertTrue(s.add(word));
		assertFalse(s.add(word));
		assertEquals(8, s.size());
		assertTrue(s.contains(new String(word)));

		assertEquals("1 2 3 4 5 6 7 pencil", s.toString());
		assertEquals(8.0 / 23.0, s.getCurrentLoad(), 0.0001);

		// This will trigger two more resizings
		for (int i = 8; i <= 50; i++) {
			assertTrue(s.add("" + i));
			assertFalse(s.add("" + i));
			assertEquals(i + 1, s.size());
			assertTrue(s.contains(new String("" + i)));
		}

	}


	// Assumes toString() is working correctly
	@Test
	public void testRemoveString() {
		// This first part checks if remove(String) follows
		// the correct algorithm
		HashSet s = new HashSet();
		s.add("house");
		s.add("dog");
		s.add("cat");
		s.add("tree");
		s.add("chair");
		s.add("snap");
		
		assertEquals("house dog snap chair tree cat", s.toString(), "add or toString() is incorrect");
				
		s = new HashSet();

		for (int i = 1; i <= 8; i++) {
			s.add("" + i);
			assertEquals(i, s.size());
		}

		int size = s.size();

		// Remove the odds
		for (int i = 1; i <= 7; i += 2) {
			assertTrue(s.remove(new String("" + i)));
			size--;
			assertFalse(s.contains(new String("" + i)));
			assertEquals(size, s.size());
			assertFalse(s.remove(new String("" + i)));
			assertFalse(s.contains(new String("" + i)));
			assertEquals(size, s.size());
		}

		// Remove the evens, backwards
		for (int i = 8; i >= 2; i -= 2) {
			assertTrue(s.remove(new String("" + i)));
			size--;
			assertFalse(s.contains(new String("" + i)));
			assertEquals(size, s.size());
			assertFalse(s.remove(new String("" + i)));
			assertFalse(s.contains(new String("" + i)));
			assertEquals(size, s.size());
		}

		assertTrue(s.isEmpty());
		assertEquals(0, s.size());
		assertFalse(s.remove("2"));

		assertTrue(s.isEmpty());
		assertEquals(0, s.size());

		assertTrue(s.add("2"));
		assertTrue(s.remove("2"));

		assertTrue(s.isEmpty());
		assertEquals(0, s.size());
	}

	@Test
	public void testClear() {
		HashSet s = new HashSet();

		s.add("A");
		s.add("B");
		s.add("C");

		assertEquals(3, s.size());
		assertFalse(s.isEmpty());
		assertTrue(s.contains("A"));
		assertTrue(s.contains("B"));
		assertTrue(s.contains("C"));

		s.clear();
		assertEquals(0, s.size());
		assertTrue(s.isEmpty());
		assertFalse(s.contains("A"));
		assertFalse(s.contains("B"));
		assertFalse(s.contains("C"));
	}

	@Test
	public void testToArray() {
		HashSet s = new HashSet();
		assertArrayEquals(new String[0], s.toArray(), "empty set should give empty array");

		String[] toBeAdded = { "cat", "dog", "house", "mouse", "tree", "ant", "book" };
		String[] result1 = { "house", "dog", "book", "mouse", "ant", "tree", "cat" };

		for (String str : toBeAdded) {
			s.add(str);
		}

		assertArrayEquals(result1, s.toArray());
	}

	@Test
	public void testResizing() {
		HashSet s = new HashSet();
		String[] toBeAdded = { "cat", "dog", "house", "mouse", "tree", "ant", "book", "spaghetti" };
		String[] result2 = { "spaghetti", "tree", "ant", "cat", "dog", "mouse", "book", "house" };

		for (String str : toBeAdded) {
			s.add(str);
		}

		assertArrayEquals(result2, s.toArray());
	}
	
	@Test
	public void testAddAll() {
		 HashSet set = new HashSet();
	     String[] words = {"apple", "banana", "cherry"};
	     set.addAll(words);

	     assertEquals(3, set.size());
	     assertTrue(set.contains("apple"));
	     assertTrue(set.contains("banana"));
	     assertTrue(set.contains("cherry"));
	}
	
	@Test
	public void testMerge() {
	    HashSet set1 = new HashSet();
	    HashSet set2 = new HashSet();

	    set1.add("cat");
	    set1.add("dog");
	    set1.add("house");

	    set2.add("tree");
	    set2.add("dog");
	    set2.add("car");

	    set1.merge(set2);

	    assertEquals(5, set1.size());

	    assertTrue(set1.contains("cat"));
	    assertTrue(set1.contains("dog"));
	    assertTrue(set1.contains("house"));
	    assertTrue(set1.contains("tree"));
	    assertTrue(set1.contains("car"));
	}
	
	@Test
	public void testIntersection() {
	    HashSet set1 = new HashSet();
	    set1.add("cat");
	    set1.add("dog");
	    set1.add("tree");

	    HashSet set2 = new HashSet();
	    set2.add("dog");
	    set2.add("house");
	    set2.add("cat");

	    HashSet result = set1.intersection(set2);

	    assertEquals(2, result.size());
	    assertTrue(result.contains("cat"));
	    assertTrue(result.contains("dog"));
	    assertFalse(result.contains("tree"));
	    assertFalse(result.contains("house"));
	}
	
	 @Test
	 public void testExtractShortWordsBasic() {
		 HashSet set = new HashSet();
	     set.add("house");
	     set.add("cat");
	     set.add("tree");
	     set.add("computer");
	     set.add("minnow");

	     HashSet shortWords = set.extractShortWords();

	     assertTrue(shortWords.contains("cat"));
	     assertTrue(shortWords.contains("tree"));
	     assertEquals(2, shortWords.size());

	     assertFalse(set.contains("cat"));
	     assertFalse(set.contains("tree"));
	     assertEquals(3, set.size());
	    }
	 
	 @Test
	 void testEqualElements() {
		 HashSet set1 = new HashSet();
	     HashSet set2 = new HashSet();

	     assertTrue(set1.equalElements(set2), "Two empty sets should be equal");

	     set1.add("cat");
	     set1.add("dog");
	     set2.add("cat");
	     set2.add("dog");
	     assertTrue(set1.equalElements(set2), "Sets with same elements should be equal");
	 }
	 
	 @Test
	 public void testToUpperCase() {
	     HashSet s = new HashSet();
	     s.add("cat");
	     s.add("Dog");
	     s.add("HOUSE");
	     s.add("aBc123");

	     s.toUpperCase();

	     assertTrue(s.contains("CAT"));
	     assertTrue(s.contains("DOG"));
	     assertTrue(s.contains("HOUSE"));
	     assertTrue(s.contains("ABC123"));

	     assertEquals(4, s.size());
	     
	     String[] arr = s.toArray();
	     for (String str : arr) {
	         assertEquals(str, str.toUpperCase());
	     }
	 }

}
