package neetcode.blind75.trie;

class TrieNode{
    TrieNode[] children;
    boolean isEndOfWord;

    public TrieNode(){
        children = new TrieNode[26];
    }
}
public class WordDictionary {
    TrieNode root;
    public WordDictionary() {
        root = new TrieNode();
    }

    public void addWord(String word) {
        TrieNode cur = root;
        for(char c: word.toCharArray()){
            // put character if it is not there
            if(cur.children[c-'a'] == null){
                cur.children[c-'a'] = new TrieNode();
            }
            // move current node
            cur = cur.children[c-'a'];
        }
        cur.isEndOfWord = true;
    }

    /**
     * search word may contain dots, in that case dot can match any letter
     */
    public boolean search(String word) {
        return dfs(word, 0, root);
    }
    public boolean dfs(String word, int i, TrieNode root){
        TrieNode cur = root;

        for(int j=i; j<word.length(); j++){
            char c = word.charAt(j);
            if(c == '.'){
                // if the char is dot then it can match all of its child nodes, so for every children we need to iteratively check remaining characters
                for(TrieNode node: cur.children){
                    if(node != null && dfs(word, j+1, node)){
                        return true;
                    }
                }
                return false;
            }else{
                if(cur.children[c-'a'] == null){
                    return false;
                }
                cur = cur.children[c - 'a'];
            }
        }
        return cur.isEndOfWord;
    }
}

/**
 * Design a data structure that supports adding new words and searching for existing words.

Implement the WordDictionary class:

void addWord(word) Adds word to the data structure.
bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise. 
    word may contain dots '.' where dots can be matched with any letter.
Example 1:

Input:
["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
[[],["day"],["bay"],["may"],["say"],["day"],[".ay"],["b.."]]

Output:
[null, null, null, null, false, true, true, true]

Explanation:
WordDictionary wordDictionary = new WordDictionary();
wordDictionary.addWord("day");
wordDictionary.addWord("bay");
wordDictionary.addWord("may");
wordDictionary.search("say"); // return false
wordDictionary.search("day"); // return true
wordDictionary.search(".ay"); // return true
wordDictionary.search("b.."); // return true
Constraints:

1 <= word.length <= 25
word in addWord consists of lowercase English letters.
word in search consist of '.' or lowercase English letters.
There will be at most 2 dots in word for search queries.
At most 10,000 calls will be made to addWord and search.

 */
