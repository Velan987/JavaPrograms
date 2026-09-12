package Leetcode;
public class Test {
    public static void main(String[] args) {
        int n=-10;
        System.out.println(Integer.toBinaryString(n));
        n = 10;
        System.out.println(String.format("%32s", Integer.toBinaryString(n))
                     .replace(' ', '0'));

        System.out.println(Integer.toBinaryString(-2147483648));
        System.out.println(String.format("%32s", Integer.toBinaryString(-2147483648))
                     .replace(' ', '0'));
        System.out.println(-8>>2);
    }
}
