import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	st = new StringTokenizer(br.readLine());
    	h = Integer.parseInt(st.nextToken());
    	n = Integer.parseInt(st.nextToken());
    	
    	System.out.println(solve(h,h, n,n));
    }
    
    
    
    static long solve(int y,int x, int ey, int ex) {
    	
    	
    	if(y == ey && x == ex) {
    		return 1;
    	}
    	
    	if(dp[y][x] != 0)
    		return dp[y][x];
    	
    	
    	long ret = 0;
    	int d = getDist(y,x,ey,ex);
    	
    	for(int dir = 0; dir < 4; dir++) {
    		int ny = y + dy[dir];
    		int nx = x + dx[dir];
    		
    		if(OOB(ny,nx) || ny > nx)
    			continue;
    		int dist = getDist(ny,nx,ey,ex);
    		if(d > dist) {
    			ret += solve(ny,nx,ey,ex);
    		}
    	}
    	
    	return dp[y][x] = ret;
    	
    }
    
    static int getDist(int y1, int x1, int y2, int x2) {
    	return Math.abs(y1-y2) + Math.abs(x1-x2);
    }
    
    static int h,n;
    static int MAX_R = 30;
    static long dp[][] = new long[MAX_R+1][MAX_R+1];
    static StringTokenizer st;
    static int dy[] = {-1,1,0,0};
    static int dx[] = {0,0,-1,1};
    static boolean OOB(int y,int x) {
    	return y<0 || y > MAX_R || x<0 || x>MAX_R;
    }
}