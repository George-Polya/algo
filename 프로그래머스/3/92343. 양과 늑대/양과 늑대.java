import java.util.*;
import java.util.stream.*;
class Solution {
	// 해당 IDX의 자식은 누가 있는지
	static ArrayList<Integer>[] adj;
	static int[] info;
	static int ans;
    static int edges[][];
 
	public static int solution(int[] _info, int[][] _edges) {
		info = _info;
        adj = new ArrayList[info.length];
        
        for(int i = 0; i < info.length;i++){
            adj[i] = new ArrayList<>();
        }
        
        edges = _edges;
        
        for(int i = 0; i < edges.length;i++){
            int from = edges[i][0];
            int to = edges[i][1];
            
            // if(adj[from] == null)
            //     adj[from] = new ArrayList<>();
            adj[from].add(to);
        }
        
 
		List<Integer> list = new ArrayList<>();
		list.add(0);
		dfs(0, 0, 0, list);
		return ans;
	}
 
    // cur : 현재 위치한 노드 인덱스
    // sheep, wolf : 양, 늑대 수
    // nextPos : 
	private static void dfs(int cur, int sheep, int wolf, List<Integer> list) {

		// 늑대/양 수, 양의 최대값 최신화
		if (info[cur] == 0) sheep++;
		else wolf++;
 
		if (wolf >= sheep) return;
		ans = Math.max(sheep, ans);

        
        
		// 갈수 있는 모든 Node에 대해 dfs 수행
        // 예를 들어, 지금 노드 1에 있고 0,1을 방문해서 양이 2마리라 하자
        // 그럼 1의 다음 노드인 2,4로 이동하는 것이 아니라
        // 0,1에 연결된 모든 노드 중 하나로 이동하는 것이다.
        // 즉, 0에 연결된 8번 노드도 갈 수 있게 되는 것이다.
        // 만약 8번 노드에 갔을 때는 2,4,7,9를 갈 수 있게 되는 것이다

        
        // 현재 위치를 또 방문하지 않도록 필터링 
        List<Integer> nexts = list.stream()
                                        .filter(nxt -> !nxt.equals(cur))  
                                        .collect(Collectors.toList());   
        
        // 현재 위치와 연결된 노드들을 전부 연결
        // 이러면 1에 연결된 0번 노드도 추가된다고 생각할 수도 있는데 방향그래프로 설정하면 괜찮다.
        nexts.addAll(adj[cur]);

		for (int nxt : nexts) {
            
			dfs(nxt, sheep, wolf, nexts);
		}
	}
}