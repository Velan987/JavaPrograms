package airbnb;

import java.util.*;

public class WordLadder {

    public static int minOperations(String beginWord, String endWord, List<String> wordList) {

        // No transformation is needed.
        if (beginWord.equals(endWord)) {
            return 0;
        }

        // Only words of the correct length can participate.
        Set<String> dictionary = new HashSet<>();

        for (String word : wordList) {
            // Adding words with same length
            if (word.length() == beginWord.length()) {
                dictionary.add(word);
            }
        }

        // The destination must be a valid dictionary word.
        if (!dictionary.contains(endWord)) {
            return -1;
        }

        Queue<String> queue = new ArrayDeque<>();
        queue.offer(beginWord);

        // Mark beginWord as visited.
        dictionary.remove(beginWord);

        int operations = 0;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();

            // Every word currently in the queue is the same
            // number of operations away from beginWord.
            for (int i = 0; i < levelSize; i++) {
                String currentWord = queue.poll();

                if (currentWord.equals(endWord)) {
                    return operations;
                }

                char[] characters = currentWord.toCharArray();

                for (int position = 0; position < characters.length; position++) {

                    char originalCharacter = characters[position];

                    for (char replacement = 'a'; replacement <= 'z'; replacement++) {

                        if (replacement == originalCharacter) {
                            continue;
                        }

                        characters[position] = replacement;
                        String nextWord = new String(characters);

                        if (dictionary.contains(nextWord)) {
                            queue.offer(nextWord);

                            // Removing it ensures that it is not
                            // visited again through another path.
                            dictionary.remove(nextWord);
                        }
                    }

                    // Restore the character before moving to
                    // the next position.
                    characters[position] = originalCharacter;
                }
            }

            operations++;
        }

        return -1;
    }

    public static void main(String[] args) {
        System.out.println(
                minOperations(
                        "hit",
                        "cog",
                        List.of("hit", "hot", "dot", "dog", "cog")
                )
        ); // 4

        System.out.println(
                minOperations(
                        "word",
                        "word",
                        List.of("word", "ward")
                )
        ); // 0

        System.out.println(
                minOperations(
                        "hit",
                        "cog",
                        List.of("hit", "hot", "dot", "dog")
                )
        ); // -1
    }
}

/**
 * Given two strings (Word 1 and Word 2) and a list of valid words from a dictionary, return the minimum number of operations required to convert Word 1 to Word 2.
Hard
Save
8
Airbnb logoAsked at Airbnb 4 years ago

Question

Solution

Mock Interview
Determine the Edit Distance in a Word Ladder: Given two words (beginWord and endWord), and a dictionary's word list, find the minimum number of operations needed to change beginWord into endWord.

You can change only one letter at a time, and each intermediate word must exist in the word list. If there is no possible transformation, return None (Python), -1 (Java & C++), null (Javascript).

Examples

beginWord = 'hit'
endWord = 'cog'
wordList = ["hit", "hot", "dot", "dog", "cog"]
output: 4 # hit -> hot -> dot -> dog -> cog

beginWord = 'word'
endWord = 'word'
wordList = ['word', 'ward']
output: 0 

beginWord = 'hit'
endWord = 'cog'
wordList = ["hit", "hot", "dot", "dog"]
output: None  # because no 'cog' in list
 */