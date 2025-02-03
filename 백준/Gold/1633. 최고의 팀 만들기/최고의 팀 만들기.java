import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	String s = "";
    	
    	while ((s = br.readLine()) != null && !s.isEmpty()) {
			st = new StringTokenizer(s);
			whites[n] = Integer.parseInt(st.nextToken());
			blacks[n] = Integer.parseInt(st.nextToken());		
			n++;
		}
//    	System.out.println(Arrays.toString(whites));
    	dp = new int[n+1][16][16];
//    	for(int z = 0; z< n; z++) {
//    		for(int y = 0; y<=15; y++) {
//    			for(int x=0; x<=15; x++) {
//    				dp[z][y][x] = -1;
//    			}
//    		}
//    	}
    	System.out.println(solve(0,0,0));
    }
	static int n, ans;
	
	static StringTokenizer st;
	static int dp[][][];
	static int whites[] = new int[1001];
	static int blacks[] = new int[1001];
	
	static int solve(int cur, int bCnt, int wCnt) {
		if(bCnt == 15 && wCnt == 15)
			return 0;
		
		if(cur == n)
			return 0;
		
		
		if(dp[cur][bCnt][wCnt] != 0)
			return dp[cur][bCnt][wCnt];
		
		int ret = solve(cur+1, bCnt, wCnt);
		if(bCnt < 15)
			ret = Math.max(ret, solve(cur+1, bCnt+1, wCnt) + blacks[cur]);
		if(wCnt < 15)
			ret = Math.max(ret,  solve(cur+1, bCnt, wCnt+1) + whites[cur]);
		
		return dp[cur][bCnt][wCnt] = ret;
	}
//	
//	static void solve(int cur, int bCnt, int wCnt, int sum) {
//		if(bCnt == 15 && wCnt == 15) {
//			ans = Math.max(ans, sum);
//			return;
//		}
//		
//		if(cur == n)
//			return;
//		
//		solve(cur+1, bCnt, wCnt);
//		
//		if(bCnt < 15)
//			solve(cur+1, bCnt+1, wCnt, sum + blacks[cur]);
//		
//		if(wCnt < 15)
//			solve(cur+1, bCnt, wCnt+1, sum + whites[cur]);
//		
//		
//			
//	}
}