import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
	static int board[][],dist[][];
	static int n,m;
	static int INT_MAX = Integer.MAX_VALUE;
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		board = new int[n+1][n+1];
		dist = new int[n+1][n+1];
		for(int y=1; y<=n; y++) {
			st = new StringTokenizer(br.readLine());
			for(int x=1; x<=n; x++) {
				board[y][x] = Integer.parseInt(st.nextToken());
				dist[y][x] = board[y][x];
			}
		}
		
		for(int k =1 ; k<=n; k++) {
			for(int i =1; i<=n; i++) {
				for(int j = 1;j<=n;j++) {
					if(dist[i][j] <= dist[i][k] + dist[k][j])
						continue;
					dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			sb.append(dist[a][b] <= cost ? "Enjoy other party" : "Stay here").append('\n');
		}
		System.out.println(sb);
		
		
		
		
	}
}