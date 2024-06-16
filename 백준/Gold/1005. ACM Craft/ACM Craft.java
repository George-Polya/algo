
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int tc;
    static StringBuilder sb = new StringBuilder();
    static int n, k;
    static int T[];
    static int inDeg[];

    static ArrayList<Integer> adj[];
    static int T_done[];

    static void topologicalSort() {
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (inDeg[i] == 0) {
                q.add(i);
                T_done[i] = T[i];
            }
        }

        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int nxt : adj[cur]) {
                inDeg[nxt] -= 1;

                if(inDeg[nxt] == 0)
                    q.add(nxt);
                T_done[nxt] = Math.max(T_done[nxt], T_done[cur] + T[nxt]);
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        tc = Integer.parseInt(br.readLine());
        for (int t = 0; t < tc; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            T = new int[n + 1];
            adj = new ArrayList[n + 1];
            inDeg = new int[n + 1];
            T_done = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                adj[i] = new ArrayList<>();
            }
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                T[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                adj[u].add(v);
                inDeg[v] += 1;
            }

            topologicalSort();
            int w = Integer.parseInt(br.readLine());
            sb.append(T_done[w]).append('\n');
        }
        System.out.println(sb);
    }
}
