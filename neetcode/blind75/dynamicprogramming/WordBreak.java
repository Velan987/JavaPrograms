package neetcode.blind75.dynamicprogramming;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordBreak {
    public boolean wordBreak(String s, List<String> wordDict) {
        return dfs(s, wordDict, 0);
    }
    /**
     * at every index i in the string, we want to decide can the suffix starting at index i be segmented into valid dictionary words
     * recursive idea is, try every word in dictionary, if a word matches the string starting at position i
     * recursively check whether the remaining substring (starting at i+len(word)) can also be broken successfully
     * if any path reaches the end of the string then answer is true
     */
    private boolean dfs(String s, List<String> wordDict, int i){
        //if any path reaches the end of the string then answer is true
        if(i == s.length()){
            return true;
        }
        for(String word: wordDict){
            // string contains current word from index i, so we need to check the remaing characters of the string
            // for those remaining characters also every word in the dictionary will be checked
            if(i+word.length() <= s.length() && s.substring(i, i+word.length()).equals(word)){
                if(dfs(s, wordDict, i+word.length())){
                    return true;
                }
            }
        }
        return false;
    }

    // above recursion repeated process is based on i only, so we can cache i result
    /**
     * While recursively checking splits, the same index i is reached many times
        The result of dfs(i) (can suffix s[i:] be segmented?) never changes
        So we cache the result for each index:

        If dfs(i) was already computed, reuse it
        This avoids recomputing exponential subtrees
     */
    Map<Integer, Boolean> memo;
    public boolean wordBreakV2(String s, List<String> wordDict) {
        memo = new HashMap<>();
        // if i reaches s.length then answer is true
        memo.put(s.length(), true);
        return dfsV2(s, wordDict, 0);
    }
    private boolean dfsV2(String s, List<String> wordDict, int i){
        if(memo.containsKey(i)){
            return memo.get(i);
        }
        for(String word: wordDict){
            // string contains current word from index i, so we need to check the remaing characters of the string
            // for those remaining characters also every word in the dictionary will be checked
            if(i+word.length() <= s.length() && s.substring(i, i+word.length()).equals(word)){
                if(dfsV2(s, wordDict, i+word.length())){
                    memo.put(i, true);
                    return true;
                }
            }
        }
        memo.put(i, false);
        return false;
    }

}

/**
 * Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of dictionary words.

You are allowed to reuse words in the dictionary an unlimited number of times. You may assume all dictionary words are unique.

Example 1:

Input: s = "neetcode", wordDict = ["neet","code"]

Output: true
Explanation: Return true because "neetcode" can be split into "neet" and "code".

Example 2:

Input: s = "applepenapple", wordDict = ["apple","pen","ape"]

Output: true
Explanation: Return true because "applepenapple" can be split into "apple", "pen" and "apple". Notice that we can reuse words and also not use all the words.

Example 3:

Input: s = "catsincars", wordDict = ["cats","cat","sin","in","car"]

Output: false
 */