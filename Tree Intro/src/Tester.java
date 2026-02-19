
public class Tester {

	public static void main(String[] args) {
		
		System.out.println("Tree Basics----------------------");
		Node root = new Node(4);
	
		System.out.println(root.data);
		System.out.println(root.left);
		
		root.left = new Node(8); 
		System.out.println(root.left.data);
		
		root.left.right = new Node(15);
		System.out.println(root.left.right.data);
		
		System.out.println("SampleTree----------------------");
		sampleTree();
		
		Node sample = sampleTree();
		System.out.println("While Statment----------------------");
		// print all the data that we get by following the children randomly
		Node curr = sample;
		while (curr != null) {
			System.out.println(curr.data + " ");
			
			if (Math.random() < 0.5) {
				curr = curr.left;
			} else {
				curr = curr.right;
			}	
		}
		
		System.out.println("SumRootChildren----------------------");
		// test sum method
		System.out.println("30? " + sumRootChildren(sample));
		System.out.println("0? " + sumRootChildren(null));
		System.out.println("12? " + sumRootChildren(root));
		System.out.println("99? " + sumRootChildren(new Node(99)));
		sample.left = null;
		System.out.println("19? " + sumRootChildren(sample));
	}
	
	// returns the sample tree from slide 7
	public static Node sampleTree() {
		Node root = new Node(10);
		
		root.left = new Node(11);
		root.left.left = new Node(7);
		root.right = new Node(9);
		root.right.right = new Node(8);
		root.right.left = new Node(15);
		
		// Print all statements to see if its wired correctly
		System.out.println(root.data);
		System.out.println(root.left.data);
		System.out.println(root.left.left.data);
		System.out.println(root.right.data);
		System.out.println(root.right.right.data);
		System.out.println(root.right.left.data);

		return root;
	}
	
	// Return the sum of the data in the root node
	// and whatever child nodes it has. 
	public static int sumRootChildren(Node root) {
		int sum = 0;
		if (root == null) {
			return sum;
		}
		sum = sum + root.data;
		if (root.left != null) {
			sum += root.left.data;
		}
		
		if (root.right != null) {
			sum += root.right.data;
		}
		
		return sum;
	}
	
	

}