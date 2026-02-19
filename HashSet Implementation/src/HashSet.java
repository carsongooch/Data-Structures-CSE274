/**
 * Implements a set of strings using hashing, ideally giving O(1) performance
 * for add, remove, and contains. We say "ideally" because efficiency depends on
 * keeping the number of collisions low.
 * 
 * Implementation details: initial array length = 11 (prime) Max load factor is 0.6,
 * which will be checked BEFORE adding an item. If the item must be added (not a
 * duplicate) and if the load factor is exceeded, then and only then will the
 * bucket array be resized.
 * 
 * @author Carson Gooch
 *
 */
public class HashSet {

	// instance variables: we need just two. One is the array of buckets
	// and the other is the size of the set. Each bucket holds a chain of
	// linked nodes.
	private Node[] buckets;
	private int size;
	

	// We will also have some useful constants.
	public static final double MAX_LOAD_FACTOR = 0.6;
	public static final int DEFAULT_CAPACITY = 11; // prime
	
	/**
	 * Constructs a HashSet with specified number of buckets.
	 * Throws an IllegalArgumentException if the number of buckets
	 * is not prime.
	 * @param bucketCount the number of buckets in the array (must be prime)
	 */
	public HashSet(int bucketCount) {
		if (!isPrime(bucketCount)) {
			throw new IllegalArgumentException("not Prime");
		}
		this.buckets = new Node[bucketCount];
		this.size = 0;
	}

	/**
	 * Constructs a HashSet with an initial default array length of 11
	 */
	public HashSet() {
		this(DEFAULT_CAPACITY);
	}
	
	// Returns the bucket index for the given string, based
	// on the string's hash code and the length of the bucket array
	// This should be the first method called by add(s), contains(s),
	// and remove(s)
	private int naturalIndex(String s) {
		int dataPoint = s.hashCode();
		dataPoint = dataPoint % buckets.length;
		if (dataPoint < 0) {
			dataPoint += buckets.length;
		}
		return dataPoint;
	}

	/**
	 * Adds the specified string to the hash set if it does not already exist.
	 * @param s the string to be added
	 * @return true if the string is added, and false otherwise
	 */
	public boolean add(String s) {
		int index = naturalIndex(s);
		if (find(s, buckets[index]) != null) {
			return false;
		}
		assureCapacity();
		index = naturalIndex(s);
		Node temp = new Node(s);
		temp.next = buckets[index];
		buckets[index] = temp.next;
		size++;
		return true;
	}
	
	/**
	 * Returns a space-delimited, trimmed string of the items in the set. The order
	 * will begin by looping through the strings in the first non-empty bucket, and
	 * then the next non-empty bucket, and so on. If the set is empty, return an
	 * empty string ""
	 */
	public String toString() {
		if (this.buckets == null) {
			return "EMPTY";
		}
		
		String result = "";
		for(int i = 0; i < this.buckets.length; i++) {
			Node curr = this.buckets[i];
			while (curr != null) {
				result += curr.data + " ";
				curr = curr.next;
			}
		}
		
		return result.trim();
	}
	
	/**
	 * Returns a string containing all the strings in this set. This string will
	 * contain all buckets, even the empty ones, and within each bucket will be a
	 * space-separated list of the strings in that bucket. The order of the
	 * strings in each bucket should start at the head node and continue in order
	 * to the tail node. Here is a sample showing the format that you must use 
	 * (the strings are not necessarily shown in their correct buckets 
	 * in this example) 
	 * [0] cat house 
	 * [1]
	 * [2] car 
	 * [3] tree mouse dog
	 * [4] 
	 * [5] 
	 * [6] apple 
	 * 
	 * and so on
	 * 	 * @return a string containing all the strings in the set
	 */
	public String toDeluxeString() {
		String result = "";
		
		// loop through all the buckets
		for (int i = 0; i < buckets.length; i++) {
			result += "[" + i + "] ";
			
			// add words from that bucket
			Node curr = buckets[i];
			while (curr != null) {
				result += curr.data+ " ";
				curr = curr.next;
			}
			
			
			result += "\n";
		}
		
		
		return result;
	}
	
	/**
	 * Returns the size of this set
	 * @return the size of this set
	 */
	public int size() {
		return size;
	}

	/**
	 * Returns whether this set is empty
	 * @return true if this set is empty and false otherwise
	 */
	public boolean isEmpty() {
		if (size == 0) {
			return true;
		}
		return false;
	}

	/**
	 * Returns whether a specified string is in this set
	 * @param s the string
	 * @return true if s is in this set, and false otherwise
	 */
	public boolean contains(String s) {
		int index = naturalIndex(s);
		if (find(s, buckets[index]) != null) {
			return true;
		}
		return false;
	}

	/**
	 * Removes the specified string from this set, if it exists.
	 * @param s the string to be removed
	 * @return true if the string is removed, and false otherwise
	 */
	public boolean remove(String s) {
		int index = naturalIndex(s);
		Node curr = buckets[index];
		Node previous = null;
		while(curr != null) {
			if (curr.data.equals(s)) {
				if (previous == null) {
					buckets[index] = curr.next;
					size--;
					return true;
				} else {
					previous.next = curr.next;
					size--;
					return true;
				}
			}
			previous = curr;
			curr = curr.next;
		}
		return false;
	}

	/**
	 * Returns an arbitrary string from the set, if the set is not empty. Otherwise,
	 * returns null; Our algorithm: find the first non-empty bucket and return the
	 * data from the first node in that bucket. WHAT IS THE TIME COMPLEXITY OF THIS
	 * OPERATION?
	 * 
	 * @return the removed string, or null if the set is empty
	 */
	public String remove() {
		for (int i = 0; i < buckets.length; i++) {
			Node curr = buckets[i];
			if (curr != null) {
				String toBeReturned = buckets[i].data;
				buckets[i] = curr.next;
				size--;
				return toBeReturned;
			}
		}	
		return null;
	}

	/**
	 * Clears all items from this set
	 */
	public void clear() {
		for (int i = 0; i < buckets.length; i++) {
			buckets[i] = null;
		} 
		size = 0;
	}
	
	/**
	 * Returns an array containing all the strings in this set. Our algorithm will
	 * be to return them beginning with the strings in the first non-empty bucket,
	 * followed by the next non-empty bucket, and so on. Within each bucket, return
	 * strings beginning with the head node. The order of the strings will be the
	 * same as the order of the strings in toString()
	 * 
	 * @return an array containing all the strings in the set
	 */
	public String[] toArray() {
		String[] allItems = new String[size];
		Node curr;
		int index = 0;
		for (int i = 0; i < buckets.length; i++) {
			curr = buckets[i];
			while (curr != null) {
				allItems[index] = curr.data;
				index++;
				curr = curr.next;
			}
		}
		return allItems;
	}
	
	/**
	 * Adds all strings in the specified array to the set.
	 * If strings is null or empty, no strings will be added (and
	 * no errors will be generated)
	 * 
	 * @param strings the array of strings to be added
	 */
	public void addAll(String[] strings) {
		if (strings == null) {
			return;
		} else {
			for (int i = 0; i < strings.length; i++) {
				if (strings[i] != null) {
					add(strings[i]);
				}
			}
		}
	}
	
	/**
	 * Returns a calculation of the current "load" which is the 
	 * number of items in the set divided by the number of buckets
	 * in the array.
	 */
	public double getCurrentLoad() {
		double load = (double) size / buckets.length;
		return load;
	}
	
	
	
	/**
	 * Adds all strings from another HashSet into this HashSet, but
	 * does not modify the other HashSet. If the other set is null
	 * or empty, nothing is added, and no error is generated.
	 * 
	 * @param other the set of strings to be added
	 */
	public void merge(HashSet other) {
		if (other == null) {
			return;
		}
		String[] other_content = other.toArray();
		for (int i = 0; i < other_content.length; i++) {
			if (other_content[i] != null) {
				add(other_content[i]);
			}
		}
	}

	/**
	 * Returns the intersection of this HashSet with another set. Try to write this
	 * in the fewest lines of code you can by using other methods you wrote in this
	 * class. Do not use any other collection classes. But you may use arrays
	 * freely. Do not modify this set or the other set.
	 * 
	 * @param other the other HashSet
	 * @return all words common to both this HashSet and the other HashSet
	 */
	public HashSet intersection(HashSet other) {
		if (other == null) {
			return null;
		}
		HashSet setToBeReturned = new HashSet(other.buckets.length);
		for (int i = 0; i < buckets.length; i++) {
			Node curr = buckets[i];
			while (curr != null) {
				if (other.contains(curr.data)) {
					setToBeReturned.add(curr.data);
				}
				curr = curr.next;
			}
		}
		return setToBeReturned;
	}

	/**
	 * Extracts from this set all strings with 4 or fewer characters, returning
	 * a new set with only those extracted strings. That is, this will modify
	 * the existing set and return a new set. For example, if this set contains
	 * {house, cat, tree, computer, minnow}, then calling this method will
	 * return a new set containing {cat, tree}, and this set will only have
	 * {house, computer, minnow} at the end of the method.
	 *  
	 * @return a new HashSet containing the strings from this HashSet that had 4
	 * or fewer characters
	 */
	public HashSet extractShortWords() {
		HashSet setToBeReturned = new HashSet(buckets.length);
		for (int i = 0; i < buckets.length; i++) {
			Node curr = buckets[i];
			Node previous = null;
			while (curr != null) {
				if (curr.data.length() <= 4) {
					setToBeReturned.add(curr.data);
					size--;
					if (previous == null) {
						buckets[i] = curr.next;
						curr = buckets[i];
					} else {
						previous.next = curr.next;
						curr = previous.next;
					}
				} else {
					previous = curr;
					curr = curr.next;
				}
			}
		}
		return setToBeReturned;
	}

	/**
	 * Returns true if this HashSet has exactly the same elements as another set,
	 * regardless of order. Does not modify either set.
	 * 
	 * @param other the other HashSet
	 * @return true if this HashSet and the other HashSet have the exact same
	 *         elements, regardless of order
	 */
	public boolean equalElements(HashSet other) {
		if (other == null) {
			return false;
		}
		String[] other_content = other.toArray();
		String[] base_content = toArray();
		int check = 0;
		for (int i = 0; i < other_content.length; i++) {
			for (int j = 0; j < base_content.length; j++) {
				if (other_content[i].equals(base_content[j])) {
					check++;
					break;
				}
			}
		}
		if (check == size) {
			return true;
		}
		return false;
	}
	
	/**
	 * Converts all strings in this set to uppercase. So, if the current
	 * set contained {"cat", "Dog", "HOUSE", "aBc123"}, then after calling
	 * this method the set should contain {"CAT", "DOG", "HOUSE", "ABC123"}. 
	 * After calling a method that changes the set, it is a good idea to
	 * make sure other set methods still work correctly.
	 */
	public void toUpperCase() {
		for (int i = 0; i < buckets.length; i++) {
			Node curr = buckets[i];
			while (curr != null) {
				remove(curr.data);
				curr.data = curr.data.toUpperCase();
				add(curr.data);
				curr = curr.next;
			}
		}
	}

	/*
	 * Get the next prime number greater than the specified value.
	 */
	private static int nextPrime(int n) {
		// loop until you find first prime after n
		n++;
		
		while (!isPrime(n)) {
			n++;
		}
		
		
		return n;
	}

	/*
	 * Checks if the capacity exceeds MAX_LOAD_FACTOR. If so, then modifies this set
	 * by moving the items in the array to a new array whose size is the first prime
	 * number >= length of the current array
	 */
	private void assureCapacity() {
			if (getCurrentLoad() >= MAX_LOAD_FACTOR) {
				int length = nextPrime(buckets.length * 2);
				Node[] newBuckets = new Node[length];
				for (int i = 0; i < buckets.length; i++) {
					Node curr = buckets[i];
					while (curr != null) {
						Node next = curr.next;
						
						int index = curr.data.hashCode();
						index = index % length;
						if (index < 0) {
							index += length;
						}
						
						curr.next = newBuckets[index];
						newBuckets[index] = curr;
						curr = next;
					}
				}
				buckets = newBuckets;
			}
	}

	// Given a starting node AND a string, returns the node containing 
	// a particular string, or null if not found.
	// This is just like our find method for LinkedSet, except that it
	// needs to know which node is the starting node.
	private Node find(String s, Node head) {
		Node curr;
		curr = head;
		while (curr != null) {
			if (curr.data == s) {
				return curr;
			}
			curr = curr.next;
		}
		return null;
	}

	////////////////////////////////////////////
	// Don't change the code below
	////////////////////////////////////////////

	/*
	 * Returns whether the specified value is prime
	 */
	private static boolean isPrime(int n) {
		if (n == 2) // 2 is the only even prime
			return true;
		if (n <= 1 || n % 2 == 0) 
			return false;

		for (int i = 3; i * i <= n; i += 2) {
			if (n % i == 0)
				return false;
		}

		return true;
	}

	// Inner node class. DO NOT MODIFY.
	// DO NOT ADD CONSTRUCTORS.
	private class Node {
		private String data;
		private Node next;

		private Node(String data) {
			this.data = data;
			this.next = null;
		}
	}
}
