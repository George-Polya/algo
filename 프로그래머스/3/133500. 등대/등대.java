import java.util.*;
class Solution {
    int n;
    ArrayList<Integer> adj[];
    boolean visited[];
    int dp[][];
    
    void dfs(int cur){
        dp[cur][1] = 1;
        
       for(int nxt : adj[cur]){
           if(visited[nxt])
               continue;
           visited[nxt]= true;
           dfs(nxt);
           
           dp[cur][0] += dp[nxt][1];
           dp[cur][1] += Math.min(dp[nxt][0], dp[nxt][1]);

       }
    }
    
    public int solution(int N, int[][] lighthouse) {
        
        n = N;
        visited = new boolean[n+1];
        adj = new ArrayList[n+1];
        dp = new int[n+1][2];
        
        for(int i = 1; i<=n; i++){
            adj[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < lighthouse.length;i++){
            int u = lighthouse[i][0];
            int v = lighthouse[i][1];
            
            adj[u].add(v);
            adj[v].add(u);
        }
        
        visited[1] = true;
        dfs(1);
        
        return Math.min(dp[1][0], dp[1][1]);
    }
}