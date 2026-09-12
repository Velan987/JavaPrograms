package hellointerview.greedy;

public class JumpGame {
    
public boolean canJump(int[] nums) {
    int maxReach = 0;
    for (int i = 0; i < nums.length; i++) {
        if (i > maxReach) {
            return false;
        }
        maxReach = Math.max(maxReach, i + nums[i]);
    }
    return true;
}
}

/**
 * Write a function to determine whether you can travel from the first index to the last index of an integer array nums, 
 * where each number in the array specifies the maximum distance you can jump from that index. The function should return true if reaching the last index is possible, 
 * and false otherwise.

Input:

nums = [1, 3, 0, 1, 4]
Output:

true
Explanation: You can jump from index 0 to index 1, then jump from index 1 to index 4 which is the last index.

Example: Input:

nums = [2,2,1,0,5,1,1]
Output:

false
Explanation: The first three indexes take you to index 3 no matter what. But you cannot move beyond index 3 because its maximum jump length is 0, 
making the indexes after index 3 unreachable.
 */

/**
 * We don't need to track every possible path. We just need to track the farthest position we can reach so far.
 * As we iterate through the array:
At each position i, update max_reach = max(max_reach, i + nums[i])
If i > max_reach, we're stuck - return false
If we finish the loop, return true

Let's trace through nums = [2, 3, 1, 1, 4]:
i=0:
i=0 ≤ max_reach=0 ✓
max_reach = max(0, 0+2) = 2
i=0: Can we reach index 0? Yes (we start here). From here, we can reach up to index 2. max_reach = 2.

i=1:
i=1 ≤ max_reach=2 ✓
max_reach = max(2, 1+3) = 4
i=1: Can we reach index 1? Yes (1 ≤ 2). From here, we can reach up to index 4! max_reach = 4.

i=2:
i=2 ≤ max_reach=4 ✓
max_reach = max(4, 2+1) = 4
i=2: Can we reach index 2? Yes (2 ≤ 4). max_reach stays 4.
We continue... max_reach = 4 >= n-1 = 4. Answer: true!

When It Fails
What if we can't reach the end? Consider nums = [3, 2, 1, 0, 4]:
At i=3:
max_reach = 3
nums[3] = 0, can't go further
Stuck! Return false
At index 3, nums[3] = 0 means we can't jump anywhere. Since max_reach = 3 and we need to reach index 4, we're stuck.
 */