package hellointerview.backtracking;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LetterCombination {

    private Map<Character, String> phone = Map.of(
        '2', "abc", '3', "def", '4', "ghi", '5', "jkl",
        '6', "mno", '7', "pqrs", '8', "tuv", '9', "wxyz"
    );
    private List<String> result;
    private String digits;
    
    public List<String> letterCombinations(String digits) {
        this.digits = digits;
        this.result = new ArrayList<>();
        
        if (digits.length() > 0) {
            backtrack("", 0);
        }
        return result;
    }
    
    private void backtrack(String path, int idx) {
        if (idx == digits.length()) {
            result.add(path);
            return;
        }
        for (char letter : phone.get(digits.charAt(idx)).toCharArray()) {
            backtrack(path + letter, idx + 1);
        }
    }
    
}


/**
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.
A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
2: "abc"
3: "def"
4: "ghi"
5: "jkl"
6: "mno"
7: "pqrs"
8: "tuv"
9: "wxyz"
Example:
Input:
"23"
Output:
["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"]
We can think about solving this problem incrementally, using the input "23" as an example.
We start with an empty string, and form all possible combinations that can be made using the first digit.
"2" -> ["a", "b", "c"]
Now we take each of these combinations above and add the letters corresponding to the second digit, "3".
Since "3" maps to "def", we add "d" to "a", "b", and "c", then "e" to "a", "b", and "c", and finally "f" to "a", "b", and "c".
"23" -> ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"]
If we were to visualize this process a tree, it would look like this, where the leaf nodes represent the final combinations:
a
b
c
ad
ae
af
bd
be
bf
cd
ce
cf
This tree conceptually represents the "solution space" of all possible letter combinations of the phone number. If we can traverse this tree, we can find all valid combinations.
 */