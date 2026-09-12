package hellointerview.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ValidParantheses {
    public Boolean isValid(String s) {
        
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');

        for (int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            if(map.containsKey(c)){
                if(stack.isEmpty() || map.get(c) != stack.peek()){
                    return false;
                }
                stack.pop();
                
            }else{
                stack.push(c);
            }
        }

        return stack.isEmpty();
    }
}


/**
 * Given an input string s consisting solely of the characters '(', ')', '{', '}', '[' and ']', 
 * determine whether s is a valid string. A string is considered valid if every opening bracket is closed by a matching type of bracket and in the correct order, 
 * and every closing bracket has a corresponding opening bracket of the same type.

Example 1:

Inputs:

s = "(){({})}"
Output:

True
Example 2:

Inputs:

s = "(){({}})"
Output:

False
 */