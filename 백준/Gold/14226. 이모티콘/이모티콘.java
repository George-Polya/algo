import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Arrays;
import java.util.StringTokenizer; // StringTokenizer 임포트 추가

public class Main {

    // 상태를 나타내는 클래스 대신 int[] {화면 개수, 클립보드 개수} 사용
    static final int MAX_SIZE = 2001; // 충분히 큰 값 (S의 두 배 정도 + 여유)
    static int[][] time = new int[MAX_SIZE][MAX_SIZE];

    public static void main(String[] args) throws IOException { // IOException 처리 추가
        // BufferedReader 와 StringTokenizer 사용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 입력 라인을 읽고 StringTokenizer로 분리 (이 문제에서는 숫자 하나만 읽음)
        StringTokenizer st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken()); // 목표 이모티콘 개수

        // time 배열 초기화 (-1은 방문하지 않음을 의미)
        for (int i = 0; i < MAX_SIZE; i++) {
            Arrays.fill(time[i], -1);
        }

        // BFS를 위한 큐 생성
        Queue<int[]> queue = new LinkedList<>();

        // 초기 상태 설정: (화면 1개, 클립보드 0개), 시간 0초
        queue.offer(new int[]{1, 0});
        time[1][0] = 0;

        int result = -1; // 결과 저장 변수 (목표 도달 시 업데이트)

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int screen = current[0];
            int clipboard = current[1];
            int currentTime = time[screen][clipboard];

            // 목표 도달 시 종료
            if (screen == S) {
                result = currentTime; // 결과 저장
                break; // BFS 종료
            }

            // --- 3가지 연산 수행 ---

            // 1. 화면 -> 클립보드 복사 (s, s)
            // 클립보드에 저장하는 것이므로 screen 값이 클립보드 인덱스가 됨
            // 복사 연산은 screen 값이 0인 경우는 의미가 없지만 (초기 상태가 1이므로 발생 안 함),
            // 또한 screen 값이 MAX_SIZE 이상이면 배열 범위를 벗어나므로 체크
            if (screen > 0 && screen < MAX_SIZE) {
                 if (time[screen][screen] == -1) { // 방문하지 않았고, 배열 범위 내
                    time[screen][screen] = currentTime + 1;
                    queue.offer(new int[]{screen, screen});
                }
            }


            // 2. 클립보드 -> 화면 붙여넣기 (s + c, c)
            // 클립보드가 비어있지 않고(clipboard > 0), 붙여넣은 결과가 범위 내에 있어야 함
            if (clipboard > 0 && screen + clipboard < MAX_SIZE) {
                if (time[screen + clipboard][clipboard] == -1) { // 방문하지 않았으면
                    time[screen + clipboard][clipboard] = currentTime + 1;
                    queue.offer(new int[]{screen + clipboard, clipboard});
                }
            }

            // 3. 화면에서 1개 삭제 (s - 1, c)
            // 화면에 이모티콘이 있어야 함 (screen > 0 이므로 screen - 1 >= 0)
            if (screen > 0) {
                 if (time[screen - 1][clipboard] == -1) { // 방문하지 않았으면
                    time[screen - 1][clipboard] = currentTime + 1;
                    queue.offer(new int[]{screen - 1, clipboard});
                }
            }
        }

        System.out.println(result); // 최종 결과 출력
        br.close(); // BufferedReader 닫기
    }
}