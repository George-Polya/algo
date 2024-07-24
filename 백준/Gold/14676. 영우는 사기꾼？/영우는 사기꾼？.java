import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
public class Main {
	static int n,m,k;
	static StringTokenizer st;
	static Set<Integer> adj[];
	static int buildCnt[], inDegree[];
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		adj = new Set[n+1];
		for(int i = 1; i<=n; i++) {
			adj[i] = new HashSet<>();
		}
		inDegree = new int[n+1];
		buildCnt = new int[n+1];
		for(int i = 0; i<m;i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			adj[from].add(to);
			inDegree[to]++;
		}
		
		for(int i = 0; i < k;i++) {
			st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			int idx = Integer.parseInt(st.nextToken());
			
			if((cmd == 1 && inDegree[idx] != 0) || (cmd == 2 && buildCnt[idx] == 0)) {
				System.out.println("Lier!");
				return;
			}
			
			if(cmd == 1) {
				buildCnt[idx]++;
				
				if(buildCnt[idx]==1) {
					for(int nxt : adj[idx]) {
						if(inDegree[nxt]==0)
							continue;
						inDegree[nxt]--;
					}
				}
				
			}else {
				buildCnt[idx]--;
				
				if(buildCnt[idx] == 0) {
					for(int nxt : adj[idx]) {
						inDegree[nxt]++;
					}
				}
			}
		}
		
		System.out.println("King-God-Emperor");
	
	}
}