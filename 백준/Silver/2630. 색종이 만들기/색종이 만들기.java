import java.io.*;
import java.util.*;
/**
 * 분할정
 * 하나의 정사각형이면 종료
 * 전부 같은 숫자면 종료
 * 둘 다 아니면
 * 시작점 4개에 대해서 분할정복
 */
public class Main {
	static int n;
	static int board[][];
	static StringTokenizer st;
	static int arr[] = new int[2];

	static boolean divide(int sy,int sx, int size){
		for(int y= sy; y <= sy + size -1; y++){
			for(int x=sx; x<=sx+size-1; x++){
				if(board[sy][sx] != board[y][x])
					return true;
			}
		}
		return false;
	}
	static void solve(int sy,int sx, int size){
		if(size == 1 || !divide(sy,sx,size)){
			arr[board[sy][sx]] += 1;
			return;
		}

		solve(sy,sx,size/2);
		solve(sy,sx+size/2, size/2);
		solve(sy+size/2, sx, size/2);
		solve(sy+size/2,sx+size/2,size/2);
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		board = new int[n+1][n+1];
		for(int y=1; y<=n; y++){
			st = new StringTokenizer(br.readLine());
			for(int x=1; x<=n; x++){
				board[y][x] = Integer.parseInt(st.nextToken());
			}
		}

		solve(1,1,n);
		for(int i = 0; i < 2; i++){
			System.out.println(arr[i]);
		}
	}
}