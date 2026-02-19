/**
 * A linked-node implementation of Set, with a private inner Node class. for
 * storing a set of String objects.
 * 
 * @author _______________
 */

public class LinkedSet {
	
	// We need exactly two instance variables
	// But first, we need a Node class.
	private Node head;
	private int size;

	/*
	 * Constructs an empty set.
	 */
	public LinkedSet() {
		this.head = null;
		this.size = 0;
	}
	
	// Returns true if the item was added, and false otherwise.
	// Should use the private helper method find(), and have no other loops
	// SLOPPY: allow duplicates at first, fix later
	public boolean add(String s) {
		//check for duplicates using find() helper method
		if (find(s) != null) {
			return false;
		}
		
		
		Node temp = new Node(s);
		temp.next = head;
		head = temp;
		
		size++;
		
		return true;
	}

	// Returns true if the item was removed, and false otherwise.
	// Example, if data starting at head is: a b c d e f
	// Then, remove("d") would change the data to: b c a e f
	// Should use the private helper method find(), and have no other loops
	public boolean remove(String s) {
		//see if s is in the set, and if so, grab the node 
		// that contains it:
		Node toDelete = find(s);
		if (toDelete == null) {
			//not found, nothing to remove
			return false;
		}
		
		//our algorithm for removing s will be to
		//copy the head data into the node that has the data
		// to delete, and then delete the head node:
		toDelete.data = head.data;
		head = head.next;
		size--;
		return true;
	}

	// Returns the removed String, or null if this set is empty
	// There should be no loop.
	public String remove() {
		// capture head data (if it exists) so that you can return it
		String deleted = "";
		if (head.next != null) {
			deleted = head.data;
			head = head.next;
			return deleted;
		} else {
			head = null;
		}
		return null;
	}

	// Returns true if the item is in this set, and false otherwise.
	// Should use the private helper method find(), and have no other loops
	public boolean contains(String s) {
		return false;
	}

	/*
	 * Returns the node containing the specified String, or null if it's not in the
	 * set. Very useful for add(s) and remove(s) and contains(s).
	 * (this is very much like the private indexOf() method we wrote for ArraySet).
	 * add(s) and contains(s) care IF the string is in the set.
	 * remove(s) cares WHERE the string is located, so it can remove that node. 
	 */
	private Node find(String s) {
		Node curr = head;
		
		while(curr != null) {
			if (curr.data.equals(s)) {
				return curr;
			}
			curr = curr.next;
		}
		//not found
		return null;
	}
	// Returns the size of this set
	public int size() {
		return this.size;
	}

	// Returns true if and only if this set is empty
	public boolean isEmpty() {
		return false;
	}

	// Empties the set. This should not have a loop (unlike ArraySet)
	public void clear() {
		this.head = null;
		size = 0;
	}

	/*
	 * Returns a space-separated trimmed list of the items in this set, in
	 * the order that the data appears in the underlying chain of nodes, beginning
	 * with the head node's data. If the set is empty, return "EMPTY"
	 */
	public String toString() {
		if (head == null) {
			return "EMPTY";
		}
		
		String result = "";
		
		Node curr = head;
		//for(int i = 0; i < this.size; i++) {
		while (curr != null) {
			result += curr.data + " ";
			curr = curr.next;
		}
		
		return result.trim();
	}
	
	/*
	 * Returns an array containing the data in this set, in
	 * the order that the data appears in the underlying chain of nodes, beginning
	 * with the head node's data. If the set is empty return an array with length 0.
	 */
	public String[] toArray() {
		return null;
	}

	// Typical inner node class.
	// The outer class is LinkedSet. The inner class is Node.
	// We make it private so that client code can't access nodes directly.
	// Even though the inner class is private, the outer class can access it.
	private class Node {
		private String data;
		private Node next;
		
		// standard constructor
		private Node(String data) {
			this.data = data;
			this.next = null;
		}
	}


} // end class