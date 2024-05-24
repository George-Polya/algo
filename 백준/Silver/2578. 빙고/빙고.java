import java.io.*;
import java.util.*;

public class Main{
	static int n = 5;
	static int board[][];
	static class Pair{
		int y,x;
		public Pair(int y,int x) {
			this.y = y;
			this.x = x;
		}
		
		public String toString() {
			return y+" "+x;
		}
	}
	
	static Pair arr[];
	static StringTokenizer st;
	
	static boolean check(int arr[]) {
		
		for(int i = 0; i<n;i++) {
			if(arr[i] != 0)
				return false;
		}
		
		return true;
	}
	
	static boolean check(int board[][]) {
		int cnt = 0;
		
		
		for(int y=0; y<n; y++) {
			if(check(board[y]))
				cnt+=1;
		}
		
		for(int x=0; x<n; x++) {
			int arr[] = new int[5];
			
			for(int y=0; y<n ;y++) {
				arr[y] = board[y][x];
			}
			if(check(arr))
				cnt+=1;
			
		}
		
		int arr[] = new int[5];
		for(int i = 0; i<n;i++) {
			arr[i] = board[i][i];
		}
		
		if(check(arr))
			cnt++;
		
		for(int i = 0; i < n; i++) {
			arr[i] = board[i][n-i-1];
		}
		if(check(arr))
			cnt++;
		
		
		return cnt >= 3;
	}
	
	static void printBoard(int board[][]) {
		for(int y=0; y<n; y++) {
			for(int x=0; x<n; x++)
				System.out.printf("%3d", board[y][x]);
			System.out.println();
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		board = new int[n][n];
		arr = new Pair[n*n+1];
		
		for(int y=0; y<n; y++) {
			st = new StringTokenizer(br.readLine());
			for(int x=0; x<n; x++) {
				int value = Integer.parseInt(st.nextToken());
				board[y][x] = value;
				arr[value] = new Pair(y,x);
			}
		}
		
		int idx = 0;
		for(int y=0;y<n;y++) {
			st = new StringTokenizer(br.readLine());
			for(int x=0; x<n; x++) {
				idx+=1;
				int value = Integer.parseInt(st.nextToken());
				Pair pair = arr[value];
				board[pair.y][pair.x] = 0;
				
				if(check(board)) {
					System.out.println(idx);
					return;
				}
			}
		}
	}
}