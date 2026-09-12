package neetcode.blind75.bitManipulation;

public class Adding2Numbers {
    /**
     * 
     * @param a
     * @param b
     * @return
     * 
     * XOR - if both bits are same then it will return 0, else 1, in addition 0 +1 =1, 1+0=1, 0+0=0, 1+1=10
     * in XOR gate we will get exactly this behaviour except 1+1 scenario, for this alone we need to take care of carry
     * & - this AND gate will return 1 only if both the bits are 1 else 0, for carry scenario we can use this and shift operator to move to left
     * so, a ^ b, will take care of addition except carry block, (a&b) << 1 will take care of carry 
     * we will repeat this process until (a & b)<<1 become zero, in that case there will be no carry and we will get our result
     */
    public int getSum(int a, int b) {
        while(b != 0){
            // carry calculation should be done in original input, that is before doing XOR
            int tmp = (a & b) << 1;
            a = a ^ b ;
            b = tmp;
        }
        return a;
    }

    public static void main(String[] args) {
        Adding2Numbers add = new Adding2Numbers();
        System.out.println(add.getSum(12, 13));
    }
}

/**
 * Given two integers a and b, return the sum of the two integers without using the + and - operators.

Example 1:

Input: a = 1, b = 1

Output: 2
Example 2:

Input: a = 4, b = 7

Output: 11
Constraints:

-1000 <= a, b <= 1000
 */