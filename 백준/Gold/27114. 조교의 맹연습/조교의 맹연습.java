import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	st = new StringTokenizer(br.readLine());
    	left = Integer.parseInt(st.nextToken());
    	right = Integer.parseInt(st.nextToken());
    	back = Integer.parseInt(st.nextToken());
    	K = Integer.parseInt(st.nextToken());
    	
    	int dp[][] = new int[4][K + 1];
    	for(int row[] : dp) {
    		Arrays.fill(row, INT_MAX);
    	}
    	
    	dp[0][0] = 0;
    	
    	for(int e=1; e<=K; e++) {
    		for(int dir = 0; dir < 4; dir++) {
    			if(e - left >= 0)
    				dp[(dir + 3) % 4][e] = Math.min(dp[(dir + 3) % 4][e], dp[dir][e - left] + 1);
    			
    			if(e - right >= 0)
    				dp[(dir + 1) % 4][e] = Math.min(dp[(dir + 1) % 4][e], dp[dir][e - right] + 1);
    			
    			if(e - back >= 0)
    				dp[(dir + 2) % 4][e] = Math.min(dp[(dir + 2) % 4][e], dp[dir][e - back] + 1);
    			
    		}
    	}
    	
    	System.out.println(dp[0][K] == INT_MAX ? -1 : dp[0][K]);
    }
    static int INT_MAX = Integer.MAX_VALUE / 2;
    static int left, right, back, K;
    static StringTokenizer st;
}