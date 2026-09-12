package neetcode.blind75;

import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {
    public String minWindow(String s, String t) {
        if(s==null || t==null || s.length()<t.length()){
            return "";
        }

        // Character count in t
        Map<Character, Integer> needMap = new HashMap<>();
        char[] tArray = t.toCharArray();
        for(char c: tArray){
            needMap.put(c, needMap.getOrDefault(c, 0)+1);
        }

        // for every iteration this will have number of required characters are there in the window
        Map<Character, Integer> haveMap = new HashMap<>();

        // number of characters currently have and number of characters needed 
        int have = 0, need = t.length();
        //window start and end position
        int start = 0, end=0;

        char[] sArray = s.toCharArray();
        String result = "";

        while(end < sArray.length){

            char c = sArray[end];
            // if it is a required char then add it in havemap and increase character count in the map
            // if count of c in havemap is less than or equal to count of c in needmap then increase have, if it is > then it is a extra char so dont increase have count.
            if(needMap.containsKey(c)){
                haveMap.put(c, haveMap.getOrDefault(c, 0)+1);
                // if it is > then it is a needed character but extra char
                if(haveMap.get(c) <= needMap.get(c)){ 
                    have ++; 
                }
                System.out.println("havemap "+ haveMap);
            }
            // if have and need count are same then we have a substring containing all chars of t, will store this substring in result and try to shrink the substring to get smaller substring
            while(have == need){
                // if result is empty then first substring so add it in result
                // if substring length is lesser than result and substring length is >= needed characters then this is a smaller valid substring
                if((end-start+1 <result.length() && end-start+1 >=need) || result.length() == 0){
                    result = s.substring(start, end+1);
                    System.out.println("tmp "+result);
                }
                // Trying to shrink the window by moving start to get smaller substring
                if(haveMap.containsKey(sArray[start])){
                    int hcount = haveMap.get(sArray[start]);
                    // this means we are going to increase the start index, so count will be reduced
                    hcount --;
                    if(hcount ==0){
                        haveMap.remove(sArray[start]);
                    }else{
                        haveMap.put(sArray[start], hcount);
                    }
                    // if hcount is >= needmap(char) - no issues, still it can form a valid substring 
                    if(hcount < needMap.get(sArray[start])){
                        have--;
                    }
                }
                start ++;
            }
            end ++;
        }
        System.out.println("result "+ result);
        return result; 
    }

    public static void main(String[] args) {
        MinimumWindowSubstring mw = new MinimumWindowSubstring();
        //mw.minWindow("OUZODYXAZV", "XYZ");
        mw.minWindow("baAaABabBba", "AbbB");
    }
}


/**
 * Given two strings s and t, return the shortest substring of s such that every character in t, including duplicates, is present in the substring. 
 * If such a substring does not exist, return an empty string "".

You may assume that the correct output is always unique.

Example 1:

Input: s = "OUZODYXAZV", t = "XYZ"

Output: "YXAZ"
Explanation: "YXAZ" is the shortest substring that includes "X", "Y", and "Z" from string t.

Example 2:

Input: s = "xyz", t = "xyz"

Output: "xyz"
Example 3:

Input: s = "x", t = "xy"

Output: ""
Constraints:

1 <= s.length <= 1000
1 <= t.length <= 1000
s and t consist of uppercase and lowercase English letters.
 */