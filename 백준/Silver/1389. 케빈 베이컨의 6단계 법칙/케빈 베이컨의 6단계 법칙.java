// https://www.acmicpc.net/problem/1389


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n,m; // 정점 수 n, 간선 수 m
    static ArrayList<Integer> adj[];
    static int dist[];


    static int bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        dist[start] = 0;

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int nxt : adj[cur]) {
                if(dist[nxt] != -1)
                    continue;

                q.add(nxt);
                dist[nxt] = dist[cur] + 1;
            }
        }

        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += dist[i];
        }
        return sum;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        adj = new ArrayList[n + 1];
        dist = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            adj[u].add(v);
            adj[v].add(u);
        }

        int result = -1;
        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist, -1);
            int temp = bfs(i);
//            System.out.println(temp);
            if (min > temp) {
                min = temp;
                result = i;
            }
        }

        System.out.println(result);
    }

}
