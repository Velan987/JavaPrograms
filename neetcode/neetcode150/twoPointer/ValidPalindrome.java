package neetcode.neetcode150.twoPointer;

public class ValidPalindrome {
    public boolean isPalindrome(String s) {
        s = s.toLowerCase();
        s = s.replaceAll(" ", "");
        int length = s.length();
        int j = length-1;
        for(int i=0; i<length && i<j; i++,j--){
            int leftChar = (int)s.charAt(i);
            int rightChar = (int)s.charAt(j);
            System.out.println("i "+i+" j"+j);
            System.out.println("lc "+leftChar + "rc "+rightChar);
            // we need to ignore characters other than alphanumeric
            while(i<j && ((leftChar <48 || leftChar>57) && (leftChar<97 || leftChar>122))){
                i ++;
                leftChar = (int)s.charAt(i);
            }
            while(i<j && ((rightChar <48 || rightChar>57) && (rightChar<97 || rightChar>122))){
                j--;
                rightChar = (int)s.charAt(j);
            }

            System.out.println("i "+i+" j"+j);
            if(s.charAt(i)!=s.charAt(j)){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        ValidPalindrome vp = new ValidPalindrome();
        System.out.println(vp.isPalindrome("Was it a car or a cat I saw?"));
    }
}


/**
 * Given a string s, return true if it is a palindrome, otherwise return false.

A palindrome is a string that reads the same forward and backward. It is also case-insensitive and ignores all non-alphanumeric characters.

Note: Alphanumeric characters consist of letters (A-Z, a-z) and numbers (0-9).

Example 1:

Input: s = "Was it a car or a cat I saw?"

Output: true
Explanation: After considering only alphanumerical characters we have "wasitacaroracatisaw", which is a palindrome.

Example 2:

Input: s = "tab a cat"

Output: false
Explanation: "tabacat" is not a palindrome.
 */