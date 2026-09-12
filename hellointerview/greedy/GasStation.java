package hellointerview.greedy;

public class GasStation {
    public int canCompleteCircuit(int[] gas, int[] cost) {
    int totalGas = 0, totalCost = 0;
    for (int i = 0; i < gas.length; i++) {
        totalGas += gas[i];
        totalCost += cost[i];
    }
    if (totalGas < totalCost) {
        return -1;
    }
    
    int start = 0, fuel = 0;
    for (int i = 0; i < gas.length; i++) {
        if (fuel + gas[i] - cost[i] < 0) {
            // can't reach next station:
            // try starting from next station
            start = i + 1;
            fuel = 0;
        } else {
            // can reach next station:
            // update remaining fuel
            fuel += gas[i] - cost[i];
        }
    }
    
    return start;
}
}

/**
 * There are n gas stations along a circular route. You are given two integer arrays gas and cost of length n. 
 * At each gas station i, gas[i] represents the amount of gas you receive by stopping at this station, 
 * and cost[i] represents the amount of gas required to travel from station i to the next station. You begin the journey with an empty tank at one of the gas stations.

Write a function to return the starting gas station's index if you can travel around the circuit once in the clockwise direction; otherwise, return -1. 
Note that if there exists a solution, it is guaranteed to be unique. Also, you can only travel from station i to station i + 1,
 and the last station will lead back to the first station.

Input:

gas = [5,2,0,3,3]
cost = [1,5,5,1,1]
Output:

3
Explanation:

Start at station 3 and fill up with 3 units of gas. Your tank = 0 + 3 = 3 Travel to station 4 with 1 unit of gas, and fill up with 3 units of gas. 
Your tank = 3 - 1 + 3 = 5 Travel to station 0 with 1 unit of gas, and fill up with 5 units of gas. Your tank = 5 - 1 + 5 = 9 Travel to station 1 with 1 unit of gas, 
and fill up with 2 units of gas. Your tank = 9 - 1 + 2 = 10 Travel to station 2 with 5 units of gas, and fill up with 0 units of gas. Your tank = 10 - 5 + 0 = 5 
Travel back to station 3 with 5 units of gas to complete the circuit. Therefore, return 3 as the starting index.
 */

/**
 * Walkthrough
 * If there is more gas along the route than the cost of the route, then there is guaranteed to be a solution to the problem. 
 * So the first step is to check if the sum of the gas is greater than or equal to the sum of the cost. If it is not, then we return -1.
Next, we iterate through the gas station to find the starting index of our circuit using a greedy approach: whenever we don't have enough gas to reach the next station, 
we move our starting gas station to the next station and reset our gas tank.

We start at the first station, and fill our tank with gas[0] = 5 units of gas. From there, it takes cost[0] = 1 units of gas to travel to the next station, 
so we arrive at station 2 (index 1) with 4 units of gas. At station 2, we fill our tank with gas[1] = 2 units of gas, for a total of 6 units of gas. 
It takes cost[1] = 5 units of gas to travel to the next station, so we arrive at station 3 with 1 unit of gas.

Greedy Approach
Now at station 3, we fill our tank with gas[2] = 0 units of gas, for a total of 1 unit of gas. It takes cost[2] = 5 units of gas to travel to the next station, 
which we don't have.
This is where our greedy approach comes in. We reset our starting station to the next station i + 1 and reset our gas tank to 0. 
We can do this because all other start indexes between 0 and 2 will also run into the same problem of not having enough gas to reach the next station, 
so we can rule them out. If we follow this approach of resetting the start index and gas tank whenever we don't have enough gas to reach the next station, 
then when we finish iterating, the last start index will be the solution to the problem.
 */