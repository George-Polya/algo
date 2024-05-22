import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/5557
// 1학년
public class Main {
    static int n;
    static long dp[][];
    static int arr[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dp = new long[n + 1][21];

        dp[1][arr[1]] = 1;

        for (int i = 2; i <= n; i++) {
            for (int prev = 0; prev <= 20; prev++) {
                for (int cur : new int[]{prev + arr[i], prev - arr[i]}) {
                    if(cur < 0 || cur > 20)
                        continue;
                    dp[i][cur] += dp[i - 1][prev];

                }
            }
        }

        System.out.println(dp[n - 1][arr[n]]);
    }
}
