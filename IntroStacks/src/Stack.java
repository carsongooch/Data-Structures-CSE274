public interface Stack<T> {
	/**
	 * pushes an item on the top of this stack	
	 * @param item the item to be pushed
	 * @return the item that was pushed
	 */
	public T push(T item);
	
	/**
	 * pops and returns the top item of this stack
	 * @return the item that was on the top of this stack
	 * @throws EmptyStackException if this stack is empty
	 */
	public T pop();
	
	/**
	 * returns the top item of this stack
	 * @return the item that is on the top of this stack
	 * @throws EmptyStackException if this stack is empty
	 */
	public T peek();
	
	/**
	 * returns whether this stack is empty
	 * @return true if this stack is empty, and false otherwise
	 */
	public boolean isEmpty();
	
	/**
	 * removes all items from this stack
	 */
	public void clear();	
}

