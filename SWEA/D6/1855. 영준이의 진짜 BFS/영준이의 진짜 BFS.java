import java.util.*;
import java.io.*;

public class Solution {
	static int T;
	static int n;
	static StringTokenizer st; 
	static ArrayList<Integer> adj[];
	static boolean visited[];
	static int depth[];
	static int MAX_H = 19;
	static int parent[][];
	
	static long LCA(int x, int y, int cnt) {
		if(depth[x] > depth[y]) {
			int temp = x;
			x = y;
			y = temp;
		}
		
		if(parent[y][0] == x)
			return 1;
		
		for(int h = MAX_H; h>=0; h--) {
			if(depth[y] - depth[x] >= (1<<h) ) {
				y = parent[y][h];
				cnt += (1 << h);
			}
		}
		
		if(parent[y][0]!= parent[x][0]) {
			for(int h = MAX_H; h>=0; h--) {
				if(parent[x][h] != parent[y][h]) {
					cnt += 2 * ( 1<< h);
					x = parent[x][h];
					y = parent[y][h];
				}
			}
		}
		
		if(parent[x][0] == parent[y][0])
			return cnt + 2;
		return cnt;
		
	}
	
	static long bfs() {
		long result = 0;
		int pre = 0, cur = 0;
		Queue<Integer> q = new LinkedList<>();
		q.add(1);
		visited[1] = true;
		pre = 1;
		
		while(!q.isEmpty()) {
			int curr = q.poll();
			
			for(int nxt : adj[curr]) {
				if(visited[nxt])
					continue;
				q.add(nxt);
				visited[nxt] = true;
				
				cur = nxt;
				
				result += LCA(pre, cur, 0);
				pre = cur;
				
			}
		}
		
		return result;
	}
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
//		System.setIn(new FileInputStream("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			n = Integer.parseInt(br.readLine());
			adj = new ArrayList[n+1];
			for(int i = 1; i<=n; i++)
				adj[i] = new ArrayList<>();
			
			visited = new boolean[n+1];
			depth = new int[n+1];
			parent = new int[n+1][MAX_H+1];
//			parent = new int[MAX_H+1][n+1];
			
			
			parent[1][0] = 0;
			depth[1] = 0;
			
			st = new StringTokenizer(br.readLine());
			for(int i = 2; i<=n; i++) {
				int p = Integer.parseInt(st.nextToken());
				adj[p].add(i);
				parent[i][0] = p;
				depth[i] = depth[p] + 1;
				
			}
			
			for(int h = 1; h <= MAX_H; h++) {
				for(int i = 1; i<=n; i++)
					parent[i][h] = parent[parent[i][h-1]][h-1];
			}
			
			
			sb.append('#').append(tc).append(' ').append(bfs()).append('\n');
		}
		System.out.println(sb);
	}

}