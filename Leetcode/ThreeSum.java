package Leetcode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        //[-1,0,1,2,-1,-4]
        Arrays.sort(nums); 
        //[-4,-1,-1,0,1,2]
        int expectedSum = 0;
        // After sorting if first element itself greater than expectedSum then there is no possible triplet
        if(nums[0]>expectedSum){
            return result;
        }
        for(int i=0; i<nums.length-2;i++){
            if(nums[0]>expectedSum){ // single number itself greater than expected sum
                break;
            }
            //nums[i] is fixed element - if that fixed element is already considered then skipping
            // with this we can avoid duplicate triplets
            if(i>0 && nums[i] == nums[i-1]){
                continue;
            }
            int target = - nums[i];
            int left = i+1;
            int right = nums.length-1;
            while(left < right){
                int sum = nums[left] + nums[right];
                //[-4,-1,-1,0,1,2]
                if(sum == target){
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    // if left and left+1 index elements are same then for an already fixed first element nums[i] this will result in duplicate triplet
                    // for example [-2,0,0,2] this input if i=0, fixed element is -2 and target is +2
                    //left is 1 and right is 3 => nums[1] is 0 and nums[3] is 2 => 0 +2 = 2 which is expected, so [0,0,2] this is one triplet
                    //in next iteration left++
                    //left is 2 and right is 3 => nums[2] is 0 and nums[3] is 2 => 0 + 2 = 2 which is expected, so [0,0,2] this is another triplet - this causes duplicate
                    //if left value and left+1 values are same that means same combination already added, so if we skip that we can avoid duplicate triplet
                    while(left < right && nums[left] == nums[left+1]){
                        left ++;
                    }
                    while(left < right && nums[right] == nums[right-1]){
                        right --;
                    }
                    // next value of left is not same as current value of left
                    left ++;
                    right --;
                }else if(sum < target){
                    left ++; // we need a bigger sum to match target, in a sorted array if we go right we will get bigger element
                }else{
                    right --; // last case which is we need a smaller sum to match target so reducing the right pointer
                }

                
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {-1,0,1,2,-1,-4};
        System.out.println(threeSum(nums));
    }
}

/**
 * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.

Notice that the solution set must not contain duplicate triplets.

 

Example 1:

Input: nums = [-1,0,1,2,-1,-4]
Output: [[-1,-1,2],[-1,0,1]]
Explanation: 
nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
The distinct triplets are [-1,0,1] and [-1,-1,2].
Notice that the order of the output and the order of the triplets does not matter.
Example 2:

Input: nums = [0,1,1]
Output: []
Explanation: The only possible triplet does not sum up to 0.
Example 3:

Input: nums = [0,0,0]
Output: [[0,0,0]]
Explanation: The only possible triplet sums up to 0.
 

Constraints:

3 <= nums.length <= 3000
-105 <= nums[i] <= 105
 */


/**
 * 

## Setup — Why sorting enables duplicate skipping

```
Original: [-1, 0, 1, 2, -1, -4]
Sorted:   [-4, -1, -1,  0,  1,  2]
            ↑
         Sorting groups identical values together,
         so duplicates are always adjacent → easy to detect with nums[x] == nums[x-1]
```

---

## Level 1 — Skipping duplicate `i` (outer loop)

```java
if (i > 0 && nums[i] == nums[i - 1]) continue;
```

```
[-4, -1, -1,  0,  1,  2]
      i=1  i=2

When i=1, nums[i] = -1 → we find triplets [-1,-1,2] and [-1,0,1]
When i=2, nums[i] = -1 → SAME fixed number, would produce SAME triplets again → SKIP

The guard "i > 0" prevents checking nums[-1] on the first iteration.
```

**Rule:** If the current fixed number equals the previous one, every pair we'd find with it has already been found — skip entirely.

---

## Level 2 & 3 — Skipping duplicate `left` and `right` (inner while loop)

```java
if (sum == target) {
    result.add(...);

    while (left < right && nums[left] == nums[left + 1]) left++;   // Level 2
    while (left < right && nums[right] == nums[right - 1]) right--; // Level 3

    left++;
    right--;
}
```

Let's trace with `nums = [-2, 0, 0, 2, 2]`, fixed `i=0` (value = -2), target = 2:

```
Step 1:
  [-2,  0,  0,  2,  2]
        L           R
  sum = 0 + 2 = 2 ✓ → add [-2, 0, 2]

Step 2: Now skip duplicates BEFORE moving pointers
  [-2,  0,  0,  2,  2]
        L   →           nums[left]==nums[left+1]? 0==0 YES → left++
             L

  [-2,  0,  0,  2,  2]
             L      R   nums[right]==nums[right-1]? 2==2 YES → right--
                    ←R

Step 3: Then move pointers inward
  [-2,  0,  0,  2,  2]
              L  R       left++ and right--
                         left >= right → STOP

Result: [[-2, 0, 2]]  ← only ONE triplet, not four duplicates!
```

**Without duplicate skipping**, we'd incorrectly add:
```
[-2, 0(idx1), 2(idx3)]
[-2, 0(idx1), 2(idx4)]  ← duplicate!
[-2, 0(idx2), 2(idx3)]  ← duplicate!
[-2, 0(idx2), 2(idx4)]  ← duplicate!
```

---

## All 3 levels together — Full trace on `[-1,0,1,2,-1,-4]`

```
Sorted: [-4, -1, -1,  0,  1,  2]
indexes:   0   1   2  3   4   5

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
i=0, nums[i]=-4, target=4
  L=1, R=5 → -1+2=1 < 4 → L++
  L=2, R=5 →  -1+2=1 < 4 → L++
  L=3, R=5 →  0+2=2 < 4 → L++
  L=4, R=5 →  1+2=3 < 4 → L++
  L=5, R=5 → stop
  No triplets found.

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
i=1, nums[i]=-1, target=1
  L=2, R=5 → -1+2=1 ✓ → add [-1,-1,2]
    skip L dupes: nums[2]==nums[3]? -1==0 NO
    skip R dupes: nums[5]==nums[4]?  2==1 NO
    L++→3, R--→4
  L=3, R=4 →  0+1=1 ✓ → add [-1,0,1]
    skip L dupes: nums[3]==nums[4]? 0==1 NO
    skip R dupes: nums[4]==nums[3]? 1==0 NO
    L++→4, R--→3
  L >= R → stop

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
i=2, nums[i]=-1
  nums[2] == nums[1]? -1 == -1 → YES → SKIP (Level 1 duplicate skip)

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
i=3, nums[i]=0, target=0
  nums[i] > 0? NO, continue
  L=4, R=5 → 1+2=3 > 0 → R--
  L=4, R=4 → stop

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
i=4: only 1 element left → loop ends (i < nums.length - 2)

Final result: [[-1,-1,2], [-1,0,1]] ✓
```

---

## Summary

| Skip Location | Code | Prevents |
|---|---|---|
| **Outer `i` loop** | `if (i > 0 && nums[i] == nums[i-1])` | Re-processing same fixed number |
| **Inner `left` pointer** | `while (nums[left] == nums[left+1])` | Same left value forming duplicate pairs |
| **Inner `right` pointer** | `while (nums[right] == nums[right-1])` | Same right value forming duplicate pairs |

The core idea: **once you've found a valid triplet, any adjacent identical values would form the exact same triplet — so jump past all of them before continuing.**
 */