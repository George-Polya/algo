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
    

    static void makeSums(List<Integer> sums, List<Integer> select, int depth, int sum){
        if(depth == n/2){
            sums.add(sum);
            return;
        }
        
        for(int i = 0; i<6;i++){
            int idx = select.get(depth);
            makeSums(sums, select, depth+1, sum+_dice[idx][i]);
        }
    }
    
    static int upperBound(List<Integer> sums, int sum){
        int l = 0;
        int r = sums.size()-1;
        int ret = Integer.MIN_VALUE;
        while(l<=r){
            int mid = (l+r)/2;
        
            
//             if(sum < sums.get(mid)){
//                 r = mid - 1;
                
//             }else{
//                 l = mid + 1;
//                 ret = Math.max(ret, mid);
//             }
        
            if(sum > sums.get(mid)){
                l = mid + 1;
                ret = Math.max(ret, mid);
            }else{
                r = mid - 1;
            }

        }
        return ret;
    }
    
    static void solve(int cur, int cnt){
        if(cnt == n / 2){
            List<Integer> aSelect = new ArrayList<>();
            List<Integer> bSelect = new ArrayList<>();
            
            init(aSelect, bSelect);
            
            List<Integer> aSums = new ArrayList<>();
            makeSums(aSums, aSelect,0,0);
            List<Integer> bSums = new ArrayList<>();
            makeSums(bSums, bSelect,0,0);
            
            Collections.sort(bSums);
            
            int winCnt = 0;
            for(int sum: aSums){
                int idx = upperBound(bSums, sum);
                if(idx != Integer.MIN_VALUE)
                    winCnt += idx + 1;
            }
            // System.out.println(winCnt);
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
        // int[] arr5 = list.stream().mapToInt(i->i).toArray();
        int[] answer = ans.stream().mapToInt(i->i+1).toArray();
        
        return answer;
    }
}