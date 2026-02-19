// A node stores data and a link to another node.
class Node {
	
	int data;
	Node next;
	
	// constructs a node that "wraps" the data
	Node(int data) {
		this.data = data;
		this.next = null;
	}
}
