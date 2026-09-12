package hellointerview.topologicalsort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

// consider prerequisites as graph
public class CourseSchedule {
    public static boolean canFinish(int numCourses, int[][] prerequisites){

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

        int count = 0;
        // iterate until queue is not empty
        while(!queue.isEmpty()){
            // get the course
            int course = queue.poll();
            // mark one course is taken
            count++;
            // if one course is taken then we should reduce the indegree for the dependent(neighbor) courses
            for(int neighbor: graph.getOrDefault(course,    new ArrayList<>())){
                indegrees[neighbor]--;
                // if indegree is 0, that is there is no dependency then add it to queue
                if(indegrees[neighbor] == 0){
                    queue.offer(neighbor);
                }
            }

        }

        return count == numCourses;
    }

}


/**
 * You have to take a total of numCourses courses, which are labeled from 0 to numCourses - 1. You are given a list of prerequisites pairs, 
 * where prerequisites[i] = [a, b] indicates that you must complete course b before course a.

Given the total number of courses and a list of prerequisite pairs, write a function to determine if it is possible to finish all courses.

Example 1:

Input:

numCourses = 3
prerequisites = [[1, 0], [2, 1]]
Output: true

Explanation: You can take courses in the following order: 0, 1, 2.

Example 2:

Input:

numCourses = 3
prerequisites = [[1, 0], [0, 1],[1,2]]
Output: false

Explanation: It is impossible to finish all courses, as you must finish course 1 before course 0 and course 0 before course 1.
 */