package hellointerview.variablelengthslidingwindow;

public class LongestRepeatingCharacterReplacement {
    public static int longestRepeatingCharacterReplacement(String s, int k) {

        /*
         * frequency[i] stores how many times a letter appears
         * inside the current sliding window.
         *
         * Index 0 represents 'A'
         * Index 1 represents 'B'
         * ...
         * Index 25 represents 'Z'
         */
        int[] frequency = new int[26];

        // Left boundary of the sliding window
        int left = 0;

        /*
         * Number of occurrences of the most frequent character
         * in the current window.
         */
        int maxFrequency = 0;

        // Length of the longest valid window found
        int maxLength = 0;

        // right represents the right boundary of the window
        for (int right = 0; right < s.length(); right++) {

            char currentCharacter = s.charAt(right);

            // Convert the character into an array index
            int index = currentCharacter - 'A';

            // Add the current character to the window
            frequency[index]++;

            // Update the frequency of the most common character
            maxFrequency = Math.max(
                maxFrequency,
                frequency[index]
            );

            /*
             * Window size:
             *     right - left + 1
             *
             * Characters that must be replaced:
             *     window size - frequency of most common character
             *
             * If the number of replacements is greater than k,
             * the window is invalid, so move the left side forward.
             */
            while ((right - left + 1) - maxFrequency > k) {

                char leftCharacter = s.charAt(left);
                frequency[leftCharacter - 'A']--;

                left++;
            }

            // The current window is valid
            int currentWindowLength = right - left + 1;
            maxLength = Math.max(maxLength, currentWindowLength);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        String s = "BBABCCDD";
        int k = 2;

        int result = longestRepeatingCharacterReplacement(s, k);

        System.out.println(result); // 5
    }
}

/**
 * DESCRIPTION (inspired by Leetcode.com)
Write a function to find the length of the longest substring containing the same letter in a given string s, 
after performing at most k operations in which you can choose any character of the string and change it to any other uppercase English letter.

Input:

s = "BBABCCDD"
k = 2
Output:

5
Explanation: Replace the first 'A' and 'C' with 'B' to form "BBBBBCDD". The longest substring with identical letters is "BBBBB", which has a length of 5.
 */

/**
 * Main idea
For any substring, we want to keep the character that occurs most often and replace all the other characters.
The number of replacements required is:
window length - frequency of most common character
For example, consider:
BBABC
The window length is 5.
Character frequencies:
B → 3
A → 1
C → 1
The most frequent character is B, appearing 3 times. Therefore:
replacements required = 5 - 3
                      = 2
With k = 2, we can replace A and C with B:
BBABC → BBBBB
Therefore, the answer is 5.
How the window works
The window expands by moving right.
[B]
[BB]
[BBA]
[BBAB]
[BBABC]
For BBABC:
window length = 5
maximum frequency = 3  // B occurs three times
replacements = 5 - 3 = 2
Because 2 <= k, this is a valid window.
When this condition becomes true:
windowLength - maxFrequency > k
the window needs too many replacements. We then move left forward until it becomes usable again.
Why maxFrequency is not decreased
When the left side moves, the code does not recalculate maxFrequency. It represents the highest useful frequency encountered while expanding the window.
A temporarily stale value may allow the window boundaries to remain the same size, but it will not produce an impossible larger answer. Recalculating it is unnecessary for finding the maximum length.
Complexity
Time: O(n) because each character enters and leaves the window at most once.
Space: O(1) because the frequency array always has exactly 26 positions.
 */