package hellointerview.trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


class TrieNode {
    Map<Character, TrieNode> children;
    boolean isEndOfWord;
    
    public TrieNode() {
        children = new HashMap<>();
        isEndOfWord = false;
    }
}

public class TrieMethods {
    private TrieNode root;
    
    public void createTrie(String[] words) {
        root = new TrieNode();
        for (String word : words) {
            insert(word);
        }
    }
    
    public void insert(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            if (!node.children.containsKey(ch)) {
                node.children.put(ch, new TrieNode());
            }
            node = node.children.get(ch);
        }
        node.isEndOfWord = true;
    }
    
    public boolean search(String word) {
        /**
         * Search the trie for the given word.
         *
         * Returns true if the word exists in the trie, false otherwise
         */
        TrieNode node = root;
        for(char c: word.toCharArray()){
            if(!node.children.containsKey(c)){
                return false;
            }
            node = node.children.get(c);
        }
        return node.isEndOfWord;
    }

    public boolean startsWith(String prefix) {
        /**
         * Check if any word in the trie starts with the given prefix.
         *
         * Returns true if the prefix path exists, false otherwise
         */
        TrieNode node = root;
        for(char c: prefix.toCharArray()){
            if(!node.children.containsKey(c)){
                return false;
            }
            node = node.children.get(c);
        }
        return true ;
    }

    public void delete(String word) {
        /**
         * Deletes the given word from the Trie.
         *
         * Returns nothing.
         */
        // Deletes the given word from the Trie.
    deleteHelper(this.root, word, 0);
}
private boolean deleteHelper(TrieNode node, String word, int index) {
    // base case: We have reached the end of the word
    if (index == word.length()) {
        // Mark the node as not being the end of a word
        node.isEndOfWord = false;
        // Return true if the node should be deleted
        return node.children.isEmpty();
    }
    
    char c = word.charAt(index);
    TrieNode child = node.children.get(c);
    
    if (child == null) {
        return false;  // Word not found
    }
    
    boolean shouldDeleteChild = deleteHelper(child, word, index + 1);
    
    if (shouldDeleteChild) {
        node.children.remove(c);
    }
    
    // Return true if current node should be deleted
    return !node.isEndOfWord && node.children.isEmpty();
}

    public Boolean[] trie(String[] initialWords, String[][] commands) {
        // === DO NOT MODIFY ===
        createTrie(initialWords);

        List<Boolean> output = new ArrayList<>();
        for (String[] command : commands) {
            if (command[0].equals("search")) {
                output.add(search(command[1]));
            } else if (command[0].equals("startsWith")) {
                output.add(startsWith(command[1]));
            } else if (command[0].equals("delete")) {
                delete(command[1]);
            }
        }
        return output.toArray(new Boolean[0]);
    }
}


/**
 * Implement the search, startsWith, and delete methods of a Trie.

search(word) returns true if the word is in the Trie, and false otherwise.
startsWith(prefix) returns true if any word in the Trie starts with the given prefix, and false otherwise.
delete(word) removes the word from the Trie, and does not return a value.
The creation of the Trie and the insert method are already implemented for you.

The test cases include two parameters:

initialWords: a list of words to add to the Trie,
commands: a list of commands to run. Each command is a tuple, where the first element is "search", "startsWith", or "delete", and the second element is the word or prefix.
The test cases will create the Trie with the initial words, and then run the commands in order, and compare the output to the expected output. 
Note we only compare the output of search and startsWith commands, not delete commands.

Input:

initialWords = ["apple", "app", "apartment"]
commands = [
["search", "apple"],
["search", "apartment"],
["search", "appl"],
["delete", "app"],
["search", "app"],
]
Output: [True, True, False, False]

Explanation:

Trie.search("apple") -> True
Trie.search("apartment") -> True
Trie.search("appl") -> False
Trie.delete("app") -> None # Return value not checked
Trie.search("app") -> False
 */