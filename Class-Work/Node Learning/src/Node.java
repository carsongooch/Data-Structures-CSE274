/*
 * A typical node class that has 2 instance variable:
 * 1. the data (in this case, int)
 * 2. a reference/pointer to the next node
 */
public class Node {
	int data;
	Node next;
	
	public Node(int data) {
		this.data = data; 
		this.next = null; // initially does not point to a next node
	}
	
	// constructs a new node with the given data, and sets its next
	// reference to another node
	Node(int data, Node next){
		this.data = data;
		this.next = next;
	}

}
