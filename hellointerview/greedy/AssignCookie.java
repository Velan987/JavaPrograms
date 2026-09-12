package hellointerview.greedy;

import java.util.Arrays;

public class AssignCookie {
    public static int satisfiedChild(int[] greed, int[] cookie){
        int count =0;
        Arrays.sort(greed);
        Arrays.sort(cookie);

        int i=0, j=0;
        while(i<greed.length && j < cookie.length){
            if(greed[i] <= cookie[j]){
                count++;
                i++;
            }
            j++;
        }

        return count;
    }
}

/**
 * given greed and cookie array find the maximum number of satisfied childrens
 * 
 * Greeds array will have children’s greed, 1 means that children will satisfy for cookie size >=1, 3 mens that children will satisfy for cookie size >=3. 
 * One child can not have 2 cookies also one cookie can not share with 2 children. If we give big cookie to smaller greedy child then we are wasting that cookie, 
 * so we can not give randomly. We need to satisfy the least greedy child first using the smallest cookie that work, don’t waste big cookie for easy to please child. 
 * If we sort both array then we can achieve this.
 */