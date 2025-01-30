import java.io.*;
import java.util.*;

public class Main {
    static int[] uf;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 정점 개수 입력
        uf = new int[N + 1];

        // 유니온 파인드 초기화
        for (int i = 1; i <= N; i++) {
            uf[i] = i;
        }

        // N-2개의 간선 입력 받기
        for (int i = 0; i < N - 2; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            union(u, v);
        }

        // 각 컴포넌트 그룹 찾기
        Map<Integer, Integer> hm = new HashMap<>();

        for (int i = 1; i <= N; i++) {
            int root = find(i);
            hm.put(root, hm.getOrDefault(root, 0) + 1);
        }

        // 가장 큰 집합의 대표 찾기
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(hm.entrySet());
        list.sort((a, b) -> Integer.compare(a.getValue(), b.getValue()));

        int idx = list.get(list.size() - 1).getKey(); // 가장 큰 그룹의 대표 노드

        // 다른 그룹의 대표 노드 찾기
        for (int i = 1; i <= N; i++) {
            if (find(i) != idx) {
                System.out.println(idx + " " + i);
                return;
            }
        }
    }

    // 경로 압축을 활용한 Find 함수
    static int find(int x) {
        if (uf[x] == x) return x;
        return uf[x] = find(uf[x]);
    }

    // Union 함수
    static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x != y) {
            uf[x] = y;
        }
    }
}