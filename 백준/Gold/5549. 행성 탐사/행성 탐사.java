import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    // Python의 calc 함수와 동일한 기능을 하는 정적 메소드
    private static int calc(int key, int y1, int x1, int y2, int x2, int[][][] pSum) {
        return pSum[key][y2][x2] - pSum[key][y2][x1 - 1] - pSum[key][y1 - 1][x2] + pSum[key][y1 - 1][x1 - 1];
    }

    public static void main(String[] args) throws IOException {
        // 빠른 입력을 위해 BufferedReader 사용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int K = Integer.parseInt(br.readLine());

        // pSum[0] = 'J'(정글), pSum[1] = 'O'(바다), pSum[2] = 'I'(얼음)의 누적 합을 저장
        int[][][] pSum = new int[3][N + 1][M + 1];

        // 누적 합 배열(pSum) 생성
        for (int y = 1; y <= N; y++) {
            String line = br.readLine();
            for (int x = 1; x <= M; x++) {
                char ch = line.charAt(x - 1);
                
                int key;
                switch (ch) {
                    case 'J': key = 0; break;
                    case 'O': key = 1; break;
                    default:  key = 2; break; // 'I'
                }

                // 세 가지 타입('J', 'O', 'I')에 대해 모두 누적 합 계산
                for (int i = 0; i < 3; i++) {
                    int add = (i == key) ? 1 : 0; // 현재 좌표의 타입과 일치하면 1을 더함
                    pSum[i][y][x] = pSum[i][y][x - 1] + pSum[i][y - 1][x] - pSum[i][y - 1][x - 1] + add;
                }
            }
        }

        // 출력할 결과를 StringBuilder에 저장하여 한 번에 출력
        StringBuilder ansBuilder = new StringBuilder();

        // K개의 쿼리 처리
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int y1 = Integer.parseInt(st.nextToken());
            int x1 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());

            // 각 타입('J', 'O', 'I')의 개수 계산
            int jungleCount = calc(0, y1, x1, y2, x2, pSum);
            int oceanCount = calc(1, y1, x1, y2, x2, pSum);
            int iceCount = calc(2, y1, x1, y2, x2, pSum);
            
            // 결과를 StringBuilder에 추가
            ansBuilder.append(jungleCount).append(" ")
                      .append(oceanCount).append(" ")
                      .append(iceCount).append("\n");
        }

        // 최종 결과 출력
        System.out.print(ansBuilder.toString());
    }
}