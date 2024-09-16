import java.io.*;
import java.util.*;

public class Main {
	static int k,n;
	static int ey,ex;
	static StringTokenizer st;
	static int board[][];
	static int cnt;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		k = Integer.parseInt(br.readLine());
		n = (1 << k);
		st = new StringTokenizer(br.readLine());
		ey = Integer.parseInt(st.nextToken()) - 1;
		ex = Integer.parseInt(st.nextToken()) - 1;
		
		
		int board[][] = new int[][] {
			{1,1,2,2},
			{1,5,5,2},
			{3,5,5,4},
			{3,3,4,4}
		};
		
        if (ey < 2 && ex < 2) {
            board[1][1] = 1;
        } else if (ey < 2 && ex >= 2) {
            board[1][2] = 2;
        } else if (ey >= 2 && ex < 2) {
            board[2][1] = 3;
        } else {
            board[2][2] = 4;
        }
        
        board[ey][ex] = -1;
        
        StringBuilder sb = new StringBuilder();
        
        for(int x =n-1; x>=0; x--) {
        	for(int y= 0; y < n; y++)
        		sb.append(board[y][x]).append(' ');
        	sb.append('\n');
        }
        System.out.println(sb);
	}
}