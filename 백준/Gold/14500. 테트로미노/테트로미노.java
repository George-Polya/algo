import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
https://www.acmicpc.net/problem/14500
 */
public class Main {
    static int board[][];
    static int n, m;
    static boolean visited[][];

    static int dx[] = {-1, 0, 1, 0};
    static int dy[] = {0, -1, 0, 1};
    static int max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                visited[i][j] = true;
                DFS(i, j, 1, board[i][j], visited);
                visited[i][j] = false;
                Exception(i, j);
            }
        }

        System.out.println(max);

    }

    static void DFS(int y, int x, int cnt, int sum, boolean[][] visited) {
        if (cnt >= 4) {
            max = Math.max(max, sum);
            return;
        }

        for (int k = 0; k < 4; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];

            if(nx<0 || ny<0|| nx>=m|| ny>=n)
                continue;
            if (!visited[ny][nx]) {
                visited[ny][nx] = true;
                DFS(ny, nx, cnt + 1, sum + board[ny][nx], visited);
                visited[ny][nx] = false;
            }
        }
    }

    static void Exception(int y, int x) {

        if(y<n-2 && x<m-1)
            max = Math.max(max, board[y][x] + board[y + 1][x] + board[y + 2][x] + board[y + 1][x + 1]);

        if(y<n-2 && x>0)
            max = Math.max(max, board[y][x] + board[y + 1][x] + board[y + 2][x] + board[y + 1][x - 1]);

        if(y < n-1 && x<m-2)
            max = Math.max(max, board[y][x] + board[y][x+1] + board[y][x+2] + board[y + 1][x + 1]);
        if(y >0  && x<m-2)
            max = Math.max(max, board[y][x] + board[y][x+1] + board[y][x+2] + board[y - 1][x + 1]);

    }
}
