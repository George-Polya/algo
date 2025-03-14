import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	st = new StringTokenizer(br.readLine());
    	N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());
    	
    	board = new int[N+1][M+1];
    	dp = new int[N+1][M+1];
    	for(int y=1; y<=N; y++) {
    		st = new StringTokenizer(br.readLine());
    		for(int x=1; x<=M; x++) {
    			board[y][x] = Integer.parseInt(st.nextToken());
    			dp[y][x] = INT_MIN;
    		}
    	}
    	
    	dp[1][1] = board[1][1];
    	for(int x=2; x<=M; x++) {
    		dp[1][x] = dp[1][x-1] + board[1][x];
    	}
    	
    	for(int y=2; y<=N; y++) {
    		int lefts[] = new int[M+1];
    		int rights[] = new int[M+1];
    		
    		lefts[1] = dp[y-1][1] + board[y][1];
    		for(int x=2; x<=M; x++) {
    			lefts[x] = Math.max(lefts[x-1], dp[y-1][x]) + board[y][x];
    		}
    		
    		
    		rights[M] = dp[y-1][M] + board[y][M];
    		for(int x = M - 1; x>=1; x--) {
    			rights[x] = Math.max(rights[x+1], dp[y-1][x]) + board[y][x];
    		}
    		
    		for(int x=1; x<=M; x++)
    			dp[y][x] = Math.max(lefts[x], rights[x]);
    		
    	}
    	System.out.println(dp[N][M]);
    	
    }
    static int N,M;
    static StringTokenizer st;
    static int board[][], dp[][];
    static int INT_MIN = Integer.MIN_VALUE/2;
}