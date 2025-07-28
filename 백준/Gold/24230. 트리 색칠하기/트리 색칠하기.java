import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    // DFS 함수에서도 접근할 수 있도록 클래스 멤버(static) 변수로 선언합니다.
    static int N;
    static int[] targetColors;
    static ArrayList<Integer>[] adj;
    static int totalOperations = 0;

    public static void main(String[] args) throws IOException {
        // 빠른 입력을 위한 BufferedReader 와 StringTokenizer
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // --- 1. 입력 처리 및 자료구조 초기화 ---
        N = Integer.parseInt(br.readLine());

        // 자료구조 초기화 (노드 번호를 1부터 사용하기 위해 N+1 크기로)
        targetColors = new int[N + 1];
        adj = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        // 목표 색상 정보 입력
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            targetColors[i] = Integer.parseInt(st.nextToken());
        }

        // N-1개의 간선 정보 입력받아 인접 리스트 구성
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj[u].add(v);
            adj[v].add(u);
        }

        // --- 3. 탐색 시작 및 결과 출력 ---
        // 루트 노드(1)에서 탐색 시작
        // 루트의 부모는 없으므로 0, 초기 색상도 칠해지지 않았으므로 0으로 설정
        dfs(1, 0, 0);

        System.out.println(totalOperations);
    }

    /**
     * 깊이 우선 탐색을 통해 최소 색칠 횟수를 계산합니다.
     *
     * @param currentNode       현재 방문 중인 노드
     * @param parentNode        현재 노드의 부모 노드 (역방향 탐색 방지용)
     * @param colorFromParent   부모로부터 물려받은 색상
     */
    public static void dfs(int currentNode, int parentNode, int colorFromParent) {
        // 1. 색상 비교 및 작업 횟수 증가
        // 부모로부터 물려받은 색이 나의 목표 색과 다르다면,
        // '나'를 루트로 하는 새로운 색칠 작업이 반드시 필요합니다.
        if (targetColors[currentNode] != colorFromParent) {
            totalOperations++;
        }

        // 2. 자식에게 색상 물려주기
        // 이제 나의 자식들은 나의 '목표 색상'을 물려받게 됩니다.
        int colorForChildren = targetColors[currentNode];

        // 3. 자식 노드로 재귀 호출
        for (int neighborNode : adj[currentNode]) {
            // 부모 노드로 다시 돌아가는 것을 방지
            if (neighborNode != parentNode) {
                dfs(neighborNode, currentNode, colorForChildren);
            }
        }
    }
}