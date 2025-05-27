import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    static int N;
    static boolean solutionFound = false; // 가장 작은 좋은 수열을 찾았는지 여부를 나타내는 플래그

    public static void main(String[] args) throws IOException {
        // 입력을 받기 위한 BufferedReader 설정
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 수열의 길이 N 입력

        findSequence(new StringBuilder()); // 빈 StringBuilder로 탐색 시작
    }

    /**
     * 좋은 수열을 찾는 재귀 함수 (백트래킹)
     * @param currentSequence 현재까지 만들어진 수열
     */
    static void findSequence(StringBuilder currentSequence) {
        // 이미 답을 찾았다면 더 이상 탐색하지 않음
        if (solutionFound) {
            return;
        }

        // 현재 수열의 길이가 N과 같아지면, 좋은 수열을 찾은 것
        if (currentSequence.length() == N) {
            System.out.println(currentSequence.toString()); // 결과 출력
            solutionFound = true; // 찾았음을 표시
            return;
        }

        // 다음에 추가할 숫자로 '1', '2', '3'을 순서대로 시도 (가장 작은 수를 만들기 위함)
        for (char digit = '1'; digit <= '3'; digit++) {
            currentSequence.append(digit); // 현재 수열에 숫자 추가

            // 추가된 수열이 "좋은 수열"인지 확인
            if (isGood(currentSequence)) {
                findSequence(currentSequence); // 다음 자리 숫자를 찾기 위해 재귀 호출
                
                // 만약 재귀 호출 과정에서 답을 찾았다면, 현재 함수도 종료
                if (solutionFound) {
                    return;
                }
            }
            // 백트래킹: 방금 추가했던 숫자를 제거하고 다음 숫자를 시도하기 위함
            currentSequence.deleteCharAt(currentSequence.length() - 1); 
        }
    }

    /**
     * 주어진 수열이 "좋은 수열"인지 판별하는 함수
     * @param sequence 판별할 수열
     * @return 좋은 수열이면 true, 아니면 false
     */
    static boolean isGood(StringBuilder sequence) {
        int len = sequence.length();

        // 확인할 부분 수열의 길이 (1부터 전체 길이의 절반까지)
        // 예: sequence "1213121", len = 7
        // subLen = 1: 마지막 "1"과 그 앞 "2" 비교
        // subLen = 2: 마지막 "21"과 그 앞 "31" 비교
        // subLen = 3: 마지막 "121"과 그 앞 "121" 비교 -> 나쁜 수열!
        for (int subLen = 1; subLen <= len / 2; subLen++) {
            // 비교할 두 부분 문자열
            // 첫 번째 부분 문자열: sequence.substring(len - 2 * subLen, len - subLen)
            // 두 번째 부분 문자열: sequence.substring(len - subLen, len)
            
            boolean identical = true; // 두 부분 수열이 같은지 여부
            for (int i = 0; i < subLen; i++) {
                // 첫 번째 부분 수열의 i번째 문자: sequence.charAt(len - 2 * subLen + i)
                // 두 번째 부분 수열의 i번째 문자: sequence.charAt(len - subLen + i)
                if (sequence.charAt(len - 2 * subLen + i) != sequence.charAt(len - subLen + i)) {
                    identical = false; // 하나라도 다르면 같지 않음
                    break;
                }
            }

            if (identical) {
                return false; // 인접한 동일한 부분 수열이 존재하므로 "나쁜 수열"
            }
        }
        return true; // 모든 검사를 통과하면 "좋은 수열"
    }
}