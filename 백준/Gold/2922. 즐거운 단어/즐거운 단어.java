import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static char[] word;
    static int n;
    // DP 테이블: long 타입으로 큰 수를 저장할 수 있게 합니다.
    // dp[index][v_count][c_count][has_L_flag]
    static long[][][][] dp;

    // 모음인지 확인하는 헬퍼 함수
    public static boolean isVowel(char c) {
        return c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';
    }

    public static long solve(int index, int v_count, int c_count, int has_L_flag) {
        // 1. 종료 조건 (실패)
        // 모음 또는 자음이 3개 연속되면 유효하지 않은 경로입니다.
        if (v_count == 3 || c_count == 3) {
            return 0;
        }

        // 2. 종료 조건 (성공)
        // 단어 끝까지 도달했다면, L을 포함하는지에 따라 1 또는 0을 반환합니다.
        if (index == n) {
            return (has_L_flag == 1) ? 1 : 0;
        }

        // 3. 메모이제이션 확인
        // 이미 계산된 상태라면 저장된 값을 반환합니다.
        if (dp[index][v_count][c_count][has_L_flag] != -1) {
            return dp[index][v_count][c_count][has_L_flag];
        }

        // 4. 재귀 호출
        long count = 0;
        char ch = word[index];

        if (ch == '_') {
            // 밑줄('_')인 경우: 3가지 선택지를 모두 탐색합니다.
            // a) 모음을 넣는 경우 (5가지)
            count += 5 * solve(index + 1, v_count + 1, 0, has_L_flag);
            
            // b) L을 넣는 경우 (1가지)
            // L을 사용했으므로 has_L_flag를 1로 설정합니다.
            count += 1 * solve(index + 1, 0, c_count + 1, 1);
            
            // c) L이 아닌 자음을 넣는 경우 (20가지)
            count += 20 * solve(index + 1, 0, c_count + 1, has_L_flag);
        } else {
            // 문자가 정해진 경우
            int new_has_L = (ch == 'L') ? 1 : has_L_flag;
            if (isVowel(ch)) {
                // 모음일 경우
                count += solve(index + 1, v_count + 1, 0, new_has_L);
            } else {
                // 자음일 경우
                count += solve(index + 1, 0, c_count + 1, new_has_L);
            }
        }
        
        // 5. 결과 저장 및 반환
        return dp[index][v_count][c_count][has_L_flag] = count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        word = br.readLine().toCharArray();
        n = word.length;

        // DP 테이블 초기화: -1은 아직 계산되지 않았음을 의미합니다.
        dp = new long[n + 1][3][3][2];
        for (long[][][] d1 : dp) {
            for (long[][] d2 : d1) {
                for (long[] d3 : d2) {
                    Arrays.fill(d3, -1);
                }
            }
        }
        
        // 초기 호출: 0번 인덱스, 연속 모음/자음 0개, L 없음(0) 상태로 시작
        long result = solve(0, 0, 0, 0);
        System.out.println(result);
    }
}