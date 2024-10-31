import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        a = br.readLine();
        b = br.readLine();
        n = a.length();
        m = b.length();
        a = "#" + a;
        b = "#" + b;
        
        dp = new int[n+1][m+1];
        for(int i = 1; i<=n; i++) {
        	for(int j = 1; j<=m; j++) {
        		if(a.charAt(i) == b.charAt(j)) {
        			dp[i][j] = dp[i-1][j-1] + 1;
        		}else {
        			dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
        		}
        	}
        }
        
        int len = dp[n][m];
        System.out.println(len);
        if(len != 0) {
        	Stack<Character> stk = new Stack<>();
        	int i = n;
        	int j = m;
        	while( i > 0 && j > 0 ) {
        		if(i == 0 || j == 0)
        			break;
        		
        		if(dp[i][j] == dp[i-1][j])
        			i--;
        		else if(dp[i][j] == dp[i][j-1])
        			j--;
        		else {
        			stk.push(a.charAt(i));
        			i--;
        			j--;
        		}
        	}
        	
        	String ret = "";
        	while(!stk.isEmpty()) {
        		ret += stk.pop();
        	}
        	
        	System.out.println(ret);
        }
    }
    static String a,b;
    static int n,m;
    static int dp[][];
    static String BLANK = "";
}