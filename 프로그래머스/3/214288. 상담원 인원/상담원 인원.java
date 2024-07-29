import java.util.*;

/*
 * 사고의 흐름
 * 구하고자 하는 것 : 멘토를 유형에 잘 배치해서 대기 시간을 최소화해라 
 * 첫번째로 생각한 것 : 중복 순열 -> 1번 멘토가 1번 유형일때, 2번멘토가 1번 유형일 때, ... -> 시간 복잡도가 5^20
 * 두번째로 생각한 것 : 중복 조합 
 * 
 * 1. 중복조합을 어떻게 구현할까? 
 * 
 * 5명의 멘토를 3명의 상담유형에 중복조합하는 방법 == 5명의 멘토와 2개의 칸막이 == 7개의 위치에서 2개의 칸막이의 위치를 뽑는 방법 
 * 
 * 2. 계산된 중복조합 결과에 따라 각 상담유형의 멘토수 계산
 * 
 * 3. 상담유형의 멘토수에 따라 대기 시간 계산 -> 우선순위 큐를 이용
 *  3.1 
 * 
 */

class Solution {
    static int n, k;
    static int selected[], types[];
    static int ans = Integer.MAX_VALUE;
    
    static boolean check(){
        for(int i = 1; i<=k;i++){
            if(types[i] == 0)
                return false;
        }
        return true;
    }
    

    // 상담 유형의 멘토수 계산 
    static void assign(){
    		int prev = 0;
    		int cur = 0;
    		for(int idx = 1; idx < k; idx++){
    			for(int i = (idx == 1 ? 0 : selected[prev]+1); i<=selected[cur]-1;i++){
    				types[idx]++;
    			}
    			prev = cur;
    			cur++;
    		}
    		
    		for(int i = selected[prev] +1 ;i<(n-1);i++){
    			types[k]++;
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
        pqs = new PriorityQueue[k+1];
        for(int i = 1; i<=k;i++){
            pqs[i] = new PriorityQueue<>();
        }
        
        for(int i = 0; i<_reqs.length;i++){
            int req = _reqs[i][0];
            int time = _reqs[i][1];
            int type = _reqs[i][2];
            
            pqs[type].add(new Tuple(i+1, req,time));
        }
    }
    

    /*
     * 대기 시간 구하기
     * pq : 요청시간과 상담에 소요된 시간이 저장된 우선순위 큐
     * endPQ : 끝나는 시간을 저장하기 위한 우선순위 큐 
     */
    static int calc(int type){
        int ret = 0;
        int limit = types[type];
        PriorityQueue<Tuple> pq = pqs[type];
        PriorityQueue<Integer> endPQ = new PriorityQueue<>();
        
        while(!pq.isEmpty()){

            Tuple cur = pq.poll();

            if(endPQ.size() < limit){ // 남아 있는 멘토가 있을 때 
                int end = cur.req + cur.time;
                endPQ.add(end);
            }else{
                if(endPQ.peek() <= cur.req){ // 가장 빨리 상담이 끝나는 시간이 현재 사람의 요청시간보다 작거나 같은 경우 
                    endPQ.poll();
                    int end = cur.req + cur.time;
                    endPQ.add(end);
                }else{ // 장 빨리 상담이 끝나는 시간이 현재 사람의 요청시간보다 큰 경우 
                    int end = endPQ.poll();
                    int waitingTime = end - cur.req; // 대기 시간 계산 
                    ret += waitingTime;
                    end = end + cur.time; 
                    endPQ.add(end);
                }
            }

        }
        return ret;
    }
    
    static void solve(int cur ,int cnt){
        if(cnt == k - 1){
            // System.out.println("------");
            // System.out.println("selected: "+Arrays.toString(selected));
            Arrays.fill(types,1);
            // 유형마다의 멘토수 계산
            assign();
            // System.out.println("types: "+Arrays.toString(types));
           
            
//            if(check()){
                init(); // 우선순위 큐 초기화 
                int elapsed = 0;
                for(int i = 1; i<=k;i++){
                    elapsed += calc(i);
                }
                ans = Math.min(ans, elapsed);
//            }
            
            
            return;
        }
        
        if(cur == n -1)
            return;
        
        selected[cnt] = cur;
        solve(cur + 1, cnt + 1);
        selected[cnt] = 0;
        
        solve(cur + 1, cnt);
        
    }
    
    static int _reqs[][];
    public  int solution(int _k, int _n, int[][] reqs) {
        
        n = _n;
        k = _k;
        _reqs = reqs;
        types = new int[k+1];
        
        if(k > 1) {
        	selected = new int[k-1];
        	solve(0,0);
        }else { // k == 1 일 땐, 따로 계산
        	init();
        	types[1] = n + k - 1;
        	int elapsed = calc(1);
        	ans = Math.min(ans, elapsed);
        }
        	
        // System.out.println(ans);
        
        return ans;
    }
    
    
}