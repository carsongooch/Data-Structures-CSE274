// Carson Gooch
// CSE274 Homework 2
public class FractionSet {
	
	// two instance variables.
	private Fraction[] data;
	private int size;
	
	//static constant for the capacity
	public static final int DEFAULT_CAPACITY = 15;
	
	/**
	 * constructs a new set with no elements,
	 * and a max size specified by client.
	 * @param capacity max number of elements
	 */
	public FractionSet(int capacity) {
		this.data = new Fraction[capacity];
		this.size = 0;
	}
	
	/**
	 * Constructs set with default capacity.
	 */
	public FractionSet() {
		this(DEFAULT_CAPACITY);
	}
	/*
	 * this method creates a new Fraction Object
	 * and calls the add function to add the new
	 * Fraction Object. Returns true or false 
	 * depending on if the fraction was successfully 
	 * added.
	 */
	public boolean add(int numerator, int denominator) {
		return add(new Fraction(numerator, denominator));
		}
	/*
	 * This method returns true or false depending on
	 * if a fraction was added to the set. This method
	 * also resizes the set if needed.
	 */
	public boolean add(Fraction f) {
		if (f == null) {
			return false;
		}
		if (indexOf(f) != -1) {
			return false;
		}
		guaranteeCapacity();
		data[size] = f;
		size++;
		return true;
	}
	// this helper method will assist add(String), remove(String)
	// and contains(String) by looping to find the item.
	// returns the index of a String in the underlying array.
	// or -1 if it's not in the array:
	private int indexOf(Fraction s) {
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
	 * returns the size of the set.
	 * @return
	 */
	public int size() {
		return this.size;
	}
	/*
	 * Determines boolean expression
	 * and returns true or false
	 * to determine if array is empty.
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/*
	 * returns a space-seperated list of the items
	 * in the set.
	 */
	public String toString () {
		String result = "";
		
		// loop through the items in array
		// as determined by the size of the set
		for (int i = 0; i < this.size; i++) {
			result += data[i] + " ";
		}
		
		return result;
	}
	/*
	 * returns true or false based off of
	 * the method removing the last item in
	 * the set.
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
	 * the method removed the
	 * specified index
	 */
	public boolean remove(Fraction s) {
		int index = indexOf(s);
		if(index != -1) {
			this.data[index] = this.data[size - 1];
			this.data[size - 1] = null;
			size -= 1;
			return true;
		} else {
			return false;
		}
	}
	
	/*
	 * returns true or false if the 
	 * method detects a specified
	 * item in the set
	 */
	public boolean contains(Fraction s) {
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
	public Fraction[] toArray() {
		Fraction[] item_holder = new Fraction[size];
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
		}
		size = 0;
	}
	/*
	 * this method creates a copy of the current set.
	 */
	public FractionSet copyOf(int capacity) {
		FractionSet myCopy = new FractionSet(capacity);
		// now I have a new set and the array is the right length,
		// but it's empty. Also the size of myCopy is 0.
		
		// copy the data from this set to the new set
		for (int i = 0; i < this.size; i++) {
			myCopy.data[i] = this.data[i];
		}
		myCopy.size = this.size;
		
		
		return myCopy;
	}
	
	/*
	 * this method determines if the array needs
	 * to be resized by looking at the size and
	 * length of the array. If size is bigger, 
	 * then the array's capacity is doubled.
	 */
	public void guaranteeCapacity() {
		if (size >= data.length) {
			FractionSet resizedArray = this.copyOf(data.length * 2);
			this.data = resizedArray.data;
		} 
	}
	
	/*
	 * Returns array with all values, including null.
	 */
	public Fraction[] peekAtUnderlyingArray() {
		return this.data;
	}
	
	/*
	 * This method returns the smallest value
	 * in the array. Uses compareTo and smallest 
	 * to store the smallest value.
	 */
	public Fraction min() {
		if (isEmpty()) {
			return null;
		}
		Fraction smallest = data[0];
		for (int i = 0; i < this.size; i++) {
			if (data[i].compareTo(smallest) < 0) {
				smallest = data[i];
			}
		}
		return smallest;
	}
	
	/*
	 * This method returns the sum of all
	 * fraction elements within a set.
	 */
	public Fraction sum() {
		if (isEmpty()) {
			return null;
		}
		Fraction total = new Fraction(0, 1);
		for (int i = 0; i < this.size; i++) {
			int numerator = total.getNumerator() * data[i].getDenominator() + data[i].getNumerator() * total.getDenominator();
			int denominator = total.getDenominator() * data[i].getDenominator();
			total = new Fraction(numerator, denominator);
		}
		return total;
	}
	
	/*
	 * this is a helper method for allSums().
	 * This method takes in two fraction
	 * arguments and adds them together.
	 * Returns the sum.
	 */
	private Fraction sum(Fraction one, Fraction two) {
		Fraction total = new Fraction(0, 1);
		int numerator = one.getNumerator() * two.getDenominator() + two.getNumerator() * one.getDenominator();
		int denominator = one.getDenominator() * two.getDenominator();
		total = new Fraction(numerator, denominator);
		return total;
	}
	
	/*
	 * this method uses nested loops to 
	 * add the sum of all possible combinations
	 * of fractions in a set. Then, this method returns
	 * an array with the sums of each possible sum.
	 */
	public FractionSet allSums() {
		FractionSet allTheSums = new FractionSet();
		for (int i = 0; i < this.size; i++) {
			int j =+ 1;
			for (j = 0; j < this.size; j++) {
				Fraction sums = sum(data[i], data[j]);
				allTheSums.add(sums);
			}
		}
		return allTheSums;
	}
	
	/*
	 * this method compares two different sets,
	 * and makes a new set with only the elements
	 * that appeared in both of the sets being 
	 * compared.
	 */
	public FractionSet intersection(FractionSet set) {
		FractionSet result = new FractionSet();
		for (int i = 0; i < this.size; i++) {
			if (set.contains(this.data[i])) {
				result.add(this.data[i]);
			}
		}
		return result;
	}
	
	/*
	 * This method determines if a set contains 
	 * a reciprocal of the fractions in the set.
	 */
	public boolean hasAllReciprocals() {
		for (int i = 0; i < this.size; i++) {
				if (data[i].getNumerator() == 0) {
					continue;
				}
				Fraction recip = new Fraction(data[i].getDenominator(), data[i].getNumerator());
				if (!contains(recip)) {
					return false;
			}
		}
		return true;
	}
	
	/*
	 * This method uses bubble sort to 
	 * reorder the set from the smallest 
	 * fractions to the largest fractions.
	 */
	public void sort() {
		for (int i = 0; i < this.size - 1; i++) {
			for (int j = 0; j < this.size - 1 - i; j++) {
				if (data[j].compareTo(data[j+ 1]) > 0) {
					Fraction tempHolder = data[j];
					data[j] = data[j + 1];
					data[j + 1] = tempHolder;
				}
			}
		}
	}
	
	/*
	 * This method combines two separate sets,
	 * and returns the combined set.
	 */
	public FractionSet union(FractionSet set) {
		FractionSet uniqueSet = new FractionSet();
		for (int i = 0; i < this.size; i++) {
			uniqueSet.add(data[i]);
		}
		for (int i = 0; i < set.size(); i++) {
			uniqueSet.add(set.data[i]);
		}
		return uniqueSet;
	}
	
	/*
	 * This method compares two different sets
	 * and returns a true or false statement 
	 * depending on if both of the sets are the 
	 * contain the same elements.
	 */
	public boolean sameElements(FractionSet set) {
		if (this.size != set.size()) {
			return false;
		}
		for (int i = 0; i < this.size; i++) {
			if (!this.contains(set.data[i])) {
				return false;
			}
		}
		return true;
	}
	
}
