import java.util.*;
import java.io.*;

public class Main{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int t=1; t<=T; t++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N+1];
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i<=N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			visited = new boolean[N+1];
			finished = new boolean[N+1];
			cnt = 0;
			for(int i = 1; i<=N; i++) {
				if(visited[i])
					continue;
				
				dfs(i);
			}
			
			sb.append(N - cnt).append('\n');
		}
		System.out.println(sb);
	}
	static int cnt;
	static void dfs(int cur) {
		
		visited[cur] = true;
		
		int nxt = arr[cur];
		if(!visited[nxt]) {
			dfs(nxt);
		}else {
			if(!finished[nxt]) {
				cnt++;
				for(int t = arr[nxt]; t != nxt; t = arr[t])
					cnt++;
			}
			
		}
		
		finished[cur] = true;
		
	}
	
	static boolean visited[], finished[];
	
	static StringTokenizer st;
	static int T, N;
	static int arr[];
	
}

