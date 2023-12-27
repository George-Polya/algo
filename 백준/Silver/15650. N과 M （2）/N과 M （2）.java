import java.io.*;
import java.util.*;
/**
 * 1 2 3 4
 * 1 2 4 3
 * 2 1 3 4 
 * ...
 * 이 전부 1 2 3 4로 바뀐다.
 * 즉, 순서가 의미 없어진다.
 * 그냥 뽑기만 하면 됨! => 조합 
 */
public class Main {
	static int n,m;
	static int arr[];
	static StringTokenizer st;
	static int selected[];
	static StringBuilder sb = new StringBuilder();
	static void solve(int cur, int cnt) {
		if(cnt == m) {
			for(int i = 0; i < m ;i++) {
				sb.append(selected[i]).append(' ');
			}
			sb.append('\n');
			return;
		}
		
		if(cur == n)
			return;
		
		selected[cnt] = arr[cur];
		solve(cur + 1, cnt + 1);
		
		solve(cur+1, cnt);
		
	}
	public static void main(String[] args) throws IOException{
	 	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	 	st = new StringTokenizer(br.readLine());
	 	n = Integer.parseInt(st.nextToken());
	 	m = Integer.parseInt(st.nextToken());
	 	arr = new int[n];
	 	for(int i = 0;i < n ;i++) {
	 		arr[i] = i + 1;
	 	}
	 	selected = new int[m];
	 	solve(0, 0);
	 	System.out.println(sb);
	}
}