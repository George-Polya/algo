import java.io.*;
import java.util.*;
public class Main {
    		
	static int n,m;
	static int arr[], selected[];
	static StringTokenizer st;
	static int ans;
	
	static void solve(int cur, int cnt, int sum) {
//		if(sum > m)
//			return;
//		
//		if(ans == m)
//			return;
		
		if(cnt == 3) {
			ans = Math.max(ans, sum);
			return;
		}
		
		if(cur == n)
			return;
		
		if(sum + arr[cur] <=m)
			solve(cur+1,cnt+1, sum + arr[cur]);
		solve(cur + 1, cnt, sum);
	}
	
    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n ;i++) {
        	arr[i] = Integer.parseInt(st.nextToken());
        }
        solve(0,0,0);
        System.out.println(ans);
    }
}