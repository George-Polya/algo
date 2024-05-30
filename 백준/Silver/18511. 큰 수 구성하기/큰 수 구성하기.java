import java.io.*;
import java.util.*;

public class Main {
	static int n,m;
	static int arr[];
	static StringTokenizer st;
	static int size;
	static int ans = Integer.MIN_VALUE;
	static void solve(int cur, int num) {
		if(n >= num)
			ans = Math.max(ans,  num);
		if(ans == n)
			return;
		if(cur == size) {
			return;
		}
		
		for(int i = 0; i<m;i++) {
			int sum = num * 10 + arr[i];
			solve(cur + 1, sum);
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		String str = st.nextToken();
		size = str.length();
		n = Integer.parseInt(str);
		m = Integer.parseInt(st.nextToken());
		arr = new int[m];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < m; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		solve(0, 0);
		System.out.println(ans);
	}
}