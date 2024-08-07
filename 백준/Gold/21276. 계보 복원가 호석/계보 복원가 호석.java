import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
	static TreeMap<String, Integer> name2id = new TreeMap<>();
	static HashMap<Integer, String> id2name = new HashMap<>();
	static int n,m;
	static StringTokenizer st;
	static List<Integer> adj[];
	static Set<String> children[];
	static int inDegree[];
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i<=n;i++) {
			String name = st.nextToken();
			name2id.put(name, i);
			id2name.put(i,name);
		}
		
		adj = new List[n+1];
		children = new Set[n+1];
		for(int i = 1;i<=n;i++) {
			adj[i] = new ArrayList<>();
			children[i] = new TreeSet<>();
		}
		
		inDegree = new int[n+1];
		m = Integer.parseInt(br.readLine());
		
		
		for(int i = 0; i <m;i++) {
			st = new StringTokenizer(br.readLine());
			String name1 = st.nextToken();
			String name2 = st.nextToken();
			int id1 = name2id.get(name1);
			int id2 = name2id.get(name2);
			
//			System.out.println(id2+" "+id1);
			
			adj[id2].add(id1);
			inDegree[id1]++;
		}
		
		Queue<Integer> q = new LinkedList<>();
		for(int i = 1; i<=n;i++) {
			if(inDegree[i] == 0) {
				q.add(i);
			}
		}
		StringBuilder sb = new StringBuilder();
		int size = q.size();
		sb.append(size).append('\n');
		List<String> ancestors = new ArrayList<Integer>(q).stream().map(id -> id2name.get(id)).collect(Collectors.toList());
		Collections.sort(ancestors);
//		System.out.println(ancestors);
		for(String ancestor: ancestors) {
			sb.append(ancestor).append(' ');
		}
		sb.append('\n');
		
		while(!q.isEmpty()) {
//			System.out.println("-----");
//			System.out.println("q: "+q);
			int cur = q.poll();
			
			for(int nxt : adj[cur]) {
				inDegree[nxt]--;
				if(inDegree[nxt] == 0) {
					children[cur].add(id2name.get(nxt));
					q.add(nxt);
				}
			}
		}
		Set<String> set = new HashSet<>(ancestors);
		for(String name : name2id.keySet()) {
			int id = name2id.get(name);
//			System.out.println(name+" "+child[id].size()+" "+child[id]);
			sb.append(name).append(' ').append(children[id].size()).append(' ');
			for(String child : children[id]) {
				sb.append(child).append(' ');
			}
			sb.append('\n');
		}
		System.out.println(sb);
		
	}
}