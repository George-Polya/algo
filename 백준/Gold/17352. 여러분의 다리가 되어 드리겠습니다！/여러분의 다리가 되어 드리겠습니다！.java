import java.util.*;

public class Main {
    static int[] uf;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        uf = new int[N + 1];

        // 초기화
        for (int i = 1; i <= N; i++) {
            uf[i] = i;
        }

        // 입력 처리 및 유니온 수행
        for (int i = 0; i < N - 2; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            union(u, v);
        }

        // 각 집합의 크기를 저장할 해시맵
        Map<Integer, Integer> hm = new HashMap<>();

        for (int i = 1; i <= N; i++) {
            int root = find(i);
            hm.put(root, hm.getOrDefault(root, 0) + 1);
        }

        // 가장 큰 집합을 찾음
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(hm.entrySet());
        list.sort((a, b) -> Integer.compare(a.getValue(), b.getValue()));

        int idx = list.get(list.size() - 1).getKey();

        // 두 번째 그룹의 대표 노드 찾기
        for (int i = 1; i <= N; i++) {
            if (find(i) != idx) {
                System.out.println(idx + " " + i);
                return;
            }
        }
    }

    // 유니온 파인드의 Find 함수 (경로 압축 포함)
    static int find(int x) {
        if (uf[x] == x) return x;
        return uf[x] = find(uf[x]);
    }

    // 유니온 함수
    static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x != y) {
            uf[x] = y;
        }
    }
}