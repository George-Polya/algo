import java.util.*;
import java.io.*;
public class Solution {
    static int T,n;
    static int board[][];
    static StringTokenizer st;
    static class Pair implements Comparable<Pair>{
        int first, second;
        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
         
        public int compareTo(Pair p) {
            if(first != p.first) {
                return p.first - first;
            }
             
            return second - p.second;
        }
         
        public String toString() {
            return first + " "+second;
        }
    }
     
    static ArrayList<Pair> arr;
     
     
    static int dy[] = {-1,1,0,0};
    static int dx[] = {0,0,-1,1};
     
    static boolean OOB(int y,int x) {
        return y<=0 || y>n || x<=0 || x>n;
    }
     
     
     
    static boolean possible(Pair pair,int dir) {
        int y = pair.first;
        int x = pair.second;
         
        while(true) {
            int ny = y + dy[dir];
            int nx = x + dx[dir];
            if(OOB(ny,nx))
                return true;
            if(board[ny][nx] != 0)
                break;
            y = ny;
            x = nx;
        }
        return false;
         
    }
     
    static int getLineCnt(Pair pair, int dir) {
        int y = pair.first;
        int x = pair.second;
        int cnt = 0;
        while(true) {
            int ny = y + dy[dir];
            int nx = x + dx[dir];
            if(OOB(ny,nx))
                break;
            y = ny;
            x = nx;
            board[y][x] = 2;
            cnt++;
        }
        return cnt;
    }
     
    static void restore(Pair pair, int dir) {
        int y = pair.first;
        int x = pair.second;
        while(true) {
            int ny = y + dy[dir];
            int nx = x + dx[dir];
            if(OOB(ny,nx))
                break;
             
            if(board[ny][nx] == 2)
                board[ny][nx] = 0;
            y = ny;
            x = nx;
        }
    }
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;
    static void solve(int cur, int cnt, int lineSum) {
        if(cur == arr.size()) {
	    	if(max > cnt)
	    		return;
        	if(max < cnt) {
        		max = Math.max(max, cnt);
        		min = lineSum;
        	}else if(max == cnt) {
        		min = Math.min(min, lineSum);
        	}
        	return;
        }
         
        Pair pair = arr.get(cur);
        for(int dir = 0; dir < 4; dir++) {
            if(possible(pair, dir)) {
                solve(cur + 1, cnt + 1, lineSum + getLineCnt(pair, dir));
                restore(pair, dir);
            }
        }
        solve(cur + 1, cnt, lineSum);
    }
     
    public static void main(String[] args) throws IOException{
        // TODO Auto-generated method stub
        // System.setIn(new FileInputStream("./input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
         
        for(int tc = 1; tc<=T;tc++) {
            n = Integer.parseInt(br.readLine());
            max = Integer.MIN_VALUE;
            min = Integer.MAX_VALUE;
            board = new int[n+1][n+1];
            arr = new ArrayList<>();
            for(int y=1; y<=n; y++) {
                st = new StringTokenizer(br.readLine());
                for(int x=1; x<=n; x++) {
                    board[y][x] = Integer.parseInt(st.nextToken());
                    if(board[y][x] == 1) {
                        if(y == 1 || y == n || x== 1 || x== n)
                            continue;
                        arr.add(new Pair(y,x));
                    }
                }
            }
             
            solve(0,0,0);
            sb.append(String.format("#%d %d\n", tc, min));
        }
        System.out.println(sb);
    }
 
}