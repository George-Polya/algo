import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        // dp[l][r] represents the maximum score for the subarray arr[l...r]
        int[][] dp = new int[n][n];

        // Iterate over the possible lengths of subarrays
        for (int len = 1; len <= n; len++) {
            for (int l = 0; l + len - 1 < n; l++) {
                int r = l + len - 1; // Right boundary of the subarray
                int depth = n - len + 1; // Depth is calculated based on the length of the subarray

                if (l == r) {
                    // Base case: single element subarray
                    dp[l][r] = arr[l] * depth;
                } else {
                    // Transition: take either the leftmost or the rightmost element
                    dp[l][r] = Math.max(
                        dp[l + 1][r] + arr[l] * depth, // Take the left element
                        dp[l][r - 1] + arr[r] * depth  // Take the right element
                    );
                }
            }
        }

        // The result for the entire array is stored in dp[0][n-1]
        System.out.println(dp[0][n - 1]);
    }
}