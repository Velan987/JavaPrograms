package hellointerview.twopointer;

public class TrappingRainWater {
    public static int trappingWater(int[] height) {
        //if there is only 2 bars then we cannot trap water between them
        if (height.length <= 2) {
            return 0;
        }
        int left = 0, right = height.length - 1;
        int leftMax = height[left], rightMax = height[right];
        int count = 0;
        // we will calculate the possible amount of water can be trap in every index
        // at every index we will check leftMax and rightMax, minimum of that - current height will be amount of water we can trap
        while (left < right) {
            // we can store water based on minimum of leftMax and rightMax, otherwise it will overflow 
            if (height[left] < height[right]) { 
                // if height of left is greater than left maximum we cannot store water - think of like a upward staircase - water will flow
                // so change the leftMax
                if (height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    // if height of left is less than leftMax 
                    //    water can stand because left side a bar is there with higher than current bar, 
                    //    to store water right side also there should be a bar which is heigher than current bar - this is already satisfied in the first if statement
                    //    the amount of water can be stored at any index is minimum(leftMax, rightMax) - current height
                    //    from outer if statement we know that leftMax is small so leftMax - current height is the water we can store in that index, above leftMax water will flow
                    count += leftMax - height[left];
                }
                left ++;
            } else {
                // if height of right is greater than right maximum we cannot store water - think of like a downward staircase - water will flow
                // so change rightMax
                if (height[right] >= rightMax) {
                    rightMax = height[right];
                } else {
                    // if height of right is less than rightMax 
                    //    water can stand because right side a bar is there with higher than current bar, 
                    //    to store water left side also there should be a bar which is heigher than current bar - this is already satisfied in the else statement
                    //    the amount of water can be stored at any index is minimum(leftMax, rightMax) - current height
                    //    from outer if statement we know that rightMax is small so rightMax - current height is the water we can store in that index, above rightMax water will flow
                    count += rightMax - height[right];
                }
                right--;
            }
        }

        return count;
    }
}


/**
 * https://www.hellointerview.com/learn/code/two-pointers/trapping-rain-water - check here for full understanding
 */