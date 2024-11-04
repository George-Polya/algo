import java.util.*;
import java.io.*;

public class Main{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		str = br.readLine();
		n = str.length();
		str = "#" + str;
		isPalindrome = new boolean[n+1][n+1];
		
		init();
		
		dp = new int[n+1];
		Arrays.fill(dp, INF);
		dp[0] = 0;
		
		for(int i = 1; i<=n; i++) {
			for(int j = 1; j<=i; j++) {
				if(isPalindrome[j][i])
					dp[i] = Math.min(dp[i], dp[j-1] + 1);
			}
		}
		System.out.println(dp[n]);
		
	}
	static int INF = Integer.MAX_VALUE / 2;
	static int dp[];
	static void init() {
		for(int i =1; i<=n; i++) {
			for(int j = 1; j<=i; j++) {
				boolean flag = true;
				int s = j; int e = i;
				
				while(s<=e) {
					if(str.charAt(s) != str.charAt(e)) {
						flag = false;
						break;
					}
					s++;
					e--;
				}
				
				if(flag)
					isPalindrome[j][i] = true;
			}
		}
	}
	
	static String str;
	static int n;
	static boolean isPalindrome[][];
}