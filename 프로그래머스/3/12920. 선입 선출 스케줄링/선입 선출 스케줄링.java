import java.util.*;

class Solution {
    
    int calc(int time, int cores[]){
        if(time == 0)
            return cores.length;
        
        int count = cores.length;
        
        for(int i = 0; i < cores.length; i++)
            count += (time / cores[i]);
        return count;
    }
    
    public int solution(int n, int[] cores) {
        int answer = 0;
        
        int l = 0;
        int r = 10000*n;
        
        int time = 0;
        int m = 0;
        while(l<=r){
            int mid = (l + r) / 2;
            int count = calc(mid, cores);
            if(count >= n){
                r = mid - 1;
                time = mid;
                m = count;
            }else{
                l = mid + 1;
            }
        }
        
        m -= n;
        for(int i = cores.length - 1; i >=0 ; i--){
            if(time % cores[i] == 0){
                if(m == 0){
                    answer = i + 1;
                    break;
                }
                m--;
            }
        }
        
        return answer;
    }
}