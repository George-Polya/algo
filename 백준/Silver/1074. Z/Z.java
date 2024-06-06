import java.io.*;
import java.util.*;

public class Main {
	static int N,r,c;
	static StringTokenizer st;
	static int board[][];
	static int idx;
	
	static int solve(int y,int x, int n) {
		
		if(n == 0)
			return 0;
		
		int half = 1 << (n-1);
		
		if(y < half && x < half)
			return solve(y,x,n-1);
		
		if(y < half && x>=half)
			return half * half + solve(y,x-half, n-1);
		
		if(y>=half && x< half)
			return 2 * half*half + solve(y-half,x,n-1);
		
		return 3 * half*half + solve(y-half, x-half, n-1);
		
	}
	

	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		System.out.println(solve(r,c,N));
	}
	
}