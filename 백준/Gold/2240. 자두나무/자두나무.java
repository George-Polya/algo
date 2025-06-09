import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {

    static int T, W;
    static int[] plums;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        plums = new int[T + 1];
        for (int i = 1; i <= T; i++) {
            plums[i] = Integer.parseInt(br.readLine());
        }

        // dp 테이블을 -1로 초기화하여 방문 여부를 확인합니다.
        dp = new int[T + 1][W + 2]; // moves는 W+1까지 가능하므로 W+2 크기
        for (int i = 0; i <= T; i++) {
            Arrays.fill(dp[i], -1);
        }

        // 1초에 1번 나무에 있는 경우(0번 움직임)와
        // 1초에 2번 나무로 이동한 경우(1번 움직임)를 모두 고려합니다.
        int result1 = solve(1, 0);
        int result2 = solve(1, 1);
        
        System.out.println(Math.max(result1, result2));
    }

    /**
     * @param time 현재 시간 (1 ~ T)
     * @param moves 현재까지 움직인 횟수 (0 ~ W)
     * @return time초부터 끝날 때까지 얻을 수 있는 최대 자두 개수
     */
    static int solve(int time, int moves) {
        // 기저 사례 1: 움직임 횟수 초과
        if (moves > W) {
            return -1_000_000_000; // max 연산에서 선택되지 않도록 아주 작은 값 반환
        }

        // 기저 사례 2: 모든 시간이 지남
        if (time > T) {
            return 0;
        }

        // 메모이제이션: 이미 계산한 값이면 바로 반환
        if (dp[time][moves] != -1) {
            return dp[time][moves];
        }

        // 현재 위치 계산: 짝수번 움직였으면 1번, 홀수번 움직였으면 2번 나무
        int location = (moves % 2) + 1;

        // 현재 시간에 자두를 받을 수 있는지 확인
        int canGetPlum = (location == plums[time]) ? 1 : 0;

        // 점화식: (현재 얻는 자두) + max(미래에 가만히 있을 경우, 미래에 움직일 경우)
        dp[time][moves] = canGetPlum + Math.max(solve(time + 1, moves), solve(time + 1, moves + 1));

        return dp[time][moves];
    }
}