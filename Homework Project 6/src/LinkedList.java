import java.util.NoSuchElementException;
/**
 * An implementation of List that uses doubly linked nodes, with references to
 * the first and last nodes. The last node's .next is null, and the first node's
 * .prev is null.
 * 
 * Allows null to be stored as an element.
 * 
 * @param <T> the object type
 */
public class LinkedList<T> implements List<T>, Deque<T> {

	// Don't add to or change the names of these instance variables
	private Node firstNode;
	private Node lastNode;
	private int size;

	// Be sure to check the interfaces to see specific details
	// about what each of these methods does, especially with
	// respect to throwing exceptions.

	// WATCH FOR EDGE CASES! adding and removing may (or may not) need to handle
	// the cases where the list is empty or where the list has exactly one node.
	
	// You should write one add() method so that it makes use of the other
	// add() method.
	
	public boolean equalCheck(T a, T b) {
		if (a == null && b == null) {
			return true;
		}
		if (a == null || b == null) {
			return false;
		}
		return a.equals(b);
	}
	
	public void noSuchElementHelper() {
		if (size == 0) {
			throw new NoSuchElementException();
		}
	}
	
	
	@Override
	public void add(int index, T item) {
	    
	    if (index < 0 || index > size) {
	        throw new IndexOutOfBoundsException();
	    }
	    
	    Node temp = new Node(item);
	    
	    if (size == 0) {
	        firstNode = lastNode = temp;
	        size++;
	        return;
	    }
	    
	    if (index == 0) {
	        temp.next = firstNode;
	        firstNode.prev = temp;
	        firstNode = temp;
	        size++;
	        return;
	    }
	    
	    Node curr = firstNode;
	    for (int i = 0; i < index - 1; i++) {
	        curr = curr.next;
	    }
	    
	    temp.prev = curr;
	    temp.next = curr.next;
	    
	    if (curr.next != null) {
	        curr.next.prev = temp;
	    } else {
	        lastNode = temp;  // We're adding at the end
	    }
	    
	    curr.next = temp;
	    size++;
	}
	
	@Override
	public void add(T item) {
		add(size, item);
	}

	@Override
	public T remove(int index) {
	    
	    if (index < 0 || index >= size) {
	        throw new IndexOutOfBoundsException();
	    }
	    
	    Node toRemove;
	    
	    if (index == 0) {
	        toRemove = firstNode;
	        firstNode = firstNode.next;
	        if (firstNode != null) {
	            firstNode.prev = null;
	        } else {
	            lastNode = null;  // List is now empty
	        }
	    } else {
	        Node curr = firstNode;
	        for (int i = 0; i < index; i++) {
	            curr = curr.next;
	        }
	        toRemove = curr;
	        
	        curr.prev.next = curr.next;
	        if (curr.next != null) {
	            curr.next.prev = curr.prev;
	        } else {
	            lastNode = curr.prev;  // Removing last node
	        }
	    }
	    
	    size--;
	    return toRemove.data;
	}

	@Override
	public boolean remove(T item) {
		
		int index = indexOf(item);
		if (index == -1) {
			return false;
		}
		remove(index);
		return true;
	}
	
	@Override
	public T remove() {
		noSuchElementHelper();
		return remove(size - 1);
	}

	@Override
	public void clear() {
		firstNode = null;
		lastNode = null;
		size = 0;
	}

	@Override
	public T set(int index, T item) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		Node curr = firstNode;
		for (int i = 0; i < index; i++) {
			curr = curr.next;
		}
		
		T toBeReturned = curr.data;
		curr.data = item;
		return toBeReturned;
	}

	@Override
	public T get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		Node curr = firstNode;
		for (int i = 0; i < index; i++) {
			curr = curr.next;
		}
		return curr.data;
	}

	@Override
	public int indexOf(T item) {
		int index = 0;
		Node curr = firstNode;
		while (curr != null) {
			if (equalCheck(curr.data, item)) {
				return index;
			}
			curr = curr.next;
			index++;
		}
		return -1;
	}

	@Override
	public int lastIndexOf(T item) {
		int index = size - 1;
		Node curr = lastNode;
		while (curr != null) {
			if (equalCheck(curr.data, item)) {
				return index;
			}
			curr = curr.prev;
			index--;
		}
		return -1;
	}

	@Override
	public Object[] toArray() {		
		Object[] array = new Object[size];
		Node curr = firstNode;
		for (int i = 0; i < size; i++) {
			array[i] = curr.data;
			curr = curr.next;
		}
		return array;
	}

	@Override
	public boolean contains(T item) {
		if (indexOf(item) != -1) {
			return true;
		}
		return false;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return (size == 0);
	}

	//////////////////// Some toString() methods for helping you debug
	/*
	 * Things can go wrong when working with nodes, especially when those nodes are
	 * doubly-linked. These two methods are a simple way to check if things are
	 * still connected the way they should be. Check both of these regularly. They
	 * will give you clues on where things are broken. And the debugger will help
	 * you sort out details as well.
	 */

	// Don't change this.
	// toStringNext() builds the toString() by using the .next links.
	// This starts at the first node and moves forward. It should produce the EXACT
	// SAME string as toStringPrev(), but it relies on the .next links to build the
	// list. So, if something is broken, it could be a missing node or it could be
	// that a .next is missing or misdirected.
	public String toStringNext() {
		String result = "";
		Node curr = firstNode;

		while (curr != null) {
			result += curr.data + " ";
			curr = curr.next;
		}

		return result.trim() + " (size=" + size + ")";
	}

	// Don't change this.
	// toStringPrev() builds the toString() by using the .prev links.
	// This starts at the last node and moves backward. It should produce the EXACT
	// SAME string as toStringNext(), but it relies on the .prev links to build the
	// list. So, if something is broken, it could be a missing node or it could be
	// that a .prev is missing or misdirected.
	public String toStringPrev() {
		String result = "";
		Node curr = lastNode;

		while (curr != null) {
			result = " " + curr.data + result;
			curr = curr.prev;
		}

		return result.trim() + " (size=" + size + ")";
	}

	// Don't change this.
	@Override
	public String toString() {
		return toStringNext();
	}

	// Don't change this.
	// A doubly-linked node, where each node has a reference to both the next
	// node and the previous node.
	private class Node {
		private Node prev;
		private T data;
		private Node next;

		// Creates a new node with both its previous and next references set to null.
		private Node(T data) {
			this.prev = null;
			this.data = data;
			this.next = null;
		}

	}
	
	@Override
	public void addFront(T item) {
		add(0, item);
	}

	@Override
	public void addBack(T item) {
		add(size, item);
	}

	@Override
	public T removeFront() {
		return remove(0);
	}

	@Override
	public T removeBack() {
		return remove();
	}


	@Override
	public T peekFront() {
		noSuchElementHelper();
		return get(0);
	}


	@Override
	public T peekBack() {
		noSuchElementHelper();
		return get(size - 1);
	}
}
