import java.util.*;
import java.io.*;
public class Main {
	static StringTokenizer st;
	static int t, k;
	static int coins[][];
	static int dp[][];
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		t = Integer.parseInt(br.readLine());
		k = Integer.parseInt(br.readLine());
		coins = new int[k+1][2];
		
		for(int i = 1; i<=k; i++) {
			st = new StringTokenizer(br.readLine());
			coins[i][0] = Integer.parseInt(st.nextToken());
			coins[i][1] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(coins,1,k+1,(int a[], int b[])->{
			return a[0] - b[0];
		});
		
		dp = new int[k+1][t+1];

		for(int i = 0; i<=k; i++) {
			dp[i][0] = 1;
		}
		
		for(int i =1; i<=k;i++) {
			int cost = coins[i][0];
			int cnt = coins[i][1];
			
			for(int money = 1; money<=t; money++) {
				for(int c =0 ; c<=cnt;c++) {
					if(money < cost * c)
						break;
//					dp[money] += dp[money - cost * c];
					dp[i][money] += dp[i-1][money - cost * c];
				}
			}
		}
		System.out.println(dp[k][t]);
		
		
	}
}