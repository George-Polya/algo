import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        while (true) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()); // 사탕 종류의 수
            double mDouble = Double.parseDouble(st.nextToken()); // 예산 (달러)

            // 종료 조건 확인 (n=0, m=0.00)
            // double 비교는 오차가 있을 수 있으므로 n=0으로 주로 확인하고,
            // mDouble이 0에 매우 가까운지 확인하는 보조적인 방법을 사용할 수 있습니다.
            // 여기서는 n=0이면 m도 0.00이라고 문제에서 명시했으므로 n만 확인해도 충분합니다.
            if (n == 0) {
                // mDouble이 0.00인지 확실히 하기 위한 추가 검사 (선택적)
                // double 비교는 epsilon을 사용하는 것이 안전합니다.
                // double epsilon = 1e-9;
                // if (Math.abs(mDouble - 0.00) < epsilon) {
                //     break;
                // }
                // 문제 조건상 n=0이면 m=0.00이므로 아래 break만으로 충분
                 break;
            }

            // 1. 예산을 센트 단위의 정수로 변환
            int mCents = (int) (mDouble * 100 + 0.5); // 부동소수점 오차 보정 및 반올림

            // 2. DP 배열 선언 및 초기화
            // dp[j] = 예산 j 센트로 얻을 수 있는 최대 칼로리
            int[] dp = new int[mCents + 1]; // 크기는 mCents + 1 (0부터 mCents까지)
            // Java에서 int 배열은 자동으로 0으로 초기화됩니다.

            // 3 & 4. 각 사탕 정보 입력 및 DP 테이블 갱신
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int calories = Integer.parseInt(st.nextToken()); // 칼로리
                double priceDouble = Double.parseDouble(st.nextToken()); // 가격 (달러)

                // 가격을 센트 단위의 정수로 변환
                int priceCents = (int) (priceDouble * 100 + 0.5);

                // 4. DP 점화식 적용 (무한 배낭 문제)
                // j는 현재 사탕의 가격(센트)부터 시작해야 함
                for (int j = priceCents; j <= mCents; j++) {
                    // 현재 예산 j에서
                    // 1. 이 사탕을 사지 않는 경우: dp[j] (이전 값 유지)
                    // 2. 이 사탕을 사는 경우: dp[j - priceCents] + calories
                    // 둘 중 더 큰 값으로 갱신
                    dp[j] = Math.max(dp[j], dp[j - priceCents] + calories);
                }
            }

            // 5. 최종 결과 저장
            sb.append(dp[mCents]).append("\n");
        }

        // 결과 출력
        System.out.print(sb.toString());
        br.close();
    }
}