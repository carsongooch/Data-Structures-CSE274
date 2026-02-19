

// A program I wrote to play around with the content learned 
// about trees in class.
// Date:11/4/2025
public class Tester {

	public static void main(String[] args) {
		Node root = new Node(3);
		System.out.println(root.data);
		buildTree();
		System.out.println("Sum: " + getSum(buildTree()));
		System.out.println("Sum of bigger side: " + biggerSide(buildTree()));
		loopTest(root);
	}
	
	
	public static Node buildTree() {
		int number = 0;
		Node root = new Node(0);
		for (int i = 0; i < 10; i++) {
			if (Math.random() > 0.5) {
				number += 4;
				root.left = new Node(number);
			} else {
				number += 5;
				root.right = new Node(number);
			}
		}
		return root;
	}
	
	
	public static int getSum(Node root) {
		int sum = 0;
		if (root == null) {
			return sum;
		}
		if (root.left != null) {
			sum += root.left.data;
		}
		if (root.right != null) {
			sum += root.right.data;
		}
		
		return sum;
	}
	
	public static int biggerSide(Node root) {
		if (root == null) {
			return 0;
		}
		
		int leftNum = 0;
		int rightNum = 0;
		
		if (root.left != null) {
			leftNum += root.left.data;
		}
		if (root.right != null) {
			rightNum += root.right.data;
		}
		if (rightNum > leftNum) {
			return rightNum;
		} else {
			return leftNum;
		}
	}
	
	public static void loopTest(Node root) {
		System.out.println("--------------------loopTest--------------------");
		if (root == null) {
			return;
		}
		Node curr = root;
		while (curr != null) {
			System.out.println(curr.data + " ");
			curr = curr.right;
		}
	}

}
