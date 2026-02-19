/*

* implements a list using an underlying array

*/

public class ArrayList<T> implements list<T> {
	private T[] data;
	private int size;
	@SuppressWarnings("unchecked")
	public ArrayList() {

		this.data = (T[])(new Object[10]);

		this.size = 0;

	}

	@Override
	public void add(T item) {
// we would check if there is room in the array

		this.data[this.size] = item;

		this.size++;

	}

	@Override
	public void add(int index, T item) {
// TODO Auto-generated method stub

	}

	@Override
	public boolean remove(T item) {
// TODO Auto-generated method stub
		
		return false;

	}

	@Override
	public T remove(int index) {
// TODO Auto-generated method stub
		return null;

	}
	
	@Override
	public T get(int index) {

// TODO Auto-generated method stub
		return null;

	}

	@Override
	public void set(int index, T newItem) {
// TODO Auto-generated method stub

	}

	@Override
	public boolean contains(T item) {
// TODO Auto-generated method stub

		return false;

	}

	@Override

	public boolean isEmpty() {
// TODO Auto-generated method stub

		return false;

	}

	@Override

	public int size() {
// TODO Auto-generated method stub

		return this.size;
	}
	@Override
	public void clear() {
// TODO Auto-generated method stub
	}
	
	@Override
	public Object[] toArray() {
// TODO Auto-generated method stub
		return null;
	}

	public String toString() {
		String result = "";
		for (int i = 0; i < this.size; i++) {
			result += this.data[i] + " ";
		}
		return result.trim();

	}

}
