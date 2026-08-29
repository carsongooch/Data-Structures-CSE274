/*
 * Maps allow us to store information in key-value pairs
 * Two common implementations are HashMap and TreeMap
 * HashMap is valued for its O(1) efficiency for put() and get() and containsKey()
 * TreeMap is valued for keeping its keys sorted (and O(log n) for those operations)
 */

import java.util.*; // Map, HashMap, TreeMap

public class MapIntro {

	public static void main(String[] args) {
		gpaExample();
		numberExample();
		wordExample1();
		wordExample2();
	} // end of main()
	
	public static void gpaExample() {
		// want to store students with their IDs and their GPAs
		// We say the KEY is the ID, and the VALUE is the GPA.
		// keys must be unique. Not true for the values
		Map<String, Double> gpas = new HashMap<>();
		
		System.out.println(gpas.put("norm", 3.1));
		gpas.put("bob", 3.9);
		System.out.println(gpas.get("norm"));
		System.out.println(gpas.get("bob"));
		
		// replace the old value for norm with 2.0
		// and returns the old value
		System.out.println(gpas.put("norm", 2.0));
		
		// containsKey is a way to look up if it is in the map
		System.out.println("false? " + gpas.containsKey("tom"));
		System.out.println("true? " + gpas.containsKey("bob"));
		
		// remove() takes the key value pair out:
		gpas.remove("norm"); // returns norm's GPA and removes him from map
		
		System.out.println(gpas.put("shrek", 3.7));
		System.out.println(gpas.put("fiona", 3.7));
		System.out.println(gpas.put("donkey", 4.0));
		System.out.println(gpas.put("farquad", 3.1));
		
		// let's grab all the keys (which do form a set)
		Set<String> names = gpas.keySet();
		System.out.println(names);
		
		// with the names in the set, I can now loop through them.
		for (String s : names) {
			// only print the people who have GPAS that are A's (3.7 or higher)
			if (gpas.get(s) >= 3.7) {
				System.out.println(s + " " + gpas.get(s));
			}
		}
	}
	
	public static void wordExample1() {
		String[] words = {"cat", "apple", "dog", "house", "car", "computer", "science"};
		// want a map where the key is a letter of the alphabet, and
		// the value is the number of words that begin with that letter
		// a --> 1, --> 3, etc. 
		Map<Character, Integer> map = new HashMap<>(); 
		
		// loop through each word and get its first letter:
		for (String s : words) {
			char letter = s.charAt(0);
			
			// either letter is already in the map: map.get() and old value and bump up
			// or it's not so put the key with a value 1.
			if (!map.containsKey(letter)) { // handles first occurrence 
				map.put(letter, 1);
			} else {
				map.put(letter, map.get(letter) + 1);
			}
		}
		
		System.out.println(map);
	}
	
	public static void wordExample2() {
		String[] words = {"cat", "apple", "dog", "house", "car", "computer", "science"};
		
		// make a map where the key is a first letter, and the value is
		// is a set of all the words that begin with that letter
		// a --> {apple}
		// c --> {cat, car, computer}, etc.
		Map<Character, Set<String>> map = new TreeMap<>();
			for (String s : words) {
				char letter = s.charAt(0);
				
				// if letter is not in there, put it in an empty set
				if (!map.containsKey(letter)) {
					map.put(letter, new TreeSet<String>());
				}
				
				// I now know for sure that the letter is in there
				map.get(letter).add(s);
			}
			System.out.println(map);
		}
	
	public static void numberExample() {
		// create a map where the key is the number, and the value 
		// is the square root of that number
		Map<Integer, Double> map = new TreeMap<>(); // TreeMap is in order
		
		// Put all the INT values from 1 to 20 in the map with their
		// square roots
		for (int i = 0; i <= 20; i++) {
			map.put(i,  Math.sqrt(i));
		}
		
		// change all even #s by increasing values by 1
		for (int i = 1; i <= 20; i++) {
			if (i % 2 == 0) {
				map.put(i, map.get(i) + 1);
			}
		}
		
		System.out.println(map);
	}

} // end class
