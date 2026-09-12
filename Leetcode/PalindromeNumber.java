package Leetcode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PalindromeNumber {
    public static boolean isPalindrome(int x) {
        if(x < 0){
            return false;
        }
        List<Integer> list = new ArrayList<>();
        while(x>0){
            int tmp = x % 10;
            list.add(tmp);
            x/=10;
        }
        int size = list.size();
        for(int i=0; i<size/2; i++){
            if(Objects.equals(list.get(i), list.get(size-(i+1)))){
                continue;
            }
            return false;
        }
        return true;
    }
    public static void main(String[] args) {
        System.out.println(isPalindrome(121));
        System.out.println(isPalindrome(-121));
        System.out.println(isPalindrome(2147483647));
    }
}
