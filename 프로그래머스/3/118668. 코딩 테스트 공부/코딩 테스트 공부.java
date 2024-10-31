import java.util.*;
class Solution {
    
    int INF = Integer.MAX_VALUE / 2;
    int maxAlp, maxCop;
    int dp[][];
    public int solution(int alp, int cop, int[][] problems) {
            
        for(int problem[] : problems){
            maxAlp = Math.max(maxAlp, problem[0]);
            maxCop = Math.max(maxCop, problem[1]);
        }
        
        dp = new int[maxAlp+1][maxCop+1];
        
        alp = Math.min(alp, maxAlp);
        cop = Math.min(cop, maxCop);
        
        for(int row[]: dp){
            Arrays.fill(row, INF);
        }
        
        dp[alp][cop] = 0;
        
        for(int a = alp; a<=maxAlp; a++){
            for(int c = cop; c<=maxCop; c++){
                if(a + 1 <= maxAlp){
                    dp[a+1][c] = Math.min(dp[a+1][c], dp[a][c] + 1);
                }
                
                if(c + 1 <= maxCop){
                    dp[a][c+1] = Math.min(dp[a][c+1], dp[a][c] + 1);
                }
                
                for(int problem[] : problems){
                    if(problem[0] <= a && problem[1] <= c){
                        int alp_rwd = problem[2];
                        int cop_rwd = problem[3];
                        int cost = problem[4];
                        int nxtAlp = Math.min(maxAlp, a + alp_rwd);
                        int nxtCop = Math.min(maxCop, c + cop_rwd);
                        
                        dp[nxtAlp][nxtCop] = Math.min(dp[nxtAlp][nxtCop], dp[a][c] + cost);
                    }
                }
                
            }
        }
        
        
        return dp[maxAlp][maxCop];
    }
}