import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // 1. 입력 처리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 문제의 개수
        int T = Integer.parseInt(st.nextToken()); // 남은 제출 기한

        int[][] problems = new int[N][2]; // 각 문제의 [소요 일수, 벌금]
        int totalFine = 0; // 전체 벌금의 합

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            problems[i][0] = Integer.parseInt(st.nextToken()); // 소요 일수 (d_i)
            problems[i][1] = Integer.parseInt(st.nextToken()); // 벌금 (m_i)
            totalFine += problems[i][1];
        }

        // 2. DP를 이용한 문제 해결 (0/1 Knapsack)
        // dp[j] = j일의 시간을 사용했을 때 아낄 수 있는 벌금의 최댓값
        int[] dp = new int[T + 1];

        // N개의 각 문제에 대하여
        for (int i = 0; i < N; i++) {
            int days = problems[i][0];
            int fine = problems[i][1];

            // 배낭 문제의 핵심: 하나의 아이템(문제)이 여러 번 선택되는 것을 막기 위해
            // 시간을 역순으로 순회한다.
            for (int j = T; j >= days; j--) {
                // 현재 j일의 시간이 있을 때, i번째 문제를 푸는 경우와 풀지 않는 경우 중
                // 더 많은 벌금을 아낄 수 있는 쪽을 선택한다.
                // dp[j] -> i번째 문제를 풀지 않는 경우 (이전 문제들로만 j일을 채웠을 때의 최대 가치)
                // dp[j - days] + fine -> i번째 문제를 푸는 경우 (i문제를 풀고 남은 시간으로 채운 최대 가치 + i문제 가치)
                dp[j] = Math.max(dp[j], dp[j - days] + fine);
            }
        }

        // 3. 결과 계산 및 출력
        // T일 동안 아낄 수 있는 최대 벌금은 dp[T]에 저장되어 있다.
        int maxSavedFine = dp[T];

        // 최종적으로 내야 하는 벌금 = 전체 벌금 - 아낀 벌금
        System.out.println(totalFine - maxSavedFine);
    }
}