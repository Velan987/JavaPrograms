package neetcode.blind75.dynamicprogramming;

import java.util.HashMap;
import java.util.Map;

public class CoinChange {
    public int coinChange(int[] coins, int amount) {

        int minCoins = dfs(coins, amount);
        return minCoins>= 1e9? -1 : minCoins;
    }
    private int dfs(int[] coins, int amount){
        if(amount ==0)
            return 0;
        /**
         * 1e9 value is 1 * 10 ^ 9 which is 1,000,000,000
         * Integer.MAX_VALUE is 2,147,483,647
         * here instead of max value we are using 1e9, because in recursion call we are adding 1
         * Integer.MAX_VALUE + 1 will give negative value which is -2,147,483,648 
         * to avoid this we are using 1e9 which is lesser than integer max value and addition few one's will not overflow
         */
        int res = (int) 1e9;
        /**
         * iterate through every coins and do recursive check - it will cover all possible combination 
         * [1,5,10], amount = 12 - in this example first we will consider 1 for 12 times after that in callstack there will be 12 entries 
         * for each entries for loop will run with next values
         * (how each callstack for loop will start from 1 is we are passing the coins array not index)
         * for loop will run like 12-1, 11-1, 10-1, .. 1-1 -> at this amount will be 0, in the next call if(amount==0) satisfies and return 0
         * this it will go to previous call from call stack, which is amount=2 -> with this for loop iterate with next coins which is 5 and 10
         * in 5 case: 2-5>=0 is false, so no recursion call
         * in 10 case: 2-10>=0 is false, so no recursion call
         * 
         * now it will take previous call from call stack, for which amount=3, for loop iterates with next coins 5 and 10
         * here also there wont be any recursion call
         * .
         * .
         * .
         * when amount=5, 5-5 will give 0 so that time one recursion call will happen ( this gives one possibilities of 7 one rupee coin and one 5 rupee coin)
         * and so on. basically it will generate every possible combination, we need only mininum coins, that is why taking min before storing it in result
         */
        for(int coin: coins){
            if(amount - coin >=0){
                res = Math.min(res, 1+ dfs(coins, amount-coin));
            }
        }
        return res;
    }

    // here repeating pattern is, we are doing recursion for same amount multiple times, so we need to cache the result for amount
    Map<Integer, Integer> memo = new HashMap<>();
    public int coinChangeV2(int[] coins, int amount) {

        int minCoins = dfsV2(coins, amount);
        return minCoins>= 1e9? -1 : minCoins;
    }
    private int dfsV2(int[] coins, int amount){
        if(amount ==0)
            return 0;
        if(memo.containsKey(amount)){
            return memo.get(amount);
        }
        int res = (int) 1e9;
        for(int coin: coins){
            if(amount - coin >=0){
                res = Math.min(res, 1+ dfsV2(coins, amount-coin));
            }
        }
        memo.put(amount, res);
        return res;
    }
}

/**
 * You are given an integer array coins representing coins of different denominations (e.g. 1 dollar, 5 dollars, etc) 
 * and an integer amount representing a target amount of money.

Return the fewest number of coins that you need to make up the exact target amount. If it is impossible to make up the amount, return -1.

You may assume that you have an unlimited number of each coin.

Example 1:

Input: coins = [1,5,10], amount = 12

Output: 3
Explanation: 12 = 10 + 1 + 1. Note that we do not have to use every kind coin available.

Example 2:

Input: coins = [2], amount = 3

Output: -1
Explanation: The amount of 3 cannot be made up with coins of 2.

Example 3:

Input: coins = [1], amount = 0

Output: 0
Explanation: Choosing 0 coins is a valid way to make up 0.

Constraints:

1 <= coins.length <= 10
1 <= coins[i] <= 2^31 - 1
0 <= amount <= 10000
 */