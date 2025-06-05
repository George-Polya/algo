import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // --- 1. 입력 받기 (수정된 부분) ---
        // 첫 줄을 읽고 StringTokenizer로 분리하여 n과 m을 읽습니다.
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 남자의 수
        int m = Integer.parseInt(st.nextToken()); // 여자의 수

        int[] men = new int[n];
        int[] women = new int[m];

        // 남자 성격 값 입력
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            men[i] = Integer.parseInt(st.nextToken());
        }

        // 여자 성격 값 입력
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            women[i] = Integer.parseInt(st.nextToken());
        }

        // --- 2. 항상 n <= m 이 되도록 처리 ---
        if (n > m) {
            int tempN = n;
            n = m;
            m = tempN;
            int[] tempArr = men;
            men = women;
            women = tempArr;
        }

        // --- 3. 두 그룹 모두 오름차순으로 정렬 ---
        Arrays.sort(men);
        Arrays.sort(women);

        // --- 4. DP 테이블 생성 ---
        long[][] dp = new long[n + 1][m + 1];

        // --- 5. DP 점화식을 이용해 테이블 채우기 ---
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                long matchCase = dp[i - 1][j - 1] + Math.abs(men[i - 1] - women[j - 1]);
                if (i == j) {
                    dp[i][j] = matchCase;
                } else { // j > i
                    long skipCase = dp[i][j - 1];
                    dp[i][j] = Math.min(matchCase, skipCase);
                }
            }
        }

        // --- 6. 최종 결과 출력 ---
        System.out.println(dp[n][m]);
    }
}