package neetcode.blind75.intervals;

import java.util.Arrays;
import java.util.List;

class Interval {
  public int start, end;

  public Interval(int start, int end) {
    this.start = start;
    this.end = end;
  }
}

public class MeetingRooms {
  public int minMeetingRooms(List<Interval> intervals) {
    int ongoingMeeting = 0, res = 0;

    /**
     * we need to find how many meeting rooms needed
     * basically we need to find maximum number of meetings happening at same time
     * will have 2 arrays (start and end) both will be sorted
     * we will iterate through start array and calculate number of meetings happening at that time
     * will have 2 indexes called s,e to iterate start and end
     * while s<size
     *    if start < end - then there is a meeting on going
     *        increament count 
     *        s ++
     *    else
     *      decrement count
     *      e ++
     *        
     *    res = max(res,count)
     */
    int s=0, e=0, size = intervals.size();
    int[] start = new int[size];
    int[] end = new int[size];
    int i=0;
    for(Interval interval: intervals){
      start[i] = interval.start;
      end[i] = interval.end;
      i++;
    }
    Arrays.sort(start);
    Arrays.sort(end);

    while(s < size){
      // A meeting is inprogress - increament ongoing meeting count
      if(start[s] < end[e]){
        ongoingMeeting ++;
        s ++;
      }else{ // one meeting is finished, so we need to reduce the ongoing meeting
        ongoingMeeting --;
        e ++;
      }
      // each iteration will check ongoing meeting count with previous ongoing meeting
      res = Math.max(res, ongoingMeeting);
    }


    return res;
  }
}

/**
 * Given an array of meeting time interval objects consisting of start and end
 * times [[start_1,end_1],[start_2,end_2],...] (start_i < end_i), find the
 * minimum number of rooms required to schedule all meetings without any
 * conflicts.
 * 
 * Note: (0,8),(8,10) is NOT considered a conflict at 8.
 * 
 * Example 1:
 * 
 * Input: intervals = [(0,40),(5,10),(15,20)]
 * 
 * Output: 2
 * Explanation:
 * room1: (0,40)
 * room2: (5,10),(15,20)
 * 
 * Example 2:
 * 
 * Input: intervals = [(4,9)]
 * 
 * Output: 1
 * 
 */