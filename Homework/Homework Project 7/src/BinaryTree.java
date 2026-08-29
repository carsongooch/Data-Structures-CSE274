import java.util.*;
/*
 * A BinaryTree class with an inner Node class. Contains lots of practice 
 * with recursive methods.
 * Many (but not all) of the methods in this class will require additional
 * recursive helper methods. The helper method would typically
 * have a Node as its only parameter or as one of its parameters.
 * 
 * Carson Gooch
 */

// Note that the following tags appear in the code:
//@formatter:off
//@formatter:on

// These tags tell the Eclipse formatter not to reformat these blocks. That
// helps make sure that the sample binary trees that appear in the comments
// will maintain their appearance.


/////////////////////////////////////////////////////////////////////////
// Unless noted otherwise, each method should be solved using recursion.
/////////////////////////////////////////////////////////////////////////

public class BinaryTree {

	public Node root; // LEAVE THIS PUBLIC TO AID WITH INSTRUCTOR TESTING

	// constructs an empty binary tree
	public BinaryTree() {
		root = null;
	}

	/*
	 * Feel free to modify this method however you want (or even write more methods
	 * like it) that will help you manually build trees for testing.
	 */
	public static BinaryTree myTree() {
		Node myRoot = new Node(4);
		myRoot.right = new Node(8);
		myRoot.right.left = new Node(12);

		BinaryTree result = new BinaryTree();
		result.root = myRoot;
		return result;
	}

	/*
	 * Returns the sum of the data in the nodes SOLVED IN FIRST VIDEO *****
	 */
	public int sum() {
		return sum(root);
	}
	
	private int sum(Node start) {
		if (start == null) return 0;
		
		return sum(start.left) + sum(start.right)+ start.data; 
	}

	/*
	 * Returns a count of the number of nodes in the tree
	 */
	public int nodeCount() {
		return nodeCount(root);
	}
	
	private int nodeCount(Node start) {
		if (start == null) return 0;
		
		return 1 + nodeCount(start.left) + nodeCount(start.right);
	}

	/*
	 * Returns the height of this binary tree. If the tree is empty, return -1
	 */
	public int height() {
		return height(root);
	}
	
	private int height(Node start) {
		if (start == null) return -1;
		if (start.left == null && start.right == null) return 0;
		
		int left = height(start.left);
		int right = height(start.right);
		return 1 + Math.max(left, right);
		
	}

	// @formatter:off
	/* We could say that a binary tree has "levels" with the root
	 * being at level 0, the root's children are at level 1, the
	 * root's grandchildren are at level 2, and so on. In this sample
	 * tree with height 2:
	 * there is 1 node at level 0
	 * there are 2 nodes at level 1
	 * there are 2 nodes at level 2
	 * 
	 *         5
	 *        / \
	 *       /   \
	 *      10   20
	 *     /  \    
	 *    30  40   
	 *  
	 *  Return the number of nodes at a level in a tree.
	 *  If the specified level is not a valid level for the tree (either
	 *  too big or too small), return 0.
	 */
	// @formatter:on
	public int nodesAtLevel(int level) {
		return nodesAtLevel(root, level);
	}
	
	private int nodesAtLevel(Node start, int level) {
		if (start == null) return 0;
		
		if (level == 0) return 1;
		
		return nodesAtLevel(start.left, level - 1) + nodesAtLevel(start.right, level - 1);
	}

	/*
	 * Returns max value in the tree. If the tree is empty, throws an
	 * IllegalStateException() SOLVED IN FIRST VIDEO *****
	 */
	public int max() { // client method
		if (root == null) {
			throw new IllegalStateException("Tree is empty");
		}
		return max(root);
	}
	
	// private helper max() method
	private int max(Node start) {
		if (start == null) return Integer.MIN_VALUE;
		
		int maxLeft = max(start.left);
		int maxRight = max(start.right);
		// return the largest of those 2 and the start data
		
		int maxResult = start.data;
		if (maxRight > maxResult) maxResult = maxRight;
		if (maxLeft > maxResult) maxResult = maxLeft;
		
		return maxResult;
	}

	/*
	 * Returns a count of how many times the specified value appears in the binary
	 * tree
	 */
	public int countValue(int n) {
		return countValue(root, n);
	}
	
	private int countValue(Node start, int n) {
		if (start == null) {
			return 0;
		}
		
		int count = 0;
		if (start.data == n) {
			count = 1;
		}
		
		return count + countValue(start.left, n) + countValue(start.right, n);
	}

	/*
	 * Returns true if this is a full binary tree, and false otherwise
	 */
	public boolean isFull() {
		return isFull(root);
	}
	
	private boolean isFull(Node start) {
		// a tree if full if both of its subtrees are full, and
		// _________ (something else)
		if (start == null) return false;
		
		if (start.right == null && start.left == null) {
			return true;
		}
		
		if (start.left == null || start.right == null) {
			return false;
		}
		
		int leftHeight = height(start.left);
		int rightHeight = height(start.right);
		
		if (leftHeight != rightHeight) {
			return false;
		}
		
		boolean leftIsFull = isFull(start.left);
		
		boolean rightIsFull = isFull(start.right);
		
		if (rightIsFull && leftIsFull) return true;
		
		return false;
	}

	/*
	 * Returns true if this is a balanced binary tree, and false otherwise
	 */ boolean isBalanced() {
		return isBalanced(root);
	}
	 
	 private boolean isBalanced(Node start) {
		 if (start == null) return true;
		 
		 int leftHeight = height(start.left);
		 int rightHeight = height(start.right);
		 
		 if (leftHeight > rightHeight + 1) {
			 return false;
		 }
		 
		 if (rightHeight > leftHeight + 1) {
			 return false;
		 }
		 
		 return (isBalanced(start.left) && isBalanced(start.right));
	 }

	/*
	 * returns the number of leaves in a tree SOLVED IN SECOND VIDEO *****
	 */
	public int leafCount() {
		return leafCount(root);
	}
	
	// recursive helper method
	private int leafCount(Node top) {
		if (top == null) return 0;
		if (top.left == null && top.right == null) {
			return 1;
		}
		return leafCount(top.left) + leafCount(top.right);
	}

	/*
	 * Returns the leftmost value of this tree. That is, navigate from the root
	 * following only left children until you can't travel left any farther, and
	 * return the value at that node. Throw an IllegalStateException if the tree is
	 * empty
	 */
	public int leftmostValue() {
	    /////////////////// Recursion optional
	    Node start = root;
	    
	    if (start == null) {
	    	throw new IllegalStateException();
	    }
	    
		while(start.left != null) {
			start = start.left;
		}
		return start.data;
	}

	/*
	 * Returns a set of all leaf values. SOLVED IN SECOND VIDEO *****
	 */
	public Set<Integer> allLeaves() {
		Set<Integer> result = new TreeSet<>();
		
		// Send the set to the recursive call so that the recursive 
		// method can put things in the set.
		allLeaves(root, result);
		
		return result;
	}
	
	public void allLeaves(Node top, Set<Integer> setSoFar) {
		
		if (top == null) return;
		else if (top.left == null & top.right == null) {
			setSoFar.add(top.data);
			return;
		}
		
		allLeaves(top.left, setSoFar);
		allLeaves(top.right, setSoFar);
	}

	/*
	 * Returns a set of all sums that can be obtained by starting at the root and
	 * traveling to each leaf. SOLVED IN SECOND VIDEO *****
	 */
	public Set<Integer> allSums() {
		Set<Integer> result = new TreeSet<>();
		
		allSums(root, result, 0);
		
		
		return result;
	}
	
	private void allSums(Node top, Set<Integer> setSoFar, int runningTotal) {
		if (top == null) return;
		
		runningTotal += top.data;
		
		if (top.left == null && top.right == null) { // reached a leaf
			setSoFar.add(runningTotal);
		}
		
		allSums(top.left, setSoFar, runningTotal);
		allSums(top.right, setSoFar, runningTotal);
	}

	// @formatter:off
	/*
	 * Returns a set of all possible paths from the root to the leaves. Similar
	 * to allSums, but rather than computing sums along the way, builds a string
	 * along the way, space-separated. 
	 * Trim the strings that get added to the sets. So, for example, in this tree:
	 *         5
	 *        / \
	 *       /   \
	 *      10   20
	 *     /  \    \
	 *    30  40   50
	 *  
	 *  allPaths() would return this set (or some permutation of this set):
	 *   {"5 10 30", "5 10 40", "5 20 50"}
	 *  TIP: allPaths() is a lot like allSums(). The difference
	 *  is that allSums is adding numbers to a total, and this
	 *  is adding numbers to a string.
	 */
	// @formatter:on
	
	public Set<String> allPaths() {
		Set<String> result = new TreeSet<>();
		
		allPaths(root, result, "");
		
		return result;
	}
	
	private void allPaths(Node start, Set<String> newResult, String runningPath) {
		if (start == null) return;
		
		runningPath += start.data + " ";
		if (start.left == null && start.right == null) { // reached a leaf
			newResult.add(runningPath.trim());
		}
		
		allPaths(start.left, newResult, runningPath);
		allPaths(start.right, newResult, runningPath);
		
	}

	// @formatter:off
	/*
	 * Returns a set of all possible sums that can be obtained
	 * in the tree by starting at the root and traveling any path.
	 * This is similar to allSums() (in fact, it will include all the sums
	 * that are returned by allSums(), but it also includes all sums that can
	 * be obtained by stopping part-way.  So, for example, in this tree:
	 *         5
	 *        / \
	 *       /   \
	 *      10   20
	 *     /  \    \
	 *    30  40   50
	 *  
	 *  allSumsIncludingPartial() would return {5, 15, 25, 45, 55, 75}
	 *  whereas allSums() would only return {45, 55, 75}
	 */
	// @formatter:on
	public Set<Integer> allSumsIncludingPartial() {
		Set<Integer> result = new TreeSet<>();
		allSumsIncludingPartial(root, result, 0);
		return result;
	}
	
	private void allSumsIncludingPartial(Node start, Set<Integer> setSoFar, int runningTotal) {
		if (start == null) return;
		
		runningTotal += start.data;
		setSoFar.add(runningTotal);
		
		allSumsIncludingPartial(start.left, setSoFar, runningTotal);
		allSumsIncludingPartial(start.right, setSoFar, runningTotal);
	}

	// @formatter:off
	/*
	 * Returns the sum obtained using a "greedy algorithm" by starting at the root
	 * and going left or right depending on which node has
	 * the larger value (in case of a tie, go left). 
	 * If the tree is empty, throw an IllegalStateException();
	 * 
	 * So, for example, in this tree:
	 *         5
	 *        / \
	 *       /   \
	 *      10   20
	 *     / \   / \
	 *    30 90 70 50
	 *  
	 *  greedySum() would return 95 (5 + 20 + 70).
	 */
	// @formatter:on
	public int greedySum() {
	    /////////////////// Recursion optional
		if (root == null) {
			throw new IllegalStateException("Empty tree");
		}
		
		Node start = root;
		
		int sum = start.data;

		while (start.left != null || start.right != null) {
			if (start.left != null && start.right == null) {
				start = start.left;
				sum += start.data;
			} else if (start.right != null && start.left == null) {
				start = start.right;
				sum += start.data;
			} else {
				if (start.left.data >= start.right.data) {
					start = start.left;
					sum += start.data;
				} else {
					start = start.right;
					sum += start.data;
				}
			}
		}
		
		return sum;
	}

	/*
	 * We will say that a node is an "only child node" if it has a parent node and
	 * it is the only child of that parent node. Return a count of how many nodes in
	 * the binary tree are "only child nodes". Note that by this definition, the
	 * root node is always an only child.
	 */
	public int onlyChildCount() {
		if (root == null) return 0;
		return 1 + onlyChildCount(root);
	}
	
	private int onlyChildCount(Node start) {
		if (start == null) return 0;
		
		int count = 0;
		
		if (start.left != null && start.right == null) {
			count = 1;
		}
		
		if (start.right != null && start.left == null) {
			count = 1;
		}
		
		return count + onlyChildCount(start.left) + onlyChildCount(start.right); 
	}

	// In-order traversal that PRINTS the nodes as they are visited,
	// space-separated
	// SOLVED IN FIRST VIDEO *****
	// THIS IS THE ONLY PROBLEM THAT IS NOT BEING GRADED (OR TESTED)
	public void inOrderTraversal() {
		inOrderTraversal(root);
		System.out.println();
	}
	
	// left, start, right
	private void inOrderTraversal(Node start) {
		if (start == null) return;
		
		inOrderTraversal(start.left);
		System.out.print(start.data + " ");
		inOrderTraversal(start.right);
	}

	// returns the the data in the tree as visited by the in-order
	// traversal. The data should be space-separated and trimmed.
	// if the tree is empty, return an empty string.
	public String toString() {
    	return toString(root).trim();
	}
	
	private String toString(Node start) {
    	if (start == null) return "";
    	return toString(start.left) + start.data + " " + toString(start.right);
	}

	/*
	 * Returns a list of all the node data in the tree, in the order that they would
	 * be visited in an in-order traversal. Solve this one recursively, building the
	 * list as you go (rather than printing the data). if the tree is empty, return
	 * an empty list.
	 */
	public ArrayList<Integer> inOrderTraversalPath() {
		ArrayList<Integer> result = new ArrayList<>();
		inOrderTraversalPath(root, result);
		return result;
	}
	
	private void inOrderTraversalPath(Node start, ArrayList<Integer> listSoFar) {
		if (start == null) return;
		
		inOrderTraversalPath(start.left, listSoFar);
		listSoFar.add(start.data);
		inOrderTraversalPath(start.right, listSoFar);
	}

	/*
	 * Returns a list of all the node data in the tree, in the order that they would
	 * be visited in a level-order traversal. Solve this one using a queue and a
	 * loop, rather than recursively. if the tree is empty, return an empty list.
	 */
	public ArrayList<Integer> levelOrderTraversalPath() {
	    /////////////////// Don't use recursion. Use a loop and a queue.
	    ArrayList<Integer> result = new ArrayList<>();
    	if (root == null) return result;
    	
    	Queue<Node> queue = new LinkedList<>();
    	queue.add(root);
    	while (!queue.isEmpty() ) {
    		Node curr = queue.remove();
    		result.add(curr.data);
    		
    		if (curr.left != null) {
    			queue.add(curr.left);
    		}
    		if (curr.right != null) {
    			queue.add(curr.right);
    		}
    	}
    	return result;
	}

	//// **** DON'T MODIFY THE METHODS BELOW ****
	//// These methods help build some trees for you.

	// Adds a node to this tree
	// Find the first open spot in my tree and that
	// is where I will put num in a new node.
	public void add(int num) {
		Node temp = new Node(num);

		if (root == null) { // empty tree? make root the new node
			root = temp;
			return;
		}

		// move through the tree using level-order traversal until
		// I find an open spot for the new node
		Queue<Node> q = new LinkedList<>();
		q.add(root);

		while (!q.isEmpty()) {
			Node first = q.remove();
			// see if the left or right is null. If so, put
			// the new node in that location.
			if (first.left == null) { // add new node to left
				first.left = temp;
				return;
			} else { // left is occupied, so try the right:
				if (first.right == null) {
					first.right = temp;
					return;
				} else { // first has two children
					q.add(first.left);
					q.add(first.right);
				}
			}
		}
	}

	public static BinaryTree sampleTree() {
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

		BinaryTree result = new BinaryTree();
		result.root = root;
		return result;
	}

	// Same structure as the sample tree, but with repeats
	// in the data
	public static BinaryTree manyRepeats() {
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

		BinaryTree result = new BinaryTree();
		result.root = root;
		return result;
	}

	// Builds a tree that only branches to the right
	public static BinaryTree rightsOnly(int n) {
		Node root = new Node(n);

		for (int i = n - 1; i >= 1; i--) {
			Node temp = new Node(i);
			temp.right = root;
			root = temp;
		}

		BinaryTree result = new BinaryTree();
		result.root = root;
		return result;
	}

	// Builds a tree that only branches to the right,
	// and all values are 50
	public static BinaryTree rightsOnlyAll50(int n) {
		Node root = new Node(50);

		for (int i = n - 1; i >= 1; i--) {
			Node temp = new Node(50);
			temp.right = root;
			root = temp;
		}

		BinaryTree result = new BinaryTree();
		result.root = root;
		return result;
	}

	// Builds a tree that only branches to the left
	public static BinaryTree leftsOnly(int n) {
		Node root = new Node(n);

		for (int i = n - 1; i >= 1; i--) {
			Node temp = new Node(i);
			temp.left = root;
			root = temp;
		}

		BinaryTree result = new BinaryTree();
		result.root = root;
		return result;
	}

	// A zig-zagging binary tree, with 1 at the top, 2 to the right of 1
	// 3 to the left of 2, etc.
	public static BinaryTree zigZag(int n) {
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

		BinaryTree result = new BinaryTree();
		result.root = root;
		return result;
	}

	// A complete binary tree, with level-order traversal
	// producing 1, 2, 3, 4, 5, 6, ... , n.
	// Assumes n >= 1
	public static BinaryTree completeTree(int n) {
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

		BinaryTree result = new BinaryTree();
		result.root = root;
		return result;
	}

	// A full tree is just a complete tree with the bottom level full.
	// fullTree(3) would give a complete tree with 15 nodes numbered
	// 1, 2, 3, 4, 5, ... , 15.
	public static BinaryTree fullTree(int height) {
		int n = (int) Math.pow(2, height + 1) - 1;

		return completeTree(n);
	}

	static class Node {
		int data;
		Node left, right;

		Node(int data) {
			this.data = data;
			this.left = null;
			this.right = null;
		}
	}
}