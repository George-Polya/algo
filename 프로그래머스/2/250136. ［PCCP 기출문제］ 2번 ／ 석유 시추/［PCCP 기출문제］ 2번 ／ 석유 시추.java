import java.util.*;

class Solution {
    static class Pair{
        int y,x;
        public Pair(int y,int x){
            this.y = y;
            this.x = x;
        }
    }

    int n,m;
    boolean visited[][];
    int dy[] = {-1,1,0,0};
    int dx[] = {0,0,-1,1};
    boolean OOB(int y,int x){
        return y<0 || y>=n || x<0 || x>=m;
    }
    int bfs(int y,int x, int id){
        Queue<int[]> q = new ArrayDeque<>();
        visited[y][x] = true;
        int size = 0;
        q.add(new int[]{y,x});
        
        while(!q.isEmpty()){
            int[] cur = q.poll();
            idBoard[cur[0]][cur[1]] = id;
            size++;
            
            for(int dir = 0; dir < 4; dir++){
                int ny = cur[0] + dy[dir];
                int nx = cur[1] + dx[dir];
                
                if(OOB(ny,nx) || board[ny][nx] == 0 || visited[ny][nx])
                    continue;
                
                q.add(new int[]{ny,nx});
                visited[ny][nx]= true;
        
                
            }
        }
        
        return size;
        
    }
    int idBoard[][];
    int board[][];
    
    boolean used[];
    ArrayList<Integer> groups = new ArrayList<>();
    
    public int solution(int[][] _board) {
        int answer = 0;
        board = _board;
        n = board.length;
        m = board[0].length;
        idBoard = new int[n][m];
        
        visited = new boolean[n][m];
        groups.add(-1);
        int id = 0;
        for(int y =0; y<n; y++){
            for(int x= 0; x<m; x++){
                if(visited[y][x] || board[y][x] == 0)
                    continue;
                int cnt = bfs(y,x, ++id);
                groups.add(cnt);
            }
        }
        
        System.out.println(groups);
        
        int cnt = id;
        for(int x = 0; x< m; x++){
            
            int sum = 0;
            used = new boolean[cnt + 1];
            
            for(int y=0; y<n; y++){
                if(idBoard[y][x] == 0)
                    continue;
                id = idBoard[y][x];
                
                
                if(!used[id]){
                    sum += groups.get(id);
                    used[id] = true;
                }    
            }   
            
            answer = Math.max(answer, sum);
        }
        
        return answer;
    }
}