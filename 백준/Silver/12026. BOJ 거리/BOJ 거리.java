import java.util.*;
import java.io.*;

public class Main {
 
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	N = Integer.parseInt(br.readLine());
    	String line = br.readLine();
    	arr = new int[N+1];
    	dp = new int[N+1][3];
    	for(int i = 1; i<=N; i++) {
    		char ch = line.charAt(i-1);
    		if(ch == 'B') {
    			arr[i] = 0;
    		}else if(ch == 'O') {
    			arr[i] = 1;
    		}else {
    			arr[i] = 2;
    		}
    		
    		for(int j = 0; j < 3;j++) {
    			dp[i][j] = INF;
    		}
    	}
    	
    	dp[1][0] = 0;
    	
    	for(int i = 2;i<=N;i++) {
    		for(int j = 1; j < i; j++) {
    			int cur = arr[i];
    			int prev = arr[j];
    			
    			if((cur == (prev + 1) % 3) && (dp[j][prev] != INF)) {
    				dp[i][cur] = Math.min(dp[i][cur], dp[j][prev] + (i-j)*(i-j));
    			}
    		}
    	}
    	
    	System.out.println(dp[N][arr[N]]==INF ? -1 : dp[N][arr[N]]);
    	
    }
    static int N;
    static int INF = Integer.MAX_VALUE / 2;
    static int arr[],dp[][];
}