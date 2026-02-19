/*
 * list is order-based (with indexes), duplicates are allowed 
 */
public interface list<T> {
	
	public void add(T item);
	public void add(int index, T item);
	public boolean remove(T item);
	public T remove(int index);
	public T get(int index);
	public void set(int index, T newItem);
	public boolean contains(T item);
	public boolean isEmpty();
	public int size();
	public void clear();
	public Object[] toArray();
	
	
	
}
