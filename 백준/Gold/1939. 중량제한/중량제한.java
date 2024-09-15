import java.util.*;

class Main {
    static class Node {
        int to;
        int weight;

        Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static int N, M;
    static ArrayList<Node>[] adj;
    static boolean[] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();  // 섬의 개수
        M = sc.nextInt();  // 다리의 개수

        adj = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        int start = 0, end = 0;

        // 간선 정보 입력
        for (int i = 0; i < M; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            adj[u].add(new Node(v, w));
            adj[v].add(new Node(u, w));
            end = Math.max(end, w);  // 최대 중량을 end로 설정
        }

        int from = sc.nextInt();  // 출발 섬
        int to = sc.nextInt();    // 도착 섬

        // 이분 탐색
        while (start <= end) {
            int mid = (start + end) / 2;
            if (canCross(from, to, mid)) {
                start = mid + 1;  // 가능한 경우 더 큰 중량도 가능한지 탐색
            } else {
                end = mid - 1;    // 불가능한 경우 더 작은 중량으로 탐색
            }
        }

        System.out.println(end);  // 가능한 최대 중량 출력
    }

    // BFS로 특정 중량을 기준으로 다리를 건널 수 있는지 확인
    static boolean canCross(int from, int to, int limit) {
        Queue<Integer> queue = new LinkedList<>();
        visited = new boolean[N + 1];
        queue.add(from);
        visited[from] = true;

        while (!queue.isEmpty()) {
            int curr = queue.poll();

            if (curr == to) return true;  // 도착 섬에 도달한 경우

            for (Node next : adj[curr]) {
                if (!visited[next.to] && next.weight >= limit) {
                    visited[next.to] = true;
                    queue.add(next.to);
                }
            }
        }

        return false;
    }
}