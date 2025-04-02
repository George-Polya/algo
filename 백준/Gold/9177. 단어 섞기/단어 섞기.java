import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 제출 시 클래스 이름은 Main으로 변경해야 합니다.
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 테스트 케이스의 수

        StringBuilder sb = new StringBuilder(); // 출력을 모아서 한 번에 처리하기 위함

        for (int caseNum = 1; caseNum <= n; caseNum++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String word1 = st.nextToken();
            String word2 = st.nextToken();
            String word3 = st.nextToken();

            int len1 = word1.length();
            int len2 = word2.length();
            int len3 = word3.length();

            // 1. 기본 길이 검사
            if (len1 + len2 != len3) {
                sb.append("Data set ").append(caseNum).append(": no\n");
                continue; // 다음 테스트 케이스로 넘어감
            }

            // 2. DP 테이블 생성 및 초기화
            // dp[i][j] := word1의 첫 i개 문자와 word2의 첫 j개 문자를 사용하여
            //             word3의 첫 i+j개 문자를 만들 수 있는가?
            boolean[][] dp = new boolean[len1 + 1][len2 + 1];

            // 초기값: 빈 문자열 두 개로 빈 문자열을 만드는 것은 항상 가능
            dp[0][0] = true;

            // 3. DP 테이블 채우기
            // i: word1에서 사용한 문자의 수 (0 ~ len1)
            // j: word2에서 사용한 문자의 수 (0 ~ len2)
            for (int i = 0; i <= len1; i++) {
                for (int j = 0; j <= len2; j++) {
                    // dp[i][j]를 계산하기 위해 이전 상태들을 확인
                    int k = i + j; // 현재까지 만들어진 word3의 길이

                    // 이전 상태에서 올 수 있는지 확인

                    // Case 1: word1의 i번째 문자를 사용하여 word3의 k번째 문자를 만드는 경우
                    // 조건: i > 0 (word1에서 문자를 가져올 수 있어야 함)
                    //       dp[i-1][j]가 true (이전 상태가 유효해야 함)
                    //       word1.charAt(i-1) == word3.charAt(k-1) (문자가 일치해야 함)
                    if (i > 0 && dp[i - 1][j] && word1.charAt(i - 1) == word3.charAt(k - 1)) {
                        dp[i][j] = true;
                    }

                    // Case 2: word2의 j번째 문자를 사용하여 word3의 k번째 문자를 만드는 경우
                    // 조건: j > 0 (word2에서 문자를 가져올 수 있어야 함)
                    //       dp[i][j-1]가 true (이전 상태가 유효해야 함)
                    //       word2.charAt(j-1) == word3.charAt(k-1) (문자가 일치해야 함)
                    // Case 1에서 이미 true가 되었을 수 있으므로, || 연산자나 if문으로 처리
                    if (!dp[i][j]) { // 아직 false인 경우에만 Case 2를 확인 (최적화는 아님)
                       if (j > 0 && dp[i][j - 1] && word2.charAt(j - 1) == word3.charAt(k - 1)) {
                            dp[i][j] = true;
                        }
                    }
                    // 또는 더 간단하게:
                    // if (j > 0 && dp[i][j - 1] && word2.charAt(j - 1) == word3.charAt(k - 1)) {
                    //     dp[i][j] = dp[i][j] || true; // 이미 true면 true 유지, false면 true로 변경
                    // }

                }
            }

            // 4. 최종 결과 확인 및 출력 문자열 만들기
            boolean result = dp[len1][len2];
            sb.append("Data set ").append(caseNum).append(": ").append(result ? "yes" : "no").append("\n");
        }

        // 모아둔 출력 한 번에 출력
        System.out.print(sb.toString());

        br.close();
    }
}