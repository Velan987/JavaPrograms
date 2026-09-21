package neetcode.blind75.graphs;

import java.util.ArrayDeque;
import java.util.Queue;

class AlienDictionary {

    /**
     * List of strings will be given, words are sorted in different logic, we dont know the logic. Generally if we sort the words, we will sort in alphabetical order, a first then b like that
     * here which letter comes first or which is smaller - that information we dont have, we have array of words, which are already sorted in alien logic
     * if two words are sorted then from that we can identify which letter comes first and which comes next right
     * ["hrn","hrf","er","enn","rfnn"]
     * in this example, "hr" is there in first 2 word, differing letter is n and f, here "hrn" comes first which means "n" comes before "f" in alien logic
     * like this we need to identify the letters orders and then using unique characters from all words we should return a word. in that word the sorting logic we learned from the array should be followed.
     * Input: words = ["hrn","hrf","er","enn","rfnn"]
    Output: "hernf"
    Explanation:

    from "hrn" and "hrf", we know 'n' < 'f'
    from "hrf" and "er", we know 'h' < 'e'
    from "er" and "enn", we know 'r' < 'n'
    from "enn" and "rfnn" we know 'e' < 'r'
    so one possible solution is "hernf"
     */
    /**
     * Basically we are doing the below steps
     * 1. Record every unique letter appearing in the input
     * 2. Compare every pair of adjacency words to learn the pattern (letter order - which letter comes first) - prerequisites
     * 3. Add all letters which are not having prerequisite in to the queue
     * 4. Iterate through the queue until it is empty, for each letter
     *      add it to the result
     *      add all of its neighbours into the queue if the neighbour's prerequisites are met (refer code for better understanding)
     * 5. if result length is not equal to unique letters count then it means there is a cycle in the graph, so return empty string
     */
    public String alienOrder(String[] words) {

        // graph[a][b] == true means: letter 'a' must appear before letter 'b'.
        boolean[][] graph = new boolean[26][26];

        // indegree[x] tells how many different letters must appear before letter x. (prerequisites)
        int[] indegree = new int[26];

        // present[x] tells whether letter x appears in any word.
        boolean[] present = new boolean[26];

        // Number of unique letters appearing in the dictionary.
        int uniqueLetterCount = 0;

        /*
         * Step 1:
         * Record every unique letter appearing in the input.
         */
        for (String word : words) {
            for (char letter : word.toCharArray()) {
                int index = letter - 'a';

                // Count each letter only once.
                if (!present[index]) {
                    present[index] = true;
                    uniqueLetterCount++;
                }
            }
        }

        /*
         * Step 2:
         * Compare every pair of adjacent words.
         *
         * Only the first position where the two words differ gives us information about the alphabet order.
         * "er","enn" - in this example, first char is same, so sorting will check next char, i.e "r" and "n"
         * the word "er" comes before "enn" - so it tells r is smaller than n. 
         * then we skip remaining characters because once the smaller char is found in 2 words char will not be considered for sorting
         * so from remaining characters we cannot learn anything
         */
        for (int i = 0; i < words.length - 1; i++) {

            String firstWord = words[i];
            String secondWord = words[i + 1];

            int minimumLength =
                    Math.min(firstWord.length(), secondWord.length());

            int position = 0;

            // Skip the common prefix. - "ape", "apple" - here "ap" is common and we cannot learn anything from it
            while (position < minimumLength &&
                    firstWord.charAt(position) ==
                    secondWord.charAt(position)) {

                position++;
            }

            /*
             * If no differing character was found, one word is either equal to or a prefix of the other.
             * "app", "apple" - here there is no differing char, first word is prefix of second
             */
            if (position == minimumLength) {

                /*
                 * This ordering is invalid:
                 *
                 * ["apple", "app"]
                 *
                 * The longer word cannot appear before its prefix.
                 */
                if (firstWord.length() > secondWord.length()) {
                    return "";
                }

                // No letter-order relationship can be learned from this pair. either both words are same or first word is prefix of second
                continue;
            }

            /*
             * These are the first characters where the words differ.
             *
             * Because firstWord appears before secondWord:
             *
             * smallerLetter must come before largerLetter.
             */
            char smallerLetter = firstWord.charAt(position);
            char largerLetter = secondWord.charAt(position);

            int from = smallerLetter - 'a';
            int to = largerLetter - 'a';

            /*
             * Add the relationship only if it does not already exist.
             *
             * This prevents counting the same dependency twice.
             */
            if (!graph[from][to]) {
                graph[from][to] = true;
                //indegree[1]=3 means for letter b there are 3 prerequisites, three letters will come before b from availabe letters
                indegree[to]++;
            }
        }

        /*
         * Step 3:
         * Add every letter with no prerequisite to the queue.
         * r->n->b, v->s->d
         * here for r and v there is no prerequisites, so we will add both into the queue
         * the resulting word should follow above learned rule that is r->n->b, v->s->d
         * so result can be rnbvsd, rvsdnb, rnvbsd, ... as long as these  satisfys the above rule
         */
        Queue<Integer> queue = new ArrayDeque<>();

        for (int letter = 0; letter < 26; letter++) {
            if (present[letter] && indegree[letter] == 0) {
                queue.offer(letter);
            }
        }

        /*
         * Step 4:
         * Perform Kahn's topological-sort algorithm.
         */
        StringBuilder alienAlphabet = new StringBuilder();
        // this logic is same as CourseSchedule.java - we can reuse that also
        while (!queue.isEmpty()) {

            // Remove a letter that currently has no prerequisites.
            int currentLetter = queue.poll();

            // Add the actual character to the answer.
            alienAlphabet.append((char) (currentLetter + 'a'));

            /*
             * Visit every letter that must come after currentLetter.
             */
            for (int nextLetter = 0; nextLetter < 26; nextLetter++) {

                if (graph[currentLetter][nextLetter]) {

                    /*
                     * currentLetter has now been placed in the answer,
                     * so nextLetter has one fewer unmet prerequisite.
                     */
                    indegree[nextLetter]--;

                    /*
                     * When no prerequisites remain, nextLetter
                     * is ready to be placed in the answer.
                     */
                    if (indegree[nextLetter] == 0) {
                        queue.offer(nextLetter);
                    }
                }
            }
        }

        /*
         * Step 5:
         * If some present letters were never added, the constraints
         * contain a cycle, so no valid alphabet order exists.
         */
        if (alienAlphabet.length() != uniqueLetterCount) {
            return "";
        }

        return alienAlphabet.toString();
    }
}


/**
 * There is a new alien language that uses the English alphabet, but the order of the letters is unknown.

You are given a list of strings words from the alien language's dictionary. It is claimed that the strings in words are sorted lexicographically by the rules of this new language.

If this claim is incorrect, and the given arrangement of strings in words cannot correspond to any order of letters, return "".

Otherwise, return a string of the unique letters in the new alien language sorted in lexicographically increasing order by the new language's rules. If there are multiple solutions, return any of them.

A string a is lexicographically smaller than a string b if either of the following is true:

The first letter where they differ is smaller in a than in b.
a is a prefix of b and a.length < b.length.

Example 1:

Input: words = ["z","o"]

Output: "zo"
Explanation:
From "z" and "o", we know 'z' < 'o', so return "zo".


Example 2:

Input: words = ["hrn","hrf","er","enn","rfnn"]

Output: "hernf"
Explanation:

from "hrn" and "hrf", we know 'n' < 'f'
from "hrf" and "er", we know 'h' < 'e'
from "er" and "enn", we know 'r' < 'n'
from "enn" and "rfnn" we know 'e' < 'r'
so one possible solution is "hernf"

Example 3:

Input: words = ["abc","ab"]

Output: ""
Explanation:
The second word is a prefix of the first word, but the first word appears before the second. This is impossible in a valid lexicographical ordering, so return "".


Constraints:

1 <= words.length <= 100
1 <= words[i].length <= 100
words[i] consists of only lowercase English letters.
 */