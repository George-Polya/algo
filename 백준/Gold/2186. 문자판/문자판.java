import java.util.*;
import java.io.*;

public class Main {
 
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	st = new StringTokenizer(br.readLine());
    	N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());
    	K = Integer.parseInt(st.nextToken());
    	
    	board = new char[N][M];
    	for(int y = 0; y<N;y++) {
    		String str = br.readLine();
    		for(int x = 0; x<M; x++) {
    			board[y][x] = str.charAt(x);
    			
    			
    		}
    	}
    	
    	line = br.readLine();
    	dp = new int[N][M][line.length()];
    	for(int y=0; y<N; y++) {
    		for(int x=0; x<M; x++) {
    			for(int z= 0; z<line.length(); z++)
    				dp[y][x][z] = -1;
    		}
    	}
    	
    	for(int y =0 ;y<N;y++) {
    		for(int x= 0; x<M; x++) {
    			if(board[y][x] == line.charAt(0)) {
    				ans += solve(y,x,0);
    			}
    		}
    	}
    	System.out.println(ans);
    }
    
    static int dp[][][];
    
    static int solve(int y,int x,int idx) {
    	if(idx == line.length() - 1)
    		return 1;
    	
    	if(dp[y][x][idx] != -1)
    		return dp[y][x][idx];
    	
    	int ret = 0;
    	for(int dir = 0; dir<4; dir++) {
    		for(int k =1; k<=K; k++) {
    			int ny = y + dy[dir] * k;
    			int nx = x + dx[dir] * k;
    			if(OOB(ny,nx))
    				continue;
//    			System.out.println(ny+" "+nx);
    			if(board[ny][nx] == line.charAt(idx+1)) {
    				ret += solve(ny,nx, idx+1);
    			}
    		}
    	}
    	
    	return dp[y][x][idx] = ret;
    }
    
//    static void solve(int y,int x, int idx) {
//    	if(idx == line.length() - 1) {
//    		ans++;
//    		return;
//    	}
//    	
//    	for(int dir = 0; dir < 4; dir++) {
//    		for(int k = 1; k<=K; k++) {
//    			int ny = y + dy[dir] * k;
//    			int nx = x + dx[dir] * k;
//    			if(OOB(ny,nx))
//    				continue;
//    			
//    			if(board[ny][nx] == line.charAt(idx+1)) {
//    				solve(ny,nx, idx+1);
//    			}
//    		}
//    	}
//    }
    static boolean OOB(int y,int x) {
    	return y<0 || y>=N || x<0 || x>=M;
    }
    static int dy[] = {-1,1,0,0};
    static int dx[] = {0,0,-1,1};
    
    static int ans;
    static int N,M,K;
    static StringTokenizer st;
    static char board[][];
    static String line;
}