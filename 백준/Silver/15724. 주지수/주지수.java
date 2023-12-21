import java.io.*;
/**
 * 누적합 
 */
import java.util.*;
public class Main {
	static int n,m,k;
	static int board[][], pSum[][];
	static StringTokenizer st;
	
	static int getArea(int y1, int x1, int y2, int x2) {
		return pSum[y2][x2] - pSum[y1-1][x2] - pSum[y2][x1-1] + pSum[y1-1][x1-1];
	}
	
	static void printBoard(int board[][]) {
		for(int y=1; y<=n; y++) {
			for(int x=1; x<=m; x++) {
				System.out.printf("%d ", board[y][x]);
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) throws IOException{
	 	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	 	st = new StringTokenizer(br.readLine());
	 	n = Integer.parseInt(st.nextToken());
	 	m = Integer.parseInt(st.nextToken());
	 	board = new int[n+1][m+1];
	 	for(int y=1; y<=n; y++) {
	 		st = new StringTokenizer(br.readLine());
	 		for(int x=1; x<=m; x++) {
	 			board[y][x] = Integer.parseInt(st.nextToken());
	 		}
	 	}
	 	
	 	pSum = new int[n+1][m+1];
	 	for(int y=1; y<=n; y++) {
	 		for(int x=1;x<=m; x++) {
	 			pSum[y][x] = pSum[y-1][x] + pSum[y][x-1] + board[y][x] - pSum[y-1][x-1];
	 		}
	 	}
	 	
//	 	printBoard(pSum);
	 	
	 	k = Integer.parseInt(br.readLine());
	 	StringBuilder sb = new StringBuilder();
	 	for(int i = 0; i<k;i++) {
	 		st = new StringTokenizer(br.readLine());
	 		int y1 = Integer.parseInt(st.nextToken());
	 		int x1 = Integer.parseInt(st.nextToken());
	 		int y2 = Integer.parseInt(st.nextToken());
	 		int x2 = Integer.parseInt(st.nextToken());
	 		sb.append(getArea(y1,x1,y2,x2)).append('\n');
	 		
	 	}
	 	System.out.println(sb);
	 	
	}
}