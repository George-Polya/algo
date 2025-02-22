import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int[] tree, lazy;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        tree = new int[4 * n];
        lazy = new int[4 * n];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (op == 0) {
                update(1, n, 1, a, b);
            } else {
                sb.append(pSum(1, n, 1, a, b)).append("\n");
            }
        }
        System.out.print(sb.toString());
    }

    static void propagate(int s, int e, int node) {
        if (lazy[node] % 2 == 1) {
            if (s != e) {
                lazy[node * 2] += lazy[node];
                lazy[node * 2 + 1] += lazy[node];
            }
            tree[node] = (e - s + 1) - tree[node];
            lazy[node] = 0;
        }
    }

    static void update(int s, int e, int node, int l, int r) {
        propagate(s, e, node);
        if (e < l || r < s) return;
        if (l <= s && e <= r) {
            lazy[node]++;
            propagate(s, e, node);
            return;
        }

        int mid = (s + e) / 2;
        update(s, mid, node * 2, l, r);
        update(mid + 1, e, node * 2 + 1, l, r);
        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }

    static int pSum(int s, int e, int node, int l, int r) {
        propagate(s, e, node);
        if (e < l || r < s) return 0;
        if (l <= s && e <= r) {
            return tree[node];
        }

        int mid = (s + e) / 2;
        return pSum(s, mid, node * 2, l, r) + pSum(mid + 1, e, node * 2 + 1, l, r);
    }
}