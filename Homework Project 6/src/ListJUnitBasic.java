import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ListJUnitBasic {
	// This does not test everything.
	// It tests SOME of your methods in your List implementation:
	// both add() methods and toArray(). It assumes that toStringNext() and
	// toStringPrev() are working correctly. It does NOT test adding null (which
	// is allowed).

	// It does not test Deque at all.

	@Test
	public void testAddString() {
		LinkedList<String> list = new LinkedList<>();

		String[] data = { "a", "b", "c", "d" };
		String result = "";
		for (int i = 0; i < data.length; i++) {
			result += data[i] + " ";
			list.add(data[i]);

			assertEquals(result + "(size=" + (i + 1) + ")", list.toStringNext());
			assertEquals(result + "(size=" + (i + 1) + ")", list.toStringPrev());
		}
	}

	@Test
	public void testAddIntString() {
		// empty list case, adding to position 0 both times
		LinkedList<String> list = new LinkedList<>();
		list.add(0, "a");
		assertEquals("a (size=1)", list.toStringNext());
		assertEquals("a (size=1)", list.toStringPrev());
		list.add(0, "b");
		assertEquals("b a (size=2)", list.toStringNext());
		assertEquals("b a (size=2)", list.toStringPrev());

		// empty list, followed by adding two items to end:
		list = new LinkedList<>();
		list.add(0, "a");
		assertEquals("a (size=1)", list.toStringNext());
		assertEquals("a (size=1)", list.toStringPrev());
		list.add(1, "b");
		assertEquals("a b (size=2)", list.toStringNext());
		assertEquals("a b (size=2)", list.toStringPrev());

		// add to beginning and then to end and then to middle
		list.add(0, "c");
		assertEquals("c a b (size=3)", list.toStringNext());
		assertEquals("c a b (size=3)", list.toStringPrev());
		list.add(3, "d");
		assertEquals("c a b d (size=4)", list.toStringNext());
		assertEquals("c a b d (size=4)", list.toStringPrev());
		list.add(2, "z");
		assertEquals("c a z b d (size=5)", list.toStringNext());
		assertEquals("c a z b d (size=5)", list.toStringPrev());
	}

	@Test
	public void testAddIntStringThrowsCorrectException() {
		// assumes add() is working
		LinkedList<String> list = new LinkedList<>();
		list.add("a");
		assertThrows(IndexOutOfBoundsException.class, () -> {
			list.add(-1, "a");
		});
		assertThrows(IndexOutOfBoundsException.class, () -> {
			list.add(2, "a");
		});

	}

	@Test
	public void testToArray() {
		// Assumes add() is working
		LinkedList<String> list = new LinkedList<>();
		assertArrayEquals(new String[0], list.toArray());

		list.add("a");
		assertArrayEquals(new String[] { "a" }, list.toArray());
		list.add("b");
		assertArrayEquals(new String[] { "a", "b" }, list.toArray());
	}

}
