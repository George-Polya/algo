import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static String[] board;
    static int[][] dist;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        board = new String[52];
        dist = new int[52][52];
        
        for(int i = 0; i < n; i++) {
            board[i] = br.readLine();
        }
        
        int result = -1;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                for(int k = 0; k < 52; k++) {
                    Arrays.fill(dist[k], -1);
                }
                
                if(board[i].charAt(j) != 'L') continue;
                result = Math.max(result, bfs(i, j));
            }
        }
        
        System.out.println(result);
    }
    
    static boolean OOB(int y, int x) {
        return y < 0 || y >= n || x < 0 || x >= m;
    }
    
    static int bfs(int y, int x) {
        dist[y][x] = 0;
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{y, x});
        
        int _dist = 0;
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            
            for(int dir = 0; dir < 4; dir++) {
                int ny = cur[0] + dy[dir];
                int nx = cur[1] + dx[dir];
                
                if(OOB(ny, nx)) continue;
                if(dist[ny][nx] != -1 || board[ny].charAt(nx) != 'L') continue;
                
                q.offer(new int[]{ny, nx});
                dist[ny][nx] = dist[cur[0]][cur[1]] + 1;
                _dist = Math.max(_dist, dist[ny][nx]);
            }
        }
        
        return _dist;
    }
}