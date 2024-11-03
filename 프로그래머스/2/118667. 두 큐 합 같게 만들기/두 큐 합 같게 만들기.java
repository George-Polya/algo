import java.util.*;

class Solution {
    int n;
    public int solution(int[] queue1, int[] queue2) {
        n = queue1.length;
        long sum1 = 0;
        long sum2 = 0;
        
        Queue<Integer> q1 = new ArrayDeque<>();
        Queue<Integer> q2 = new ArrayDeque<>();
        for(int i = 0; i < n; i++){
            q1.add(queue1[i]);
            q2.add(queue2[i]);
            
            sum1 += queue1[i];
            sum2 += queue2[i];
        }
        
        int ans = 0;
        while(sum1 != sum2){
            if(ans > 4 * n){
                return -1;
            }
            
            if(sum1 < sum2){
                int value = q2.poll();
                q1.add(value);
                sum1 += value;
                sum2 -= value;
            }else if(sum1 > sum2){
                int value = q1.poll();
                q2.add(value);
                sum1 -= value;
                sum2 += value;
            }else{
                return ans;
            }
             
            ans++;
        }
        
        return ans;
        
    }
}