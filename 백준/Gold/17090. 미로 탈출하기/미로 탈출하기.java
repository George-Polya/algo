import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static char[][] maze;
    static int[][] dp; // 0: UNCHECKED, 1: ESCAPABLE, -1: TRAPPED, 2: VISITING

    // ... (main 함수는 동일)
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        maze = new char[N][M];
        dp = new int[N][M];

        for (int i = 0; i < N; i++) {
            maze[i] = br.readLine().toCharArray();
        }

        int escapableCount = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // dfs의 결과가 1(ESCAPABLE)이면 카운트
                if (dfs(i, j) == 1) {
                    escapableCount++;
                }
            }
        }
        System.out.println(escapableCount);
    }
    
    public static int dfs(int r, int c) {
        // 1. 미로 범위를 벗어남 -> 탈출 성공
        if (r < 0 || r >= N || c < 0 || c >= M) {
            return 1; // ESCAPABLE
        }

        // 2. 이미 결과가 계산된 칸
        if (dp[r][c] != 0 && dp[r][c] != 2) { // UNCHECKED(0)나 VISITING(2)가 아니면
            return dp[r][c]; // 저장된 결과(1 또는 -1) 반환
        }

        // 3. 현재 탐색중인 경로에서 다시 만남 -> 사이클!
        if (dp[r][c] == 2) {
            return -1; // TRAPPED
        }

        // 현재 칸을 '탐색 중'으로 표시
        dp[r][c] = 2; // VISITING

        char direction = maze[r][c];
        int next_r = r, next_c = c;
        if (direction == 'U') next_r--;
        else if (direction == 'R') next_c++;
        else if (direction == 'D') next_r++;
        else if (direction == 'L') next_c--;

        // 다음 칸 탐색 결과를 현재 칸의 최종 결과로 저장 및 반환
        return dp[r][c] = dfs(next_r, next_c);
    }
}