import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static ArrayList<Integer> adj[];
    static int T[];
    static int T_done[];
    static int inDeg[];
    static StringBuilder sb = new StringBuilder();

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
        n = Integer.parseInt(br.readLine());
        adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        T = new int[n + 1];
        T_done = new int[n + 1];
        inDeg = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            while (true) {
                int num = Integer.parseInt(st.nextToken());
                if(num == -1)
                    break;
                adj[num].add(i);
                inDeg[i] += 1;
            }
        }

        topologicalSort();
        for (int i = 1; i <= n; i++) {
            sb.append(T_done[i]).append('\n');
        }
        System.out.println(sb);
    }
}
