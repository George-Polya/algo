import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static final int MOD = 1_000_000_009;
    static final int MAX_N = 100_000;
    static final int MAX_HALF = MAX_N / 2; // n의 절반에 대한 dp 테이블이 필요

    public static void main(String[] args) throws IOException {
        // 빠른 입력을 위한 BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 1. 보조 DP 테이블 계산 (미리 계산해두기)
        // dp[k] = 정수 k를 1, 2, 3의 합으로 나타내는 방법의 수
        long[] dp = new long[MAX_HALF + 1];

        // 기저 상태(Base cases) 설정
        dp[0] = 1; // 합이 0이 되는 경우는 아무것도 더하지 않는 1가지

        // 점화식을 이용해 dp 테이블 채우기
        for (int i = 1; i <= MAX_HALF; i++) {
            // dp[i] = dp[i-1] + dp[i-2] + dp[i-3]
            if (i >= 1) {
                dp[i] = (dp[i] + dp[i - 1]) % MOD;
            }
            if (i >= 2) {
                dp[i] = (dp[i] + dp[i - 2]) % MOD;
            }
            if (i >= 3) {
                dp[i] = (dp[i] + dp[i - 3]) % MOD;
            }
        }

        // 테스트 케이스 처리
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine());

            // 2. 본 문제 해결: 4가지 경우의 합 구하기
            long answer = 0;

            // Case 1: 가운데 항이 없는 경우 (예: 1+2+2+1 -> 절반은 1+2)
            // n = 2k, 즉 n이 짝수일 때만 가능
            // 절반인 n/2를 만드는 방법의 수와 같음
            if (n % 2 == 0) {
                answer = (answer + dp[n / 2]) % MOD;
            }
            
            // Case 2: 가운데 항이 2인 경우 (예: 1+2+1 -> 절반은 1)
            // n = 2k + 2, 즉 n-2가 짝수일 때만 가능 (n이 짝수)
            // 절반인 (n-2)/2를 만드는 방법의 수와 같음
            if (n - 2 >= 0 && (n - 2) % 2 == 0) {
                 answer = (answer + dp[(n - 2) / 2]) % MOD;
            }

            // Case 3: 가운데 항이 1인 경우 (예: 2+1+2 -> 절반은 2)
            // n = 2k + 1, 즉 n-1이 짝수일 때만 가능 (n이 홀수)
            // 절반인 (n-1)/2를 만드는 방법의 수와 같음
            if (n - 1 >= 0 && (n - 1) % 2 == 0) {
                answer = (answer + dp[(n - 1) / 2]) % MOD;
            }

            // Case 4: 가운데 항이 3인 경우 (예: 1+3+1 -> 절반은 1)
            // n = 2k + 3, 즉 n-3이 짝수일 때만 가능 (n이 홀수)
            // 절반인 (n-3)/2를 만드는 방법의 수와 같음
            if (n - 3 >= 0 && (n - 3) % 2 == 0) {
                answer = (answer + dp[(n - 3) / 2]) % MOD;
            }
            
            sb.append(answer).append('\n');
        }

        System.out.print(sb.toString());
    }
}