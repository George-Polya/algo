import java.io.*;
import java.util.*;

public class Main {
    static final int MOD = 10007;
    static int N, M, H;
    static int[][] dp;
    static List<Integer>[] blocks;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        blocks = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            blocks[i] = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                blocks[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        dp = new int[N + 1][H + 1];
        for (int i = 0; i <= N; i++) {
            Arrays.fill(dp[i], -1);
        }

        System.out.println(solve(1, 0));
    }

    static int solve(int i, int h) {
        if (h == H) return 1;
        if (i > N || h > H) return 0;
        if (dp[i][h] != -1) return dp[i][h];

        int result = solve(i + 1, h) % MOD;
        for (int block : blocks[i]) {
            result = (result + solve(i + 1, h + block)) % MOD;
        }
        return dp[i][h] = result;
    }
}
