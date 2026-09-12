package hellointerview.intervals;

import java.util.ArrayList;
import java.util.List;

public class InsertInterval {

    public int[][] insertIntervals(int[][] intervals, int[] newInterval) {
        /**
         * intervals is already sorted, no overlapping is there
         */
        List<int[]> merged = new ArrayList<>();
        int i = 0;
        int n = intervals.length;
        // current intervals end is less than new interval start time then there is no overlap
        // we will iterate this loop until there is a overlap
        while (i < n && intervals[i][1] < newInterval[0]) {
            merged.add(intervals[i]);
            i++;
        }
        // if there is a overlap
        // we need to merge those two intervals by taking minumum for start and maximum for end
        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(intervals[i][0], newInterval[0]);
            newInterval[1] = Math.max(intervals[i][1], newInterval[1]);
            i++;
        }
        merged.add(newInterval);
        
        for (int j = i; j < n; j++) {
            merged.add(intervals[j]);
        }
        /**
         * new int[3][] 
         * creates space for three int[] references:
            [
                null,
                null,
                null
            ]
            toArray() then fills those positions using the arrays from merged.
         */
        return merged.toArray(new int[merged.size()][]);
    }
}

/**
 * Given a list of intervals intervals and an interval newInterval, write a function to insert newInterval into a list of existing, non-overlapping, and sorted intervals based on their starting points. The function should ensure that after the new interval is added, the list remains sorted without any overlapping intervals, merging them if needed.

Two intervals are considered overlapping if they share any common time, including if one ends exactly when another begins (e.g., [1,4] and [4,7] overlap and should be merged into [1,7]).

Input:

intervals = [[1,3],[6,9]]
newInterval = [2,5]
Output:

[[1,5],[6,9]]
Explanation: The new interval [2,5] overlaps with [1,3], so they are merged into [1,5].

Input:

intervals = [[1,2],[3,5],[6,7],[8,10]]
newInterval = [5,6]
Output:

[[1,2],[3,7],[8,10]]
Explanation: The new interval [5,6] touches [3,5] and [6,7], so all three are merged into [3,7].
 */