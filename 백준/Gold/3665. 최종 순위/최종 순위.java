import java.io.*;
import java.util.*;
public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        A : for(int tc=1; tc<=T;tc++) {
        	n = Integer.parseInt(br.readLine());
        	arr = new int[n+1];
        	st = new StringTokenizer(br.readLine());
        	
        	for(int i = 1; i<=n; i++) {
        		arr[i] = Integer.parseInt(st.nextToken());
        	}
        	
        	
        	
        	inDegree = new int[n+1];
        	adj = new HashSet[n+1];
        	for(int i = 1; i<=n; i++) {
        		adj[i] = new HashSet<>();
        	}
        	
        	for(int i = 1; i<=n; i++) {
        		int from = arr[i];
        		for(int j = i + 1; j<=n; j++) {
        			int to = arr[j];
        			adj[from].add(to);
        			inDegree[to]++;
        		}
        	}
        	
        	m = Integer.parseInt(br.readLine());
        	for(int i = 0; i <m; i++) {
        		st = new StringTokenizer(br.readLine());
        		int from = Integer.parseInt(st.nextToken());
        		int to = Integer.parseInt(st.nextToken());
        		
        		
        		if(adj[from].contains(to)) {
        			adj[from].remove(to);
        			adj[to].add(from);
        			inDegree[from]++;
        			inDegree[to]--;
        		}else {
        			adj[to].remove(from);
        			adj[from].add(to);
        			inDegree[to]++;
        			inDegree[from]--;
        		}
        		
        	}
        	
        	
        	Queue<Integer> q = new ArrayDeque<>();
        	for(int i = 1; i<=n; i++) {
        		if(inDegree[i] == 0)
        			q.add(i);
        	}
        	
        	
        	if(q.size() > 1) {
        		sb.append("?\n");
        		continue A;
        	}
        	
        	
        	
        	boolean flag = false;
        	Queue<Integer> result = new ArrayDeque<>();
        	for(int i = 1; i<=n; i++) {
        		if(q.isEmpty()) {
        			sb.append("IMPOSSIBLE\n");
        			flag = true;
        			break;
        		}
        		
        		int cur = q.poll();
        		result.add(cur);
        		for(int nxt : adj[cur]) {
        			inDegree[nxt]--;
        			if(inDegree[nxt] == 0)
        				q.add(nxt);
        		}
        		
        	}
        	
        	if(flag)
        		continue A;
        	
        	while(!result.isEmpty())
        		sb.append(result.poll()).append(' ');
        	
        	sb.append('\n');
        }
        System.out.println(sb);
    }
    static HashSet<Integer> adj[];
    static int inDegree[];
    static StringTokenizer st;
    static int T;
    static int n,m;
    static int arr[];
}