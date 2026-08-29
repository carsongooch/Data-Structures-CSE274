import java.util.EmptyStackException;

// Stack practice - Implement the methods in this class
// Name: YOUR_NAME_HERE

public class Tester {

	public static void main(String[] args) {
		// Do your testing here. Write test code BEFORE writing the method.
		
		Stack<Integer> stk = new ArrayStack<>();
		stk.push(4);
		stk.push(8);
		stk.push(15);
		System.out.println(stk);
		pushUnder(stk, 16);
		System.out.println(stk);
//		stk.push(4);
//		stk.push(8);
//		stk.push(15);
//		System.out.println(stk);
//		stk.peek();
	//	stk.clear();
//		System.out.println(stk);

	}
	
	/////////////////////////////////////////////////////////////////////////
	// Solve the methods below using the 5 standard Stack methods
	// push, pop, peek, isEmpty, and clear. Do not use any other collections.
	// You can create additional stacks to solve a problem, but you can't use
	// arrays or sets or lists or any other collections.
	/////////////////////////////////////////////////////////////////////////

	/*
	 * Puts an integer under the top item in a stack. If the stack is empty, just
	 * put the item on the top two times. For example: if stk starting at the top
	 * is: 7, 8, 5, 11, then pushUnder(stk, 20) would result in: 7, 8, 5, 20, 11 Or:
	 * if stk is empty, then pushUnder(stk, 20) would result in: 20, 20
	 */
	public static void pushUnder(Stack<Integer> data, int value) {
		
		Stack<Integer> stk = new ArrayStack();
		stk.push(4);
		stk.push(8);
		stk.push(15);
		
		System.out.println(stk);
		System.out.println(peekBottom(stk));
		System.out.println(stk);
	}

	/*
	 * Returns a count of how many even numbers are in the given stack of
	 * non-negative integers. It's ok to destroy the stack in the process.
	 */
	public static int countEvens(Stack<Integer> data) {
		// you can use the helper method I've written below, buildStack() to 
		// quickly create a stack of integers for testing:
		return -1;
	}

	/*
	 * Returns the bottom value of the stack. At the end of the method the stack
	 * should be in the same order as when it started. If the stack is empty,
	 * throw an EmptyStackException
	 */
	public static Integer peekBottom(Stack<Integer> stk) {
		// A place to hold the values so we can restore the stack when done
		Stack<Integer> temp = new ArrayStack<>();
		
		// move all items from stk to temp
		while (!stk.isEmpty()) {
			temp.push(stk.pop());
		}
		
		// stk is now empty
		// temp is stk flipped over
		Integer bottom = temp.peek();
		
		while (!temp.isEmpty()) {
			stk.push(temp.pop());
		}
		
		return bottom;
	}

	/*
	 * Returns an exact copy of the given stack. At the end of this method the
	 * original stack should be the same as when it started, and a new copy of that
	 * stack should be returned.
	 */
	public static Stack<Integer> duplicateStack(Stack<Integer> s) {
		return null;
	}

	// a helper method to build a stack of Integers quickly:
	// the first int value will be the bottom of the stack.
	// Notice that Java has this technique for creating methods
	// that take an arbitrary number of parameters.
	public static Stack<Integer> buildStack(int... items) {
		// items is now an array, and you can treat it like an
		// array
		Stack<Integer> stk = new ArrayStack<>();
		for (int n : items) {
			stk.push(n);
		}
		return stk;
	}

	// Runs some very basic tests on a stack implementation
	public static void quickCheck(Stack<Integer> stk) {
		System.out.println("true? " + stk.isEmpty());
		System.out.print("1 2 3 4 5 6 7 8 9 10 11? ");
		for (int i = 1; i <= 11; i++) {
			System.out.print(stk.push(i) + " ");
		}
		System.out.println();
		System.out.println("false? " + stk.isEmpty());

		System.out.println("11? " + stk.peek());
		System.out.println("11? " + stk.peek());

		System.out.println("11? " + stk.pop());
		System.out.println("10? " + stk.pop());

		stk.clear();
		System.out.println("true? " + stk.isEmpty());

		String message = "Exception not thrown correctly for peek().";
		try {
			stk.peek();
		} catch (EmptyStackException e) {
			message = "Correct exception thrown for peek(). Yay!";
		}

		System.out.println(message);

		message = "Exception not thrown correctly for pop().";
		try {
			stk.pop();
		} catch (EmptyStackException e) {
			message = "Correct exception thrown for pop(). Yay!";
		}

		System.out.println(message);
	}
}
