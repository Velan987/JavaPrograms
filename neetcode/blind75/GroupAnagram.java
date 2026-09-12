package neetcode.blind75;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAnagram {
    // count every character in the word and compare
    public List<List<String>> groupAnagrams(String[] strs) {
       Map<String, List<String>> map = new HashMap<>();

        for(String str:strs){
            int[] count = new int[26];
            for(char c:str.toCharArray()){
                count[c-'a'] ++;
            }
            map.computeIfAbsent(Arrays.toString(count), k->new ArrayList<>()).add(str);
        }

        return new ArrayList<>(map.values());
    }

    // convert each word into char array and sort the array, then compare it if already there then add original word to the list
    public List<List<String>> groupAnagramsV2(String[] strs) {
       Map<String, List<String>> map = new HashMap<>();

        for(String str:strs){
            char[] charray = str.toCharArray();
            Arrays.sort(charray);
            String key = new String(charray);
            // below line is little slow, instead use if(containskey) then get and update the list else create list and add it
            map.computeIfAbsent(key, k-> new ArrayList<>()).add(str);
        }

        return new ArrayList<>(map.values());
    }
}

/**
 * Given an array of strings strs, group all anagrams together into sublists. You may return the output in any order.

An anagram is a string that contains the exact same characters as another string, but the order of the characters can be different.

Example 1:

Input: strs = ["act","pots","tops","cat","stop","hat"]

Output: [["hat"],["act", "cat"],["stop", "pots", "tops"]]
Example 2:

Input: strs = ["x"]

Output: [["x"]]
Example 3:

Input: strs = [""]

Output: [[""]]
Constraints:

1 <= strs.length <= 1000.
0 <= strs[i].length <= 100
strs[i] is made up of lowercase English letters.
 */
