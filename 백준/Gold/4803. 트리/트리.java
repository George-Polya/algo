import java.io.*;
import java.util.*;

public class Main {
    static List<ArrayList<Integer>> graph;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stz;

        int testCase = 1;

        while (true) {
            stz = new StringTokenizer(br.readLine(), " ");

            int n = Integer.parseInt(stz.nextToken());
            int m = Integer.parseInt(stz.nextToken());

            // 0 0이면 종료
            if (n == 0 && m == 0) break;

            graph = new ArrayList<>();
            for (int i = 0; i < n + 1; i++) {
                graph.add(new ArrayList<>());
            }

            visited = new boolean[n + 1];

            // 입력값으로 그래프를 만듦
            for (int i = 0; i < m; i++) {
                stz = new StringTokenizer(br.readLine(), " ");
                int a = Integer.parseInt(stz.nextToken());
                int b = Integer.parseInt(stz.nextToken());

                graph.get(a).add(b);
                graph.get(b).add(a);
            }

            // 아직 방문하지 않은 노드들을 확인해 트리인지 확인
            // 루트 노드만 있어도 트리임
            int tree = 0;
            for (int i = 1; i < n + 1; i++) {
                if (!visited[i]) {
                    tree += checkTree(i);
                }
            }
            bw.write("Case " + testCase + ": ");

            if (tree > 1) {
                bw.write("A forest of " + tree + " trees.");
            } else if (tree == 1) {
                bw.write("There is one tree.");
            } else {
                bw.write("No trees.");
            }
            bw.write("\n");

            testCase++;
        }

        bw.flush();
        bw.close();
        br.close();
    }

    // 트리일 경우 n = e+1
    private static int checkTree(int root) {
        Queue<Integer> qu = new LinkedList<>();
        int node = 0;
        int edge = 0;

        qu.add(root);

        while (!qu.isEmpty()) {
            int cn = qu.poll();

            if (visited[cn]) continue;
            visited[cn] = true;
            node++;

            for (int i = 0; i < graph.get(cn).size(); i++) {
                int nn = graph.get(cn).get(i);
                edge++;
                if (!visited[nn]) {
                    qu.add(nn);
                }
            }
        }

        // 무방향 그래프 이므로 (e/2)해야 함
        return (edge / 2) + 1 == node ? 1 : 0;
    }
}