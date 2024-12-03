import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	n = Integer.parseInt(br.readLine());
    	lose = new int[n + 1];
    	plus = new int[n + 1];
    	
    	st = new StringTokenizer(br.readLine());
    	for(int i = 1; i<=n; i++) {
    		lose[i] = Integer.parseInt(st.nextToken());
    	}
    	
    	st = new StringTokenizer(br.readLine());
    	for(int i = 1; i<=n; i++) {
    		plus[i] = Integer.parseInt(st.nextToken());
    	}
    	
    	solve(1, 100, 0);
    	System.out.println(ans);
    	
    }
    
    static void solve(int cur, int health, int sum) {
    	if(cur == n + 1) {
    		ans = Math.max(ans, sum);
    		return;
    	}
    	
    	if(health > lose[cur]) {
    		solve(cur + 1, health - lose[cur], sum + plus[cur]);
    	}
    	
    	solve(cur +1, health, sum);
    }
    
    
    static int ans;
    static StringTokenizer st;
    static int n;
    static int lose[], plus[];
}