/*
 * YOUR INFORMATION HERE:
 * Name:Carson Gooch
 * Practice working with the Node class we created.
 * 
 * RULES: Except for the toArray() method, you cannot create any other
 * collections. Don't create arrays or lists or sets or anything else like
 * that.
 */
public class LinkedNodeProblems {

	public static void main(String[] args) {
		// Do testing of your own here. It's still a reliable way
		// to test as you go. But once you think a method is
		// complete, check the JUnit tester to see if it passes.
		
		int[] arr = {4, 8, 15, 16, 23, 42};
		Node head = buildChain(arr);
		
		System.out.println(sumFirstTwo(head));
		
		head.next = new Node(4);
		head.next.next = new Node(2);
		
		System.out.println(dataToString(head)); // 4, 8, 15, 16, 23, 42
		head = removeFirst(head);
		System.out.println(dataToString(head)); // 8, 15, 16, 23, 42
		
//		System.out.println(contains(head, 4));
//		System.out.println(contains(head, 66));
//		System.out.println(contains(head, 42));
//		System.out.println(contains(head, 15));
		
		
		
		
		
		
		
		
		Node empty = null;
		System.out.println(dataToString(empty));
		
		Node oneToTen = null;
		
		// loop to create a chain of nodes 1, 2, 3 ... 10
		//but start with the 10 and work toward the 1
		for (int count = 10; count >= 1; count--) {
			Node temp = new Node(count);
			temp.next = oneToTen;
			oneToTen = temp;
			
		}
		
		System.out.println(dataToString(oneToTen));
		
		System.out.println(sumFirstTwo(oneToTen)); // 3
		System.out.println(sumFirstTwo(head)); // 5
		System.out.println(sumFirstTwo(new Node(99)));

		

	}
	
	/*
	 * Returns the sum of the numbers in the first two nodes in a non-empty
	 * chain of nodes. If there is only one node, then just return the number
	 * in that node.
	 */
	public static int sumFirstTwo(Node start) {
		
		if (start.next == null) {
			return start.data;
		}
		return start.data + start.next.data;
	}
	
	/*
	 * Returns the number of nodes in a chain of nodes beginning at the
	 * specified node.
	 */
	public static int length(Node start) {
		if (start == null) {
			return 0;
		}
		if (start.next == null) {
			return 1;
		}
		int counter = 1;
		while(start.next != null) {
			counter++;
			start = start.next;
		}
		return counter;
	}
	
	/*
	 * Returns the data in the last node of a non-empty chain of nodes
	 */
	public static int getLast(Node start) {
		while (start.next != null) {
			start = start.next;
		}
		return start.data;
	}
	
	/*
	 * Returns the sum of the numbers in the last two nodes in a possibly empty
	 * chain of nodes. If there is only one node, then just return the number
	 * in that node. If there are no nodes (start is null), return 0.
	 * 
	 * First, handle the non-loop cases where there are fewer than 2 nodes.
	 * Then, handle the looping, by getting a pointer to stop at the second-to-last 
	 * node. Once you arrive at that node, you can grab its data and the data of the
	 * last node.
	 */
	public static int sumLastTwo(Node start) {
		if (start == null) {
			return 0;
		} else if (start.next == null) {
			return start.data;
		} else if (start.next.next == null) {
			return start.data + start.next.data;
		}
		
		while(start.next.next != null) {
			start = start.next;
		}
		return start.data + start.next.data;
	}
	
	/*
	 * Returns a String containing all the data in a chain of nodes starting at the
	 * specified node. The data is space-separated, with no leading or trailing
	 * spaces. If the start node is null, return the string "EMPTY".
	 */
	public static String dataToString(Node start) {
		
		if (start == null) {
			return "EMPTY";
		}
		
		String result = "";
		
		Node curr = start;
		while (curr.next != null) {
			result += curr.data + " ";
			curr = curr.next;
		}
		result += curr.data;
		return result;
	}
	
	/*
	 * Creates a chain of nodes from the specified array of data, where the head
	 * node will contain the data at index 0. If the array is empty, returns null.
	 */
	public static Node buildChain(int[] data) {
		if (data.length == 0) {
			return null;
		}
		
		Node result = null;
		
		for (int i = data.length - 1; i >= 0; i--) {
			Node temp = new Node(data[i]);
			temp.next = result;
			result = temp;
		}
		return result;
	}
	
	/*
	 * Returns the sum of the data in a chain of nodes beginning at the
	 * specified node.
	 */
	public static int sum(Node start) {
		if (start == null) {
			return 0;
		}
		
		int result = 0;
		
		Node curr = start;
		while (curr.next != null) {
			result += curr.data;
			curr = curr.next;
		}
		result += curr.data;
		return result;
	}

	/*
	 * Modifies a non-empty chain of nodes by swapping the data in the first and
	 * last nodes. This does NOT rewire nodes but just changes the data. It's possible
	 * that the first node IS the last node.
	 */
	public static void swapFirstLast(Node start) {
		int temp = start.data;
		Node curr = start;
		while(curr.next != null) {
			curr = curr.next;
		}
		start.data = curr.data;
		curr.data = temp;
	}

	/*
	 * Returns true if the key appears as data in the chain of nodes beginning at
	 * the specified start node, and false otherwise.
	 */
	public static boolean contains(Node start, int key) {
		
		//Node curr = start;
		
		//non recursive way
		//loop checking nodes. Return true if we find it
		
		//while(curr != null) {
			//if (curr.data == key) {
				//return true;
			//}
			//curr = curr.next;
		//}
		//return false;
		
		//Recursive way
		if (start == null) return false;
		if (start.data == key) return true;
		return contains(start.next, key);
	}
		

	/*
	 * Returns the index of the first occurrence of key in the chain of nodes,
	 * beginning with the specified start node, or returns -1 if the key is not in
	 * the chain. For example, if the chain were: 3, 7, 9, 3 Then indexOf(start, 3)
	 * would return 0 indexOf(start, 9) would return 2 indexOf(start, 50) would
	 * return -1
	 */
	//do this one recursively
	public static int indexOf(Node start, int key) {
		if (start == null) return -1;
		if (start.data == key) return 0;
		if (indexOf(start.next, key) == -1) return -1;
		else return 1 + indexOf(start.next, key);
	}

	/*
	 * Returns the number of times that the key appears as data in the chain of
	 * nodes beginning at the specified start node.
	 */
	public static int getFrequency(Node start, int key) {
		if (start == null) {
			return 0;
		}
		if (start.next == null && start.data == key) {
			return 1;
		} else if (start.next == null) {
			return 0;
		}
		
		int count = 0;
		while (start.next != null) {
			if (start.data == key) {
				count++;
			}
			start = start.next;
		}
		if (start.data == key) {
			count++;
		}
		return count;
	}

	/*
	 * Appends the second chain of linked nodes to the end of the first chain of
	 * linked nodes. For example, if first were: 3, 4, 5, and second were 8, 4, 9,
	 * then at the end of this method, first will be 3, 4, 5, 8, 4, 9 Assume each
	 * chain contains at least one node.
	 */
	public static void append(Node first, Node second) {
		while (first.next != null) {
			first = first.next;
		}
		first.next = second;
	}

	/*
	 * Given a chain of nodes THAT COULD BE EMPTY, return true if there are two
	 * side-by-side nodes whose data add up to 10.
	 * For example:
	 * If the chain were 1, 7, 2, 8, 5 then return true (because 2 + 8 = 10)
	 * If the chain were 3, 10, 7 then return false
	 * If the chain were 3, 10, 0 then return true
	 * If the chain were 10 then return false
	 * If the chain were 9 then return false
	 * If the chain has no nodes then return false
	 */
	public static boolean has10TotalAdjacent(Node start) {
		if (start == null) {
			return false;
		}
		
		while (start.next != null) {
			int adder = 0;
			adder += start.data + start.next.data;
			if (adder ==  10) {
				return true;
			}
			start = start.next;
		}
		return false;
	}
	
	/*
	 * Given a chain of nodes, return true if there are two different
	 * nodes in the chain whose data add up to 10.
	 * For example:
	 * If the chain were 1, 7, 2, 5, 8 then return true (because 2 + 8 = 10)
	 * If the chain were 3, 10, 7 then return true
	 * If the chain were 3, 10 then return false
	 * If the chain were 5 then return false
	 * If the chain were 10 then return false
	 * If the chain were 5, 1, 5 then return true
	 * If the chain has no nodes then return false
	 */
	public static boolean has10TotalSomewhere(Node start) {
		Node curr1 = start;
		int num;
		
		if (start == null || start.next == null) {
			return false;
		}
		
		while(curr1 != null) {
			Node curr2 = curr1.next;
			num = curr1.data;
			while(curr2 != null) {
				num += curr2.data;
				if (num == 10) {
					return true;
				}
				num = curr1.data;
				curr2 = curr2.next;
			}
			curr1 = curr1.next;
		}
		return false;
	}
	
	/*
	 * Given a chain of nodes THAT COULD BE EMPTY, compute the sum of all values that appear
	 * immediately before a 10. For example: if the chain were 10, 7, 5, 10, 8, 10
	 * then return 13 (5 + 8) if the chain were 10, 10, 10, 10 then return 30 (10 +
	 * 10 + 10) if the chain were 3, 10 then return 3. If the chain were 10, 3 then return
	 * 0. If the chain were a single node or is empty, return 0.
	 */
	public static int sumDataBefore10(Node start) {
		if (start == null) {
			return 0;
		}
		
		int result = 0;
		while (start.next != null) {
			if (start.next.data == 10) {
				result += start.data;
			}
			start = start.next;
		}
		return result;
	}
	
	/*
	 * Creates an array containing the data in the chain of nodes,
	 * in the same order that it appears in the chain. If the start node
	 * is null, returns an empty array.
	 */
	public static int[] toArray(Node start) {
		if (start == null) {
			int[] empty = new int[0];
			return empty;
		}
		
		Node curr = start;
		int size = 0;
		while (curr != null) {
			size++;
			curr = curr.next;
		}
		int[] nodeArray = new int[size];
		for (int i = 0; i < size; i++) {
			nodeArray[i] = start.data;
			start = start.next;
		}
		return nodeArray;
	}
	
	/*
	 * Creates a chain of nodes consisting of only the even numbers from the
	 * given chain of nodes, and returns the starting node of the new chain.
	 * The order of the new nodes should be the same as their order in the
	 * original chain.
	 * Do not modify the original chain of nodes. Don't use arrays or any
	 * other collections. Just work with the given chain of nodes to build
	 * a new chain of nodes. 
	 */
	public static Node justTheEvens(Node start) {
		Node onlyEvens = null;
		Node curr = start;
		Node pointer = null;
		while(curr!= null) {
			if (curr.data % 2 == 0) {
				if (onlyEvens == null) {
					onlyEvens = new Node(curr.data);
					pointer = onlyEvens;
				} else {
					pointer.next = new Node(curr.data);
					pointer = pointer.next;
				}
			}
			curr = curr.next;
		}
		return onlyEvens;
	}
	
	/*
	 * Modifies a chain of nodes by inserting a duplicate of each
	 * node after that node. For example, if the chain were 1, 2, 3,
	 * then this method would change the chain to 1, 1, 2, 2, 3, 3
	 * The chain might be empty, in which case no change would be made.
	 */
	public static void duplicateChain(Node start) {
		Node curr = start;
		while(curr != null) {
			Node insert = new Node(curr.data);
			insert.next = curr.next;
			curr.next = insert;
			curr = insert.next;
		}
	}
	
	/*
	 * Modifies a chain by removing consecutive duplicate
	 * values. That is, if two or more consecutive node contain the
	 * same data, keep the first of those nodes and delete the subsequent nodes.
	 * Here are some examples:
	 * 1, 1, 2, 3, 5, 5, 5, 3 would become 1, 2, 3, 5, 3
	 * 8, 6, 7, 7, 7, 5, 7, 7, 3, 0, 9, 9 would become 8, 6, 7, 5, 7, 3, 0, 9
	 * 
	 */
	public static void removeDuplicates(Node start) {
		if (start == null) {
			return;
		}
		
		int num1;
		int num2;
		Node curr = start;
		while (curr.next != null) {
			num1 = curr.data;
			num2 = curr.next.data;
			if (num1 == num2) {
				curr.next = curr.next.next;
			} else {
				curr = curr.next;
			}
		}
	}
	
	/* ***************** NOTE.... ********************* */
	/*
	 * NOTE: the remaining methods could possibly cause the start node to 
	 * end up pointing to some other Node than when the method started.
	 * For that reason, this kind of method needs to return the start node reference
	 * at the end.
	 * Generally speaking when we are writing code that changes what the starting node points
	 * to, we will need to treat that in a special way. 
	 */
	
	/*
	 * Removes the first node of a chain of nodes. The chain of nodes might be empty
	 * or might only contain one node. In either of those cases, return null.
	 */
	public static Node removeFirst(Node start) {
		// We will modify the chain as needed, and then
		// return its head node.
		if(start == null) return null;
		start = start.next;
		return start;
	}
	
	/*
	 * Removes the last node of a chain of nodes. The chain of nodes might be empty
	 * or might only contain one node. In either of those cases, return null.
	 */
	public static Node removeLast(Node start) {
		if (start == null || start.next == null) {
			return null;
		}
		
		Node curr = start;
		while(curr.next.next!= null) {
			curr = curr.next;
		}
		curr.next = null;
		return start;
	}
	
	/*
	 * Removes the node containing the first occurrence of the specified key, if it
	 * exists. Otherwise, makes no changes. The method returns the head of the
	 * resulting chain of nodes. If the starting or ending chain of nodes is empty,
	 * return null.
	 */
	public static Node removeFirstOccurrence(Node start, int key) {
		if (start == null) {
			return null;
		}
		if (start.data == key) {
			return start = start.next;
		}
		
		Node curr = start;
		Node previous = start;
		curr = start.next;
		while(curr != null && curr.data != key) {
			curr = curr.next;
			previous = previous.next;
		}
		if (curr != null) {
			previous.next = curr.next;
		}
		return start;
	}
	
	/*
	 * Joins two chains by appending the nodes from the second chain to the
	 * end of the nodes from the first chain. One or both chains could be empty.
	 * Returns the start of the chain.
	 */
	public static Node joinChains(Node start1, Node start2) {
		if (start1 == null && start2 == null) {
			return null;
		}
		if (start1 == null) {
			return start2;
		}
		if (start2 == null) {
			return start1;
		}
		
		Node temp = start1;
		while(temp.next != null) {
			temp = temp.next;
		}
		
		temp.next = start2;
		return start1;
	}
}
