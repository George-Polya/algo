import java.util.*;
import java.io.*;

public class Main {
    static int N = 5;
    static int[][][] original = new int[N][N][N];
    static int[][][] cube = new int[N][N][N];
    static boolean[] used = new boolean[5];
    static int[][][] dist;
    static int INF = Integer.MAX_VALUE / 2;
    static int ans = INF;
    
    // 방향 벡터 (z, y, x 순서)
    static int[] dz = {-1, 1, 0, 0, 0, 0};
    static int[] dy = {0, 0, -1, 1, 0, 0};
    static int[] dx = {0, 0, 0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        for(int z = 0; z < N; z++) {
            for(int y = 0; y < N; y++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int x = 0; x < N; x++) {
                    original[z][y][x] = Integer.parseInt(st.nextToken());
                }
            }
        }
        
        // 순열 생성 (층 쌓는 순서 정하기)
        factorial(0, new ArrayList<Integer>());
        
        System.out.println(ans == INF ? -1 : ans);
    }
    
    // 층의 순서를 정하는 함수
    static void factorial(int idx, List<Integer> arr) {
        if(idx == 5) {
            makeCube(arr); // cube 배열 구성
            solve(0);      // 각 층 회전하며 최단거리 찾기
            return;
        }
        
        for(int i = 0; i < 5; i++) {
            if(used[i]) continue;
            
            arr.add(i);
            used[i] = true;
            factorial(idx + 1, arr);
            used[i] = false;
            arr.remove(arr.size() - 1);
        }
    }
    
    // 각 층을 회전시키는 DFS
    static void solve(int cur) {
        if(cur == 5) {
            // 입구/출구 후보 4가지에 대해 BFS 수행
            for(int[][] coords : buildMaze()) {
                int[] start = coords[0];
                int[] end = coords[1];
                
                int cnt = bfs(start, end);
                ans = Math.min(ans, cnt);
            }
            return;
        }
        
        // 0, 90, 180, 270도 회전 (총 4번)
        for(int i = 0; i < 4; i++) {
            cube[cur] = rotate(cube[cur]);
            solve(cur + 1);
        }
    }
    
    // BFS: Coord 대신 int[] 사용
    static int bfs(int[] start, int[] end) {
        // 시작점이나 끝점이 막혀있으면 불가능
        if(isBlock(start) || isBlock(end)) return INF;
        
        dist = new int[N][N][N];
        for(int z = 0; z < N; z++) {
            for(int y = 0; y < N; y++) {
                Arrays.fill(dist[z][y], INF);
            }
        }
        
        // int[] 배열을 담는 큐
        Queue<int[]> q = new ArrayDeque<>();
        q.add(start);
        dist[start[0]][start[1]][start[2]] = 0; // z, y, x 순서
        
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int cz = cur[0];
            int cy = cur[1];
            int cx = cur[2];
            
            // 도착지점 도달 확인
            if(cz == end[0] && cy == end[1] && cx == end[2]) {
                return dist[cz][cy][cx];
            }
            
            for(int dir = 0; dir < 6; dir++) {
                int nz = cz + dz[dir];
                int ny = cy + dy[dir];
                int nx = cx + dx[dir];
                
                // 범위 벗어남, 이미 방문함, 막힌 길 체크
                if(OOB(nz, ny, nx) || dist[nz][ny][nx] != INF || cube[nz][ny][nx] == 0)
                    continue;
                
                dist[nz][ny][nx] = dist[cz][cy][cx] + 1;
                q.add(new int[]{nz, ny, nx});
            }
        }
        
        return INF;
    }
    
    static boolean OOB(int z, int y, int x) {
        return z < 0 || z >= N || y < 0 || y >= N || x < 0 || x >= N;
    }
    
    static boolean isBlock(int[] p) {
        return cube[p[0]][p[1]][p[2]] == 0;
    }
    
    // 2차원 배열 회전
    static int[][] rotate(int[][] board) {
        int[][] temp = new int[N][N];
        
        for(int y = 0; y < N; y++) {
            for(int x = 0; x < N; x++) {
                // 시계 방향 90도 회전 공식
                temp[x][N - 1 - y] = board[y][x];
            }
        }
        return temp;
    }
    
    // 순열에 따라 원본 층을 cube로 복사
    static void makeCube(List<Integer> arr) {
        for(int i = 0; i < 5; i++) {
            // 주의: 여기서 original의 참조를 가져오지만, 
            // solve() 내부의 rotate()가 새로운 배열을 반환하여 덮어쓰므로 원본 오염 없음
            cube[i] = original[arr.get(i)];
        }
    }
    
    // 입구와 출구 좌표 목록 반환 (int[][][])
    static int[][][] buildMaze() {
        return new int[][][] {
            { {0,0,0}, {4,4,4} },
            { {0,0,4}, {4,4,0} },
            { {0,4,0}, {4,0,4} },
            { {4,0,0}, {0,4,4} }
        };
    }
}