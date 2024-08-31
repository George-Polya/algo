import java.util.*;
class Solution {
      static ArrayList<Integer>[] adj;
    static int n;
    static int scores[];
    public static int[] solution(int[][] edges, int[] target) {
        int[] answer = {};
        n = edges.length + 1;
        adj = new ArrayList[n];
        for(int i = 0; i<n; i++){
            adj[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < edges.length;i++){
            int u = edges[i][0];
            int v = edges[i][1];
            
            adj[u-1].add(v-1);
        }
        
        for(int i = 0; i < n ;i++) {
        	Collections.sort(adj[i]);
        }
        
        int counts[] = new int[n];
        int passCnt[] = new int[n];
        boolean checked[] = new boolean[n];
        List<Integer> leafs = new ArrayList<>();
        int T = 0;
        
        for(int i = 0; i < n;i++) {
        	if(adj[i].isEmpty() && target[i] > 0)
        		T++;
        }
        
        while(T > 0) {
        	int cur = 0;
        	
        	while(adj[cur].size() > 0) {
        		cur = adj[cur].get(passCnt[cur]++ % adj[cur].size());
        	}
        	
        	counts[cur]++;
        	leafs.add(cur);
        	
        	if(counts[cur] > target[cur]) {
        		return new int[]{-1};
        	}
        	
        	if(!checked[cur] && target[cur] <= 3 * counts[cur]) {
        		checked[cur] = true;
        		T--;
        	}
        }
        
        List<Integer> result = new ArrayList<>();
        
        for(int leaf : leafs) {
        	counts[leaf]--;
        	
        	for(int num = 1; num <= 3; num++) {
        		if(counts[leaf] <= target[leaf] - num && 
        				target[leaf] - num <= 3 * counts[leaf]) {
        			result.add(num);
        			target[leaf] -= num;
        			break;
        		}
        	}
        }
        
        
        
        
        return result.stream().mapToInt(r->r).toArray();
    }
}