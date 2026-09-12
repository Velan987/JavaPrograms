package hellointerview.fixedlengthslidingwindow;

// https://www.hellointerview.com/learn/code/sliding-window/maximum-points-you-can-obtain-from-cards
public class MaxPointsFromCards {
    /**
     * we need to pick zero or more cards from beginning and pick zero or more cards from end
     * we cannot skip both end 
     * first glance it might look it is not fixed length sliding window problem but it is
     * instead of considering k elments for sliding window consider inputArrayLength-k cards for sliding window
     * for 7 elments and k is 3, our sliding window is 4, because at any combination we will skip 4 continuous values only
     * it is reverse of sliding window ( for easy understanding)
     * so first take sum of all elements, then subtrack sliding window sum from total, this will give sum of k elements.
     * 
     */
    public int maxPointsFromCards(int[] cards, int k){
        int maxPoint = 0;

        int total = 0;
        for(int i : cards){
            total += i;
        }
        if(k == cards.length)
            return total;

        // in this problem we are picking k cards and skipping length-k cards
        // skipping cards is continuous - either from beginning or mid we have to skip length-k cards - that is our sliding window candidate
        // to get the picked cards sum we can subtract the slidingwindowsum from total
        int slidingWindow = cards.length - k;
        int slidingWindowSum = 0;
        // start and end indexes are sliding windows indexes
        int start = 0;
        for (int end=0; end< cards.length; end++){
            slidingWindowSum += cards[end];
            if(end - start == slidingWindow-1){ // index starts with 0 so slidingWindow -1 - if it matches then we have a sliding windows
                maxPoint = Math.max(maxPoint, total-slidingWindowSum);
                // subtracting current sliding window start value from sliding window sum, so that in next iteration we just need to add next value
                slidingWindowSum -= cards[start];
                start ++;
            }
        }

        return maxPoint;
    }
}


/**
 * Given an array of integers representing card values, write a function to calculate the maximum score you can achieve by picking exactly k cards.

You must pick cards in order from either end. You can take some cards from the beginning, then switch to taking cards from the end, but you cannot skip cards or pick from the middle.

For example, with k = 3:

Take the first 3 cards: valid
Take the last 3 cards: valid
Take the first card, then the last 2 cards: valid
Take the first 2 cards, then the last card: valid
Take card at index 0, skip some, then take card at index 5: not valid (skipping cards)
Constraints: 1 <= k <= cards.length

Example 1: Input:

cards = [2,11,4,5,3,9,2]
k = 3
Output:

17
Explanation:

First 3 cards: 2 + 11 + 4 = 17
Last 3 cards: 3 + 9 + 2 = 14
First 1 + last 2: 2 + 9 + 2 = 13
First 2 + last 1: 2 + 11 + 2 = 15
 */