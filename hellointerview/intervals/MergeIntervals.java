package hellointerview.intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeIntervals {
    public int[][] mergeIntervals(int[][] intervals) {
        List<int[]> merged = new ArrayList<>();
        // Sort the intervals by start of the interval
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        for (int[] interval : intervals) {
            // if list is empty or current interval start time is greater than list's last interval end time  then there is no issue, so just add it
            if (merged.isEmpty() || interval[0] > merged.get(merged.size() - 1)[1]) {
                merged.add(interval);
            } else {
                // current interval's starttime is <= list's last interval's end time - that meeans there is overlap
                // since previous interval is already there in the list and we sorted the interval by start time so the starttime of interval in the list is the smallest
                // now to merge these 2 intervals we just need to change the endtime of interval in the list.
                merged.get(merged.size() - 1)[1] = Math.max(interval[1], merged.get(merged.size() - 1)[1]);
            }
        }

        return merged.toArray(new int[merged.size()][]);
    }
}


/**
 * Write a function to consolidate overlapping intervals within a given array intervals, where each interval intervals[i] consists of a start time starti and an end time endi.

Two intervals are considered overlapping if they share any common time, including if one ends exactly when another begins (e.g., [1,4] and [4,5] overlap and should be merged into [1,5]).

The function should return an array of the merged intervals so that no two intervals overlap and all the intervals collectively cover all the time ranges in the original input.

Input:

intervals = [[3,5],[1,4],[7,9],[6,8]]
Output:

[[1,5],[6,9]]
Explanation: The intervals [3,5] and [1,4] overlap and are merged into [1,5]. Similarly, [7,9] and [6,8] overlap and are merged into [6,9].
 */