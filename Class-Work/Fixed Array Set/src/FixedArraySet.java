import java.util.Arrays;

/**
 * A fixed-capacity array-based implementation 
 * of a set of strings. It will require loops
 * for adding, removing, and searching for a string.
 * Does not necessarily maintain order of elements 
 */
public class FixedArraySet {
	
	// our only two instance variables. 
	private String[] data;
	private int size;
	
	// static constants allow us to avoid putting "mystery
	// numbers" in our code.
	public static final int DEFAULT_CAPACITY = 10;
	
	/**
	 * Constructs a new set with no elements, and 
	 * a maximum size specified by the client.
	 * @param capacity maximum number of elements 
	 */
	public FixedArraySet(int capacity) {
		this.data = new String[capacity]; // all elements are null
		this.size = 0;
	}
	
	/**
	 * Constructs a new set with a default capacity.
	 */
	public FixedArraySet() {
		this(DEFAULT_CAPACITY); //passes to the int-parameter constructor
	}
	
	
	/**
	 * Adds a specified string if there is room and it
	 * is not already in the set
	 * @param s the string to be added
	 * @return true if s is successfully added, false otherwise
	 */
	public boolean add(String s) {
		if (this.size == data.length) {
			return false;
		}
		
		// need to check for duplicates
		if (indexOf(s) != -1) {
			return false;
		}
		
		data[size] = s;
		size++;
		return true;
	}
	
	// this helper method will assist add(String), remove(String)
	// and contains(String) by looping to find the item.
	// returns the index of a String in the underlying array.
	// or -1 if it's not in the array:
	private int indexOf(String s) {
		for (int i = 0; i < this.size; i++) {
			if (s.equals(this.data[i])) {
				return i;
			}
		}
		// if we make it to this part of the method,
		// we didn't find it.
		return -1;
	}
	
	/**
	 * Returns the size of this set
	 * @return the size of this set
	 */
	public int size() {
		return this.size;
	}
	
	/**
	 * Returns whether this set is empty
	 * @return true if this set is empty, and false otherwise
	 */
	public boolean isEmpty() {
		//evaluates the boolean expression
		return size == 0;
	}
	
	
	/*
	 * returns a space-seperated list of the items
	 * in the set 
	 */
	public String toString() {
		String result = "";
		
		// loop through the items in the array
		// as determined by the size of the set
		for (int i = 0; i < this.size; i++) {
			result += data[i] + " ";
		}
		
		return result;		
	}
	/*
	 *returns true or false based
	 *on if the method removed the
	 *last item in the set.
	 */
	public boolean remove() {
		if (this.size != 0) {
			this.data[size - 1] = null;
			size -= 1;
			return true;
		} else {
			return false;
		}

	}
	
	/*
	 * returns true or false if
	 * the method removed specified
	 * index.
	 */
	public boolean remove(String s) {
		// before removing we need to know 
		// WHERE the item is in the set
		int index = indexOf(s);
		if(index != -1) {
			//item is in the set
			this.data[index] = this.data[size - 1];
			this.data[size - 1] = null;
			size -= 1;
			return true;
		} else { 
			//item is not in the set
			return false;
		}
	}
	
	/*
	 * returns true or false if
	 * the method detects a specified
	 * item in the set.
	 */
	public boolean contains(String s) {
		// need to know if the item is in the set
		if (indexOf(s) != -1) {
			return true;
		}
		return false;
	}
	/*
	 * Returns a variable with each 
	 * item, and shows each item
	 * on the console.
	 */
	public String[] toArray() {
		String[] item_holder = new String[size];
		for (int i = 0; i < size; i++) {
			item_holder[i] = data[i];
		}
		return item_holder;
	}
	/*
	 * clears all the items in the set.
	 */
	public void clear() {
		for (int i = 0; i < size; i++) {
			data[i] = null;
			size--;
		}
		size -= 1;
	}
	
	public FixedArraySet copyOf() {
		FixedArraySet myCopy = new FixedArraySet(this.data.length);
		// now I have a new set and the array is the right length,
		// but it's empty. Also the size of myCopy is 0.
		
		// copy the data from this set to the new set
		for (int i = 0; i < this.size; i++) {
			myCopy.data[i] = this.data[i];
		}
		myCopy.size = this.size;
		
		
		return myCopy;
	}
	
}
