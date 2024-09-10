import java.util.*;
class Solution{
    boolean pillar[][], bar[][];
    int n;
    int cnt;
    void add(int y, int x, int type){
        if(type == 0){
            if(checkPillar(y,x)){
                pillar[y][x] = true;
                cnt++;
            }
        }else{
            if(checkBar(y,x)){
                bar[y][x] = true;
                cnt++;
            }
        }
    }
    
    void remove(int y,int x,int type){
        if(type == 0){
            pillar[y][x] = false;
            if(!canRemove())
                pillar[y][x] = true;
            else
                cnt--;
        }else{
            bar[y][x] = false;
            if(!canRemove())
                bar[y][x] = true;
            else
                cnt--;
        }
    }
    
    boolean canRemove(){
        for(int y= 0; y<=n; y++){
            for(int x= 0;x<=n; x++){
                if(pillar[y][x] && !checkPillar(y,x))
                    return false;
                if(bar[y][x] && !checkBar(y,x))
                    return false;
            }
        }
        return true;
    }
    
    boolean checkPillar(int y,int x){
        if(y == 0)
            return true;
        if(pillar[y-1][x])
            return true;
        if(x > 0 && bar[y][x-1] || bar[y][x])
            return true;
        return false;
    }
    
    boolean checkBar(int y,int x){
        if(y > 0 && pillar[y-1][x] || pillar[y-1][x+1])
            return true;
        if(x > 0 && bar[y][x-1] && bar[y][x+1])
            return true;
        return false;
    }
    
    public int[][] solution(int _n, int build_frame[][]){
        n = _n;
        pillar = new boolean[n+1][n+1];
        bar = new boolean[n+1][n+1];
        
        for(int i = 0; i < build_frame.length;i++){
            int x = build_frame[i][0];
            int y = build_frame[i][1];
            int type = build_frame[i][2];
            int flag = build_frame[i][3];
            
            if(flag == 1){
                add(y,x,type);
            }else{
                remove(y,x,type);
            }
        }
        // System.out.println(cnt);
        int answer[][] = new int[cnt][3];
        int idx=0;
        List<int[]> result = new ArrayList<>();
        // System.out.println(pillar[n][n]);
        for(int y=0; y<=n; y++){
            for(int x=0;x<=n; x++){
                if(pillar[y][x]){
                    result.add(new int[]{x,y,0});
                }
                
                if(bar[y][x]){
                    result.add(new int[]{x,y,1});
                }
                
            }
            
        }
        
        Collections.sort(result, (a, b)->{
            
            if(a[0] != b[0])
                return a[0] - b[0];
            if(a[1] != b[1])
                return a[1] - b[1];
            return a[2] - b[2];
        });
        for(int i = 0; i < cnt;i++){
            answer[i] = result.get(i);
        }
        return answer;
        
    }
}