import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringBuilder sb = new StringBuilder();
    	int T = Integer.parseInt(br.readLine());
    	for(int tc=1;tc<=T;tc++) {
    		board = new char[MAX_Y][MAX_X];
    		
    		int pinCnt = 0;
    		for(int y= 0; y< MAX_Y; y++) {
    			String str = br.readLine();
    			for(int x=0; x< MAX_X;x++) {
    				board[y][x] = str.charAt(x);
    				if(board[y][x] == 'o')
    					pinCnt++;
    			}
    		}
    		
    		total = pinCnt;
    		
    		for(int y=0; y<MAX_Y; y++) {
    			for(int x=0; x<MAX_X;x++) {
    				if(board[y][x] == 'o')
    					dfs(y,x, pinCnt, 0);
    			}
    		}
    		br.readLine();
    		sb.append(total+" "+move+"\n");
    	}
    	System.out.println(sb);
    }
    
    static void dfs(int sy,int sx, int cnt, int moveCnt) {
    	if(cnt <= total) {
    		total = cnt;
    		move = moveCnt;
    	}
    	
    	for(int dir = 0; dir < 4 ;dir++) {
    		int ny = sy + dy[dir];
    		int nx = sx + dx[dir];
    		
    		if(!OOB(ny,nx) && board[ny][nx] == 'o') { // 핀이 꽂혀 있으면
    			int nny = ny + dy[dir];
    			int nnx = nx + dx[dir];
    			
    			if(!OOB(nny,nnx) && board[nny][nnx] == '.') { // 빈칸이면 
    				board[sy][sx] = board[ny][nx] = '.';
    				board[nny][nnx] = 'o';
    				
    				
    				for(int y=0; y<MAX_Y;y++) {
    					for(int x=0; x<MAX_X; x++) {
    						if(board[y][x] == 'o')
    							dfs(y,x, cnt-1, moveCnt+1);
    					}
    				}
    				
    				// 복원 
    				board[sy][sx] = board[ny][nx] = 'o';
    				board[nny][nnx] = '.';
    				
    			}
    		}
    	}
    }
    
    static boolean OOB(int y,int x) {
    	return y<0 || y>=MAX_Y || x<0 || x>=MAX_X;
    }
    
    static char board[][];
    static int dy[] = {-1,0,1,0};
    static int dx[] = {0,1,0,-1};
    static int MAX_Y = 5;
    static int MAX_X = 9;
    static int total, move;
}