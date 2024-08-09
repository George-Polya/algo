import java.io.*;
import java.util.*;


public class Main {
	static StringTokenizer st;
	static int n,m,k;
	static int uf[], arr[];
	
	static int find(int x) {
		if(x == uf[x])
			return x;
		return uf[x] = find(uf[x]);
	}
	
	static void union(int a,int b) {
		int A = find(a);
		int B = find(b);
		
		if(A == B)
			return;
		
		uf[A] = B;
		
		
	}
	
	static int getMinCost(HashSet<Integer> set) {
		int ret = Integer.MAX_VALUE;
//		System.out.println(set);
		for(int id : set) {
			ret = Math.min(ret, arr[id]);
		}
		return ret;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		
		uf = new int[n+1];
		arr = new int[n+1];
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i<=n; i++) {
			uf[i] = i;
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		
		for(int i = 0; i < m ;i++) {
			st= new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			union(a,b);
		}
//			System.out.println(Arrays.toString(uf));
		
		Set<Integer> set = new HashSet<>();
		for(int i = 1; i<=n; i++) {
			int idx = find(i);
//			System.out.println(idx);
			set.add(idx);
		}
		
		HashMap<Integer, HashSet<Integer>> map = new HashMap<>();
//		System.out.println(set);
		for(int idx : set) {
			map.put(idx, new HashSet<>());
		}
		
		for(int i = 1; i<=n; i++) {
			int idx = find(i);
			map.get(idx).add(i);
		}
		
		long sum = 0;
		for(int idx : map.keySet()) {
			int min = getMinCost(map.get(idx));
			
			sum += min;
		}
		System.out.println(sum <= k ? sum : "Oh no");
		
	}
}