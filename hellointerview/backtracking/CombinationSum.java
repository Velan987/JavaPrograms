package hellointerview.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum {

    /**
     * Finds all unique combinations whose elements add up to the target.
     * Each candidate can be selected an unlimited number of times.
     *
     * @param candidates distinct positive integers
     * @param target     required sum
     * @return all unique combinations that add up to target
     */
    public static List<List<Integer>> combinationSum(
            int[] candidates, int target) {

        List<List<Integer>> result = new ArrayList<>();

        /*
         * Sorting is not required for correctness, but it allows us to stop
         * searching as soon as a candidate becomes larger than the remaining
         * target.
         */
        Arrays.sort(candidates);

        /*
         * Start backtracking:
         *
         * startIndex = 0 means every candidate is initially available.
         * remainingTarget tracks how much more must be added.
         * currentCombination stores the combination currently being built.
         */
        backtrack(
                candidates,
                target,
                0,
                new ArrayList<>(),
                result
        );

        return result;
    }

    /**
     * Builds combinations recursively using backtracking.
     *
     * @param candidates        available candidate numbers
     * @param remainingTarget   amount still needed to reach the target
     * @param startIndex        first candidate index allowed in this branch
     * @param currentCombination combination currently being constructed
     * @param result            collection of all valid combinations
     */
    private static void backtrack(
            int[] candidates,
            int remainingTarget,
            int startIndex,
            List<Integer> currentCombination,
            List<List<Integer>> result) {

        /*
         * Base case:
         * A remaining target of zero means the selected numbers add up
         * exactly to the original target.
         */
        if (remainingTarget == 0) {
            /*
             * Add a copy because currentCombination will continue to be
             * modified while backtracking.
             */
            result.add(new ArrayList<>(currentCombination));
            return;
        }

        /*
         * Consider candidates starting from startIndex.
         *
         * We do not restart from index zero during recursion. This keeps
         * combinations in nondecreasing order and prevents duplicates such as
         * [2, 2, 3], [2, 3, 2], and [3, 2, 2].
         */
        for (int i = startIndex; i < candidates.length; i++) {
            int candidate = candidates[i];

            /*
             * Because candidates is sorted, if this candidate is too large,
             * every candidate after it will also be too large.
             */
            if (candidate > remainingTarget) {
                break;
            }

            // Choose the current candidate.
            currentCombination.add(candidate);

            /*
             * Recursively continue with index i, not i + 1.
             * Passing i allows the same candidate to be selected again.
             */
            backtrack(
                    candidates,
                    remainingTarget - candidate,
                    i,
                    currentCombination,
                    result
            );

            /*
             * Undo the previous choice so the loop can try another candidate.
             * This is the backtracking step.
             */
            currentCombination.remove(currentCombination.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] candidates = {2, 3, 6, 7};
        int target = 7;

        List<List<Integer>> combinations =
                combinationSum(candidates, target);

        System.out.println(combinations);
        // Output: [[2, 2, 3], [7]]
    }
}

/**
 * Given an array of distinct integers candidates and a target integer target, generate all unique combinations of candidates which sum to target. 
 * The combinations may be returned in any order, and the same number may be chosen from candidates an unlimited number of times.

Constraints:

All values in candidates are positive integers.
1 <= candidates.length <= 30
2 <= candidates[i] <= 40
All elements of candidates are distinct.
1 <= target <= 40
Input:

candidates = [2,3,6,7]
target = 7
Output:

[[2,2,3],[7]]
Explanation:

2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times. 7 is a candidate, and 7 = 7. These are the only two combinations.
 */