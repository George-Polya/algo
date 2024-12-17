import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        
        arr = new Pair[n+1];
        dp = new long[n+1][5];
        
        
        for(int i = 0; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            arr[i] = new Pair(y,x);
        }
        
        for(int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], -1);
        }
        
        // 결과 출력
        System.out.println(solve(1, 0));
    }
    static int n;
    static long dp[][];
    static StringTokenizer st;
    static int dy[] = {0,-1,1,0,0};
    static int dx[] = {0,0,0,-1,1};
    static int MAX_R = 100_000;
    static boolean OOB(int y,int x) {
    	return y<=0 || y > MAX_R || x<=0 || x>MAX_R;
    }
    
    static Pair arr[];
    static class Pair{
    	int y,x;
    	public Pair(int y,int x) {
    		this.y = y;
    		this.x = x;
    	}
    }
    static long LONG_MAX = Long.MAX_VALUE;
    static long solve(int cur, int dir) {
        if(cur > n) return 0;
        
        if(dp[cur][dir] != -1) 
        	return dp[cur][dir];
        
        dp[cur][dir] = LONG_MAX;
        for(int nextDir = 0; nextDir < 5; nextDir++) {
        	int py = arr[cur - 1].y + dy[dir];
        	int px = arr[cur - 1].x + dx[dir];
        	
        	int y = arr[cur].y + dy[nextDir];
        	int x = arr[cur].x + dx[nextDir];
            
            if(OOB(y,x))
            	continue;
            
            long dist = getDist(py,px, y,x);
            long nxtCost = solve(cur + 1, nextDir);
            if(nxtCost != LONG_MAX)
            	dp[cur][dir] = Math.min(dp[cur][dir], dist+nxtCost);
        }
        
        return dp[cur][dir];
    }
    
    static long getDist(int y1,int x1, int y2, int x2) {
    	return Math.abs(y1-y2) + Math.abs(x1-x2);
    }
}