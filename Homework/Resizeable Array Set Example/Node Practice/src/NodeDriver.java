
public class NodeDriver {

	public static void main(String[] args) {
		
		Node n1 = new Node(20);
		Node n2= new Node(13, n1); // new node, 13 is the data, and it puts to n1
		
		System.out.println(n1.data);
		
		
		//Node n = new Node(8);
		//Node n2 = new Node(13);
		
		// add n2 as n's next node
		//n.next = n2;
		//n.next.next = new Node(5);
		
		//System.out.println(n.data);
		//System.out.println(n.next.data);
		//System.out.println(n.next.next.data);

	}

}
