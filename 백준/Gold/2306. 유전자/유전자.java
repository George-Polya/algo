import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	str = br.readLine().toCharArray();
    	n = str.length;
    	dp = new int[n][n];
    	for(int row[] : dp)
    		Arrays.fill(row, -1);
    	
    	System.out.println(solve(0, n - 1));
    }
    static int dp[][];
    static int solve(int l,int r) {
    	if(l>=r)
    		return 0;
    	
    	if(dp[l][r] != -1)
    		return dp[l][r];
    	
    	int ret = 0;
    	for(int k = l; k<r; k++) {
    		ret = Math.max(ret, solve(l,k) + solve(k+1,r));
    	}
    	
    	if(check(l,r))
    		ret = Math.max(ret, solve(l+1,r-1) + 2);
    	return dp[l][r] = ret;
    }
    
    static boolean check(int l, int r) {
    	return (str[l] == 'a' && str[r] == 't') || (str[l] == 'g' && str[r] == 'c');
    }
    static int n;
    static char str[];
}