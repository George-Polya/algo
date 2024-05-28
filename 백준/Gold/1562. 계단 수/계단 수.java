// https://www.acmicpc.net/problem/1562

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int n;
    static long dp[][][];
    static final int mod = 1000000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        // i번째 자리에 k가 오려면 (i-1)번째 자리에 (k+1) 또는 (k-1)이 있어야함
        // 비트마스크를 사용하지 않고 메모이제이션을 하면 점화식은 dp[i][k] = dp[i-1][k-1] + dp[i-1][k+1]
        // 0부터 9까지 모든 수를 한번씩 사용해야 하므로 이를 비트마스크를 이용해서 기록
        // 1,2,3을 사용했다면 0000001110로, 0~9 모두 사용했다면 1111111111로 나타낼 수 있음
        // 비트마스크를 이용해 모든 수를 한번씩 사용했는지 확인을 위해 dp를 3차원으로 만들어 비트마스크 정보를 나타내는 인덱스를 만듦
        // dp[i][k][visit|(1<<k)] = dp[i-1][k-1][visit] + dp[i-1][k+1][visit]
        // i번째 자리에 k가 왔고, k를 사용했음을 visit | (1<<k)로 기록
        dp = new long[n + 1][10][1 << 10];

        // 초기값
        // 1번쨰 자리에 i가 올때
        for (int i = 1; i <= 9; i++) {
            dp[1][i][1 << i] = 1;
        }

        for (int i = 2; i <= n; i++) {
            for (int k = 0; k <= 9; k++) {
                for (int visit = 0; visit < (1 << 10); visit++) {
                    int newVisit = visit | (1 << k); // visit의 k번쨰 비트를 켜서 만든 새로운 visit

                    if (k == 0) { // 현재 위치에 0이 온다면 0보다 작은 수는 없으니까 이전 위치인 (i-1)번째 수는 1이다
                        dp[i][k][newVisit] += dp[i - 1][k + 1][visit] % mod;
                    } else if (k == 9) { // 현재 위치에 9가 온다면 9보다 큰 수는 없으니까 이전 위치인 (i-1)번째 수는 8이다
                        dp[i][k][newVisit] += dp[i - 1][k - 1][visit] % mod;
                    } else {
                        dp[i][k][newVisit] += dp[i - 1][k + 1][visit] % mod + dp[i - 1][k - 1][visit] % mod;
                    }

                    dp[i][k][newVisit] %= mod;

                }
            }
        }

        long sum = 0;
        for (int i = 0; i <= 9; i++) {
            sum += dp[n][i][(1<<10)-1] % mod;
            sum %= mod;
        }
        System.out.println(sum);

    }
}
