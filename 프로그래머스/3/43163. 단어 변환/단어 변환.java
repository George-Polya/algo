import java.util.*;

class Solution {
    Map<String, Integer> map = new HashMap<>();
    Queue<String> q = new ArrayDeque<>();
    int calc(String nxt, String cur){
        int cnt = 0;
        for(int i = 0 ; i < nxt.length(); i++){
            if(nxt.charAt(i) != cur.charAt(i))
                cnt++;
        }
        return cnt;
    }
    
    public int solution(String begin, String target, String[] words) {
        q.add(begin);
        map.put(begin, 0);
        
        while(!q.isEmpty()){
            String cur = q.poll();
            
            for(String nxt : words){
                if(map.containsKey(nxt))
                    continue;
                if(calc(nxt, cur) == 1){
                    q.add(nxt);
                    map.put(nxt, map.get(cur) + 1);
                }
            }
        }
        
        return map.containsKey(target) ? map.get(target) : 0;
    }
}