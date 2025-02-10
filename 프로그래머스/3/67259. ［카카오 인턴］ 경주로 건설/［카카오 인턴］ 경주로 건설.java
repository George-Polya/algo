import java.util.*;

class Solution {
    int dy[] = {-1,1,0,0};
    int dx[] = {0,0,-1,1};
    boolean OOB(int y,int x){
        return y<0 || y >= n || x<0 || x>=n;
    }
    int n;
    int INF = Integer.MAX_VALUE / 2;
    class Road{
        int y,x, cost, dir;
        public Road(int y,int x,int cost,int dir){
            this.y = y;
            this.x = x;
            this.cost = cost;
            this.dir = dir;
        }
    }
    
    int dist[][][];
    public int solution(int[][] board) {
        n = board.length;
        Queue<Road> q = new ArrayDeque<>();
        q.add(new Road(0,0,0,1));
        q.add(new Road(0,0,0,3));
        dist = new int[4][n+1][n+1];
        
        for(int dir = 0; dir < 4;dir++){
            for(int y= 0 ;y <n; y++){
                for(int x= 0; x<n; x++){
                    dist[dir][y][x] = INF;
                }
            }
        }
        
        
        dist[1][0][0] = 0;
        dist[3][0][0] = 0;
        int answer = INF;
        
        while(!q.isEmpty()){
            Road cur = q.poll();
            
            if(cur.y == n - 1 && cur.x == n - 1){
                answer = Math.min(answer, cur.cost);
            }
            
            for(int dir = 0; dir < 4;dir++){
                int ny = cur.y + dy[dir];
                int nx = cur.x + dx[dir];
                
                if(OOB(ny,nx) || board[ny][nx] == 1)
                    continue;
                
                int nxtCost = cur.cost + (cur.dir != dir ? 600 : 100);
                
                if(dist[dir][ny][nx] >= nxtCost){
                    q.add(new Road(ny,nx,nxtCost, dir));
                    dist[dir][ny][nx] = nxtCost;
                }
                
            }
        }
        
        return answer;
    }
}