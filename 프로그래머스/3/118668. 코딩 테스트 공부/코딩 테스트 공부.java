import java.util.*;
class Solution {
    
    int INF = Integer.MAX_VALUE / 2;
    int maxAlp, maxCop;
    int dp[][];
    int problems[][];
    
    int solve(int alp, int cop){
        if(maxAlp <= alp && maxCop <= cop)
            return 0;
        
        if(dp[alp][cop] != INF)
            return dp[alp][cop];
        
        dp[alp][cop] = INF + 1;
        int nxtAlp = Math.min(maxAlp, alp+1);
        int nxtCop = Math.min(maxCop, cop+1);
        
        dp[alp][cop] = Math.min(dp[alp][cop], solve(nxtAlp, cop) + 1);
        dp[alp][cop] = Math.min(dp[alp][cop], solve(alp, nxtCop) + 1);
        
        
        for(int problem[] : problems){
            int alp_req = problem[0];
            int cop_req = problem[1];
            int alp_rwd = problem[2];
            int cop_rwd = problem[3];
            int cost = problem[4];
            
            if(alp_req <= alp && cop_req <= cop){
                nxtAlp = Math.min(maxAlp, alp + alp_rwd);
                nxtCop = Math.min(maxCop, cop + cop_rwd);
                dp[alp][cop] = Math.min(dp[alp][cop], solve(nxtAlp, nxtCop) + cost);
            }
        }
        
        return dp[alp][cop];
    }
    
    public int solution(int alp, int cop, int[][] _problems) {
        problems = _problems;
        for(int problem[] : problems ){
            maxAlp = Math.max(maxAlp, problem[0]);
            maxCop = Math.max(maxCop, problem[1]);
        }
        
        dp = new int[maxAlp+1][maxCop+1];
        for(int row[] : dp){
            Arrays.fill(row, INF);
        }
        
        alp = Math.min(alp, maxAlp);
        cop = Math.min(cop, maxCop);
        
        return solve(alp, cop);
        
    }
}