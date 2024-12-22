import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		visited = new boolean[n + 1];
		isLeaf = new boolean[n + 1];

		for (int i = 1; i <= n; i++) {
			adj[i] = new ArrayList<>();
		}
		for (int i = 0; i <=n; i++) {
			treeNodes[i] = new ArrayList<>();
			treeEdges[i] = new ArrayList<>();
		}

		// 간선 입력
		for (int i = 1; i <= m; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			adj[u].add(new Edge(v, i));
			adj[v].add(new Edge(u, i));
		}
		if (n <= 2 || m == 0) {
			System.out.println(-1);
			return;
		}


		// 연결 요소 찾기
		for (int i = 1; i <= n; i++) {
			if (visited[i])
				continue;
			visited[i] = true;
			makeTree(i, treeNum);
			treeNum += 1;
		}

		// 이미 그래프가 세 개 이상의 부분으로 분리되어 있으면
		if (treeNum > 2) {
			System.out.println(-1);
			return;
		}

		int startV = 0, selected = 0;

		if (treeNum < 2) {
			// 리프 노드 하나 골라서 분리
			for (int x : treeNodes[0]) {
				if (isLeaf[x]) {
					selected = x;
				} else {
					startV = x;
				}
			}

			// 이전에 만든 트리 초기화
			treeNodes[0].clear();
			treeEdges[0].clear();
			visited = new boolean[n + 1];
			visited[selected] = true;

			// 트리 재생성
			visited[startV] = true;
			makeTree(startV, 0);

			treeNodes[1].add(selected);
		}

		// 두 부분의 크기가 서로 같을 때
		if (treeNodes[0].size() == treeNodes[1].size()) {
			System.out.println(-1);
			return;
		}

		System.out.println(treeNodes[0].size() + " " + treeNodes[1].size());

		print(treeNodes[0]);
		print(treeEdges[0]);
		print(treeNodes[1]);
		print(treeEdges[1]);
	}

	static final int N_MAX = (int) 1e5;
	static int n, m, treeNum;
	static boolean visited[], isLeaf[];

	static class Edge {
		int node, edge;

		public Edge(int node, int edge) {
			this.node = node;
			this.edge = edge;
		}
	}

	static StringTokenizer st;

	static ArrayList<Edge>[] adj = new ArrayList[N_MAX + 1];
	static ArrayList<Integer>[] treeNodes = new ArrayList[N_MAX + 1];
	static ArrayList<Integer>[] treeEdges = new ArrayList[N_MAX + 1];

	static void makeTree(int cur, int treeIdx) {
		treeNodes[treeIdx].add(cur);
		boolean leaf = true;

		for (Edge nxt : adj[cur]) {
			if (visited[nxt.node])
				continue;
			visited[nxt.node] = true;
			treeEdges[treeIdx].add(nxt.edge);
			leaf = false;
			makeTree(nxt.node, treeIdx);
		}

		isLeaf[cur] = leaf;
	}

	static void print(ArrayList<Integer> v) {
		Collections.sort(v);
		StringBuilder sb = new StringBuilder();
		for (int x : v) {
			sb.append(x).append(" ");
		}
		System.out.println(sb);
	}
}