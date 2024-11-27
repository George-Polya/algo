import java.io.*;
import java.util.*;

public class Main {
    private static int N, M;
    private static ArrayList<Node>[] adj;
    private static long[] distance;

    static class Node implements Comparable<Node> {
        int index;
        long cost;
        Node(int index, long cost) {
            this.index = index;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(this.cost, o.cost);
        }
    }

    private static void dijkstra() {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.offer(new Node(1, 0));
        distance[1] = 0;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if (cur.cost > distance[cur.index])
                continue;

            for (Node nxt : adj[cur.index]) {
                int nextIndex = nxt.index;
                long nextCost;
                if (cur.cost <= nxt.cost) {
                    nextCost = nxt.cost + 1;
                } else {
                    nextCost = ((long) Math.ceil(((double)cur.cost - nxt.cost) / M)) * M + nxt.cost + 1;
                }

                if (nextCost < distance[nextIndex]) {
                    distance[nextIndex] = nextCost;
                    queue.offer(new Node(nextIndex, nextCost));
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        distance = new long[N + 1];
        Arrays.fill(distance, Long.MAX_VALUE);

        adj = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj[u].add(new Node(v, i));
            adj[v].add(new Node(u, i));
        }

        dijkstra();
        System.out.println(distance[N]);
    }
}