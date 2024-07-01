import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static class Node{
        int idx, cnt;

        public Node(int num, int weight) {
            this.idx = num;
            this.cnt = weight;
        }
    }
    static int n;
    static int max = 0;
    static int max_idx = 0;
    static boolean visited[];

    static ArrayList<Node> list[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        list = new ArrayList[n+1];

        for (int i = 0; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < n-1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            list[parent].add(new Node(child, weight));
            list[child].add(new Node(parent, weight));
        }

        visited = new boolean[n + 1];
        visited[1] = true;
        dfs(1, 0);

        visited = new boolean[n + 1];
        visited[max_idx] = true;
        dfs(max_idx, 0);
        System.out.println(max);


    }

    static void dfs(int idx, int cnt) {
        if (max < cnt) {
            max = cnt;
            max_idx = idx;
        }

        for (Node a : list[idx]) {
            if (!visited[a.idx]) {
                visited[a.idx] = true;
                dfs(a.idx, cnt + a.cnt);
            }
        }
    }
}
