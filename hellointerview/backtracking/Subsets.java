package hellointerview.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Subsets {
    List<List<Integer>> result;
    int[] nums;
    public List<List<Integer>> subsets(int[] nums){
        this.nums = nums;
        this.result = new ArrayList<>();
        backtrack(0, new ArrayList<>());
        return result;
    }

    public void backtrack(int index, List<Integer> path){
        if(index == nums.length){
            result.add(new ArrayList<>(path));
            return;
        }

        // include nums[index]
        path.add(nums[index]);
        backtrack(index+1, path);

        //exclude nums[index]
        path.remove(path.size() - 1);
        backtrack(index+1, path);
    }
}

/**
 * Given an integer array nums of unique elements, return all possible subsets that can be made from the elements in nums.

The solution set must not contain duplicate subsets, and the subsets can be returned in any order.

Example 1:

Input:

nums = [1,2]
Output:

[[],[1],[2],[1,2]]
Example 2:

Input:

nums = [0]
Output:

[[],[0]]
 */

/**
 * Explanation
Let's start by figuring out how to incrementally generate all possible subsets of a given set, starting from an empty set.
 Doing so will help us visualize the "solution space tree" which we can then traverse using a depth-first search and a backtracking approach.
To generate all possible subsets, we can choose to either include or exclude each element in the set to generate all possible subsets. 
We'll walkthrough how to do this with a simple example where we generate all possible subsets of the set [1, 2, 3].
Step 1 (first element)
We start with an empty set []. We can either include or exclude the first element 1 to generate all possible subsets.
This gives us two possible subsets:
Include 1 in the subset: [1]
Exclude 1 from the subset: []
Subsets: [[], [1]]
Step 2 (second element)
Next, we move onto index = 1. For each of the subsets generated in the previous step, we can either include or exclude the element at index 1.
This gives us the following subsets:
Include 2 in the subset: [2]
Include 2 in the subset: [1, 2]
Exclude 2 from the subset: [1]
Exclude 2 from the subset: []
Subsets: [[], [1], [2], [1, 2]]
Step 3 (third element)
Finally, we move onto index = 2. For each of the subsets generated in the previous step, we can either include or exclude the element at index 2.
This gives us the following subsets:
Include 3 in the subset: [3]
Include 3 in the subset: [1, 3]
Include 3 in the subset: [2, 3]
Include 3 in the subset: [1, 2, 3]
Exclude 3 from the subset: []
Exclude 3 from the subset: [1]
Exclude 3 from the subset: [2]
Exclude 3 from the subset: [1, 2]
Visualized as a tree
 */