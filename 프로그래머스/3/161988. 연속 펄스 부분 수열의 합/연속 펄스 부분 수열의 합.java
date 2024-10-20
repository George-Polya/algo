import java.util.*;
class Solution {
    int n;
    int purse1[], purse2[];
    long dp1[], dp2[];
    public long solution(int[] sequence) {
        n = sequence.length;
        purse1 = new int[n];
        purse2 = new int[n];
        
        for(int i = 0; i < n; i++){
            if(i % 2 == 1){
                purse1[i] = sequence[i];
                purse2[i] = -sequence[i];
            }else{
                purse1[i] = -sequence[i];
                purse2[i] = sequence[i];
            }
        }
        // System.out.println(Arrays.toString(purse1));
        // System.out.println(Arrays.toString(purse2));
        dp1 = new long[n];
        dp2 = new long[n];
        
        dp1[0] = purse1[0];
        dp2[0] = purse2[0];
        
        long ans = Math.max(dp1[0], dp2[0]);
        
        for(int i = 1; i < n;i++){
            dp1[i] = Math.max(purse1[i], dp1[i-1] + purse1[i]);
            dp2[i] = Math.max(purse2[i], dp2[i-1] + purse2[i]);
            
            ans = Math.max(ans, Math.max(dp1[i], dp2[i]));
        }
         
        
        return ans;
    }
}