import java.io.*;
import java.util.*;

public class Main {

    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    static boolean OOB(int y, int x) {
        return y < 0 || y >= 3 || x < 0 || x >= 3;
    }

    static String swap(int curY, int curX, int ny, int nx, String board) {
        char[] boardChars = board.toCharArray();
        int curIdx = curY * 3 + curX;
        int nxtIdx = ny * 3 + nx;
        char temp = boardChars[curIdx];
        boardChars[curIdx] = boardChars[nxtIdx];
        boardChars[nxtIdx] = temp;
        return new String(boardChars);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int sy = -1, sx = -1;

        for (int y = 0; y < 3; y++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int x = 0; x < 3; x++) {
                String s = st.nextToken();
                sb.append(s);
                if (s.equals("0")) {
                    sy = y;
                    sx = x;
                }
            }
        }

        String start = sb.toString();
        Queue<State> q = new ArrayDeque<>();
        Set<String> visited = new HashSet<>(); // String을 저장하는 Set
        Map<String, Integer> dist = new HashMap<>(); // 거리를 저장하는 Map

        q.add(new State(sy, sx, start));
        visited.add(start);
        dist.put(start, 0);

        while (!q.isEmpty()) {
            State cur = q.poll();

            if (cur.board.equals("123456780")) {
                System.out.println(dist.get(cur.board));
                return;
            }

            for (int dir = 0; dir < 4; dir++) {
                int ny = cur.y + dy[dir];
                int nx = cur.x + dx[dir];

                if (OOB(ny, nx)) continue;

                String nxtBoard = swap(cur.y, cur.x, ny, nx, cur.board);
                if (visited.contains(nxtBoard)) continue;

                visited.add(nxtBoard);
                dist.put(nxtBoard, dist.get(cur.board) + 1);
                q.add(new State(ny, nx, nxtBoard));
            }
        }

        System.out.println(-1);
    }
     static class State {
        int y, x;
        String board; // 문자열로 변경

        public State(int y, int x, String board) {
            this.y = y;
            this.x = x;
            this.board = board;
        }
    }
}