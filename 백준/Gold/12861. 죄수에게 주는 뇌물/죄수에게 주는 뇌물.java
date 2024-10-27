import java.io.*;
import java.util.*;

public class Main {
    static final int INF = Integer.MAX_VALUE;

    static int p, q;
    static int arr[];
    static int dp[][];
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        p = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        arr = new int[q + 2]; // 위치를 저장할 배열 생성
        arr[0] = 0; // 시작 위치 추가
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= q; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        arr[q + 1] = p + 1; // 끝 위치 추가

        Arrays.sort(arr);

        // dp 배열 초기화
        dp = new int[q+2][q+2];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        System.out.println(solve(0, q+1));
    }

    static int solve(int L, int R) {
        if (L+1 >= R) {
            return 0;
        }

        if (dp[L][R] != -1) {
            return dp[L][R];
        }
        
        dp[L][R] = INF;
        
        for (int i = L + 1; i < R; i++) {
            dp[L][R] = Math.min(dp[L][R], solve(L, i) + solve(i, R) +
                    (arr[R] - 1) - (arr[L] + 1));
        }
        
        return dp[L][R];
    }
}