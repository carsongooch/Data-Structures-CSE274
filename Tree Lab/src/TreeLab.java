import java.util.LinkedList;
import java.util.Queue;

public class TreeLab {

	public static void main(String[] args) {
		// Make a tree, and then call some of its methods.
		// You can make a tree manually, or call one of the methods below that
		// builds some useful trees.
		Node tree = new Node(3);
//		System.out.println("60? " + tree.sum()); // calls the sum in Node class
//		System.out.println("60? " + tree.nodeCount());
//		    Node tree = sampleTree();
//		    System.out.println(sum(tree));
//		    System.out.println(sum(rightsOnly(5)));
//		    System.out.println(sum(leftsOnly(5)));
//		    System.out.println(sum(zigZag(6)));
//		    System.out.println(sum(leftsOnly(1)));
//		    System.out.println(sum(null));
		System.out.println("True? " + contains(tree, 15));
		System.out.println("True? " + contains(tree, 10));
		System.out.println("True? " + contains(tree, 7));
		System.out.println("False? " + contains(tree, 100));
		
		levelOrderTraversal(sampleTree()); // 10 11 9 7 15 8
		levelOrderTraversal(null);
	}
	
	/*
	 * level-order traversal, solved non recursively using a queue to manage
	 * the nodes and their children
	 * 1. put the root in a queue (the node of it not the data)
	 * 2. Loop:
	 *		remove 
	 *		process
	 *		add children to queue
	 */
	public static void levelOrderTraversal(Node root) {
		Queue<Node> queue = new LinkedList<>();
		
		if (root == null) {
			return;
		}
		
		queue.add(root);
		while(!queue.isEmpty()) {
			Node front = queue.remove();
			System.out.println(front.data + " ");
			if (front.right != null) {
				queue.add(front.right);
			}
			if (front.left != null) {
				queue.add(front.left);
			}
		}
	}
	
	/*
	 *  Returns true if the given tree contains the given value
	 */
	public static boolean contains(Node root, int value) {
		if (root == null) {
			return false;
		}
		
		if (root.data == value) {
			return true;
		}
		
		if (contains(root.left, value)) {
			return true;
		}
		
		if (contains(root.right, value)) {
			return true;
		}
		
		return false;
	}
	
	// Computes the sum of all the nodes in the tree with the given root.
	// Visits all the nodes recursively. Note that the root could be null
	// and so our solution needs to take that into account.
	public static int sum(Node root) {
		
		if (root == null) return 0;
		
		// Formula for recursion I have in notes
		
	    return sum(root.left) + sum(root.right) + root.data; 
	}
	
	// These will build the various sample trees shown in these slides
	// https://docs.google.com/presentation/d/173QoVXFhamxOFbEvURe1iyYW9XPmbn99GoaHyjiwN-I

	// You should also be testing your code with edge cases.
	// For example, does the method work with:
	// a root with no children?
	// a root with a left child but not a right child?
	// a root with a right child but not a left child?
	
	// This builds the sample binary tree we built in class
	public static Node sampleTree() {
		Node root = new Node(10); // root

		// Build left subtree:
		Node leftST = new Node(11);
		leftST.left = new Node(7);

		// Build right subtree:
		Node rightST = new Node(9);
		rightST.left = new Node(15);
		rightST.right = new Node(8);

		root.left = leftST;
		root.right = rightST;

		return root;
	}

	// Same structure as the sample tree, but with repeats
	// in the data
	public static Node manyRepeats() {
		Node root = new Node(10); // root

		// Build left subtree:
		Node leftST = new Node(11);
		leftST.left = new Node(10);

		// Build right subtree:
		Node rightST = new Node(9);
		rightST.left = new Node(10);
		rightST.right = new Node(11);

		root.left = leftST;
		root.right = rightST;

		return root;
	}

	// Builds a tree that only branches to the right
	public static Node rightsOnly(int n) {
		Node root = new Node(n);

		for (int i = n - 1; i >= 1; i--) {
			Node temp = new Node(i);
			temp.right = root;
			root = temp;
		}

		return root;
	}

	// Builds a tree that only branches to the left
	public static Node leftsOnly(int n) {
		Node root = new Node(n);

		for (int i = n - 1; i >= 1; i--) {
			Node temp = new Node(i);
			temp.left = root;
			root = temp;
		}

		return root;
	}

	// A zig-zagging binary tree, with 1 at the top, 2 to the right of 1
	// 3 to the left of 2, etc.
	public static Node zigZag(int n) {
		Node root = new Node(1);
		Node curr = root;

		for (int i = 2; i <= n; i++) {
			Node temp = new Node(i);
			if (i % 2 == 0)
				curr.right = temp;
			else
				curr.left = temp;
			curr = temp;
		}

		return root;
	}

	// A complete binary tree, with level-order traversal
	// producing 1, 2, 3, 4, 5, 6, ... , n.
	// Assumes n >= 1
	public static Node completeTree(int n) {
		Node root = new Node(1);

		// Nodes get in line to have children attached
		Queue<Node> q = new LinkedList<>();
		q.add(root);

		int i = 2;

		while (i <= n) {
			Node temp = new Node(i);
			Node front = q.peek();
			if (front.left == null)
				front.left = temp;
			else {
				front.right = temp;
				q.remove();
				q.add(front.left);
				q.add(front.right);
			}
			i++;
		}

		return root;
	}

	// A full tree is just a complete tree with the bottom level full.
	// fullTree(3) would give a complete tree with 15 nodes numbered
	// 1, 2, 3, 4, 5, ... , 15.
	public static Node fullTree(int height) {
		int n = (int) Math.pow(2, height + 1) - 1;

		return completeTree(n);
	}
	
	public static boolean containsNonRecursive(Node root, int value) {
		if (root == null) {
			return false;
		}
		
		Queue<Node> queue = new LinkedList<>();
		queue.add(root);
	    
		while (!queue.isEmpty()) {
			Node front = queue.remove();
			if (front.data == value) {
				return true;
			}
			
			if (front.right != null) {
				queue.add(front.right);
			}
			
			if (front.left != null) {
				queue.add(front.left);
			}
		}
		return false;
	}
}
