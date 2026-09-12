package hellointerview.greedy;

public class BestTimeStock {
    public static Integer maxProfit(int[] prices) {
        int maxProfit = 0;
        // we can not sort the array, if we sort then [9,7,5,3,1] this will give profit of 8 but this will not give any profit because it is full downtrend only
        // each step calculate profit and store max profit
        int minPrice = prices[0];
        for(int i=0;i<prices.length;i++){
            minPrice = Math.min(minPrice, prices[i]);
            maxProfit = Math.max(prices[i]-minPrice, maxProfit);
        }
        return maxProfit;
    }
    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{3,4,6,2,5,8}));
    }
}


/**
 * Write a function to determine the maximum profit you can obtain from a series of stock prices given in an array prices, where prices[i] represents the stock price on the ith day. You are allowed to buy and then sell the stock once, as long as as the sell date is after the buy date. If no profit can be made, the function should return 0.

Input:

prices = [3,4,6,2,5,8]
Output:

6
Explanation: Buy on day 4 (price = 2) and sell on day 6 (price = 8), profit = 8-2 = 6.

Input:

prices = [9,7,5,3,1]
Output:

0
Explanation: Prices are in descending order, so there's no opportunity to make a profit, thus the maximum profit = 0.
 */