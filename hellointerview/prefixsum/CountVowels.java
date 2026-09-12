package hellointerview.prefixsum;

public class CountVowels {
    public int[] vowelStrings(String word, int[][] queries) {
        int[] result = new int[queries.length];
        int[] countArray = new int[word.length()];
        int i=0, tmp=0;
        for(char c: word.toCharArray()){
            if(c=='a' || c=='e' || c=='i' || c=='o' || c =='u'){
                tmp++;
            }
            // each index will have vowels count until that index
            // for "aba" -> [1,1,2]
            countArray[i] = tmp;
            
            i++;
        }
        for(int j=0;j<queries.length;j++){
            int[] query = queries[j];
            // if query starts with 0 then given range is the start of the array so no need of subtraction
            // "aba" -> [1,1,2] - in this example for [0, 1] vowel count is 1 for [0,2] vowel count is 2
            // for [1,2] the vowel count should be countUntil[2] - countBefore[1]
            result[j] = query[0] >0 ? countArray[query[1]] - countArray[query[0]-1] : countArray[query[1]];
            
        }

        return result;
    }
}


/**
 * Write a function to efficiently count vowels within specified substrings of a given string.

The substrings will be given to you a list queries of [left, right] pairs, which correspond to the substring word[left:right + 1] in Python.

The function should return a list of integers, where each integer represents the vowel count for the corresponding query. You can assume the input string will only contain lowercase letters.

Your function should be optimized to run efficiently for a large number of queries.

Input:

word = "prefixsum"
queries = [[0, 2], [1, 4], [3, 5]]
Output: [1, 2, 1]

Explanation:

word[0:3] -> "pre" contains 1 vowels
word[1:5]-> "refi" contains 2 vowels
word[3:6]-> "fix" contains 1 vowels
 */