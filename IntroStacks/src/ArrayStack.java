/*
 * Array-based implementation of a Stack
 */

import java.util.Arrays;
import java.util.EmptyStackException;

public class ArrayStack<T> implements Stack<T>{
	private int topIndex;
	private T[] data;
	
	/*
	 * constructs a new Stack with an underlying array length of 10
	 */
	@SuppressWarnings("unchecked")
	public ArrayStack() {
		this.topIndex = -1;
		this.data = (T[])(new Object[10]);
	}

	@Override
	public T push(T item) {
		if (topIndex == data.length - 1) {
			data = Arrays.copyOf(data, 2 * data.length);
		}
		data[topIndex + 1] = item;
		topIndex++;
		return item;
	}

	@Override
	public T pop() {
		// call peek to get the return value
		// remove that item from the stack
		T result = peek();
		
		data[topIndex] = null;
		topIndex--;
		
		return result;
	}
	
	// peek has a lot of the pop() logic. So write it first, and then
	// call it from pop()
	@Override
	public T peek() {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		return this.data[topIndex];
	}

	@Override
	public boolean isEmpty() {
		return topIndex == -1;
	}

	@Override
	public void clear() {
		for (int i = 0; i <= topIndex; i++) {
			data[i] = null;
		}
		topIndex = -1;
	}

	public String toString() {
		String result = "bottom [";
		
		for (int i = 0; i <= topIndex; i++) {
			result += data[i] + " ";
		}
		
		return result.trim() + "] top";
	}
}
