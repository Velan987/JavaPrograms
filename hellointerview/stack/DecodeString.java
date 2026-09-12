package hellointerview.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class DecodeString {
    public static String decodeString(String s) {

        /*
         * Stores repeat counts.
         *
         * Example:
         * In "3[a2[c]]", the stack will temporarily contain:
         * [3, 2]
         */
        /**
         * stack is legacy class and double ended queue (Deque) is the modern recommendation for stack
         * deque is faster than stack and it work as either a stack or a queue
         */
        Deque<Integer> countStack = new ArrayDeque<>();

        /*
         * Stores the partially decoded strings that appeared
         * before each opening bracket.
         */
        Deque<String> stringStack = new ArrayDeque<>();

        /*
         * Builds the decoded string for the section we are
         * currently processing.
         */
        String currentString = "";

        /*
         * Builds a repeat count that may contain multiple digits.
         *
         * For example, when reading "12[a]", it becomes:
         * 1 -> 12
         */
        int currentNumber = 0;

        for (char ch : s.toCharArray()) {

            // Case 1: Build a potentially multi-digit repeat count.
            if (Character.isDigit(ch)) {
                currentNumber = currentNumber * 10 + (ch - '0');
            }

            /*
             * Case 2: Start of an encoded section.
             *
             * Save:
             * 1. Its repeat count
             * 2. The string constructed before this bracket
             *
             * Then start building the contents inside the bracket.
             */
            else if (ch == '[') {
                countStack.push(currentNumber);
                stringStack.push(currentString);

                currentNumber = 0;
                currentString = "";
            }

            /*
             * Case 3: End of an encoded section.
             *
             * currentString contains the decoded contents of the
             * current brackets.
             *
             * Repeat it and attach it to the previous string.
             */
            else if (ch == ']') {
                int repeatCount = countStack.pop();
                String previousString = stringStack.pop();

                previousString = previousString + currentString.repeat(repeatCount);
           
                /*
                 * The combined result becomes the current string.
                 * This is especially important for nested brackets.
                 */
                currentString = previousString;
            }

            // Case 4: A normal lowercase letter.
            else {
                currentString+=ch;
            }
        }

        return currentString.toString();
    }

    public static void main(String[] args) {
        System.out.println(decodeString("3[a]2[bc]"));
        System.out.println(decodeString("3[a2[c]]"));
        System.out.println(decodeString("10[a]"));
        System.out.println(decodeString("2[abc]3[cd]ef"));
    }
}


/**
 * Given an encoded string s, write a function to return its decoded string.

The encoding rule is k[encoded_string], where the encoded_string inside the square brackets is repeated exactly k times. 
k is always a positive integer, and the brackets can be nested.

You can assume the input is always well-formed: there are no extra spaces, every square bracket is properly matched, 
and digits only ever appear to specify a repeat count k (so you won't see input like 3a or 2[4]).

Constraints:

1 <= s.length
s consists of lowercase English letters, digits, and the square brackets [ and ].
All repeat counts k are positive integers and may have more than one digit (for example, 10[a]).
The input string is guaranteed to be valid.
Example 1:

Inputs:

s = "3[a]2[bc]"
Output:

"aaabcbc"
(Explanation: 3[a] decodes to "aaa" and 2[bc] decodes to "bcbc".)

Example 2:

Inputs:

s = "3[a2[c]]"
Output:

"accaccacc"
(Explanation: the inner 2[c] becomes "cc", so a2[c] is "acc", which is then repeated 3 times.)

Example 3:

Inputs:

s = "2[abc]3[cd]ef"
Output:

"abcabccdcdcdef"
 */

/**
 * How the algorithm works
We maintain four things:
countStack    → Repeat counts
stringStack   → Text created before each [
currentNumber → Number currently being read
currentString → Text inside the current brackets
Processing digits
For:
12[a]
we must read 12 as one number, not as 1 and 2.
currentNumber = currentNumber * 10 + (ch - '0');
Step-by-step:
Read '1':
currentNumber = 0 × 10 + 1 = 1

Read '2':
currentNumber = 1 × 10 + 2 = 12
Processing [
Suppose the input is:
abc3[xy]
Before [, we have:
currentString = "abc"
currentNumber = 3
We save both values:
countStack.push(currentNumber);
stringStack.push(currentString);
Then reset them for the bracket contents:
currentString = ""
currentNumber = 0
Now xy can be constructed separately.
Processing ]
At ], suppose:
Previous string = "abc"
Current string  = "xy"
Repeat count    = 3
We repeat the current string:
"xy" repeated 3 times = "xyxyxy"
Then attach it to the previous string:
"abc" + "xyxyxy" = "abcxyxyxy"
Nested example: 3[a2[c]]
Read 3[
Save the count 3 and begin a new section:
Count stack:  [3]
String stack: [""]
Current:      ""
Read a
Current: "a"
Read 2[
Save count 2 and the current string "a":
Count stack:  [2, 3]
String stack: ["a", ""]
Current:      ""
The top of each stack is shown first.
Read c
Current: "c"
Read the first ]
Repeat "c" twice and attach it to "a":
"a" + "cc" = "acc"
Read the second ]
Repeat "acc" three times:
"acc" + "acc" + "acc"
Final result:
accaccacc
 */