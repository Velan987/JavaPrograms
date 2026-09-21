package neetcode.blind75.trie;

import java.util.ArrayList;
import java.util.List;

/**
 * First we insert all the words into a trie. Then we do a DFS search on the board, starting from each cell.
 * If the current cell's character is not in the current trie node's children, we return. If it is, we move to the child node and continue the search in all four directions.
 * If we reach a trie node that has a non-null word, we add it to the result list and set the word to null to avoid duplicates. We also mark the current cell as visited
 */
class WordSearch2 {

    /*
     * Each TrieNode represents one character position
     * in one or more dictionary words.
     */
    private static class TrieNode {

        // children[0] represents 'a',
        // children[1] represents 'b', ..., children[25] represents 'z'.
        TrieNode[] children = new TrieNode[26];

        /*
         * If this node completes a dictionary word,
         * word stores that complete word.
         *
         * Otherwise, word is null.
         */
        String word;
    }

    public List<String> findWords(char[][] board, String[] words) {

        List<String> result = new ArrayList<>();

        // Defensive checks for empty input.
        if (board == null || board.length == 0 ||
                board[0].length == 0 ||
                words == null || words.length == 0) {
            return result;
        }

        /*
         * Step 1:
         * Insert all dictionary words into a Trie.
         */
        TrieNode root = new TrieNode();

        for (String word : words) {
            insert(root, word);
        }

        /*
         * Step 2:
         * Start a DFS from every board cell.
         *
         * Every word can potentially begin at any cell.
         */
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[0].length; column++) {
                search( board, row, column, root, result );
            }
        }

        return result;
    }

    /*
     * Inserts one word into the Trie.
     */
    private void insert(TrieNode root, String word) {

        TrieNode current = root;

        for (char letter : word.toCharArray()) {

            int index = letter - 'a';

            // Create the path only when it does not already exist.
            if (current.children[index] == null) {
                current.children[index] = new TrieNode();
            }

            current = current.children[index];
        }

        /*
         * Store the complete word at its final Trie node.
         *
         * For example, for "cat", the word is stored
         * at the node representing 't'.
         */
        current.word = word;
    }

    /*
     * Searches the board using DFS and backtracking.
     */
    private void search( char[][] board, int row, int column, TrieNode parent, List<String> result) {

        char letter = board[row][column];

        /*
         * '#' means this cell is already being used in the current word path.
         */
        if (letter == '#') {
            return;
        }

        int index = letter - 'a';

        /*
         * If the current Trie prefix has no child matching this board letter, no dictionary word can continue through this cell.
         */
        TrieNode current = parent.children[index];

        if (current == null) {
            return;
        }

        /*
         * If current.word is not null, the current board path has completed a dictionary word.
         */
        if (current.word != null) {
            result.add(current.word);

            /*
             * Prevent the same word from being added again if it appears through another board path.
             */
            current.word = null;
        }

        /*
         * Mark this cell as temporarily used.
         *
         * This ensures the same cell cannot be used twice while constructing one word.
         */
        board[row][column] = '#';

        /*
         * Continue searching in the four allowed directions.
         */

        // Move up.
        if (row > 0) {
            search(board, row - 1, column, current, result);
        }

        // Move down.
        if (row + 1 < board.length) {
            search(board, row + 1, column, current, result);
        }

        // Move left.
        if (column > 0) {
            search(board, row, column - 1, current, result);
        }

        // Move right.
        if (column + 1 < board[0].length) {
            search(board, row, column + 1, current, result);
        }

        /*
         * Backtracking:
         * Restore the cell so another search path can use it.
         */
        board[row][column] = letter;

        /*
         * Optional Trie pruning:
         *
         * If this Trie node no longer contains a word and
         * has no children, there are no unfound words using
         * this prefix. Remove it from the Trie.
         */
        if (current.word == null && hasNoChildren(current)) {
            parent.children[index] = null;
        }
    }

    /*
     * Returns true when a Trie node has no child nodes.
     */
    private boolean hasNoChildren(TrieNode node) {

        for (TrieNode child : node.children) {
            if (child != null) {
                return false;
            }
        }

        return true;
    }
}

/**
 * Given a 2-D grid of characters board and a list of strings words, return all words that are present in the grid.

For a word to be present it must be possible to form the word with a path in the board with horizontally or vertically neighboring cells. The same cell may not be used more than once in a word.

Example 1:



Input:
board = [
  ["a","b","c","d"],
  ["s","a","a","t"],
  ["a","c","k","e"],
  ["a","c","d","n"]
],
words = ["bat","cat","back","backend","stack"]

Output: ["cat","back","backend"]
Example 2:



Input:
board = [
  ["x","o"],
  ["x","o"]
],
words = ["xoxo"]

Output: []
Constraints:

1 <= board.length, board[i].length <= 12
board[i] consists only of lowercase English letter.
1 <= words.length <= 30,000
1 <= words[i].length <= 10
words[i] consists only of lowercase English letters.
All strings within words are distinct.
 */