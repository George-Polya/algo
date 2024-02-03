import java.io.*;
import java.util.*;
public class Solution {
    static int T,n;
    static int MAX_N = 300;
    static int board[][] = new int[MAX_N+5][MAX_N+5];
    static StringTokenizer st;
    static int ans;
    
    static int dy[] = {-1,-1,0,1,1,1,0,-1};
    static int dx[] = {0,1,1,1,0,-1,-1,-1};
    static boolean OOB(int y,int x) {
        return y<=0 ||y >n || x<=0 || x>n;
    }
    
    static class Pair{
        int y,x;
        public Pair(int y,int x) {
            this.y = y;
            this.x = x;
        }
    }
    
    static boolean isZero(int y,int x) {
        for(int dir = 0; dir<8; dir++) {
            int ny = y + dy[dir];
            int nx = x + dx[dir];
            
            if(OOB(ny,nx))
                continue;
            if(board[ny][nx] == -2)
                return false;
        }
        return true;
    }
    
    static void click(int y,int x) {
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(y,x));
        board[y][x] = 0;
        
        while(!q.isEmpty()) {
            Pair cur = q.poll();
            
            for(int dir = 0; dir < 8; dir++) {
                int ny = cur.y + dy[dir];
                int nx = cur.x + dx[dir];
                
                if(OOB(ny,nx) || board[ny][nx]!=-1)
                    continue;
                if(isZero(ny,nx)) {
                	q.add(new Pair(ny,nx));
                }
                board[ny][nx] = 0;
            }
        }
    }
    
    
    static void solve() {
        for(int y=1;y<=n;y++) {
            for(int x=1; x<=n; x++) {
                if(board[y][x] != -1)
                    continue;
                if(isZero(y,x)) {
                    click(y,x);
                    ans++;
                }
            }
        }
        
        for(int y=1;y<=n; y++) {
            for(int x=1; x<=n; x++) {
                if(board[y][x] == -1)
                	ans++;
            }
        }
        
    }

    public static void main(String[] args) throws IOException{
//    	System.setIn(new FileInputStream("./input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());
        for(int tc=1;tc<=T;tc++) {
            n = Integer.parseInt(br.readLine());
            ans = 0;
            for(int y=1; y<=n;y++) {
                String str = br.readLine();
                for(int x=1; x<=n; x++) {
                    board[y][x] = str.charAt(x-1) == '.' ? -1 : -2;
                }
            }
            
            solve();
            sb.append('#').append(tc).append(' ').append(ans).append('\n');
            
        }
        
        
        System.out.println(sb);
    }
    
}