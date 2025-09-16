import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        // 1. 입력 처리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] profit = new int[N + 1][N + 1];
        int[][] prefixSum = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                profit[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 2. 누적 합 배열 생성 (전처리)
        // prefixSum[i][j] = (1,1)부터 (i,j)까지의 사각형 합
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                prefixSum[i][j] = profit[i][j] 
                                + prefixSum[i - 1][j] 
                                + prefixSum[i][j - 1] 
                                - prefixSum[i - 1][j - 1];
            }
        }

        // 3. 최대 이익 계산
        // 초기 최댓값은 가장 작은 값으로 설정
        int maxProfit = Integer.MIN_VALUE;

        // K: 정사각형의 크기 (1 ~ N)
        for (int k = 1; k <= N; k++) {
            // i, j: 정사각형의 오른쪽 아래 꼭짓점 좌표
            for (int i = k; i <= N; i++) {
                for (int j = k; j <= N; j++) {
                    // (i, j)를 오른쪽 아래 꼭짓점으로 하는 k x k 정사각형의 합 계산
                    int currentSum = prefixSum[i][j] 
                                   - prefixSum[i - k][j] 
                                   - prefixSum[i][j - k] 
                                   + prefixSum[i - k][j - k];
                    
                    // 최댓값 갱신
                    if (currentSum > maxProfit) {
                        maxProfit = currentSum;
                    }
                }
            }
        }

        // 4. 결과 출력
        System.out.println(maxProfit);
    }
}