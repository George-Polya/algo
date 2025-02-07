import java.util.*;

class Solution {
    class Edge{
        int idx, cost;
        public Edge(int idx, int cost){
            this.idx = idx;
            this.cost = cost;
        }
        
        public String toString(){
            return idx+" "+cost;
        }
    }
    
    HashSet<Integer> gates = new HashSet<>();
    HashSet<Integer> summits = new HashSet<>();
    ArrayList<Edge> adj[];
    int n,m;
    long INF = Long.MAX_VALUE / 2;
    class Pair{
        int summit;
        long intensity;
        public Pair(int summit, long intensity){
            this.summit = summit;
            this.intensity = intensity;
        }
        
        public boolean isHigher(Pair o){
            if(intensity != o.intensity){
                return intensity < o.intensity;
            }
            
            return summit < o.summit;
        }
        
        public String toString(){
            return summit+" "+intensity;
        }
    }
    
    Pair WORST = new Pair(50001,(int)1e7+1);
    
    class State implements Comparable<State>{
        int idx;
        long cost;
        public State(int idx, long cost){
            this.idx = idx;
            this.cost = cost;

        }
        
        public int compareTo(State o){
            return Long.compare(cost, o.cost);
        }
        
        public String toString(){
            return String.format("idx: %d, cost: %d", idx, cost);
        }
 
    }
    
    long dist[];

    
 
    PriorityQueue<State> pq = new PriorityQueue<>();
    public long[] solution(int _n, int[][] paths, int[] _gates, int[] _summits) {
        n = _n;
        adj = new ArrayList[n+1];
        
        for(int i = 0; i < _gates.length;i++){
            gates.add(_gates[i]);
        }
        
        for(int i = 0; i < _summits.length;i++){
            summits.add(_summits[i]);
        }
        dist = new long[n+1];
        for(int i = 0; i<=n; i++){
            adj[i] = new ArrayList<>();
            dist[i] = -1;
        }
        m = paths.length;
        
        for(int i = 0; i < m; i++){
            int from = paths[i][0];
            int to = paths[i][1];
            int cost = paths[i][2];
            
            adj[from].add(new Edge(to, cost));
            adj[to].add(new Edge(from, cost));
        }
        for(int gate: gates){
            dist[gate] = 0;
            pq.add(new State(gate, 0));
        }
        
        Pair ans = WORST;
        
        while(!pq.isEmpty()){
            // System.out.println("======");
            // System.out.println("pq: "+pq);
            // System.out.println("dist: "+Arrays.toString(dist));
            State cur = pq.poll();
            // System.out.println("cur: "+cur);
            
            if(dist[cur.idx] < cur.cost)
                continue;
            
            if(summits.contains(cur.idx)){
                Pair pair = new Pair(cur.idx, cur.cost);
                // System.out.println("pair: "+pair);
                if(pair.isHigher(ans))
                    ans = pair;
                
                continue;
            }
            
            for(Edge nxt : adj[cur.idx]){
                // System.out.printf("nxt: %d, %d\n", nxt.idx, nxt.cost);
                if(dist[nxt.idx] != -1 && dist[nxt.idx] <= Math.max(cur.cost, nxt.cost))
                    continue;
              
                
                dist[nxt.idx] = Math.max(cur.cost, nxt.cost);
                pq.add(new State(nxt.idx, dist[nxt.idx]));

            }
        }
        
//         Pair ans = WORST;
//         for(int gate : gates){
//             // System.out.println("-----");
//             // System.out.println("gate: "+gate);
//             Pair ret = solve(gate);
            
//             // ans = updateReturn(ans, ret);
//             if(ret.isHigher(ans))
//                 ans = ret;
//         }
        
        
        return new long[]{ans.summit, ans.intensity};
    }
}