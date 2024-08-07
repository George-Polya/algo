import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
	static int n,m,l;
	static StringTokenizer st;
	static int inDegree[];
	static List<Integer> adj[];
	static int target[];
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		boolean visited[] = new boolean[n+1];
		adj = new List[n+1];
		for(int i = 0;i<=n;i++) {
			adj[i] = new ArrayList<>();
		}
		
		target = new int[m];
		inDegree = new int[m];
		
		for(int i = 0; i<m;i++) {
			st = new StringTokenizer(br.readLine());
			int len = Integer.parseInt(st.nextToken());
			
			inDegree[i] = len;
			for(int j = 0; j<len;j++) {
				adj[Integer.parseInt(st.nextToken())].add(i);
			}
			
			target[i] = Integer.parseInt(st.nextToken());
		}

		
		l = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		Queue<Integer> q = new LinkedList<>();

		for(int i = 0; i <l;i++) {
			int id = Integer.parseInt(st.nextToken());
			visited[id]=true;
			q.add(id);
		}
		
		
		
		
		while(!q.isEmpty()) {
//			System.out.println("-----");
//			System.out.println("q: "+q);
//			System.out.println("inDegree: "+Arrays.toString(inDegree));
			int cur = q.poll();
			
			for(int nxt : adj[cur]) {
				inDegree[nxt]--;
				if(inDegree[nxt] == 0 && !visited[target[nxt]]) {
					visited[target[nxt]] = true;
					q.add(target[nxt]);
				}
			}
		}
//		Collections.sort(result);
		StringBuilder sb = new StringBuilder();
		int cnt = 0;
		
		for(int i = 1; i<=n; i++) {
			if(visited[i]) {
				cnt++;
				sb.append(i).append(' ');
			}
		}
		System.out.println(cnt);
		System.out.println(sb);
	}
}