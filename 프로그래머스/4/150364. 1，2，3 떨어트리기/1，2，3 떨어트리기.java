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
        		// diff : leaf에 num이 떨어졌다 가정했을 때, target과의 차이 
        		int diff = target[leaf] - num;
        		
        		// counts[leaf] <= diff : leaf에 떨어진 숫자가 전부 1인 경우보다 크다 
        		// counts[leaf] * 3 >= diff : leaf에 떨어진 숫자가 전부 3인 경우보다 작다. 
        		// 즉, 이번 leaf에 num이 떨어졌을 때 target을 만족시킬 수 있는 경우는 위의 두가지 
        		if(counts[leaf] <= diff && diff <= 3 * counts[leaf]) {
        			result.add(num);
        			target[leaf] -= num; // 처리했으면 다음시행을 위해 빼준다. 
        			break; 
        		}
        	}
        }
        
        
        return result.stream().mapToInt(r->r).toArray();
    }
    
}