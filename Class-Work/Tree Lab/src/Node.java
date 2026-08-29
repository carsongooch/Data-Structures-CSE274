import java.util.*;

// We are using package-level access here to make it easy to 
// code in our tester
class Node {
	// These are our usual binary node instance variables
	int data;
	Node left, right;

	// Constructs a binary node with the specified data and
	// no children
	Node(int data) {
		this.data = data;
		this.left = null;
		this.right = null;
	}

	//////////////////////////////////////////////
	/*
	 * There are a variety of places we could write Node methods. In this case,
	 * we are putting them directly into the Node class. We could have written them
	 * in the Tester class instead, and the syntax would have been a little
	 * different.
	 * 
	 * Note that the methods below exist INSIDE a Node object. That means that the
	 * node could never be null. It's possible that its children are null though,
	 * and that's something to always be checking for.
	 */
	///////////////////////////////////////////////

	// Returns the sum of the data in this tree. Note that
	// because this method exists inside the Node class, it's
	// not possible for it to be null, but its children could be, and
	// so we need to watch for that.
//	int sum() {
//		int sum = data;
//		
//		if (left != null) sum += left.sum();
//		if (right != null) sum += right.sum();
//		System.out.println(data + " ");
//		
//		return sum;
//	}
//
//	// Returns the number of nodes in this tree. Solve recursively.
//	// Ask yourself: how is the number of nodes in the tree related
//	// to the number of nodes in each subtree?
//	int nodeCount() {
//		int count = 1; // For myself because I am a node
//		
//		if (left != null) count += left.nodeCount();
//		if (right != null) count += right.nodeCount();
//		
//		return count;
//	}
//
//	// Returns the largest value in this tree
//	// Write recursively.
//	// Ask yourself: if I know the max in the left subtree, and
//	// I know the max in the right subtree, and I know my own data,
//	// how can I figure out the max?
//	int max() {
//		return -1;
//	}
//
//	// Returns the height of this tree
//	// Write recursively.
//	// If you know the height of the left and right subtrees,
//	// how can you use those to find the height of this tree?
//	// A tip: Even though it doesn't make sense to talk about
//	// the height of a "null" subtree, it can be extremely
//	// convenient to think of that height as -1.
//	int height() {
//		return -1;
//	}
//
//	// Returns true if the key appears in this tree
//	boolean find(int key) {
//		return false;
//	}
//
//	// Prints the pre-order traversal of the tree rooted at this node
//	// Space separated. all on one line.
//	// Write recursively.
//	void preOrderTraversal() {
//		// me, left, right
//		System.out.print(data + " ");
//		
//		if (left != null) left.preOrderTraversal();
//		
//		if (right != null) right.preOrderTraversal();
//		
//	}
//
//	// Print all the nodes in the tree rooted at this node,
//	// using post-order traversal: left subtree, right subtree, then root data
//	// Write recursively.
//	void postOrderTraversal() {
//
//	}
//
//	// Prints the in-order traversal of the tree rooted at this node
//	// Space separated. all on one line
//	// Write recursively.
//	void inOrderTraversal() {
//
//	}
//
//	// Print all the nodes in the tree rooted at this node,
//	// using level-order traversal: top-to-bottom, left-to-right
//	// Use a queue and a loop to solve this. Recursion doesn't work
//	// well here.
//	void levelOrderTraversal() {
//
//	}
//
//	// Returns a space-delimited string containing the
//	// pre-order traversal, rather than printing it.
//	// Write recursively.
//	String preOrderTraversalString() {
//		return "";
//	}
}