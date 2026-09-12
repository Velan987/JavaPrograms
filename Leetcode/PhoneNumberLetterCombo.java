package Leetcode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhoneNumberLetterCombo {
    public static List<String> letterCombinations(String digits) {
        Map<String,List<String>> source = new HashMap<>();
        source.put("2", Arrays.asList("a","b","c"));
        source.put("3", Arrays.asList("d","e","f"));
        source.put("4", Arrays.asList("g","h","i"));
        source.put("5", Arrays.asList("j","k","l"));
        source.put("6", Arrays.asList("m","n","o"));
        source.put("7", Arrays.asList("p","q","r","s"));
        source.put("8", Arrays.asList("t","u","v"));
        source.put("9", Arrays.asList("w","x","y","z"));
        List<String> result = new ArrayList<>();
        for(char c: digits.toCharArray()){
            String s = c+"";
            List<String> sourceList = source.get(s);
            if(result.isEmpty()){
                result.addAll(sourceList);
            }else{
                List<String> tmp = new ArrayList<>();
                result.forEach(str->{
                    sourceList.forEach(str2->{
                        tmp.add(str+str2);
                    });
                });
                result = tmp;
            }
        }
        return result;
        /**
         * without map it should be solved means
         * private static final String[] map = {
                "",     // 0
                "",     // 1
                "abc",  // 2
                "def",  // 3
                "ghi",  // 4
                "jkl",  // 5
                "mno",  // 6
                "pqrs", // 7
                "tuv",  // 8
                "wxyz"  // 9
            };
            String letters = map[digits.charAt(index) - '0'];
         */
    }

    public static void main(String[] args) {
        String digits = "234";
        System.out.println(letterCombinations(digits));
    }
}

/**
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.

A mapping of digits to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.

Example 1:

Input: digits = "23"
Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
Example 2:

Input: digits = "2"
Output: ["a","b","c"]
 

Constraints:

1 <= digits.length <= 4
digits[i] is a digit in the range ['2', '9'].
 */
