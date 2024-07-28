import java.util.*;
class Solution {
    static int _n, _k;
    static int selected[], types[];
    static int ans = Integer.MAX_VALUE;
    
    static boolean check(){
        for(int i = 1; i<=_k;i++){
            if(types[i] == 0)
                return false;
        }
        return true;
    }
    
    static void assign(){
    		int prev = 0;
    		int cur = 0;
    		for(int idx = 1; idx < _k; idx++){
    			for(int i = (idx == 1 ? 0 : selected[prev]+1); i<=selected[cur]-1;i++){
    				types[idx]++;
    			}
    			prev = cur;
    			cur++;
    		}
    		
    		for(int i = selected[prev] +1 ;i<(_n+_k-1);i++){
    			types[_k]++;
    		}
    }
    
    
    
    static PriorityQueue<Tuple> pqs[];
    
    static class Tuple implements Comparable<Tuple>{
        int id, req, time;
        public Tuple(int id, int req,int time){
            this.id = id;
            this.req = req;
            this.time = time;
        }
        
        public int compareTo(Tuple t){
            
            return req - t.req;        
        }
        
        public String toString(){
            return String.format("id: %d, req: %d, time: %d", id,req,time);
        }
    }
    
    static void init(){
        pqs = new PriorityQueue[_k+1];
        for(int i = 1; i<=_k;i++){
            pqs[i] = new PriorityQueue<>();
        }
        
        for(int i = 0; i<_reqs.length;i++){
            int req = _reqs[i][0];
            int time = _reqs[i][1];
            int type = _reqs[i][2];
            
            pqs[type].add(new Tuple(i+1, req,time));
        }
    }
    
    static int calc(int type){
        int ret = 0;
        int limit = types[type];
        PriorityQueue<Tuple> pq = pqs[type];
        PriorityQueue<Integer> endPQ = new PriorityQueue<>();
        
        while(!pq.isEmpty()){
//            System.out.println("=======");
            Tuple cur = pq.poll();
//            System.out.println("cur: "+cur);
            if(endPQ.size() < limit){
                int end = cur.req + cur.time;
                endPQ.add(end);
            }else{
                if(endPQ.peek() <= cur.req){
                    endPQ.poll();
                    int end = cur.req + cur.time;
                    endPQ.add(end);
                }else{
                    int end = endPQ.poll();
                    int waitingTime = end - cur.req;
                    ret += waitingTime;
                    end = end + cur.time;
                    endPQ.add(end);
                }
            }
//            System.out.println("endPQ: "+endPQ);
        }
        return ret;
    }
    
    static void solve(int cur ,int cnt){
        if(cnt == _k - 1){
            
//        	System.out.println("-----");
//        	System.out.println("selected: "+ Arrays.toString(selected));
            
            Arrays.fill(types,0);
            assign();
           
            
            if(check()){
                init();
//                System.out.println("types: "+Arrays.toString(types));
                int elapsed = 0;
                for(int i = 1; i<=_k;i++){
                    elapsed += calc(i);
                }
                ans = Math.min(ans, elapsed);
            }
            
            
            return;
        }
        
        if(cur == _n + _k - 1)
            return;
        
        selected[cnt] = cur;
        solve(cur + 1, cnt + 1);
        selected[cnt] = 0;
        
        solve(cur + 1, cnt);
        
    }
    
    static int _reqs[][];
    public  int solution(int k, int n, int[][] reqs) {
        
        _n = n;
        _k = k;
        _reqs = reqs;
        types = new int[_k+1];
        
        if(k > 1) {
        	selected = new int[_k-1];
        	solve(0,0);
        }else {
        	init();
        	types[1] = n + k - 1;
//        	System.out.println(pqs[1]);
        	int elapsed = calc(1);
        	ans = Math.min(ans, elapsed);
        }
        	
        // System.out.println(ans);
        int answer = 0;
        return ans;
    }
    
    
}