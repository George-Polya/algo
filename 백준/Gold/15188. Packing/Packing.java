import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // Use BufferedReader for fast input reading.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int P = Integer.parseInt(br.readLine().trim());
        StringBuilder sb = new StringBuilder();
        
        // Process each problem
        for (int prob = 1; prob <= P; prob++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int W1 = Integer.parseInt(st.nextToken());
            int W2 = Integer.parseInt(st.nextToken());
            int[] weights = new int[N];
            int[] values = new int[N];
            
            // Read candidate present weights
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                weights[i] = Integer.parseInt(st.nextToken());
            }
            // Read candidate present values
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                values[i] = Integer.parseInt(st.nextToken());
            }
            
            // dp[w1][w2] will store the maximum total value achievable when
            // the first drone has used weight w1 and the second drone has used weight w2.
            int[][] dp = new int[W1 + 1][W2 + 1];
            // Initialize dp with -1 (impossible states) except for the starting state (0,0)
            for (int i = 0; i <= W1; i++) {
                Arrays.fill(dp[i], -1);
            }
            dp[0][0] = 0;
            
            // Process each present (each item can be used at most once)
            for (int i = 0; i < N; i++) {
                int wt = weights[i];
                int val = values[i];
                // Traverse in reverse order to avoid using the same item more than once.
                for (int w1 = W1; w1 >= 0; w1--) {
                    for (int w2 = W2; w2 >= 0; w2--) {
                        if (dp[w1][w2] == -1) continue; // not a reachable state
                        // Option 1: load the item on drone 1 if it fits.
                        if (w1 + wt <= W1) {
                            dp[w1 + wt][w2] = Math.max(dp[w1 + wt][w2], dp[w1][w2] + val);
                        }
                        // Option 2: load the item on drone 2 if it fits.
                        if (w2 + wt <= W2) {
                            dp[w1][w2 + wt] = Math.max(dp[w1][w2 + wt], dp[w1][w2] + val);
                        }
                    }
                }
            }
            
            // Find the best achievable total value among all valid loadings.
            int ans = 0;
            for (int i = 0; i <= W1; i++) {
                for (int j = 0; j <= W2; j++) {
                    ans = Math.max(ans, dp[i][j]);
                }
            }
            
            sb.append("Problem ").append(prob).append(": ").append(ans).append("\n");
        }
        
        System.out.print(sb);
    }
}