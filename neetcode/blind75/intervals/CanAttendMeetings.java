package neetcode.blind75.intervals;

import java.util.List;

class Interval {
     public int start, end;
     public Interval(int start, int end) {
         this.start = start;
         this.end = end;
     }
  }
 

public class CanAttendMeetings {
    public boolean canAttendMeetings(List<Interval> intervals) {
        if(intervals.size() == 0)
            return true;

        // Sort intervals by start of the interval
        intervals.sort((a,b) -> a.start - b.start);
        for(int i=1;i<intervals.size(); i++){
            // we are starting with index 1, so starting with second interval
            // if second interval first element is less than previous interval's last element then that is overlapping
            if(intervals.get(i).start < intervals.get(i-1).end){
                return false;
            }
        }

        return true;
    }
}


/**
 * Given an array of meeting time interval objects consisting of start and end times [[start_1,end_1],[start_2,end_2],...] (start_i < end_i), determine if a person could add all meetings to their schedule without any conflicts. The intervals may be provided in any order.

Note: (0,8),(8,10) is not considered a conflict at 8

Example 1:

Input: intervals = [(0,30),(5,10),(15,20)]

Output: false
Explanation:

(0,30) and (5,10) will conflict
(0,30) and (15,20) will conflict
Example 2:

Input: intervals = [(5,8),(9,15)]

Output: true
 */