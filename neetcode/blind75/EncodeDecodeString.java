package neetcode.blind75;

import java.util.ArrayList;
import java.util.List;

public class EncodeDecodeString {
    // one way to do is we can just merge all words with any delimiter and split using that delimitter - but if the delimiter itself is in the string then this wont work
    // we will encode every string with number of characters followed by special character(numbers can be multiple digit, will get characters until special character)
    // [neet, code] - encoded version can be 4#neet4#code
    public String encode(List<String> strs) {
        String encoded = "";
        for(String str: strs){
            encoded += str.length()+"#"+str;
        }
        return encoded;
    }

    // 4#neet5#coder
    public List<String> decode(String str) {
        List<String> decodedList = new ArrayList<>();
        int length = str.length();
        int start = 0;
        while(start < length){
            int count = 0;
            while(str.charAt(start)!='#'){
                int tmp = str.charAt(start) - '0';
                count = count*10 + tmp;
                start ++;
            }
            start ++;
            String word = str.substring(start, start+count);
            decodedList.add(word);
            start +=count;
        }

        return decodedList;
    }
    
    public static void main(String[] args) {
        EncodeDecodeString encodeDecode = new EncodeDecodeString();
        System.out.println(encodeDecode.decode("4#neet5#coder"));
    }
}

/**
 * Design an algorithm to encode a list of strings to a string. The encoded string is then sent over the network and is decoded back 
 * to the original list of strings.

Machine 1 (sender) has the function:

String encode(List<String> strs) {
    // ... your code
    return encoded_string;
}
Machine 2 (receiver) has the function:

List<String> decode(String encoded_string) {
    // ... your code
    return decoded_strs;
}
So Machine 1 does:

String encoded_string = encode(strs);
and Machine 2 does:

List<String> decoded_strs = decode(encoded_string);
decoded_strs in Machine 2 should be the same as the input strs in Machine 1.

Implement the encode and decode methods.

Example 1:

Input: strs = ["Hello","World"]

Output: ["Hello","World"]
Explanation:

Solution solution = new Solution();
String encoded_string = solution.encode(strs);

// Machine 1 ---encoded_string---> Machine 2

List<String> decoded_strs = solution.decode(encoded_string);

Example 2:

Input: strs = [""]

Output: [""]

 */