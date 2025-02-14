import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.function.Function;

class Position{
    int y;
    int x;

    public Position(int y, int x) {
        this.y = y;
        this.x = x;
    }
    public boolean isSame(int y,int x){
        if(this.y == y && this.x == x){
            return true;
        }else{
            return false;
        }
    }
}
public class Main {
    private static final char EMPTY = '.';
    private static final char WALL = '*';
    private static final char MIRROR = '!';
    private static final char DOOR = '#';
    private static final int NOT_VALID = -1;
    private static final int INF = 987654321;
    private static final int[] DY = {-1,0,1,0};
    private static final int[] DX = {0,1,0,-1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Function<String,Integer> stoi = Integer::parseInt;
        int n = stoi.apply(br.readLine());
        char[][] map = new char[n][n];
        Position start = null;
        Position end = null;
        for(int i = 0 ; i < n ; i ++){
            String command = br.readLine();
            for(int j = 0 ; j < n ; j++){
                map[i][j] = command.charAt(j);
                if(map[i][j] == DOOR){
                    if(start == null){
                        start = new Position(i,j);
                    }else{
                        end = new Position(i,j);
                    }
                }
            }
        }
        System.out.println(bfs(end,start.y,start.x,map,n));
    }
    private static int bfs(Position end,int y,int x,char[][] map, int n){
        PriorityQueue<int[]> q = new PriorityQueue<>((o1,o2)->{
            return o1[2] - o2[2];
        });
        boolean[][][] visited = new boolean[n][n][4];
        for(int i = 0 ; i < 4; i++){
            q.offer(new int[] {y,x,0,i});
        }
        int result = INF;
        while(!q.isEmpty()){
            int[] now = q.poll();
            visited[now[0]][now[1]][now[3]] = true;
            if(end.isSame(now[0],now[1])){
                return now[2];
            }
            int dir = now[3];
            if(map[now[0]][now[1]] == EMPTY || map[now[0]][now[1]] == DOOR){
                int ny = now[0] + DY[dir];
                int nx = now[1] + DX[dir];
//                System.out.println("new:"+ny+" "+nx);
                if(checkBound(ny,nx,n) && map[ny][nx] != WALL && !visited[ny][nx][dir]){
                    q.offer(new int[] {ny,nx,now[2],dir});
                }
                continue;
            }
            for(int i = -1 ; i <= 1; i++){
                int nextDir = (dir + i + 4) % 4;
                int ny = now[0] + DY[nextDir];
                int nx = now[1] + DX[nextDir];
                int nextMirrorCnt = now[2];
                if(i != 0){
                    nextMirrorCnt++;
                }
                if(checkBound(ny,nx,n) && map[ny][nx] != WALL && !visited[ny][nx][nextDir]){
                    q.offer(new int[] {ny,nx,nextMirrorCnt,nextDir});
                }
            }
        }
        return INF;
    }
    private static boolean checkBound(int y,int x,int n){
        if(y >= 0 && y < n && x >= 0 && x < n){
            return true;
        }else{
            return false;
        }
    }
}