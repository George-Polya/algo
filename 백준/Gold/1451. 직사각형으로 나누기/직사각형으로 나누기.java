import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	st = new StringTokenizer(br.readLine());
    	N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());
    	board = new int[N+1][M+1];
    	pSum = new long[N+1][M+1];
    	
    	for(int y=1; y<=N; y++) {
    		String line = br.readLine();
    		for(int x=1; x<=M; x++) {
    			board[y][x] = line.charAt(x-1) - '0';
    			pSum[y][x] = pSum[y][x-1] + pSum[y-1][x] - pSum[y-1][x-1] + board[y][x];
    		}
    	}
    	
    	for(int x1=1; x1<= M-2; x1++) {
    		for(int x2=x1+1; x2<= M-1; x2++) {
    			Rec rec1 = new Rec(1,1, N, x1);
    			Rec rec2 = new Rec(1,x1+1,N, x2);
    			Rec rec3 = new Rec(1,x2+1,N, M);
    			
    			ans = Math.max(ans, rec1.calc() * rec2.calc() * rec3.calc());
    		}
    	}
    	
    	for(int y1=1; y1<=N-2; y1++) {
    		for(int y2=y1+1; y2<=N-1; y2++) {
    			Rec rec1 = new Rec(1,1,y1,M);
    			Rec rec2 = new Rec(y1+1,1,y2,M);
    			Rec rec3 = new Rec(y2+1,1,N,M);
    			ans = Math.max(ans, rec1.calc() * rec2.calc() * rec3.calc());
    		}
    	}
    	
    	
    	for(int y=1; y<N; y++) {
    		for(int x=1; x<M; x++) {
    			Rec rec1 = new Rec(1,1, N,x);
    			Rec rec2 = new Rec(1,x+1,y,M);
    			Rec rec3 = new Rec(y+1,x+1,N,M);
    			
    			ans = Math.max(ans, rec1.calc() * rec2.calc() * rec3.calc());
    			
    			
    			rec1 = new Rec(1,1,y,x);
    			rec2 = new Rec(y+1,1,N, x);
    			rec3 = new Rec(1,x+1,N,M);
    			ans = Math.max(ans, rec1.calc() * rec2.calc() * rec3.calc());
    			
    			rec1 = new Rec(1,1,y,x);
    			rec2 = new Rec(1,x+1,y,M);
    			rec3 = new Rec(y+1,1,N,M);
    			ans = Math.max(ans, rec1.calc() * rec2.calc() * rec3.calc());
    			
    			rec1 = new Rec(1,1,y,M);
    			rec2 = new Rec(y+1,1, N,x);
    			rec3 = new Rec(y+1,x+1,N,M);
    			ans = Math.max(ans, rec1.calc() * rec2.calc() * rec3.calc());
    		}
    	}
    	
    	System.out.println(ans);
    	
    }
 
    
    static long ans;
    static int N,M;
    static int board[][];
    static long pSum[][];
    static StringTokenizer st;
    static class Rec{
    	int y1, x1, y2,x2;
    	public Rec(int y1,int x1, int y2, int x2) {
    		this.y1 = y1;
    		this.x1 = x1;
    		this.y2 = y2;
    		this.x2 = x2;
    	}
    	
    	long calc() {
    		return pSum[y2][x2] - pSum[y1-1][x2] - pSum[y2][x1-1] + pSum[y1-1][x1-1];
    	}
    	
    	public String toString() {
    		return String.format("(%d, %d) (%d, %d)", y1,x1,y2,x2);
    	}
    }
}