package Leetcode;
import java.util.ArrayList;
import java.util.List;

public class GenerateParenthesis {
    public static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(result, new StringBuilder(), 0, 0, n);
        return result;
    }

    private static void backtrack(List<String> result, StringBuilder current,
                           int open, int close, int n) {

        // base case
        if (current.length() == 2 * n) {
            // in fibonacci, we return a value, here we add to result - in fibonacci we return single value, here we return a list of values
            // each time we reach a valid combination, we add it to the result list
            result.add(current.toString());
            return;
        }

        // add '('
        if (open < n) {
            current.append('(');
            backtrack(result, current, open + 1, close, n);
            current.deleteCharAt(current.length() - 1);
        }

        // add ')'
        if (close < open) {
            current.append(')');
            backtrack(result, current, open, close + 1, n); // in this step when close=3 and open=3, string would be "((()))" 
            // after that there wont be any function call because close < open condition will fail, so below line will be executed and we will backtrack to previous state
            current.deleteCharAt(current.length() - 1);
        }
    }

    public static void main(String[] args) {
        int n = 3;
        List<String> parentheses = generateParenthesis(n);
        System.out.println(parentheses);
    }
}

/**
 * How to identify the repetitive process
 * Step 1: What is the “repetitive task”?

Ask yourself:

At any point, what am I doing again and again?

For this problem:

👉 Build a valid parentheses string character by character

At every step:

Choose next character → '(' OR ')'

That’s your repetitive action.

🔹 Step 2: What changes between calls?

Each recursive call differs by:

current string
open count
close count

So state is:

(current, open, close)
🔥 Step 3: Decision at each step

At every recursion call:

Can I add '(' ?
Can I add ')' ?

This becomes:

if (open < n) → add '('
if (close < open) → add ')'

👉 THIS is the repeating logic.

🔹 Step 4: Why is this “recursive”?

Because:

After adding one character,
the remaining problem is SAME as original problem

Example:

Original: build string of length 6
After adding "(":
→ build string of length 5 with updated state

Same problem, smaller input → perfect for recursion.

🔥 Step 5: General Recursion Pattern

You can map it like this:

Problem:
Generate all valid parentheses

↓

Break into:
Choose next char → solve remaining

↓

Recursive call:
backtrack(current + choice)
🔹 Step 6: Base Case

When do we stop?

When length == 2 * n
🔥 How I Identify This Pattern (Important for you)

Whenever I see:

"Generate all combinations"
"Return all possible"
"Build sequences"

I immediately think:

→ Backtracking
→ Try all choices
→ Undo (backtrack)
 */

/**
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

 

Example 1:

Input: n = 3
Output: ["((()))","(()())","(())()","()(())","()()()"]
Example 2:

Input: n = 1
Output: ["()"]
 

Constraints:

1 <= n <= 8
 */
