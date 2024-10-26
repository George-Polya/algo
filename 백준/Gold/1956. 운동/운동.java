import java.io.*;
import java.util.*;

public class Main {
    static int v, e;
    static int[][] matrix;
    static final int INF = 0x3f3f3f3f;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        
        matrix = new int[v + 1][v + 1];

        // 초기화
        for (int i = 1; i <= v; i++) {
            Arrays.fill(matrix[i], INF);
            matrix[i][i] = 0;
        }

        // 간선 정보 입력
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            matrix[a][b] = cost;
        }

        // Floyd-Warshall 알고리즘 적용
        for (int k = 1; k <= v; k++) {
            for (int i = 1; i <= v; i++) {
                for (int j = 1; j <= v; j++) {
                    if (matrix[i][k] < INF && matrix[k][j] < INF) {
                        matrix[i][j] = Math.min(matrix[i][j], matrix[i][k] + matrix[k][j]);
                    }
                }
            }
        }

        // 최소 사이클 찾기
        int ans = INF;
        for (int i = 1; i <= v; i++) {
            for (int j = 1; j <= v; j++) {
                if (i != j && matrix[i][j] < INF && matrix[j][i] < INF) {
                    ans = Math.min(ans, matrix[i][j] + matrix[j][i]);
                }
            }
        }

        // 결과 출력
        if (ans == INF) {
            System.out.println(-1);
        } else {
            System.out.println(ans);
        }
    }
}