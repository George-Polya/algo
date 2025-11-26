import java.util.*;
import java.io.*;
 class Main{
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N  = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		adj = new List[T+1];
		
		for(int i = 0; i <= T; i++) {
			adj[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < N ;i++) {
			st = new StringTokenizer(br.readLine());
			int x= Integer.parseInt(st.nextToken());
			int y= Integer.parseInt(st.nextToken());
			adj[y].add(x);
		}
		
		System.out.println(bfs());
	}
	static Map<Pair, Integer> dist = new HashMap<>();
	
	static int bfs() {
		Queue<Pair> q = new ArrayDeque<>();
		Pair start = new Pair(0,0);
		q.add(start);
		dist.put(start, 0);
		
		while(!q.isEmpty()) {
			Pair cur = q.poll();
			
			if(cur.y == T) {
				return dist.get(cur);
			}
			
			for(int ny = cur.y - 2; ny <= cur.y + 2; ny++) {
				if(ny < 0 || ny > T)
					continue;
				
				for(int nx : adj[ny]) {
					Pair nxt = new Pair(nx,ny);
					if(Math.abs(nx - cur.x) <= 2 && !dist.containsKey(nxt)) {
						dist.put(nxt, dist.get(cur) + 1);
						q.add(nxt);
					}
				}
			}
		}
		return -1;
	}
	
	static class Pair{
		int x,y;
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public boolean equals(Object o) {
			if(this == o)
				return true;
			
			if(getClass() != o.getClass())
				return false;
			
			Pair p = (Pair)o;
			return x == p.x && y == p.y;
		}
		
		public int hashCode() {
			return Objects.hash(x,y);
		}
		
		public String toString() {
			return x+" "+y;
		}
	}
	static StringTokenizer st;
	static int N,T;
	static List<Integer> adj[];
}
