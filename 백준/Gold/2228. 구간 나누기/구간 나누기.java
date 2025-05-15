import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] arr = new int[N + 1]; // 1-indexed
        int[] S = new int[N + 1];   // 1-indexed prefix sum, S[0] = 0

        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            S[i] = S[i - 1] + arr[i];
        }

        // dp[i][j]: i번째 원소를 j번째 구간의 마지막 원소로 사용했을 때, j개 구간의 최대 합
        int[][] dp = new int[N + 1][M + 1];
        // maxOverall[i][j]: 첫 i개 원소까지 고려하여 j개 구간을 선택했을 때의 최종 최대 합
        int[][] maxOverall = new int[N + 1][M + 1];

        // 매우 작은 음수 값으로 초기화 (문제 제약상 나올 수 있는 최소 합보다 작게)
        // 최소 합: 100 * (-32768) = -3,276,800
        // M개의 구간을 선택해야 하므로, 초기값은 이보다 더 작아야 할 수 있음.
        // 예를 들어, M=1이고 모든 수가 음수일 때, 하나의 음수 구간을 선택해야 함.
        // 안전하게 -400,000,000 정도로 설정 (int 범위 내)
        int MIN_VALUE = -400000000; // 충분히 작은 값

        for (int i = 0; i <= N; i++) {
            Arrays.fill(dp[i], MIN_VALUE);
            Arrays.fill(maxOverall[i], MIN_VALUE);
        }

        // 0개의 구간을 선택하면 합은 0
        for (int i = 0; i <= N; i++) {
            maxOverall[i][0] = 0;
        }

        // j: 구간의 개수
        for (int j = 1; j <= M; j++) {
            // i: 현재 고려하는 원소의 인덱스 (j번째 구간의 끝)
            for (int i = 1; i <= N; i++) {
                // dp[i][j] 계산
                // arr[i]를 j번째 구간의 마지막 원소로 사용
                // j번째 구간은 arr[k...i] 형태
                for (int k = 1; k <= i; k++) {
                    int currentSectionSum = S[i] - S[k - 1];
                    int prevMaxSum;

                    if (j == 1) {
                        prevMaxSum = 0; // 첫 번째 구간이면 이전 합은 0
                    } else {
                        // k-2가 0보다 작으면 j-1개의 구간을 만들 공간이 없음
                        // (k-2) < (j-1)*2 -1  -> 이 조건은 M개의 구간을 만들 수 있는지에 대한 조건과 유사
                        // k-2 인덱스까지 j-1개의 구간을 만들어야 함.
                        // 최소한 j-1개의 구간을 만들려면 (j-1)개의 숫자와 (j-2)개의 공백이 필요.
                        // 즉, (j-1) + (j-2) = 2j - 3 개의 원소가 필요.
                        // k-2 >= 0 이어야 함.
                        if (k - 2 >= 0) {
                             prevMaxSum = maxOverall[k - 2][j - 1];
                        } else {
                            prevMaxSum = MIN_VALUE; // 불가능한 경우
                        }
                    }

                    if (prevMaxSum != MIN_VALUE) { // 이전 j-1개 구간 형성이 가능했다면
                        dp[i][j] = Math.max(dp[i][j], prevMaxSum + currentSectionSum);
                    }
                }

                // maxOverall[i][j] 계산
                // 1. i번째 원소를 사용하지 않는 경우
                if (i > 0) { // i=0일 때는 maxOverall[-1][j]가 되므로 방지
                     maxOverall[i][j] = maxOverall[i-1][j];
                } else { // i=0일 때, j>0 이면 구간을 만들 수 없으므로 MIN_VALUE
                    maxOverall[i][j] = (j==0) ? 0 : MIN_VALUE;
                }

                // 2. i번째 원소를 j번째 구간의 마지막으로 사용하는 경우 (dp[i][j] 값)
                maxOverall[i][j] = Math.max(maxOverall[i][j], dp[i][j]);
            }
        }

        System.out.println(maxOverall[N][M]);
    }
}