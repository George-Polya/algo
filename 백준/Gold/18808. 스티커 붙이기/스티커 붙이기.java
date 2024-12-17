import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        
        board = new int[n][m];
        stickers = new int[k][][];
        Y = new int[k];
        X = new int[k];
        
        for(int i = 0; i < k; i++) {
        	st = new StringTokenizer(br.readLine());
        	Y[i] = Integer.parseInt(st.nextToken());
        	X[i] = Integer.parseInt(st.nextToken());
        	
        	stickers[i] = new int[Y[i]][X[i]];
        	
        	for(int y =0; y < Y[i];y++) {
        		st = new StringTokenizer(br.readLine());
        		for(int x = 0; x < X[i]; x++) {
        			stickers[i][y][x] = Integer.parseInt(st.nextToken());
        		}
        	}
        }
        
        
        solve(0);
        
        int ans=0;
        for(int y=0; y<n; y++) {
        	for(int x=0; x<m; x++) {
        		if(board[y][x] == 1)
        			ans++;
        	}
        }
        System.out.println(ans);
    }
    
    
    static void solve(int cur) {
    	if(cur == k)
    		return;
    	
    	int sticker[][] = stickers[cur];
    	
    	// 회전 시도
    	for(int rotation = 0; rotation <4 ; rotation++) {
    		int r = sticker.length;
    		int c = sticker[0].length;
    		
    		for(int y = 0; y<=n-r; y++) {
    			for(int x = 0; x<=m-c; x++) {
    				if(canAttach(y,x,sticker)) { // 붙일 수 있으면 
    					attach(y,x,sticker); // 붙이고 
    					solve(cur + 1); // 다음으로 넘어가고 
    					return; // 종료 
    				}
    			}
    		}
    		
    		sticker = rotate(sticker);
    	}
    	
    	solve(cur + 1); // 회전을 모두 해도 붙이지 못하면 다음 스티커로 
    }
    
    static int[][] rotate(int sticker[][]){
    	int r = sticker.length;
    	int c = sticker[0].length;
    	
    	int rotated[][] = new int[c][r];
    	
    	for(int y= 0 ;y < r; y++) {
    		for(int x =0; x< c;x++) {
    			rotated[x][r - 1 - y] = sticker[y][x];
    		}
    	}
    	
    	return rotated;
    }
    
    static boolean canAttach(int sy,int sx, int sticker[][]) {
    	int r = sticker.length;
    	int c = sticker[0].length;
    	
    	for(int y=0;y <r ;y++) {
    		for(int x=0; x<c; x++) {
    			if(sticker[y][x] == 1 && board[sy + y][sx + x] == 1)
    				return false;
    		}
    	}
    	
    	return true;
    }
    
    static void attach(int sy, int sx, int sticker[][]) {
    	int r = sticker.length;
    	int c = sticker[0].length;
    	
    	for(int y= 0; y< r; y++) {
    		for(int x=0; x<c;x++) {
    			if(sticker[y][x] == 1)
    				board[sy+y][sx+x] = 1;
    		}
    	}
    }
    
    static StringTokenizer st;
    static int n,m,k;
    static int board[][], stickers[][][], Y[], X[];
    
}