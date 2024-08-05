import java.util.*;
import java.io.*;

public class Main {
	static StringTokenizer st;
	static int n,m;
	static int board[][], pSum[][];
	
	static int calc(int sy,int sx, int ey, int ex) {
		return pSum[ey][ex] - pSum[ey][sx-1] - pSum[sy-1][ex] + pSum[sy-1][sx-1];
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		board = new int[n+1][m+1];
		pSum = new int[n+1][m+1];
		
		for(int y=1; y<=n; y++) {
			st = new StringTokenizer(br.readLine());
			for(int x=1; x<=m; x++) {
				board[y][x] = Integer.parseInt(st.nextToken());
				pSum[y][x] = pSum[y-1][x] + pSum[y][x-1] - pSum[y-1][x-1] + board[y][x];
			}
		}
		
//		for(int y=1; y<=n; y++) {
//			System.out.println(Arrays.toString(pSum[y]));
//		}
		
		int ans = Integer.MIN_VALUE;
		
		for(int sy=1; sy<=n;sy++) {
			for(int sx=1; sx<=m; sx++) {
				for(int ey = sy; ey<=n; ey++) {
					for(int ex=sx; ex<=n; ex++) {
						ans = Math.max(ans, calc(sy,sx,ey,ex));
					}
				}
			}
		}
		System.out.println(ans);
	}
}