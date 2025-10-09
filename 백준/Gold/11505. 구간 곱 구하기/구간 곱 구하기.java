import java.util.*;
import java.io.*;

public class Main{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		arr = new int[N + 1];
		for(int i = 1; i<=N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		SegTree seg = new SegTree();
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			if(a == 1)
				seg.update(1, 1, N, c, b, b);
			else
				sb.append(seg.query(1, 1, N, b, c)).append('\n');
		}
		System.out.println(sb);
	}
	static class SegTree{
		long tree[], lazy[];
		boolean isLazy[];
		
		public SegTree() {
			int h = (int)(Math.log(N) / Math.log(2)) + 1;
			int size = 1 << (h + 1);
			tree = new long[size];
			lazy = new long[size];
			isLazy = new boolean[size];
			Arrays.fill(lazy, 1);
			init(1,1,N);
		}
		
		private void init(int cur, int start, int end) {
			if(start == end) {
				tree[cur] = arr[start];
				return;
			}
			
			int mid = (start + end) / 2;
			init(cur * 2 , start ,mid);
			init(cur * 2 + 1, mid + 1, end);
			tree[cur] = (tree[cur * 2] * tree[cur * 2  + 1]) % MOD; 
		}
		
		public void update(int cur, int left, int right, int value, int qLeft, int qRight) {
			propagate(cur,left,right);
			
			if(qRight < left || right < qLeft)
				return;
			
			if(qLeft <= left && right <= qRight) {
				isLazy[cur] = true;
				lazy[cur] *= value;
				propagate(cur,left,right);
				return;
			}
			
			int mid = (left + right)/2;
			update(cur * 2, left, mid, value,qLeft, qRight);
			update(cur * 2 + 1, mid + 1, right, value, qLeft, qRight);
			tree[cur] = (tree[cur * 2] * tree[cur * 2 + 1]) % MOD;
				
		}
		
		public void propagate(int cur, int left, int right) {
			if(!isLazy[cur])
				return;
			
			if(left != right) {
				lazy[cur*2] = (lazy[cur * 2 ] * lazy[cur]) % MOD;
				lazy[cur * 2 + 1] = (lazy[cur*2 + 1] * lazy[cur]) % MOD;
				isLazy[cur*2] = true;
				isLazy[cur * 2 + 1] = true;
			}
			
			tree[cur] = (long)Math.pow(lazy[cur], right - left + 1) % MOD;
			lazy[cur] = 1;
			isLazy[cur] = false;
		}
		
		public long query(int cur, int left, int right, int qLeft, int qRight) {
			propagate(cur, left, right);
			
			if(qRight < left || right < qLeft)
				return 1;
			
			if(qLeft <= left && right <= qRight)
				return tree[cur];
			
			int mid = (left + right) / 2;
			long leftValue = query(cur * 2, left, mid, qLeft, qRight);
			long rightValue = query(cur * 2 + 1, mid + 1, right, qLeft, qRight);
			
			return (leftValue * rightValue) % MOD;
		}
	}
	static long MOD = (int)1e9 + 7;
	static StringTokenizer st;
	static int N,M,K;
	static int arr[];
}
