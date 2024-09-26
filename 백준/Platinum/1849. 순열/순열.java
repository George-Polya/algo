import java.io.*;

public class Main {
    static int[] tree;
    static int[] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        tree = new int[4 * N];
        result = new int[N + 1];

        // 세그먼트 트리 초기화
        init(1, 1, N);

        // 입력 처리 및 결과 계산
        for (int i = 1; i <= N; i++) {
            int A = Integer.parseInt(br.readLine());
            int index = query(1, 1, N, A + 1);
            result[index] = i;
            update(1, 1, N, index, -1);
        }

        // 결과 출력
        for (int i = 1; i <= N; i++) {
            bw.write(result[i] + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    // 세그먼트 트리 초기화
    static void init(int node, int start, int end) {
        if (start == end) {
            tree[node] = 1;
            return;
        }
        int mid = (start + end) / 2;
        init(node * 2, start, mid);
        init(node * 2 + 1, mid + 1, end);
        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }

    // 구간 합 쿼리
    static int query(int node, int start, int end, int count) {
        if (start == end) return start;
        int mid = (start + end) / 2;
        if (tree[node * 2] >= count) {
            return query(node * 2, start, mid, count);
        } else {
            return query(node * 2 + 1, mid + 1, end, count - tree[node * 2]);
        }
    }

    // 세그먼트 트리 업데이트
    static void update(int node, int start, int end, int index, int diff) {
        if (index < start || index > end) return;
        tree[node] += diff;
        if (start != end) {
            int mid = (start + end) / 2;
            update(node * 2, start, mid, index, diff);
            update(node * 2 + 1, mid + 1, end, index, diff);
        }
    }
}