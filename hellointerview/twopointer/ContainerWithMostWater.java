package hellointerview.twopointer;

public class ContainerWithMostWater {
    public Integer max_area(int[] heights) {
        int maxarea = 0;
        int left = 0;
        int right = heights.length - 1;
        while (left < right){
            int height = Math.min(heights[left],heights[right]);
            int widht = right - left;
            maxarea = Math.max(maxarea, height * widht);
            if(heights[left] < heights[right]){
                left++;
            } else {
                right --;
            }
        }
        return maxarea;
    }
}

