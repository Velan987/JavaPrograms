package hellointerview.backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * GenerateParanthesis
 * There are two important rule for this
 * Rule 1: We can add only n number of open paranthesis
 * RUle 2: Number of ')' can not exceed number of '(' in any step - if ')' is more than '(' then that would be invalid combination in any step
 */
public class GenerateParanthesis {
    List<String> result;
    int n;
    Map<String, Integer> countMap;
    public List<String> generateParanthesis(int n){
        result = new ArrayList<>();
        countMap = new HashMap<>();
        this.n = n;
        String s = "";

        backtrack(s, 0, 0);
        return result;
    }

    // dont use Map to store open and close count, because same map will be sent all call stack and open and close count values will be same, so use primitives itself
    public void backtrack(String s, int open, int close){

        if(s.length() == 2*n){
            result.add(s);
            return;
        }
        // Rule 1
        if(open < n){
            backtrack(s+"(", open+1, close);
        }

        // RUle 2
        if(close < open){
            backtrack(s+")", open, close+1);
        }
    }

    public static void main(String[] args) {
        GenerateParanthesis gp = new GenerateParanthesis();
        System.out.println(gp.generateParanthesis(3));
    }
}


/**
 * Given an integer n, write a function to return all well-formed (valid) expressions that can be made using n pairs of parentheses.

Example 1:

Input:

n = 3
Output:

["((()))","(()())","(())()","()(())","()()()"]
Example 2:

Input:

n = 2
Output:

["()()", "(())"]
 */


/**
 * Let's start by figuring out how to incrementally generate all valid combinations of n pairs of parentheses starting from an empty string s = "". 
 * Doing so will help us visualize the "solution space tree" which we can then traverse using a recursive backtracking approach.
At each step below, we can add either an opening parenthesis '(' or a closing parenthesis ')' to the string s as long as the resulting string remains valid. 
Let's walkthrough the process of generating all valid combinations of parentheses for n = 2:
s = ``
If we start with an empty string, then we can only add an opening parenthesis '(', as adding a closing parenthesis ')' would result in an invalid combination.
s = (
We can add both an opening parenthesis '(' and a closing parenthesis ')' to the string s without making it invalid.
s = (( and s = ()
For this string s = ((: we can't add an opening parenthesis '(' to s as it would make it invalid. This is because the number of opening parentheses is already equal to n. 
However, we can add a closing parenthesis ')' to s.
For the string s = (): we can only add an opening parenthesis '(' as adding a closing parenthesis would make it invalid. 
This is because s has an equal number of opening and closing parentheses, and we need to add another opening parenthesis before we can add a closing parenthesis.
From this, we can notice two rules:
We can add an opening parenthesis '(' if the number of opening parentheses in s is less than n.
We can add a closing parenthesis ')' if the number of closing parentheses in s is less than the number of opening parentheses in s.
s = (() and s = ()(
For the string s = ((): we can only add a closing parentheses ')' (since there are already n = 2 opening parentheses in s)
For the string s = ()(: we can only add a closing parentheses ')' (since there are already n = 2 opening parentheses in s)
s = (()) and s = ()()
In both cases, the length of the string s is equal to 2 * n, so we are done.
 */