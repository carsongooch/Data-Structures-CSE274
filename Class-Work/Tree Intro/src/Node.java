/*
 * A node in a binary tree, so it can have two 
 * child nodes called left and right.
 */
public class Node {
	int data;
	Node left; // point to left child node
	Node right; // point to right child node
	
	Node(int data) {
		this.data = data; 
		this.left = null;
		this.right = null;
	}
}
