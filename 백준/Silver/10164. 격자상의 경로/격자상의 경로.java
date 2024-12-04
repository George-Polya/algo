import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	m = Integer.parseInt(st.nextToken());
    	k = Integer.parseInt(st.nextToken());
    	
    	dp1 = new int[n+1][m+1];
    	dp1[1][1] = 1;
    	for(int y=1; y<=n; y++) {
    		for(int x=1; x<=m; x++) {
    			if(y == 1 && x==1)
    				continue;
    			dp1[y][x] = dp1[y-1][x] + dp1[y][x-1];
    		}
    	}
    	
//    	printBoard(dp1);
    	if(k == 0) {
    		System.out.println(dp1[n][m]);
    		return;
    	}
    	
    	dp2 = new int[n+1][m+1];
    	int y = (k-1) / m + 1;
    	int x = (k-1) % m + 1;
//    	System.out.println(y+" "+x);
    	
    	solve(1, 1);
//    	printBoard(dp2);
    	int a = dp1[y][x];
    	int b = dp2[y][x];
    	System.out.println(a * b);
    	
    }
    
    static int solve(int y,int x) {
    	if(y > n || x > m)
    		return 0;
    	
    	if(y == n && x == m)
    		return 1;
    	
    	if(dp2[y][x] != 0)
    		return dp2[y][x];
    	
    	return dp2[y][x] = solve(y+1,x) + solve(y,x+1);
    }
    
    static void printBoard(int board[][]) {
    	for(int y=1; y<=n; y++) {
    		for(int x=1; x<=m; x++) {
    			System.out.printf("%3d", board[y][x]);
    		}
    		System.out.println();
    	}
    }
    
    static int n,m,k;
    static StringTokenizer st;
    static int dp1[][], dp2[][];
}