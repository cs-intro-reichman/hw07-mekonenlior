
public class SpellChecker {

	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}
    
	// returns the substring of a string excluding the first character.
	public static String tail(String str) {
		String tail = str.substring(1);
		return tail;
	}

	// calculates the Levenshtein distance between two strings recursively.
	public static int levenshtein(String word1, String word2) {
		word1 = word1.toLowerCase();
		word2 = word2.toLowerCase();

		if (word2.length() == 0) {
			return word1.length();

		} else if (word1.length() == 0) {
			return word2.length();

		} else if (word1.charAt(0) == word2.charAt(0)) {
			return levenshtein(tail(word1), tail(word2));

		} else {
			int min = Math.min(levenshtein(tail(word1), word2), levenshtein(word1, tail(word2)));
			return 1 + Math.min(min, levenshtein(tail(word1), tail(word2)));
		}
	}

	// reads a dictionary file and stores the words in an array.
	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];
		In in = new In(fileName);
		for (int i = 0; in.hasNextLine(); i++) {
			String line = in.readLine();
			dictionary[i] = line;
		}
		return dictionary;
	}

	// iterates over the dictionary to find the word with the minimum Levenshtein distance to the given word.
	public static String spellChecker(String word, int threshold, String[] dictionary) {
		int minDistance = threshold + 1;
		String correctWord = word;
		for (int i = 0; i < dictionary.length; i++) {
			int distance = levenshtein(word, dictionary[i]);
			if (distance < minDistance) {
				minDistance = distance;
				correctWord = dictionary[i];
			}
		}
		return correctWord;
	}
}
