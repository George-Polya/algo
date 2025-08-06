import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, k;
    static int[][] map; // 맵 정보 (0: 위험, 1: 안전)
    static boolean[][] visited; // 방문 여부 체크

    // 캐릭터의 위치 정보를 담을 내부 클래스
    static class Point {
        int line; // 0: 왼쪽, 1: 오른쪽
        int pos;  // 칸 번호

        Point(int line, int pos) {
            this.line = line;
            this.pos = pos;
        }
    }

    public static void main(String[] args) throws IOException {
        // 입력을 빠르게 받기 위한 설정
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        // 맵과 방문 배열 초기화 (인덱스를 1부터 사용하기 위해 N+1 크기로)
        map = new int[2][N + 1];
        visited = new boolean[2][N + 1];

        // 맵 정보 입력받기
        for (int i = 0; i < 2; i++) {
            String line = br.readLine();
            for (int j = 1; j <= N; j++) {
                map[i][j] = line.charAt(j - 1) - '0';
            }
        }

        // BFS 탐색 시작 후 결과 출력
        System.out.println(bfs());
    }

    public static int bfs() {
        // BFS를 위한 큐 생성
        Queue<Point> queue = new LinkedList<>();
        
        // 시작점: 왼쪽(0) 줄의 1번 칸
        queue.add(new Point(0, 1));
        visited[0][1] = true;

        int time = 0; // 시간(사라지는 칸의 기준)

        while (!queue.isEmpty()) {
            // 현재 시간에 탐색해야 할 위치의 개수만큼만 반복 (레벨 단위 탐색)
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Point current = queue.poll();

                // 현재 위치가 이미 사라진 칸이라면 더 이상 진행 불가
                if (current.pos <= time) {
                    continue;
                }

                // 1. 한 칸 앞으로 이동
                int nextPos1 = current.pos + 1;
                if (nextPos1 > N) return 1; // 탈출 성공!
                if (map[current.line][nextPos1] == 1 && !visited[current.line][nextPos1]) {
                    visited[current.line][nextPos1] = true;
                    queue.add(new Point(current.line, nextPos1));
                }

                // 2. 한 칸 뒤로 이동
                int nextPos2 = current.pos - 1;
                // 뒤로 갈 칸이 존재해야 하고(1 이상), 사라지지 않았으며, 안전하고, 방문한 적 없어야 함
                if (nextPos2 > time + 1 && map[current.line][nextPos2] == 1 && !visited[current.line][nextPos2]) {
                    visited[current.line][nextPos2] = true;
                    queue.add(new Point(current.line, nextPos2));
                }

                // 3. 반대편으로 점프
                int nextLine = 1 - current.line; // 0 -> 1, 1 -> 0
                int nextPos3 = current.pos + k;
                if (nextPos3 > N) return 1; // 탈출 성공!
                // 점프할 칸이 사라지지 않았으며, 안전하고, 방문한 적 없어야 함
                if (map[nextLine][nextPos3] == 1 && !visited[nextLine][nextPos3]) {
                    visited[nextLine][nextPos3] = true;
                    queue.add(new Point(nextLine, nextPos3));
                }
            }
            // 현재 레벨(시간)의 탐색이 모두 끝났으므로 시간 1 증가
            time++;
        }

        // 큐가 비워질 때까지 탈출하지 못했다면 실패
        return 0;
    }
}