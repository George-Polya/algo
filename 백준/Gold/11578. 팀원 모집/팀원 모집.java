import java.util.*;
import java.io.*;

public class Main{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[M][];
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int sz = Integer.parseInt(st.nextToken());
			arr[i] = new int[sz];
			for(int j = 0; j < sz;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		solve(0,0,0);
		System.out.println(ans == INT_MAX ? -1 : ans);
		
	}
	
	static void solve(int cur, int cnt, int bit) {
		if(cur == M) {
			if(bit == (1 << (N+1)) - 2) {
				ans = Math.min(ans, cnt);
			}
			return;
		}
		
		int nxt = calc(bit, cur);
		solve(cur+1, cnt+1, nxt);
		
		solve(cur +1 , cnt, bit);
	}
	
	static int calc(int bit, int idx) {
		int sz = arr[idx].length;
		for(int i = 0; i < sz;i++) {
			bit |= (1 << arr[idx][i]);
		}
		
		return bit;
	}
	
	static int INT_MAX=Integer.MAX_VALUE / 2;
	static int ans = INT_MAX;
	static int N,M;
	static StringTokenizer st;
	static int arr[][];
}