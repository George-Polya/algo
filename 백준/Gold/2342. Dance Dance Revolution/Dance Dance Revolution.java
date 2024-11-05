import java.util.*;
import java.io.*;

public class Main{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		while(true) {
			int value = Integer.parseInt(st.nextToken());
			if(value == 0)
				break;
			arr.add(value);
		}
		n = arr.size();
		
		dp = new int[n][5][5];
		for(int z = 0; z < n; z++) {
			for(int y = 0 ;y < 5; y++) {
				for(int x = 0; x< 5; x++) {
					dp[z][y][x] = INF;
				}
			}
		}
		
		ans = solve(0,0,0);
		System.out.println(ans);
	}
	static int n;
	static int dp[][][];
	
	static int solve(int cur, int l, int r) {
		if(cur == n)
			return 0;
		
		if(dp[cur][l][r] != INF)
			return dp[cur][l][r];
		
		int ret = INF;
		if(arr.get(cur) != r) {
			ret = Math.min(ret,  solve(cur + 1, arr.get(cur), r) + calc(arr.get(cur), l));
		}
		
		if(arr.get(cur) != l) {
			ret = Math.min(ret,  solve(cur + 1, l, arr.get(cur)) + calc(arr.get(cur), r));
		}
		
		return dp[cur][l][r] = ret;
//		return ret;
		
	}
	
//	static void solve(int cur, int l, int r, int sum) {
//		if(cur == n) {
//			ans = Math.min(ans, sum);
//			return;
//		}
//		
//		if(arr.get(cur) != r) {
//			solve(cur + 1, arr.get(cur), r, sum + calc(arr.get(cur), l) );
//		}
//		
//		if(arr.get(cur) != l) {
//			solve(cur + 1, l , arr.get(cur), sum + calc(arr.get(cur), r));
//		}
//	}
	
	
	static int calc(int nxt, int cur) {
		if(cur == 0) // 현재 위치가 중앙일때 
			return 2;
		
		if(nxt == cur) // 현재위치와 다음 위치가 같을 때 
			return 1;
		
		if((nxt - 1) == ((cur - 1 + 2) % 4)) // 현재위치와 다음위치가 반대 방향일 
			return 4;
		
		// 그외 
		return 3;
		
	}
	static int INF = Integer.MAX_VALUE / 2;
	static int ans = INF;
	static StringTokenizer st;
	static ArrayList<Integer> arr = new ArrayList<>();
}