import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 각 노드(집)의 정보를 담을 클래스 (거리, 집 번호)
// 우선순위 큐에서 거리를 기준으로 정렬하기 위해 Comparable 인터페이스 구현
class Node implements Comparable<Node> {
    int index;
    long distance;

    public Node(int index, long distance) {
        this.index = index;
        this.distance = distance;
    }

    @Override
    public int compareTo(Node other) {
        // 거리가 짧은 것이 우선순위가 높도록 설정
        return Long.compare(this.distance, other.distance);
    }
}

public class Main {
    static final long INF = Long.MAX_VALUE;
    static int N, M, X, Y;
    static List<List<Node>> graph;
    static long[] dist;

    public static void main(String[] args) throws IOException {
        // 1. 입력 처리 및 그래프 구성
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            // 양방향 그래프
            graph.get(a).add(new Node(b, c));
            graph.get(b).add(new Node(a, c));
        }

        // 2. 다익스트라 실행
        dijkstra(Y);

        // 3. 결과 분석 및 날짜 계산
        
        // 왕복 거리를 담을 리스트
        List<Long> roundTripDistances = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            if (i == Y) continue; // 자기 자신은 제외

            // 도달할 수 없는 집이 있다면 -1 출력 후 종료
            if (dist[i] == INF) {
                System.out.println(-1);
                return;
            }
            
            long roundTrip = dist[i] * 2;
            
            // 왕복 거리 자체가 하루 제한을 넘으면 -1 출력 후 종료
            if (roundTrip > X) {
                System.out.println(-1);
                return;
            }
            roundTripDistances.add(roundTrip);
        }

        // 가까운 순서대로 방문하기 위해 오름차순 정렬
        Collections.sort(roundTripDistances);

        int days = 1;
        long dailyWalk = 0;

        for (long trip : roundTripDistances) {
            if (dailyWalk + trip <= X) {
                dailyWalk += trip;
            } else {
                days++;
                dailyWalk = trip;
            }
        }
        
        // 이웃집이 하나도 없는 경우를 처리 (roundTripDistances가 비어있음)
        if (roundTripDistances.isEmpty()) {
            System.out.println(0);
        } else {
            System.out.println(days);
        }
    }

    private static void dijkstra(int start) {
        dist = new long[N];
        Arrays.fill(dist, INF);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        
        dist[start] = 0;
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node currentNode = pq.poll();
            int now = currentNode.index;
            long distance = currentNode.distance;

            // 이미 처리된 노드라면 무시
            if (dist[now] < distance) {
                continue;
            }

            // 현재 노드와 연결된 다른 노드들을 확인
            for (Node nextNode : graph.get(now)) {
                long cost = distance + nextNode.distance;
                if (cost < dist[nextNode.index]) {
                    dist[nextNode.index] = cost;
                    pq.add(new Node(nextNode.index, cost));
                }
            }
        }
    }
}