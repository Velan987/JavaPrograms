package Leetcode;
public class SortedArrayMedian {
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int l1 = nums1.length;
        int l2 = nums2.length;
        int l3 = l1+l2;
        int [] nums3 = new int[l3];
        int j=0;
        int k=0;
        for(int i=0;i<l3;i++){
            if( j< l1 && k<l2 && nums1[j] <= nums2[k]){
                nums3[i] = nums1[j];
                j++;
            }else if( j<l1 && k<l2 && nums1[j] > nums2[k]){
                nums3[i] = nums2[k];
                k++;
            }else if(j<l1 && k>=l2){
                nums3[i] = nums1[j];
                j++;
            }else{
                nums3[i] = nums2[k];
                k++;
            }
        }
        if(l3%2 != 0){ // array has odd number of elements
            return nums3[l3/2];
        }
        int mid = l3/2;
        double d = (double)(nums3[mid] + nums3[mid-1])/2;
        return d;
    }
    public static void main(String[] args) {
        int[] nums1 = {1, 3};
        int[] nums2 = {2};
        double result = findMedianSortedArrays(nums1, nums2);
        System.out.println(result);
    }
}

/*
    Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.

The overall run time complexity should be O(log (m+n)).

 

Example 1:

Input: nums1 = [1,3], nums2 = [2]
Output: 2.00000
Explanation: merged array = [1,2,3] and median is 2.
Example 2:

Input: nums1 = [1,2], nums2 = [3,4]
Output: 2.50000
Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.

*/