/*
 * Be very careful to follow these rules. 
 * 1. Use sets, maps, and lists to solve the following problems. Do
 *    not use other collections. 
 * 2. Do not use arrays, with one exception: in the alphagrams problem
 *    you might find it useful to get an array of all the characters
 *    in a word so that you can sort them.
 * 3. For sets: you are limited to the following methods: add(), contains(), 
 *    remove(), size(), isEmpty(), clear()
 * 4. For maps: you are limited to the following methods: put(), get(), containsKey(),
 *    keySet(), remove(), size(), isEmpty(), clear()
 * 5. In general, keys should only reflect the data you have. So, for example,
 *    in frequencyByFirstLetter(), your keys should only be letters that appear as 
 *    first letters in the file. So, if the list didn't have any words starting with 
 *    'q', then 'q' should not appear in your map as a key.    
 */

////// Watch the video where you can see how to approach this assignment //////

import java.util.*;
import java.io.*;

public class SetMapWordProblems {
	// Make sure the file in loadWords() exists in the project folder (not
	// in the src folder)
	// It is recommended that you create some of your own test files as well
	// so that you can do some precise checking. Give each a different name.
	// HOWEVER: before you submit your file, be sure to change the string
	// below to "words.txt".
	public static ArrayList<String> words = loadWords("words.txt");
	public static PrintStream output;

	public static void main(String[] args) throws FileNotFoundException {
		// The next two lines let you switch between sending results to
		// the console, or sending them to a file. Console works great
		// for short output, but a file works better if the output is long.
		//output = new PrintStream("results.txt"); // file
		output = System.out; // console
		System.out.println("STARTING");
		
		display(frequenciesByLength('r'));
		
		System.out.println("DONE!");
		output.close(); // only really needed for files, but ok regardless
	}

	///////////// Solved in video
	// returns a set containing all the words that contain the letter q
	// but not the letter u
	public static Set<String> qButNotu() {
		Set<String> result = new TreeSet<>();
		
		// loop through words
		for (String w : words) {
			if (w.contains("q") && !w.contains("u")) {
				result.add(w);
			}
		}
		
		
		return result;
	}

	// returns a set containing all the words that contain more than one z
	public static Set<String> multiZWords() {
		Set<String> result = new TreeSet<>();
		int count = 0;
		for (String w : words) {
			for (int i = 0; i < w.length(); i++) {
				char letter = w.charAt(i);
				if (letter == 'z') {
					count++;
				}
			}
			if (count > 1) {
				result.add(w);
			}
			count = 0;
		}
		return result;
	}

	// returns a set containing all the words that have the specified length
	public static Set<String> wordsWithLength(int len) {
		Set<String> result = new TreeSet<>();
		
		for (String w : words) {
			if (w.length() == len) {
				result.add(w);
			}
		}
		return result;
	}

	// returns a set containing all the words that do not contain
	// any letter more than once. For example, "computer" could be in
	// the set because each letter is unique, but "science" could not
	// be in the set because the "c" is in the words twice (as is the "e").
	public static Set<String> wordsWithUniqueLetters() {
		Set<String> result = new TreeSet<>();
		
		for (String w: words) {
			boolean isUnique = true;
			
			for (int i = 0; i < w.length(); i++) {
				char letter = w.charAt(i);
				for (int j = 0; j < w.length(); j++) {
					if (letter == w.charAt(j) && i != j) {
						isUnique = false;
					}
				}
			}
			if (isUnique == true) {
				result.add(w);
			}
		}
		
		return result;
	}

	///////////// Solved in video
	// returns a map where the key is a letter, and the value
	// is the number of words beginning with that letter.
	public static Map<Character, Integer> frequencyByFirstLetter() {
		Map<Character, Integer> result = new TreeMap<>();
		
		for (String w : words) {
			char letter = w.charAt(0);
			
			if (!result.containsKey(letter)) { // add key for first time
				result.put(letter, 1);
			} else { // key already there bump up by 1
				result.put(letter, result.get(letter) + 1);
			}
		}
		
		return result;
	}

	///////////// Solved in video
	// returns a map where the key is a letter, and the value
	// is the number of words beginning with that letter.
	public static Map<Character, Set<String>> mapByFirstLetter() {
		Map<Character, Set<String>> map = new TreeMap<>();
		
		for (String w : words) {
			char letter = w.charAt(0);
			
			if (!map.containsKey(letter)) { // add key with an empty set
				map.put(letter, new TreeSet<>());
			}
			
			// I know the map contains the key, so get its
			// set of words and add w to it.
			
			map.get(letter).add(w);
		}
		return map;
	}

	// returns a map where the key is a letter, and the value
	// is the set of all words that begin and end with that same
	// letter. For example, for the letter 'a', "alpha" would be one of
	// the words in the set.
	public static Map<Character, Set<String>> mapByMatchingEndLetters() {
		Map<Character, Set<String>> map = new TreeMap<>();
		
		for (String w: words) {
			char letter = w.charAt(0);
			char endLetter = w.charAt(w.length() - 1);
			
			if (letter == endLetter) {
				if (!map.containsKey(letter)) {
					map.put(letter, new TreeSet<>());
				}
				map.get(letter).add(w);
			}
		}
		return map;
	}

	// returns a map where the key is a 2-letter string, and the value
	// is the number of words that contain that 2-letter sequence
	public static Map<String, Integer> frequencyOfLetterPairs() {
		Map<String, Integer> map = new TreeMap<>();
		
		for (String w: words) {
			for (int i = 0; i < w.length() - 1; i++) {
				String twoLetters = w.substring(i, i + 2);
				if (!map.containsKey(twoLetters)) { // add key with an empty set
					map.put(twoLetters, 1);
				} else {
					map.put(twoLetters, map.get(twoLetters) + 1);
				}
			}
			
		}
		
		return map;
	}

	// returns a map where the key is a letter, and the value
	// is the set of all letters that appear immediately after
	// that letter in some word. For example, consider the list of
	// words {"happy", "apple", "play"}. This method should return a map with
	// the following key-value pairs:
	// a---------->[p, y]
	// h---------->[a]
	// l---------->[a, e]
	// p---------->[l, p, y]
	public static Map<Character, Set<Character>> mapByNextCharacter() {
		Map<Character, Set<Character>> map = new TreeMap<>();
		
		for (String w: words) {
			for (int i = 0; i < w.length() - 1; i++) {
				char letter = w.charAt(i);
				if (!map.containsKey(letter)) {
					map.put(letter, new TreeSet<>());
				}
				map.get(letter).add(w.charAt(i + 1));
			}
		}
		return map;
	}

	// returns a list of all the letters that appear anywhere within
	// the word list, sorted from most frequent to least frequent. This does
	// not only look at the first letter of each word, but rather all letters
	// in all words. So, for example, if the letter 'r' appears most frequently
	// and the letter 'z' appears least frequently, then you should expect
	// the returned list will have 'r' in the first position, and 'z' in the
	// last position. If two letters are tied for most frequent, list them
	// in ascending alphabetical order. For example, if the letters 'e' and 'b'
	// are tied in frequency, then 'b' should appear before 'e' in the
	// returned list because 'b' is first alphabetically.
	// try testing this with a very small file. You could even use made-up
	// words in order to get frequencies that help you with testing.
	public static List<Character> lettersSortedByFrequency() {
		List<Character> result = new ArrayList<>();
		
		// TODO: Implement this
		return result;
	}

	// returns a map where the key is a letter, and the value
	// is the set of all words beginning with that letter.
	public static Map<Character, Set<String>> wordsByLetter() {
		Map<Character, Set<String>> map = new TreeMap<>();
		
		for (String w: words) {
			char letter = w.charAt(0);
			if (!map.containsKey(letter)) {
				map.put(letter, new TreeSet<>());
			}
			map.get(letter).add(w);
		}
		
		
		return map;
	}

	// returns a map of all the words in the list, where the
	// key is the word, and the value is the length of that word
	public static Map<String, Integer> lengthOfEachWord() {
		Map<String, Integer> map = new TreeMap<>();
		
		for (String w: words) {
			if (!map.containsKey(w)) {
				map.put(w, w.length());
			}
		}
		return map;
	}

	// returns a map, where the key is a word length, and the value
	// is the number of words that have that length
	public static Map<Integer, Integer> frequencyByLength() {
		Map<Integer, Integer> map = new TreeMap<>();
		
		for (String w: words) {
			if (!map.containsKey(w.length())) {
				map.put(w.length(), 1);
			} else {
				map.put(w.length(), map.get(w.length()) + 1);
			}
		}
		
		return map;
	}

	// returns a map where the key is a letter, and the value is the
	// length of the longest word that begins with that letter
	public static Map<Character, Integer> maxLengthForEachLetter() {
		Map<Character, Integer> map = new TreeMap<>();
		
		for (String w:  words) {
			char letter = w.charAt(0);
			if (!map.containsKey(letter)) {
				map.put(letter, w.length());
			} else {
				int count = map.get(letter);
				if (w.length() > count) {
					count = w.length();
					map.put(letter, count);
				}
			}
		}
	
		return map;
	}

	// returns a map where the key is a letter, and the value is the set of
	// the longest words that begin with that letter. For example, if the longest
	// word beginning with 'a' were 12, then this map would contain
	// 'a'----->[set of all 12-letter words beginning with 'a']
	public static Map<Character, Set<String>> longestWordsForEachLetter() {
		Map<Character, Set<String>> map = new TreeMap<>();
		Map<Character, Integer> maxLengths = maxLengthForEachLetter();
		for (String w: words) {
			char letter = w.charAt(0);
			int maxLength = maxLengths.get(letter);
			if (w.length() == maxLength) {
				if (!map.containsKey(letter)) {
					map.put(letter, new TreeSet<>());
				}
				map.get(letter).add(w);
			}
		}
		
		
		
		return map;
	}

	/*
	 * For a specified character c, return a map where the key is a word length, and
	 * the value is the number of words that start with c and have that length. The
	 * map should only have non-zero values. In other words, don't have a key in the
	 * map if its corresponding value will be 0. For example,
	 * frequenciesByLength('q') would return a map where the keys are all the
	 * lengths of q-words in the list, and the values are the number of q-words with
	 * that length
	 */
	public static Map<Integer, Integer> frequenciesByLength(char c) {
		Map<Integer, Integer> map = new TreeMap<>();
		
		for (String w: words) {
			if (w.charAt(0) == c) {
				if (!map.containsKey(w.length())) {
					map.put(w.length(), 1);
				} else {
					map.put(w.length(), map.get(w.length()) + 1);
				}
			}
		}
		return map;
	}

	/*
	 * a palindrome is a word that is spelled the same forward as backward. Create a
	 * map where the key is a letter of the alphabet, and the value is the set of
	 * all palindromes in the list that begin with that letter. For example, the map
	 * would contain 'a'----->["aa", "aba", (and so on)
	 */
	public static Map<Character, Set<String>> palindromesByLetter() {
		Map<Character, Set<String>> map = new TreeMap<>();
		boolean isSame = true;
		for (String w: words) {
			for (int i = 0; i < w.length() / 2; i++) {
				if (w.charAt(i) != w.charAt(w.length() - 1 - i)) {
					isSame = false;
				}
			}
			if (isSame == true) {
				char letter = w.charAt(0);
				if (!map.containsKey(letter)) {
					map.put(letter, new TreeSet<>());
				}
				map.get(letter).add(w);
			}
			isSame = true;
		}
		
		return map;
	}

	/*
	 * The alphagram of a word is formed by arranging its letters in alphabetical
	 * order. For example, the alphagram of "stop" is "opst". Notice that the
	 * alphagram of "post" is also "opst". In fact, there are several words that
	 * have the same "opst" alphagram: ["opts", "spot", "stop", "pots", "post"]
	 * Create a map where each key is an alphagram, and the value is the set of all
	 * words in the list that have that same alphagram. Only include alphagrams that
	 * have more than one word in the list associated with it.
	 * 
	 * THIS IS THE ONE PROBLEM WHERE YOU COULD USE AN ARRAY IF YOU WOULD LIKE. It
	 * could be helpful to get the array of characters that make up a word.
	 */
	public static Map<String, Set<String>> alphagrams() {
		Map<String, Set<String>> map = new TreeMap<>();
		
		for (String w: words) {
			char[] letters = w.toCharArray();
			Arrays.sort(letters);
			String alphagram = new String(letters);
			if (!map.containsKey(alphagram)) {
				map.put(alphagram, new TreeSet<>());
			}
			map.get(alphagram).add(w);
		}
		
		
		return map;
	}

	//////////////////////////////////////////////
	/// **** DON'T MODIFY THE CODE BELOW **** ////
	//////////////////////////////////////////////

	// Loads the words from the specified file.
	// If the file is not found, prints stack trace and exits.
	public static ArrayList<String> loadWords(String filename) {
		ArrayList<String> result = new ArrayList<>();
		Scanner in = null;
		try {
			in = new Scanner(new File(filename));
		} catch (IOException e) {
			System.out.println("FILE NOT FOUND");
			e.printStackTrace();
			System.exit(0);
		}

		while (in.hasNext()) {
			result.add(in.next());
		}

		return result;
	}

	// Don't modify this method. It assists with displaying results.
	public static <K, V> void display(Map<K, V> items) {
		if (items == null) {
			output.println("null");
			return;
		}
		for (K key : items.keySet()) {
			output.print(key + "---------->");
			display(items.get(key));
		}
	}

	// Don't modify this method. It assists with displaying results.
	public static void display(Object item) {
		if (item == null) {
			output.println("null");
			return;
		}
		output.println(item);
	}

	// Don't modify this method. It assists with displaying results.
	public static <T> void display(Collection<T> items) {
		if (items == null) {
			output.println("null");
			return;
		}
		int LEN = 80;
		String line = "[";
		for (T item : items) {
			line += item.toString() + ",";
			if (line.length() > LEN) {
				output.println(line);
				line = "";
			}
		}
		if (line.charAt(line.length() - 1) == ',') {
			line = line.substring(0, line.length() - 1);
		}
		output.println(line + "]");
	}

}
