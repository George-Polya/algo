import java.io.*;
import java.util.*;
public class Main {
	static int a;
	static long b;
	static long board[][];
	static StringTokenizer st;

	static int MOD = 1000;
	static long[][] product(long board1[][], long board2[][]){
		long ret[][] = new long[a][a];
		
		for(int i = 0; i < a; i++) {
			for(int j = 0; j < a; j++) {
				for(int k = 0; k < a; k++) {
					ret[i][j] += (board1[i][k] * board2[k][j]) % MOD;
					ret[i][j] %= MOD;
				}
			}
		}
		
		
		return ret;
		
	}
	
	static long[][] product(long a[][], long b){
		if(b == 1) {
			return a; 
		}
		
		long temp[][] = product(a, b/2);
		if( b % 2 == 0) {
			return product(temp, temp);
		}else {
			temp = product(temp, temp);
			return product(temp, a);
		}
	}
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		a = Integer.parseInt(st.nextToken());
		b = Long.parseLong(st.nextToken());
		board = new long[a][a];
		
		for(int y= 0 ;y < a; y++) {
			st = new StringTokenizer(br.readLine());
			for(int x = 0 ; x < a; x++)
				board[y][x] = Integer.parseInt(st.nextToken());
		}
		
		StringBuilder sb = new StringBuilder();
		long result[][] = product(board,b);
		for(int y= 0 ;y < a; y++) {
			for(int x= 0; x< a;x++)
				sb.append(result[y][x] % MOD).append(' ');
			sb.append('\n');
		}
		System.out.println(sb);
		
	}

}