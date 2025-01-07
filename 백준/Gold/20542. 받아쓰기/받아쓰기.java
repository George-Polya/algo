import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		A = "#" + br.readLine();
		B = "#" + br.readLine();
		dp = new int[n+1][m+1];
		
		System.out.println(solve(1,1));
	}
	static int dp[][];
	static int INF = Integer.MAX_VALUE;
	static int solve(int i, int j) {
		if(j > m) {
			return n - i + 1;
		}
		
		if(i > n) {
			return m - j + 1;
		}
		
		if(dp[i][j] != 0)
			return dp[i][j];
		
		
		int ret = INF;
		if(A.charAt(i) == B.charAt(j)) {
			ret = Math.min(ret, solve(i+1,j+1));
		}else {
			if(A.charAt(i) == 'i' && (B.charAt(j) == 'i' || B.charAt(j) == 'j' || B.charAt(j) == 'l')) {
				ret = Math.min(ret, solve(i+1,j+1));
			}else if(A.charAt(i) == 'v' && (B.charAt(j) == 'v' || B.charAt(j) == 'w')) {
				ret = Math.min(ret, solve(i+1,j+1));
			}else {
				// i번째에 b[j]를 추가 
				ret = Math.min(ret, solve(i, j+1) + 1);
				
				// a[i]를 b[j]로 변경
				ret = Math.min(ret,  solve(i+1,j+1) + 1);
				
				// i번째를 삭제
				ret = Math.min(ret,  solve(i+1,j) + 1);
			}
		}
		return dp[i][j] = ret;
	}
	
	static void solve(int i, int j, int sum) {
		if(j > m) {
			sum += (n - i + 1);
			ans = Math.min(ans, sum);
			return;
		}
		
		if(i > n) {
			sum += (m - j + 1);
			ans = Math.min(ans, sum);
			return;
		}
		
		if(A.charAt(i) == B.charAt(j)) {
			solve(i + 1, j + 1, sum);
		}else {
			if(A.charAt(i) == 'i' && (B.charAt(j) == 'i' || B.charAt(j) == 'j' || B.charAt(j) == 'l')) {
				solve(i + 1, j + 1, sum);
			}else if(A.charAt(i) == 'v' && (B.charAt(j) == 'v' || B.charAt(j) == 'w')) {
				solve(i + 1, j + 1, sum);
			}else {
				// i번째에 b[j]를 추가 
				solve(i, j + 1, sum + 1);
				
				// a[i]를 b[j]로 변경
				solve(i+1,j+1, sum + 1);
				
				// i번째를 삭제
				solve(i+1, j, sum + 1);
			}
				
		}
		
		
	}
	
	static int ans = Integer.MAX_VALUE;
			
	static StringTokenizer st;
	static int n,m;
	static String A, B;
}
