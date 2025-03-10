import java.util.*;
import java.io.*;

public class Main {
 
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	st = new StringTokenizer(br.readLine());
    	N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());
    	target = new char[N+1][M+1];
    	board = new char[N+1][M+1];
    	for(int y=1; y<=N; y++) {
    		String line = br.readLine();
    		for(int x=1; x<=M; x++) {
    			target[y][x] = line.charAt(x-1);
    			board[y][x] = '.';
    		}
    	}
    	
    	int maxSize = (Math.min(N, M) - 1) / 2;
    	
    	List<int[]> ans = new ArrayList<>();
    	
    	for(int size = 1; size<=maxSize;size++) {
    		for(int cy = 1 + size; cy<=N-size;cy++) {
    			for(int cx = 1 + size; cx<=M-size; cx++) {
    				if(possible(cy,cx,size)) {
    					cover(cy,cx,size);
    					ans.add(new int[] {cy,cx,size});
    					
    					if (isEqual(target, board)) {
    						StringBuilder sb = new StringBuilder();
    						sb.append(ans.size()).append('\n');
    						for(int[] t : ans) {
    							sb.append(t[0]+" "+t[1]+" "+t[2]).append('\n');
    						}
    						
    						System.out.println(sb);
    						System.exit(0);
    					}
    					
    				}
    			}
    		}
    	}
    	
    	System.out.println(-1);
    			
    			
    }
    
    static boolean possible(int cy, int cx, int sz) {
    	if (target[cy][cx] == '.')
    		return false;
    	
    	for(int size =1; size<=sz; size++) {
    		for(int dir = 0; dir< 4;dir++) {
    			int ny = cy + dy[dir] * size;
    			int nx = cx + dx[dir] * size;
    			
    			if(target[ny][nx] == '.')
    				return false;
    		}
    	}
    	
    	return true;
    }
    
    static void cover(int cy, int cx, int sz) {
    	board[cy][cx] = '*';
    	for(int size =1; size<=sz; size++) {
    		for(int dir = 0; dir< 4;dir++) {
    			int ny = cy + dy[dir] * size;
    			int nx = cx + dx[dir] * size;
    			
    			board[ny][nx] = '*';
    		}
    	}
    }
    
    static boolean isEqual(char target[][], char board[][]) {
    	for(int y=1; y<=N; y++) {
    		for(int x=1; x<=M; x++) {
    			if(target[y][x] != board[y][x])
    				return false;
    		}
    	}
    	
    	return true;
    }
    
    static int dy[] = {-1,1,0,0};
    static int dx[] = {0,0,-1,1};
    
    static StringTokenizer st;
    static int N,M;
    static char target[][], board[][];
}