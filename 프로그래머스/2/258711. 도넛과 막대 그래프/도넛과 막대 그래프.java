import java.util.*;
class Solution {
    
    static HashSet<Integer> adj[];
    static Map<Integer,Integer> idx2cnt = new HashMap<>();
    static Map<Integer,Integer> cnt2idx = new HashMap<>();
    static int m;
    static int cnt = 1;
    
    static int inDegree[], outDegree[];
    
    static int findCreatedNode(){
        for(int i = 1; i<=cnt;i++){
            if(inDegree[i] == 0 && outDegree[i] >= 2)
                return i;
        }
        return 0;
    }
    
   
    
    static int[] countGraph(int node){
        int count[] = new int[3];
        int num = adj[node].size();
        // System.out.println("adj[node]: "+adj[node]);
        for(int n : adj[node]){
            inDegree[n]--;
        }
        
        
        for(int i = 1; i<cnt; i++){
            if(i == node)
                continue;
            if(outDegree[i] == 0){
                count[1]++;
            }
            
            if(inDegree[i] == 2 && outDegree[i] == 2){
                count[2]++;
            }
        }
        
        count[0] = num - (count[1] + count[2]);
        return count;

    }
    
    static boolean visited[];
    public int[] solution(int[][] edges) {
        m = edges.length;
        for(int i = 0; i <m ;i++){
            int from = edges[i][0];
            if(!idx2cnt.containsKey(from)){
                idx2cnt.put(from, cnt);
                cnt2idx.put(cnt, from);
                cnt++;
            }
            
            from = idx2cnt.get(from);
            
            int to = edges[i][1];
            if(!idx2cnt.containsKey(to)){
                idx2cnt.put(to, cnt);
                cnt2idx.put(cnt, to);
                cnt++;
            }
            
            to = idx2cnt.get(to);
            
        }
        
        // System.out.println(idx2cnt);
        // System.out.println(cnt2idx);
        // System.out.println("cnt: "+cnt);
        adj = new HashSet[cnt+1];
        inDegree = new int[cnt+1];
        outDegree = new int[cnt+1];
        
        
        for(int i = 1; i<=cnt;i++){
            adj[i] = new HashSet<>();
        }
        
        for(int i = 0; i <m ;i++){
            int from = edges[i][0];
            from = idx2cnt.get(from);
            int to = edges[i][1];
            to = idx2cnt.get(to);
            
            adj[from].add(to);
            
            inDegree[to]++;
            outDegree[from]++;
        }
        
        // System.out.println("inDegree: "+Arrays.toString(inDegree));
        // System.out.println("outDegree: "+Arrays.toString(outDegree));
        visited = new boolean[cnt+1];
        int createdNode = findCreatedNode();
        // System.out.println("inDegree: "+Arrays.toString(inDegree));
        int counts[] = countGraph(createdNode);
        
        // System.out.println("inDegree: "+Arrays.toString(inDegree));
        
        createdNode = cnt2idx.get(createdNode);
        // System.out.println("createdNode: "+createdNode);
        int[] answer = new int[]{createdNode, counts[0], counts[1], counts[2]};
        return answer;
    }
}