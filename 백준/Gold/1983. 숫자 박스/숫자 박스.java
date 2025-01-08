import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		board = new int[2][n+1];
		for(int y = 0; y< 2; y++) {
			st = new StringTokenizer(br.readLine());
			for(int x=1; x<=n;x++) {
//				board[y][x] = Integer.parseInt(st.nextToken());
				int value = Integer.parseInt(st.nextToken());
				
				if(value == 0)
					continue;
				
				if(y == 0)
					board[y][++up] = value;
				else
					board[y][++down] = value;
			}
		}
		
//		dp = new int[n+1][up+1][down+1];
		dp = new int[n+1][n+1][n+1];
		
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                for (int k = 1; k <=i; k++) {
                    if (j == i && k == i) {
                        dp[i][j][k] = dp[i - 1][j - 1][k - 1] + board[0][j] * board[1][k];
                    } else if (j == i) {
                        dp[i][j][k] = Math.max(dp[i - 1][j - 1][k], dp[i - 1][j - 1][k - 1] + board[0][j] * board[1][k]);
                    } else if (k == i) {
                        dp[i][j][k] = Math.max(dp[i - 1][j][k - 1], dp[i - 1][j - 1][k - 1] + board[0][j] * board[1][k]);
                    } else {
                        dp[i][j][k] = Math.max(
                                dp[i - 1][j - 1][k - 1] + board[0][j] * board[1][k],
                                Math.max(dp[i - 1][j - 1][k], dp[i - 1][j][k - 1])
                        );
                    }
                }
            }
        }

        // 결과 출력
        System.out.println(dp[n][up][down]);
		
	}
	static int dp[][][];
	static StringTokenizer st;
	static int n,up, down;
	static int board[][];
		
}