import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class Node implements Comparable<Node> {
        int vertex;
        long cost; // 경로 길이는 long 타입 사용

        Node(int vertex, long cost) {
            this.vertex = vertex;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node other) {
            // 오름차순 정렬 (비용 기준)
            return Long.compare(this.cost, other.cost);
        }
    }

    static ArrayList<Node>[] graph;
    static final long INF = Long.MAX_VALUE; // 무한대 값 설정 (long 타입)
    static int V; // 정점 개수

    // 다익스트라 알고리즘 구현
    static long[] dijkstra(int start) {
        long[] dist = new long[V + 1]; // 1번부터 V번까지 사용
        Arrays.fill(dist, INF);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            // 이미 더 짧은 경로를 찾았다면 스킵
            if (current.cost > dist[current.vertex]) {
                continue;
            }

            // 현재 정점과 연결된 다른 정점들을 확인
            for (Node neighbor : graph[current.vertex]) {
                long newDist = dist[current.vertex] + neighbor.cost;
                // 새로운 경로가 기존 경로보다 짧다면 갱신하고 큐에 추가
                if (newDist < dist[neighbor.vertex]) {
                    dist[neighbor.vertex] = newDist;
                    pq.offer(new Node(neighbor.vertex, newDist));
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken()); // 정점 개수
        int E = Integer.parseInt(st.nextToken()); // 간선 개수
        int P = Integer.parseInt(st.nextToken()); // 건우 위치

        // 그래프 초기화 (인접 리스트)
        graph = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) {
            graph[i] = new ArrayList<>();
        }

        // 간선 정보 입력 및 그래프 구성 (양방향)
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u].add(new Node(v, w));
            graph[v].add(new Node(u, w)); // 양방향
        }

        // 1. 1번 정점에서 모든 정점까지의 최단 거리 계산
        long[] distFromStart = dijkstra(1);

        // 2. P번 정점에서 모든 정점까지의 최단 거리 계산
        long[] distFromP = dijkstra(P);

        // 필요한 최단 거리 값 추출
        long shortestPathTotal = distFromStart[V]; // 1 -> V 최단 거리
        long shortestPathViaP_part1 = distFromStart[P]; // 1 -> P 최단 거리
        long shortestPathViaP_part2 = distFromP[V]; // P -> V 최단 거리

        // 경로 존재 여부 확인 및 비교
        // 만약 1->V, 1->P, P->V 중 하나라도 경로가 없다면 (INF), 건우를 거쳐가는 최단 경로는 불가능
        if (shortestPathTotal == INF || shortestPathViaP_part1 == INF || shortestPathViaP_part2 == INF) {
             // 1->V 경로 자체가 없다면 당연히 P를 거쳐가는 경로도 최단경로가 될 수 없음
             // 1->P 또는 P->V 경로가 없다면 P를 거쳐갈 수 없음
             // 하지만 문제 조건상 연결 그래프가 주어질 가능성이 높음.
             // 만약 1->V 최단경로가 존재하는데, 1->P->V 경로가 존재하지 않는다면(INF), 아래 비교에서 자동으로 걸러짐.
             // 따라서 INF 체크는 사실상 아래 비교로 충분함.
        }


        // 3. 결과 비교 및 출력
        // 1->V 최단 거리 == (1->P 최단 거리 + P->V 최단 거리) 인지 확인
        if (shortestPathTotal == shortestPathViaP_part1 + shortestPathViaP_part2) {
            System.out.println("SAVE HIM");
        } else {
            System.out.println("GOOD BYE");
        }

        br.close();
    }
}