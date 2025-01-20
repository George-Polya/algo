import java.util.*;
class Solution {
    static int n;
    static boolean a[];
    
    static void init(List<Integer> aSelect, List<Integer> bSelect){
        for(int i = 0; i<n;i++){
            if(a[i])
                aSelect.add(i);
            else
                bSelect.add(i);
        }
    }
    static List<Integer> ans = new ArrayList<>();
    static int best = 0;
    
    static int _dice[][];
    
    // idx : 골라진 주사위의 select에서의 위치
    // cur : select에서 idx에 해당하는 주사위
    // _dict[cur][i] : 주사위 cur의 i번째 수
    // sum : 주사위를 굴렸을 때 나온 수들의 합
    static void makeSums(List<Integer> sums, List<Integer> select, int idx, int sum){
        if(idx == select.size()){
            sums.add(sum);
            return;
        }
        
        for(int i = 0; i<6;i++){
            int cur = select.get(idx);
            makeSums(sums, select, idx+1, sum+_dice[cur][i]);
        }
    }
    
    static int binarySearch(List<Integer> sums, int target){
        int l = 0;
        int r = sums.size()-1;
        int ret = -1;
        while(l<=r){
            int mid = (l+r)/2;
            
            if(target > sums.get(mid)){
                l = mid + 1;
                ret = Math.max(ret, mid);
            }else{
                r = mid - 1;
            }

        }
        return ret;
    }
    
    static void solve(int cur, int cnt){
        // A가 n/2개를 고른다 
        if(cnt == n / 2){
            List<Integer> aSelect = new ArrayList<>();
            List<Integer> bSelect = new ArrayList<>();
            
            // a가 선택한 주사위와 b가 선택한 주사위를 분리
            // aSelect: a가 선택한 주사위의 인덱스의 리스트
            // aSelect: b가 선택한 주사위의 인덱스의 리스트 
            init(aSelect, bSelect);
            
            List<Integer> aSums = new ArrayList<>();
            makeSums(aSums, aSelect,0,0);
            List<Integer> bSums = new ArrayList<>();
            makeSums(bSums, bSelect,0,0);
            // System.out.println(bSums.size());
            
            Collections.sort(bSums);
            
            int winCnt = 0;
            // aSums : a가 선택한 주사위들을 굴렸을 때 나올 수 있는 sum들 
            // bSums : b가 선택한 주사위들을 굴렸을 때 나올 수 있는 sum들 
            // 시간을 줄이려면... 
            // (aSums의 원소 sum보다 작은 최초의 인덱스 + 1)이 bSums에서 sum보다 작은 수들의 개수라는 걸 파악해야야함
            // 이걸 파악했으면 이분탐색을 이용해 시간을 줄일 수 있음
            // treeset은 idx가 안나와서 안되나?
            for(int sum: aSums){
                
                int idx = binarySearch(bSums, sum);
                if(idx != -1)
                    winCnt += idx + 1;
            }
            
            if(best < winCnt){
                
                best = winCnt;
                ans = aSelect;
            }
            
            return;
        }
        
        if(cur == n)
            return;
        
        a[cur] = true;
        solve(cur + 1, cnt+1);
        a[cur] = false;
        
        solve(cur + 1, cnt);
    }
    public int[] solution(int[][] dice) {
        
        n = dice.length;
        _dice = dice;
        a = new boolean[n];
        solve(0,0);
        int size = ans.size();
        
        int[] answer = ans.stream().mapToInt(i->i+1).toArray();
        
        return answer;
    }
}