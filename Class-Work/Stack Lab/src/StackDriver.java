import java.util.Stack;

/*
 * DO NOT USE any other data structures to solve these problems.
 * You may use additional stacks if needed, but may not use arrays or
 * any other data structures.
 * Although Java stacks have a lot of other methods, you are limited to
 * the following stack methods: push(), pop(), peek(), isEmpty(), and clear().
 * 
 * Unless a problem states otherwise, you may assume the stack is not null, but
 * might be empty. 
 * Unless a problem states otherwise, you may assume that any items in a given stack 
 * are not null.
 * 
 * In all of the the documentation shown below, the bottom of the stack is shown
 * on the left, and the top of the stack is shown on the right. 
 * So, [1, 2, 3] shows a stack with 1 at the bottom and 3 at the top.
 */

public class StackDriver {
	
	public static Stack<Integer> createStack(int ... items) {
		Stack<Integer> stk = new Stack<>();
		for (int v : items) {
			stk.push(v);
		}
		return stk;
	}
	public static boolean stacksEqual(Stack<Integer> stkA, Stack<Integer> stkB) {
		if (stkA == null && stkB == null)
			return true;
		else if (stkA == null || stkB == null)
			return false;
		
		return stkA.equals(stkB);
	}
	
	// coding begins
	
	public static void testDup() {
		System.out.print("dup: ");
		Stack<Integer> stk1 = createStack(1, 2, 3);
		dup(stk1);
		System.out.print(stacksEqual(stk1, createStack(1, 2, 3, 3)) ? "." : "*");
		
		Stack<Integer> stk2 = createStack();
		try {
			dup(stk2);
			System.out.print(stacksEqual(stk2, createStack()) ? "." : "*");
		}
		catch (IllegalArgumentException e) {
			System.out.print(".");
		}
		catch (Exception e) {
			System.out.print("*");			
		}

		System.out.println();
	}
	/**
	 * This will push an extra copy of the stack's top. So, if the stack contains
	 * [1, 2, 3], after leaving the method, the stack will be [1, 2, 3, 3]. If
	 * stk=[1], the stack should then be: [1,1] If the stack is empty, make no
	 * changes. If the stack is null, throw an IllegalArgumentException.
	 * 
	 * @param stk the Stack to process
	 */
	public static void dup(Stack<Integer> stk) {
		if (stk == null) {
			throw new IllegalArgumentException();
		}
		
		int top = stk.peek();
		if (stk.isEmpty()) {
			return;
		}
	
		stk.push(top);
	}

	
	public static void testHas3() {
		System.out.print("has3: ");
		Stack<Integer> stk1 = createStack(1, 2, 3, 4, 10);
		boolean result1 = has3(stk1);
		System.out.print(result1 == true && stacksEqual(stk1, createStack(1, 2, 3, 4, 10)) ? "." : "*");
		
		Stack<Integer> stk2 = createStack();
		boolean result2 = has3(stk2);
		System.out.print(result2 == false && stacksEqual(stk2, createStack()) ? "." : "*");
		
		Stack<Integer> stk3 = createStack();
		stk3 = null;
		System.out.print(result2 == false && stacksEqual(stk2, createStack()) ? "." : "*");
		
		Stack<Integer> stk4 = createStack(1, 2);
		System.out.print(result2 == false && stacksEqual(stk2, createStack()) ? "." : "*");

		System.out.println();
	}
	/**
	 * This will return true if the stack contains at least 3 elements, and false
	 * otherwise. At the end of the method, the contents of the stack should be the
	 * same as when the method began.
	 * 
	 * @param stk the Stack to process
	 * @return true if the stack contains at least 3 elements, and false otherwise
	 */
	public static boolean has3(Stack<Integer> stk) {
		Stack<Integer> temp = createStack();
		int count = 0;
		while (!stk.isEmpty()) {
			temp.push(stk.pop());
			count++;			
		}
		while (!temp.isEmpty()) {
			stk.push(temp.pop());
		}
		if (count >= 3) {
			return true;
		}
		return false;
	}

	
	
	public static void testExch() {
		System.out.print("exch: ");
		Stack<Integer> stk1 = createStack(1,2,3);
		exch(stk1);
		System.out.print(stacksEqual(stk1, createStack(1,3,2)) ? "." : "*");
		
		Stack<Integer> stk2 = null;
		try {
			exch(stk2);
			System.out.print("*");
		}
		catch (IllegalArgumentException e) {
			System.out.print(".");
		}
		catch (Exception e) {
			System.out.print("*");			
		}
		
		System.out.println();
	}
	/**
	 * This method swaps the top two elements of the stack. If the stack contains
	 * [1, 2, 3], then after leaving the method, the stack will look like [1, 3, 2].
	 * If the stack is null or does not contain at least two elements, throws an
	 * IllegalArgumentException
	 * 
	 * @param stk the Stack to process
	 * @throws IllegalArgumentException if stk is null or does not contain at least
	 *                                  two elements.
	 */
	public static void exch(Stack<Integer> stk) {
	}
	
	
	public static void testTop2AreSame() {
		System.out.print("top2AreSame: ");
		Stack<Integer> stk1 = createStack(1, 1, 2);
		top2AreSame(stk1);
		System.out.print(top2AreSame(stk1) && stacksEqual(stk1, createStack(1, 1, 2))? "*" : ".");
		
		System.out.println();
	}
	
	
	/**
	 * This method determines if the top two elements of the stack are the same.
	 * When leaving the method, the contents of the stack should be exactly as
	 * they were when entering the method. The stack [1, 1, 2, 3] should return false
	 * [1, 2, 3, 3] should return true.
	 * @param stk the Stack to process
	 * @throws IllegalArgumentException if stk is null or does not contain
	 * at least two elements.
	 */
	public static boolean top2AreSame(Stack<Integer> stk) {
		Stack<Integer> nullCheck = createStack();
		int count = 0;
		while(!stk.isEmpty()) {
			nullCheck.push(stk.pop());
			count++;
		}
		if (count > 2) {
			throw new IllegalArgumentException();
		}
		while(!nullCheck.isEmpty()) {
			stk.push(nullCheck.pop());
		}
		int num1 = stk.pop();
		int num2 = stk.pop();
		if (num1 == num2) {
			return true;
		}
		return false;
	}

	
	public static void testWeave() {
		System.out.print("weave: ");
		Stack<Integer> stkA = createStack(11, 12, 13);
		Stack<Integer> stkB = createStack(21, 22, 23);		
		Stack<Integer> res = weave(stkA, stkB);
		System.out.print(stacksEqual(res, createStack(13, 23, 12, 22, 11, 21)) &&
								stkA.isEmpty() && stkB.isEmpty() ? "." : "*");
		
		System.out.println();
	}

	/**
	 * This method accepts two stacks. A new stack is returned with the elements of
	 * the two individual stacks inserted into the new stack. The top element of the
	 * first stack is pushed, then the top element of the second stack. This process
	 * is repeated until one or both of the stacks are empty. If either of the
	 * stacks have remaining elements, they are then pushed onto the newly created
	 * stack. When done stk1 and stk2 should both be empty.
	 * 
	 * If stk1=[11, 12, 13] and stk2=[21, 22, 23], the result should be: 
	 * [13, 23, 12, 22, 11, 21]
	 * 
	 * If stk1=[11] and stk2=[21, 22, 23], the result should be: 
	 * [11, 23, 22, 21]
	 *
	 * @param stk1 the first stack
	 * @param stk2 the second stack
	 */

	public static Stack<Integer> weave(Stack<Integer> stk1, Stack<Integer> stk2) {
		return null;
	}	
	
	
	public static void testClearTo() {
		System.out.print("clearto: ");
		Stack<Integer> stkA = createStack(0,1,1,2,3);	
		clearto(stkA, 0);
		System.out.print(stacksEqual(stkA, createStack(0)) ? "." : "*");
		
		System.out.println();
	}
	/**
	 * This method removes elements from the top of the stack until it reaches a
	 * given value. For example, the stack [1,1,2,2,3,3,4,5] and the value 2, will
	 * change the stack to: [1,1,2,2]. If the value is not found in the stack, throw
	 * an IllegalArgumentException
	 * 
	 * @param stk the Stack to process
	 * @param v   the target value
	 * @throws IllegalArgumentException if stk does not contain the given value.
	 */	
	public static void clearto(Stack<Integer> stk, int v) {
	}
	
	public static void testSum() {
		System.out.print("sum: ");
		Stack<Integer> stk = createStack(1,2,3,4,5,6);	
		int result = sum(stk);
		System.out.print(result == 21 && stacksEqual(stk, createStack(1,2,3,4,5,6)) ? "." : "*");
	
		System.out.println();
	}
	/**
	 * This will return the sum of the items on the stack. At the end of the
	 * method, the stack should be returned to its original order. Throw an 
	 * IllegalArgumentException if the stack is null.
	 *
	 * @param stk the stack to be processed
	 * @return the sum of the items on the stack
	 * @throws IllegalArgumentException if stk is null
	 */
	public static int sum(Stack<Integer> stk) {
		return 0;
	}
	
	public static void testRollN() {
		System.out.print("rollN: ");
		Stack<Integer> stkA = createStack(1,2,3,4,5,6,7);	
		rollN(stkA, 3);
		System.out.print(stacksEqual(stkA, createStack(1,2,3,4,6,7,5)) ? "." : "*");
		
		System.out.println();
	}
	/**
	 * This will move the Nth item of the stack to the top of the stack. N will be
	 * at least 1. Example: [1,2,3,4,5,6,7], with N=3 --> [1,2,3,4,6,7,5]
	 * 
	 * @param stk the Stack to process
	 * @param N   the position of the item (as measured from the top of the stack).
	 *            N=1 would refer to the top item. N will be at least 1.
	 * @throws IllegalArgumentException if stk does not contain an Nth element
	 */
	public static void rollN(Stack<Integer> stk, int N) {
	}

	public static void testMark() {
		System.out.print("mark: ");
		Stack<Integer> stkA = createStack(3,2,3,4,5);	
		mark(stkA, 3, 99);
		System.out.print(stacksEqual(stkA, createStack(3,2,3,99,4,5)) ? "." : "*");
		
		System.out.println();
	}
	/**
	 * This method will find the first instance of a key-value, and insert a
	 * mark-value just above that first instance. For example, if the stack is
	 * [3,2,3,4,5], and keyValue=3, markValue=99 The stack would be changed to
	 * [3,2,3,99,4,5]. If the key-value is not found in the stack, no change is made
	 * to the stack.
	 * 
	 * @param stk
	 * @param keyValue  the value that should serve as the location where the mark
	 *                  should be inserted
	 * @param markValue the value to be inserted into stack just above the first
	 *                  instance of keyValue
	 */
	public static void mark(Stack<Integer> stk, int value, int mark) {
	}
	
	public static void testMaxTop() {
		System.out.print("maxTop: ");
		Stack<Integer> stkA = createStack(1,2,10);	
		Stack<Integer> stkB = createStack(3,4,5);	
		System.out.print(maxTop(stkA, stkB) == 10 ? "." : "*");
		
		System.out.println();
	}
	/**
	 * This method accepts two stacks. The method returns the maximum of the stack
	 * tops. If both stacks are empty, throw an IllegalArgumentException, but if
	 * only one of the stacks is empty, the maximum is the top of the other stack.
	 * The contents of the stacks should not be changed by these methods.
	 *
	 * @param stk1 the first Stack
	 * @param stk2 the second Stack
	 * @return the maximum of the values at the top of the stacksk
	 * @throws IllegalArgumentException if both stacks are empty.
	 */
	public static int maxTop(Stack<Integer> stkA, Stack<Integer> stkB) {
		return 0;
	}
	
	public static void testSameBottoms() {
		System.out.print("sameBottoms: ");
		Stack<Integer> stkA = createStack(0,1,1,2,3);	
		Stack<Integer> stkB = createStack(0,5,7);	
		System.out.print(sameBottoms(stkA, stkB) && stacksEqual(stkA, createStack(0,1,1,2,3))&&
								stacksEqual(stkB, createStack(0,5,7)) ? "." : "*");

		Stack<Integer> stkC = createStack();	
		Stack<Integer> stkD = createStack(0,5,3);	

		System.out.print(!sameBottoms(stkC, stkD) && stacksEqual(stkC, createStack())&&
				stacksEqual(stkD, createStack(0,5,3)) ? "." : "*");

		System.out.println();
	}
	/**
	 * Given two stacks, return true if the bottom value in each stack is the same,
	 * or if both stacks are empty. When leaving the method, the contents of both
	 * stacks should be the same as they were at the start of the method.
	 * 
	 * @param stk1 the first stack
	 * @param stk2 the second stack
	 * @return true if the bottom value in both stacks are the same, or if both
	 *         stacks are empty, and false otherwise
	 */
	
	public static boolean sameBottoms(Stack<Integer> stk1, Stack<Integer> stk2) {
		return false;
	}
	
	public static void main(String[] args) {
		testDup();
		testHas3();
		testExch();
		//testTop2AreSame();
		testWeave();
		testClearTo();
		testSum();
		testRollN();
		testMark();
		testMaxTop();
		testSameBottoms();
	}
}
