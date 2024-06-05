import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int cost[][];
	static StringTokenizer st;
	static int selected[];
	static boolean visited[];
	static int INT_MAX = Integer.MAX_VALUE;
	static int ans = INT_MAX;
	
	static void solve(int cur) {
		if(cur == n) {
			int start = selected[0];
			int sum = 0;
			int prev = start;
			
			for(int i = 1; i<n; i++) {
				if(cost[prev][selected[i]] == 0)
					return;
				sum += cost[prev][selected[i]];
				prev = selected[i];
			}
			
			if(cost[prev][start]==0)
				return;
			sum += cost[prev][start];
			ans = Math.min(ans, sum);
			
			return;
		}
		
		
		for(int i = 1;i <=n; i++) {
			if(visited[i])
				continue;
			selected[cur] = i;
			visited[i] = true;
			solve(cur + 1);
			visited[i] = false;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		cost = new int[n+1][n+1];
		selected = new int[n];
		visited = new boolean[n+1];
		for(int y=1; y<=n; y++) {
			st = new StringTokenizer(br.readLine());
			for(int x=1; x<=n; x++) {
				cost[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		solve(0);
		System.out.println(ans);
		
	}
}