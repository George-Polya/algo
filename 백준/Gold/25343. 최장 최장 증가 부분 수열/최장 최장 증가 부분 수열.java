import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        // 빠른 입력을 위한 BufferedReader 사용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 배열 크기 N을 입력받습니다.
        int N = Integer.parseInt(br.readLine());
        int[][] grid = new int[N][N];
        
        // N x N 배열의 값을 입력받습니다.
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        // dp 배열: 각 좌표에서의 최장 증가 부분 수열 길이를 저장합니다.
        int[][] dp = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], 1); // 모든 셀은 최소 길이 1을 가집니다.
        }
        
        // 각 셀에 대해, 왼쪽 위 영역에 있는 셀들 중
        // 현재 셀의 값보다 작은 셀을 찾아 dp 값을 갱신합니다.
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k <= i; k++) {
                    for (int l = 0; l <= j; l++) {
                        if (grid[k][l] < grid[i][j]) {
                            dp[i][j] = Math.max(dp[i][j], dp[k][l] + 1);
                        }
                    }
                }
            }
        }
        
        // dp 배열에서 가장 큰 값을 찾습니다.
        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                answer = Math.max(answer, dp[i][j]);
            }
        }
        
        System.out.println(answer);
    }
}