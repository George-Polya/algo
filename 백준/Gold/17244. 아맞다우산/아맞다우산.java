import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Point {
    int r, c, dist; // 행, 열, 거리

    Point(int r, int c) {
        this.r = r;
        this.c = c;
    }

    Point(int r, int c, int dist) {
        this.r = r;
        this.c = c;
        this.dist = dist;
    }
}

public class Main {
    static int N, M; // 미로의 세로, 가로 크기
    static char[][] map;
    static Point startPoint;
    static Point endPoint;
    static List<Point> items;
    static int numItems;

    // BFS를 위한 방향 벡터 (상, 하, 좌, 우)
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static final int INF = Integer.MAX_VALUE / 2; // 경로가 없을 경우를 위한 무한대 값

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken()); // 가로 크기 (열)
        N = Integer.parseInt(st.nextToken()); // 세로 크기 (행)

        map = new char[N][M];
        items = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'S') {
                    startPoint = new Point(i, j);
                } else if (map[i][j] == 'X') {
                    items.add(new Point(i, j));
                } else if (map[i][j] == 'E') {
                    endPoint = new Point(i, j);
                }
            }
        }
        numItems = items.size();

        // 만약 챙길 물건이 없다면 시작점에서 도착점까지의 거리만 계산
        if (numItems == 0) {
            int result = bfs(startPoint, endPoint);
            System.out.println(result == INF ? -1 : result); // 도달 불가능하면 -1 출력 (문제 조건에 따라 다를 수 있음)
            return;
        }

        // 2. 지점 간 최단 거리 계산
        // distS_X[i]: 시작점 -> i번째 물건
        int[] distS_X = new int[numItems];
        for (int i = 0; i < numItems; i++) {
            distS_X[i] = bfs(startPoint, items.get(i));
        }

        // distX_X[i][j]: i번째 물건 -> j번째 물건
        int[][] distX_X = new int[numItems][numItems];
        for (int i = 0; i < numItems; i++) {
            for (int j = 0; j < numItems; j++) {
                if (i == j) continue;
                distX_X[i][j] = bfs(items.get(i), items.get(j));
            }
        }

        // distX_E[i]: i번째 물건 -> 도착점
        int[] distX_E = new int[numItems];
        for (int i = 0; i < numItems; i++) {
            distX_E[i] = bfs(items.get(i), endPoint);
        }

        // 3. 비트마스크 DP 실행
        // dp[mask][lastItemIdx]: mask 상태의 물건들을 줍고, 마지막으로 lastItemIdx 물건을 주웠을 때의 최소 시간
        int[][] dp = new int[1 << numItems][numItems];
        for (int i = 0; i < (1 << numItems); i++) {
            Arrays.fill(dp[i], INF);
        }

        // 초기 상태 설정: 시작점에서 각 물건으로 바로 가는 경우
        for (int i = 0; i < numItems; i++) {
            if (distS_X[i] != INF) {
                dp[1 << i][i] = distS_X[i];
            }
        }

        // DP 점화식 적용
        for (int mask = 1; mask < (1 << numItems); mask++) {
            for (int currentItemIdx = 0; currentItemIdx < numItems; currentItemIdx++) {
                // 현재 아이템이 mask에 포함되어 있고, dp값이 유효한 경우
                if ((mask & (1 << currentItemIdx)) != 0 && dp[mask][currentItemIdx] != INF) {
                    for (int nextItemIdx = 0; nextItemIdx < numItems; nextItemIdx++) {
                        // 다음 아이템이 mask에 포함되어 있지 않은 경우 (아직 줍지 않은 물건)
                        if ((mask & (1 << nextItemIdx)) == 0) {
                            if (distX_X[currentItemIdx][nextItemIdx] != INF) {
                                int nextMask = mask | (1 << nextItemIdx);
                                dp[nextMask][nextItemIdx] = Math.min(dp[nextMask][nextItemIdx],
                                        dp[mask][currentItemIdx] + distX_X[currentItemIdx][nextItemIdx]);
                            }
                        }
                    }
                }
            }
        }

        // 최종 답 계산
        int minTotalTime = INF;
        int finalMask = (1 << numItems) - 1; // 모든 물건을 다 주운 상태

        for (int lastItemIdx = 0; lastItemIdx < numItems; lastItemIdx++) {
            if (dp[finalMask][lastItemIdx] != INF && distX_E[lastItemIdx] != INF) {
                minTotalTime = Math.min(minTotalTime, dp[finalMask][lastItemIdx] + distX_E[lastItemIdx]);
            }
        }

        System.out.println(minTotalTime == INF ? -1 : minTotalTime); // 문제에서 도달 불가능 시 -1을 요구하는지 확인 필요
    }

    // BFS 함수: from 지점에서 to 지점까지의 최단 거리를 반환
    static int bfs(Point from, Point to) {
        Queue<Point> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];

        q.offer(new Point(from.r, from.c, 0));
        visited[from.r][from.c] = true;

        while (!q.isEmpty()) {
            Point current = q.poll();

            if (current.r == to.r && current.c == to.c) {
                return current.dist;
            }

            for (int i = 0; i < 4; i++) {
                int nr = current.r + dr[i];
                int nc = current.c + dc[i];

                if (nr >= 0 && nr < N && nc >= 0 && nc < M && !visited[nr][nc] && map[nr][nc] != '#') {
                    visited[nr][nc] = true;
                    q.offer(new Point(nr, nc, current.dist + 1));
                }
            }
        }
        return INF; // 도달 불가능
    }
}