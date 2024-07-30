import java.util.*;

class Solution {
	// 해당 IDX의 자식은 누가 있는지
	static ArrayList<Integer>[] adj;
	static int[] info;
	static int maxSheepCnt = 0;
    static int edges[][];
 
	public static int solution(int[] _info, int[][] _edges) {
		info = _info;
        adj = new ArrayList[info.length];
        
        // for(int i = 0; i < info.length;i++){
        //     adj[i] = new ArrayList<>();
        // }
        
        edges = _edges;
        
        for(int i = 0; i < edges.length;i++){
            int from = edges[i][0];
            int to = edges[i][1];
            
            if(adj[from] == null)
                adj[from] = new ArrayList<>();
            adj[from].add(to);
        }
        
 
		List<Integer> list = new ArrayList<>();
		list.add(0);
		dfs(0, 0, 0, list);
		return maxSheepCnt;
	}
 
	private static void dfs(int idx, int sheepCnt, int wolfCnt, List<Integer> nextPos) {
		// 늑대/양 수, 양의 최대값 최신화
		if (info[idx] == 0) sheepCnt++;
		else wolfCnt++;
 
		if (wolfCnt >= sheepCnt) return;
		maxSheepCnt = Math.max(sheepCnt, maxSheepCnt);
   
        List<Integer> list = new ArrayList<>();
        list.addAll(nextPos);
        list.remove(Integer.valueOf(idx));
        if(adj[idx] != null)
            list.addAll(adj[idx]);
        
		// 갈수 있는 모든 Node Dfs
		for (int next : list) {
            
			dfs(next, sheepCnt, wolfCnt, list);
		}
	}
}