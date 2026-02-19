// Implements a set of int values using an underlying 
// binary search tree.
// Implement each of the unimplemented methods.
// Don't use arrays or any other Java collections, except for toArray()

import java.util.LinkedList;
import java.util.Queue;

public class TreeSet { // binary search tree with no duplicates,
	// no efforts to keep the tree balanced
	// unbalanced binary search trees = 0(n) for add, remove, contains
	// balanced BSTs = 0(log(n))

	// Don't create any additional instance variables
    private Node root;
    
    public TreeSet() {
        root = null;
    }
    
    // We'll solve iteratively and recursively
    // It's a set, so don't add duplicates.
    public void addWithLoop(int n) {
        // loop solution
    	if (root == null) {
    		root = new Node(n);
    		return;
    	}
    	
    	// tree has a non-null root
    	Node curr = root;
    	Node parent = null;
    	
    	// goal of this loop is to get parent and curr in place
    	// to add the new node
    	while (curr != null) {
    		parent = curr;
    		
    		// move curr left or right
    		if (n == curr.data) { // no duplicates allowed
    			return;
    		} 
    		
    		if (n > curr.data) {
    			curr = curr.left;
    		} else {
    			curr = curr.right;
    		}
    	}
    	
    	// now parent points to the node that will get 
    	// a new child
    	
    	// check parent data to decide which side gets the node
    	if (n > parent.data) {
    		parent.left = new Node(n);
    	} else {
    		parent.right = new Node(n);
    	}
    	
    }
    
    public void add(int n) {
    	root = add(root, n);
    }
    private Node add(Node start, int n) {
    	if (start == null) return new Node(n);
    	if (start.data == n) return start; // duplicate check
    	
    	if (n < start.data) {
    		start.left = add(start.left, n);
    	} else {
    		start.right = add(start.right, n);
    	}
    	
    	return start;
    	
    }
        
    // Returns the size of this set. 
    // It would be great if you had a size instance variable. But you don't
    // and you shouldn't create one.
    // Create a private recursive helper method.
    
	
    public int size() {
    	return size(root);
    }
    private int size(Node start) {
    	if (start == null) return 0;
    	return size(start.right) + size(start.left) + 1; 
    }
    
    // returns a string containing the items in this set in
    // sorted order (which happens naturally if you create the in-order
    // traversal of a BST). Use a recursive helper method.
    // Numbers should be wrapped in square brackets, and should be space-delimited.
    // Create a private recursive helper method
    // Examples:
    // [2 3 5]
    // [] (for an empty set)
    // A nice clean solution can be written by writing a private recursive
    // helper method containing 2 or 3 lines of code, and then putting just
    // one line of code in toString(). It's ok if it takes you a couple more lines,
    // but if you are writing a lot of code, you are probably overcomplicating
    // the problem.
    public String toString() {
    	return "[" + inOrderTraversal(root).trim() + "]";
    }
    private String inOrderTraversal(Node start) {
    	if (start == null) return "";
    	return inOrderTraversal(start.left) + start.data + " " + inOrderTraversal(start.right);
    }
    
    // Since this is a binary SEARCH tree, you should write
    // an efficient solution to this that takes advantage of the order
    // of the nodes in a BST.  Your algorithm should be, on average,
    // O(h) where h is the height of the tree.
    // IN OTHER WORDS: if the root doesn't contain the value n, then
    // figure out which of the two subtrees you should check next (don't check both)
    // solve recursively or with a loop
    public boolean contains(int n) {
    	//use > < to determine if we need to go right or left 
    	//if number is not in tree keep going until we hit null
    	
    	if (root == null) {
    		return false;
    	}
    	Node curr = root;
    	while (curr != null) {
    		if (n == curr.data) {
    			return true;
    		}
    		if (n < curr.data) {
    			curr = curr.left;
    		} else {
    			curr = curr.right;
    		}
    	}
        return false;
    }
    
    // returns the smallest value in the set.
    // If the tree is empty, throw an IllegalStateException()
    // Your solution should take advantage of this being
    // a binary search tree. In other words, you should
    // write an O(h) solution, where h is the height of the tree. 
    // Ask yourself: in a BST, what series of moves will always get me 
    // to the smallest value?
    // solve recursively or with a loop
    public int min() {
    	if (root == null) {
    		throw new IllegalStateException();
    	}
    	Node curr = root;
    	while (curr.left != null) {
    		curr = curr.left;
    	}
    	return curr.data;
    }
    
    // returns the smallest value of all the leaf nodes
    // If the tree is empty, throw an IllegalStateException()
    // Spend a little time thinking about how you get to the smallest
    // leaf when you start at the root.
    // solve recursively or with a loop
    public int minLeaf() {
    	if (root == null) {
    		throw new IllegalStateException();
    	}
    	return minleaf(root);
    }
    
    public int minleaf(Node passedRoot) {
    	if (passedRoot.left == null && passedRoot.right == null) {
    		return passedRoot.data;
    	}
    	int minLeft = Integer.MAX_VALUE;
    	int minRight = Integer.MAX_VALUE;
    	
    	if (passedRoot.left != null) {
    		minLeft = minleaf(passedRoot.left);
    	}
    	if (passedRoot.right != null) {
    		minRight = minleaf(passedRoot.right);
    	}
    	if (minLeft < minRight) {
    		return minLeft;
    	} else {
    		return minRight;
    	}
    }

    // returns an array of the data in level-order (so, use
    // a queue and a loop)
    // If set is empty, return an array of length 0
    public int[] toArray() {
    	if (root == null) {
    		return new int[0];
    	}
    	int size = size();
    	int[] toBeReturned = new int[size];
    	int index = 0;
    	
    	Queue<Node> queue = new LinkedList<>();
    	queue.add(root);
    	while (!queue.isEmpty() ) {
    		Node curr = queue.remove();
    		toBeReturned[index] = curr.data;
    		index++;
    		
    		if (curr.left != null) {
    			queue.add(curr.left);
    		}
    		if (curr.right != null) {
    			queue.add(curr.right);
    		}
    	}
    	return toBeReturned;
    }
     
    // Don't modify this standard Binary Node definition.
    private class Node {
        private int data;
        private Node left, right;
        
        private Node(int data) {
            this.data = data;
            left = right = null;
        }
    }
}
