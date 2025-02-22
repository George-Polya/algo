import java.util.*;
import java.io.*;

public class Main {
    static StringTokenizer st;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        SegTree seg = new SegTree(N);
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (cmd == 0) {
                seg.update(1, 0, N - 1, a - 1, b - 1);  // ✅ 인덱스 보정
            } else {
                sb.append(seg.query(1, 0, N - 1, a - 1, b - 1)).append('\n');  // ✅ 인덱스 보정
            }
        }
        System.out.println(sb);
    }

    static class SegTree {
        int size;
        int[] tree;
        boolean[] lazy;  // ✅ lazy를 boolean 배열로 변경

        public SegTree(int n) {
            int h = (int) Math.ceil(Math.log(n) / Math.log(2));
            size = (1 << (h + 1));
            tree = new int[size];
            lazy = new boolean[size];  // ✅ 초기값 false
        }

        public void update(int cur, int left, int right, int qLeft, int qRight) {
            propagate(cur, left, right);  // ✅ 갱신 전 lazy 처리

            if (qRight < left || qLeft > right) return;  // ✅ 범위 밖이면 리턴

            if (qLeft <= left && right <= qRight) {
                lazy[cur] ^= true;  // ✅ 반전 처리
                propagate(cur, left, right);  // ✅ 변경 즉시 반영
                return;
            }

            int mid = (left + right) / 2;
            update(cur * 2, left, mid, qLeft, qRight);
            update(cur * 2 + 1, mid + 1, right, qLeft, qRight);
            tree[cur] = tree[cur * 2] + tree[cur * 2 + 1];  // ✅ 자식 값 갱신
        }

        private void propagate(int cur, int left, int right) {
            if (!lazy[cur]) return;  // ✅ lazy가 없으면 리턴

            tree[cur] = (right - left + 1) - tree[cur];  // ✅ 전체 반전

            if (left != right) {  // ✅ 리프 노드가 아닐 때
                lazy[cur * 2] ^= true;
                lazy[cur * 2 + 1] ^= true;
            }

            lazy[cur] = false;  // ✅ 현재 노드의 lazy 해제
        }

        public int query(int cur, int left, int right, int qLeft, int qRight) {
            propagate(cur, left, right);  // ✅ 쿼리 전 lazy 처리

            if (qRight < left || qLeft > right) return 0;  // ✅ 범위 벗어나면 리턴

            if (qLeft <= left && right <= qRight) return tree[cur];  // ✅ 완전 포함 시 반환

            int mid = (left + right) / 2;
            int leftValue = query(cur * 2, left, mid, qLeft, qRight);
            int rightValue = query(cur * 2 + 1, mid + 1, right, qLeft, qRight);
            return leftValue + rightValue;
        }
    }
}