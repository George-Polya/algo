import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static int[][] memo; // 메모이제이션 배열
    static final int MOD = 1_000_000_003;

    // n개의 색이 선형으로 있을 때, k개의 색을 이웃하지 않게 선택하는 경우의 수
    // (탑다운 DP)
    static int solve_linear(int n, int k) {
        // 기저 사례
        if (k == 0) { // 선택할 색이 없으면 1가지 방법 (아무것도 선택하지 않음)
            return 1;
        }
        if (n <= 0) { // 더 이상 선택할 색이 없는데 k개가 남았으면 불가능
            return 0;
        }
        // k개의 색을 이웃하지 않게 뽑으려면 최소 n >= 2k-1 이어야 함.
        // 하지만 k=1일 때는 n=1도 가능.
        // 좀 더 명확하게, k=1일 때 n개를 리턴하도록 처리.
        if (k == 1) {
            return n; // n개 중 1개 선택하는 경우의 수는 n가지
        }
        // 만약 n개의 아이템으로 k개의 아이템을 뽑는데, 필요한 최소 개수보다 적다면 0
        // (예: 3개로 2개 뽑으려면 O X O, 최소 3개 필요. 2개로 2개 뽑으려면 O X O 불가)
        // n < k (당연히 불가능) 또는 n < 2k-1 (이웃하지 않게 뽑을 공간 부족)
        // 좀 더 단순화하면, 만약 k개를 뽑는데 n개가 k보다 작으면 당연히 0
        if (n < k) { // 뽑아야 할 개수가 전체 개수보다 많으면 불가능
            return 0;
        }


        if (memo[n][k] != -1) { // 이미 계산된 값이면 반환
            return memo[n][k];
        }

        // 점화식:
        // 1. n번째 색을 선택하는 경우:
        //    n-1번째 색은 선택 불가. n-2개 중에서 k-1개 선택
        long count = solve_linear(n - 2, k - 1);

        // 2. n번째 색을 선택하지 않는 경우:
        //    n-1개 중에서 k개 선택
        count = (count + solve_linear(n - 1, k)) % MOD;

        return memo[n][k] = (int) count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        memo = new int[N + 1][K + 1];
        for (int i = 0; i <= N; i++) {
            Arrays.fill(memo[i], -1); // -1로 초기화 (아직 계산 안됨 표시)
        }

        if (K == 0) {
            System.out.println(1); // 아무것도 안 고르는 경우 1가지 (문제 조건에 따라 0일 수도 있음, 문제에서는 K>=1)
                                  // 문제에서는 K가 1 이상이므로 이 경우는 사실상 없음.
            return;
        }
        
        if (K == 1) {
            System.out.println(N);
            return;
        }

        // N/2 보다 많은 색을 선택하는 것은 불가능 (서로 이웃하지 않아야 하므로)
        if (K > N / 2) {
            System.out.println(0);
            return;
        }

        // 원형 배열 문제 해결:
        // 1. 첫 번째 색(1번)을 선택하지 않는 경우:
        //    2번부터 N번까지의 N-1개의 색 중에서 K개를 선택하는 선형 문제.
        //    이는 1번부터 N-1번까지의 N-1개 색 중 K개를 선택하는 것과 동일.
        int ans1 = solve_linear(N - 1, K);

        // 2. 첫 번째 색(1번)을 선택하는 경우:
        //    1번 색을 선택했으므로, 2번과 N번 색은 선택 불가.
        //    남은 색은 3번부터 N-1번까지 (총 N-3개).
        //    이 중에서 K-1개를 선택하는 선형 문제.
        int ans2 = solve_linear(N - 3, K - 1);
        
        // N=4, K=2일 때, N-3 = 1. solve_linear(1, 1) = 1.
        // N=3, K=2일 때, N/2 = 1.5. K > N/2 이므로 0.
        //   ans1 = solve_linear(2,2) -> k=1일때 n 리턴 조건에 안걸리고, n<k도 아님.
        //      solve_linear(0,1) + solve_linear(1,2) = 0 + 0 = 0
        //   ans2 = solve_linear(0,1) = 0
        //   답 0. (실제: 3개로 2개 못고름)

        int final_ans = (ans1 + ans2) % MOD;
        System.out.println(final_ans);
    }
}