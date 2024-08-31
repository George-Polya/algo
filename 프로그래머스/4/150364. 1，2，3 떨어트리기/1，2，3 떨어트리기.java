import java.util.*;
class Solution {
     
    static ArrayList<Integer>[] adj;
    static int n;
    static int scores[], counts[], target[], pass[];
    static int leafCnt;
    static Set<Integer> done = new HashSet<>();
    static List<Integer> leafs = new ArrayList<>();
    
    static int dfs(int cur) {
    	if(adj[cur].isEmpty()) {
    		counts[cur]++;
    		
    		if(counts[cur] * 3 >= target[cur]) {
    			done.add(cur);
    		}
    		
    		leafs.add(cur);
    		return counts[cur] <= target[cur] ? cur : -1;
    	}
    	
    	int path = pass[cur]++ % adj[cur].size();
    	return dfs(adj[cur].get(path));
    }
    
    public static int[] solution(int[][] edges, int[] _target) {
        target = _target;
        n = target.length;
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
        
        
        for(int i = 0; i < n;i++) {
        	if(target[i] > 0)
        		leafCnt++;
        }
        
        counts = new int[n];
        pass = new int[n];
        
        while(true) {
        	if(dfs(0) == -1)
        		return new int[] {-1};
        	if(done.size() == leafCnt)
        		break;
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
//        return new int[]{};
    }
}