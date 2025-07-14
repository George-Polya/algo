import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {

    // 그래프의 각 노드(분기점)와 그곳까지의 비용(시간)을 저장할 클래스
    // Comparable 인터페이스를 구현하여 우선순위 큐에서 비용을 기준으로 자동 정렬되도록 함
    static class Node implements Comparable<Node> {
        int index; // 분기점 번호
        long cost; // 0번 분기점에서부터의 총 이동 시간

        public Node(int index, long cost) {
            this.index = index;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node other) {
            // 비용(cost)이 낮은 노드가 우선순위가 높도록 설정 (최소 힙)
            return Long.compare(this.cost, other.cost);
        }
    }

    static int N, M;
    static ArrayList<Node>[] adj; // 인접 리스트로 그래프 표현
    static int[] sight;           // 각 분기점의 시야 정보
    static long[] dist;           // 시작점에서 각 분기점까지의 최단 시간을 저장할 배열
    static final long INF = Long.MAX_VALUE; // 도달 불가능을 나타내는 무한대 값

    public static void main(String[] args) throws IOException {
        // --- 1. 입력 및 초기화 ---
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        sight = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            sight[i] = Integer.parseInt(st.nextToken());
        }

        // [핵심 조건 처리]
        // 도착점(N-1)은 시야에 보여도 도착할 수 있음.
        // 탐색 로직을 단순화하기 위해, 도착점은 시야에 보이지 않는다고 가정한다.
        sight[N - 1] = 0;

        // 인접 리스트 및 거리 배열 초기화
        adj = new ArrayList[N];
        dist = new long[N];
        for (int i = 0; i < N; i++) {
            adj[i] = new ArrayList<>();
        }
        Arrays.fill(dist, INF);

        // M개의 길 정보 입력받아 그래프 구성 (양방향)
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            adj[u].add(new Node(v, t));
            adj[v].add(new Node(u, t));
        }

        // --- 2. 다익스트라 알고리즘 실행 ---
        dijkstra(0);

        // --- 3. 결과 출력 ---
        if (dist[N - 1] == INF) {
            System.out.println(-1); // 도착점에 도달하지 못한 경우
        } else {
            System.out.println(dist[N - 1]); // 계산된 최단 시간 출력
        }
    }

    static void dijkstra(int start) {
        // 우선순위 큐 생성 및 시작점 설정
        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[start] = 0;
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            // 현재 가장 가까운 노드를 꺼냄
            Node current = pq.poll();

            // 만약 이미 더 짧은 경로가 발견되었다면, 이 경로는 탐색할 필요 없음
            if (current.cost > dist[current.index]) {
                continue;
            }

            // [핵심 조건 처리]
            // 현재 노드와 연결된 다른 노드들을 탐색
            for (Node next : adj[current.index]) {
                // 다음 노드가 시야에 보이는 곳이면 건너뜀 (단, 도착점은 위에서 sight[N-1]=0 처리)
                if (sight[next.index] == 1) {
                    continue;
                }

                // 현재 노드를 거쳐 다음 노드로 가는 것이 더 빠르다면
                if (dist[next.index] > current.cost + next.cost) {
                    // 최단 거리를 갱신하고 우선순위 큐에 추가
                    dist[next.index] = current.cost + next.cost;
                    pq.offer(new Node(next.index, dist[next.index]));
                }
            }
        }
    }
}