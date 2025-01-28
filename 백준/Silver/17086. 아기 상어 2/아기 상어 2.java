import java.io.*;
import java.util.*;

public class Main {
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[][] board = new int[N + 1][M + 1];
        int[][] dist = new int[N + 1][M + 1];
        for (int[] row : dist) Arrays.fill(row, -1);

        Queue<Pair> q = new LinkedList<>();

        for (int y = 1; y <= N; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 1; x <= M; x++) {
                board[y][x] = Integer.parseInt(st.nextToken());
                if (board[y][x] == 1) {
                    q.add(new Pair(y, x));
                    dist[y][x] = 0;
                }
            }
        }
        int ans = -1;
        while (!q.isEmpty()) {
            Pair cur = q.poll();
            ans = Math.max(ans, dist[cur.y][cur.x]);
            for (int dir = 0; dir < 8; dir++) {
                int ny = cur.y + dy[dir];
                int nx = cur.x + dx[dir];

                if (OOB(ny, nx) || dist[ny][nx] != -1) continue;

                dist[ny][nx] = dist[cur.y][cur.x] + 1;
                q.add(new Pair(ny, nx));
            }
        }


        System.out.println(ans);
    }

    static boolean OOB(int y, int x) {
        return y <= 0 || y > N || x <= 0 || x > M;
    }

    static class Pair {
        int y, x;

        Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}