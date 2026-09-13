package hellointerview.intervals;

import java.util.Arrays;

public class NonOverlappingIntervals {
    public Integer nonOverlappingIntervals(int[][] intervals) {
        int result = 0;

        if(intervals.length == 0)
            return 0;

        // this is a non overlapping interval we need, so we need to sort by end time
        Arrays.sort(intervals, (a, b)-> a[1] - b[1]);
        int end = intervals[0][1];
        // we are finding non overlapping intervals count and then subtracting from interval length
        // length will start from 1 to n, not 0 to n-1, i.e why count starts with 1
        // if count starts with 0 then while subtracting we need to subtract one additionally
        int count = 1;

        for (int i=1; i<intervals.length; i++){
            // if current start is greater than or equal to previous end then it is a non overlapping interval
            // problem description is given as Intervals that only touch at their endpoints are not considered overlapping (e.g., [2,5] and [5,7] do not overlap).
            // that is why checking >=
            if(intervals[i][0] >= end){
                count ++;
                end = intervals[i][1];
            }
        }

        return intervals.length - count;
    }
}

/**
 * Write a function to return the minimum number of intervals that must be removed from a given array intervals, 
 * where intervals[i] consists of a starting point starti and an ending point endi, to ensure that the remaining intervals do not overlap. 
 * Intervals that only touch at their endpoints are not considered overlapping (e.g., [2,5] and [5,7] do not overlap).

Input:

intervals = [[1,3],[5,8],[4,10],[11,13]]
Output:

1
Explanation: Removing the interval [4,10] leaves all other intervals non-overlapping.
 */