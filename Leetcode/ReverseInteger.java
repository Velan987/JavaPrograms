package Leetcode;
public class ReverseInteger {
    // without string conversion
    public static int reverse(int x) {
        int reversed = 0;
        while (x != 0) {
            int pop = x % 10;
            System.out.println("pop: " + pop);
            x /= 10;
            System.out.println("x: " + x);
            // integer limit -2147483648 to 2147483647
            if (reversed > Integer.MAX_VALUE/10 || (reversed == Integer.MAX_VALUE / 10 && pop > 7)) return 0; // because if reversed is 214748364 and pop is greater than 7, then it will be greater than 2147483647
            if (reversed < Integer.MIN_VALUE/10 || (reversed == Integer.MIN_VALUE / 10 && pop < -8)) return 0; // because if reversed is -214748364 and pop is less than -8, then it will be less than -2147483648  
            reversed = reversed * 10 + pop;
        }
        return reversed;
    }

    // public static int reverse(int x) {
    //     String c="";
    //     if(x<0){
    //         c= "-";
    //         x *= -1;
    //     }
    //     String s = ""+x;
    //     s = c+ new StringBuilder(s).reverse().toString();
    //     try{
    //         return Integer.parseInt(s);
    //     }catch(NumberFormatException e){
    //         return 0;
    //     }
    // }

    public static void main(String[] args) {
        int x = -2147483648;
        int result = reverse(x);
        System.out.println(result);
    }
}

/**
 * Given a signed 32-bit integer x, return x with its digits reversed. If reversing x causes the value to go outside the signed 32-bit integer range [-231, 231 - 1], then return 0.

Assume the environment does not allow you to store 64-bit integers (signed or unsigned).

 

Example 1:

Input: x = 123
Output: 321
Example 2:

Input: x = -123
Output: -321
Example 3:

Input: x = 120
Output: 21
 

Constraints:

-231 <= x <= 231 - 1
 */