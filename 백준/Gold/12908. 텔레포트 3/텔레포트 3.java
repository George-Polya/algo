import java.util.*;
import java.io.*;

public class Main{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		pairs = new Pair[8];
		st = new StringTokenizer(br.readLine());
		sx = Integer.parseInt(st.nextToken());
		sy = Integer.parseInt(st.nextToken());
		pairs[idx] = new Pair(sx,sy);
		idx++;
		
		st = new StringTokenizer(br.readLine());
		ex = Integer.parseInt(st.nextToken());
		ey = Integer.parseInt(st.nextToken());
		pairs[idx] = new Pair(ex,ey);
		idx++;
		dist = new long[8][8];
		for(long row[] : dist) {
			Arrays.fill(row, INF);
		}
		for(int a = 0; a < 3; a++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			pairs[idx] = new Pair(x1,y1);
			int i = idx;
			idx += 1;
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			pairs[idx] = new Pair(x2,y2);
			int j = idx;
			idx += 1;
			
			dist[i][j] = 10;
			dist[j][i] = 10;
		}
		
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(i == j) {
					dist[i][j] = dist[j][i] = 0;
					continue;
				}
				
				Pair pair1 = pairs[i];
				Pair pair2 = pairs[j];
				
				long distance = Math.abs(pair1.x - pair2.x) + Math.abs(pair1.y - pair2.y);
				dist[i][j] = Math.min(dist[i][j], distance);
				dist[j][i] = Math.min(dist[j][i], distance);
			}
		}
		
		for(int k = 0; k < 8; k++) {
			for(int i = 0; i < 8; i++) {
				for(int j = 0; j< 8; j++) {
					dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
				}
			}
		}
		
		System.out.println(dist[0][1]);
		
	}
	static long dist[][];
	static long INF = Long.MAX_VALUE / 2;
	static StringTokenizer st;
	static int idx;
	static Pair pairs[];
	static int sx,sy,ex,ey;
	
	static class Pair{
		int x,y;
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}