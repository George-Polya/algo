import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	st = new StringTokenizer(br.readLine());
    	y1 = Integer.parseInt(st.nextToken());
    	x1 = Integer.parseInt(st.nextToken());
    	y2 = Integer.parseInt(st.nextToken());
    	x2 = Integer.parseInt(st.nextToken());
    	
    	int y = 0;
    	int x = 0;
    	int dir = 0;
    	int moveNum = 1;
    	int cnt = 1;
    	
    	ry = Math.abs(y2-y1) + 1;
    	rx = Math.abs(x2-x1) + 1;
//    	System.out.println(ry+ " "+rx);
    	board = new int[ry][rx];

    	
    	A: while(check()) {
    		for(int num = 0; num < moveNum; num++) {
    			int _y = y - y1;
    			int _x = x - x1;
    			if(!OOB(_y,_x, ry, rx)){
    				board[_y][_x] = cnt;
    			}
    			
    			if(!check()) {
    				break A;
    			}
    			y = y + dy[dir];
    			x = x + dx[dir];
    			cnt++;
    		}
    		
    		dir = (dir + 1) % 4;
    		if(dir == 0 || dir == 2)
    			moveNum++;
    	}
    	
    	printBoard(board, String.valueOf(cnt).length());
//    	System.out.println("end");
    }
    static boolean check() {
    	for(int y= 0; y<ry; y++) {
    		for(int x=0; x<rx; x++) {
    			if(board[y][x] == 0)
    				return true;
    		}
    	}
    	return false;
    }
    
    
    static void printBoard(int board[][], int len) {
    	int n = board.length;
    	int m = board[0].length;
    	StringBuilder sb = new StringBuilder();
    	for(int y=0; y<n; y++) {
    		for(int x=0; x<m; x++) {
    			sb.append(String.format("%" + len +"d", board[y][x]));
    			if( x < m - 1)
    				sb.append(" ");
    		}
    		sb.append('\n');
    	}
    	System.out.println(sb);
    }
    static int board[][];
    static boolean OOB(int y,int x, int ry, int rx) {
    	return y< 0 || y>=ry || x<0 || x>=rx;
    }
    static int dy[] = {0,-1,0,1};
    static int dx[] = {1,0,-1,0};
    static StringTokenizer st;
    static int y1,x1, y2,x2, ry, rx;
}