
public class SpellChecker {

	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
		if (str.length() == 1) {
			return str;
		}
		return str = str.substring(1);
	}

	public static char head(String str) {
		char head = str.charAt(0);
		return head;
	}

	public static int levenshtein(String word1, String word2) {
		int a = word1.length();
		int b = word2.length();
		if (b == 0) {
			return a;
		}
		if (a == 0) {
			return b;
		}
		if (head(word1) == head(word2)) {
			return levenshtein(tail(word1), tail(word2));
		}
		int deletion = levenshtein(tail(word1), word2);
		int substitution = levenshtein(tail(word1), tail(word2));
		int addition = levenshtein(word1, tail(word2));
		return (1 + Math.min(deletion, Math.min(addition, substitution)));

	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];
		In in = new In(fileName);
		for (int i = 0; in.hasNextLine(); i++) {
			String line = in.readLine();
			dictionary[i] = line;
		}
		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		int minDistance = threshold + 1;
		String sameWord = word;
		for (int i = 0; i < dictionary.length; i++) {
			int distance = levenshtein(word, dictionary[i]);
			if (distance < minDistance) {
				minDistance = distance;
				sameWord = dictionary[i];
			}
		}
		return sameWord;
	}
}
