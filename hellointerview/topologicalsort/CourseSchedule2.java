package hellointerview.topologicalsort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

// consider prerequisites as graph
public class CourseSchedule2 {
    public static int[] canFinish(int numCourses, int[][] prerequisites){

        // this will store dependent for each course
        // for example to take English and Maths I should complete Tamil first, also to take English I should complete Hindi first
        // prerequisites will be like [ [E, T], [M, T], [E, H]]
        // English and Maths dependending on Tamil and English is depending on Hindi
        // graph = {T: [E,M], H: [E]}
        // indegree of T and E is 0, both will go to queue and if T goes first, after dequeue E and M's indegree will be decremented
        Map<Integer, List<Integer>> graph = new HashMap<>();

        // Array to store indegrees of every courses
        int[] indegrees = new int[numCourses];

        // store indegree and dependecy
        for(int[] prerequisite : prerequisites){
            int dest = prerequisite[0];
            int src = prerequisite[1];
            // incrementing the indegree
            indegrees[dest]++;
            // below is a 2 step process and it is equivalent to
            // if(!graph.containsKey(src)){
            //   graph.put(src, new ArrayList<>());
            //}
            // graph.get(src).add(dest);

            // HashMap computeIfAbsent - adds value to map only when the key is not already there in the map
            //If the second iteration has the same src, computeIfAbsent() returns the existing list instead of creating a new one. The new dest is then added to that list.
            graph.computeIfAbsent(src, k -> new ArrayList<>()).add(dest);
        }

        Queue<Integer> queue = new LinkedList<>();
        // Add all nodes which has indegree 0(no dependency nodes) to the queue
        for(int i=0; i<numCourses; i++){
            if(indegrees[i] == 0){
                queue.offer(i);
            }
        }

        List<Integer> result = new ArrayList<>();
        // iterate until queue is not empty
        while(!queue.isEmpty()){
            // get the course
            int course = queue.poll();
            // mark one course is taken
            result.add(course);
            // if one course is taken then we should reduce the indegree for the dependent(neighbor) courses
            for(int neighbor: graph.getOrDefault(course,    new ArrayList<>())){
                indegrees[neighbor]--;
                // if indegree is 0, that is there is no dependency then add it to queue
                if(indegrees[neighbor] == 0){
                    queue.offer(neighbor);
                }
            }

        }
        if(result.size() == numCourses)
            return result.stream().mapToInt(Integer::intValue).toArray();

        return new int[0];
    }

}


/**
 DESCRIPTION (inspired by Leetcode.com)
You have to take a total of numCourses courses, which are labeled from 0 to numCourses - 1. You are given a list of prerequisites pairs, where prerequisites[i] = [a, b] indicates that you must complete course b before course a.

Given the total number of courses and a list of prerequisite pairs, write a function to return the ordering of courses you should take to finish all courses.

If there are multiple valid orderings, return any valid ordering. If it is impossible to finish all courses, return an empty array.

Example 1:

Input:

numCourses = 4
prerequisites = [[1,0], [2,0], [3,1], [3,2]]
Output: [0, 1, 2, 3] or [0, 2, 1, 3]

Explanation: There are a couple of ways to complete all courses, one possible order is [0, 1, 2, 3] and another is [0, 2, 1, 3].

Example 2:

Input:

numCourses = 2
prerequisites = [[1, 0], [0, 1]]
Output: []

Explanation: It is impossible to finish all courses, as you must finish course 0 before course 1 and course 1 before course 0.
 */