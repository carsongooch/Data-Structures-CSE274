/**
 * Represents a fraction of integer values.
 * 
 * Additional notes: Fractions will always be reduced to "simplest terms" when
 * constructed. For example, 3/6 will be stored as 1/2. The denominator of a
 * fraction cannot be zero. If the fraction represents a negative value, such as
 * -3/4, then the negative sign will be stored with the numerator rather than
 * the denominator. If a fraction is equivalent to zero, then it will be stored
 * with 0 as the numerator, and 1 as the denominator.
 */

public class Fraction implements Comparable<Fraction> {
	private int numerator;
	private int denominator;

	/**
	 * Returns the numerator of this fraction. The numerator can be any integer
	 * value (positive, negative, or zero)
	 * 
	 * @return the numerator
	 */
	public int getNumerator() {
		return numerator;
	}

	/**
	 * Returns the denominator of this fraction. The denominator will always be a
	 * positive integer value (because any negative fractions will have a negative
	 * numerator rather than denominator)
	 * 
	 * @return the denominator
	 */
	public int getDenominator() {
		return denominator;
	}

	/**
	 * Constructs a fraction with specified numerator and non-zero denominator. The
	 * fraction will be simplified, always with a positive denominator. And, if the
	 * fraction represents a negative number, the numerator will hold the negative
	 * sign. For example: new Fraction(6, -8) will be stored with numerator -3 and
	 * denominator 4.
	 * 
	 * @param numerator   the numerator of the fraction
	 * @param denominator the denominator of the fraction
	 * @throws IllegalArgumentException if the denominator is zero
	 */
	public Fraction(int numerator, int denominator) {
		if (denominator == 0) {
			throw new IllegalArgumentException("denominator must be non-zero");
		}

		this.numerator = numerator;
		this.denominator = denominator;

		simplify();
	}

	/*
	 * Computes the greatest common divisor of two numbers using euclid's recursive
	 * algorithm
	 */
	private static int gcd(int a, int b) {
		if (a % b == 0) {
			return b;
		}
		return gcd(b, a % b);
	}

	/*
	 * Simplifies a fraction to "lowest terms" by dividing the numerator and
	 * denominator by their greatest divisor, and shifting the negative sign to the
	 * numerator if needed.
	 */
	private void simplify() {
		// fix the sign so that if there is a negative, it's in
		// the numerator:
		if (this.denominator < 0) {
			this.numerator *= -1;
			this.denominator *= -1;
		}

		int gcd = gcd(Math.abs(this.numerator), this.denominator);
		this.numerator /= gcd;
		this.denominator /= gcd;
	}

	/**
	 * returns a String representation of this fraction, such as 3/4, or -7/5. If
	 * the fraction represents a negative number, then the negative will appear
	 * before the numerator
	 * 
	 * @return a string in the form numerator/denominator
	 * 
	 */
	public String toString() {
		return this.numerator + "/" + this.denominator;
	}

	/**
	 * Returns whether this fraction is equal to
	 * another object.
	 * @return true if the other object is a fraction with numerator
	 * and denominator equal to the numerator and denominator of this
	 * fraction, and false otherwise
	 */
	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof Fraction)) {
			return false;
		}
		Fraction that = (Fraction) other;
		return this.numerator == that.numerator && this.denominator == that.denominator;

	}

	/**
	 * Compares this fraction to another fraction, returning an integer to indicate
	 * whether this fraction is smaller than, larger than, or equal to the other
	 * fraction.
	 * 
	 * @param that the fraction being compared to this fraction
	 * @return -1 if this fraction is smaller than that fraction, 1 if this fraction
	 *         is larger than that fraction, and 0 if this fraction and that
	 *         fraction are equal.
	 */
	@Override
	public int compareTo(Fraction that) {
		int thisValue = this.numerator * that.denominator;
		int thatValue = that.numerator * this.denominator;

		if (thisValue < thatValue) {
			return -1;
		} else if (thisValue > thatValue) {
			return 1;
		} else {
			return 0;
		}
	}
}
