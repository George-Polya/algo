import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	N = Integer.parseInt(br.readLine());
    	arr = new int[N+1];
    	st = new StringTokenizer(br.readLine());
    	for(int i = 1; i<=N; i++) {
    		arr[i] = Integer.parseInt(st.nextToken());
    	}
    	
    	SegTree seg = new SegTree(N);
    	
    	M = Integer.parseInt(br.readLine());
    	StringBuilder sb = new StringBuilder();
    	for(int i = 0; i < M ;i++) {
    		st = new StringTokenizer(br.readLine());
    		int cmd = Integer.parseInt(st.nextToken());
    		
    		if(cmd == 1) {
    			int idx = Integer.parseInt(st.nextToken());
    			int value = Integer.parseInt(st.nextToken());
    			seg.update(1, 1, N, value, idx, idx);
    		}else if(cmd == 2) {
    			int qLeft = Integer.parseInt(st.nextToken());
    			int qRight = Integer.parseInt(st.nextToken());
    			sb.append(seg.query(1, 1, N,qLeft,qRight)).append('\n');
    		}
    	}
    	System.out.println(sb);
    }
    static int N,M,arr[];
    static int INF = Integer.MAX_VALUE / 2;
    static StringTokenizer st;
    static class SegTree{
    	int tree[], lazy[];
    	boolean isLazy[];
    	
    	public SegTree(int n) {
    		int h = (int)Math.ceil(Math.log(n) / Math.log(2));
    		int size = 1<<(h+1);
    		tree = new int[size];
    		lazy = new int[size];
    		isLazy = new boolean[size];
    		
    		this.build(1, 1, n);
    	}
    	private void build(int cur,int left, int right) {
    		if(left == right) {
    			tree[cur] = arr[left];
    			return;
    		}
    		int mid = (left + right) / 2;
    		this.build(cur * 2, left, mid);
    		this.build(cur * 2 + 1, mid + 1, right);
    		
    		tree[cur] = Math.min(tree[cur*2], tree[cur*2+1]);
    	}
    	
    	public void update(int cur, int left, int right, int value, int qLeft, int qRight) {
    		propagate(cur, left, right);
    		if(right < qLeft || qRight < left)
    			return;
    		
    		if(qLeft <= left && right <= qRight) {
    			isLazy[cur] = true;
    			lazy[cur] = value;
    			propagate(cur, left, right);
    			return;
    		}
    		
    		int mid = (left + right ) / 2;
    		update(cur * 2, left, mid, value, qLeft, qRight);
    		update(cur * 2 + 1, mid + 1, right ,value, qLeft, qRight);
    		
    		tree[cur] = Math.min(tree[cur * 2], tree[cur * 2 + 1]);
    		
    	}
    	
    	private void propagate(int cur, int left, int right) {
    		if (!isLazy[cur])
    			return;
    		
    		if(left != right) {
    			lazy[cur * 2] = lazy[cur];
    			lazy[cur * 2 + 1] = lazy[cur];
    			isLazy[cur * 2] = true;
    			isLazy[cur * 2 + 1] = true;
    		}
    		
    		tree[cur] = lazy[cur] * (right - left + 1);
    		isLazy[cur] = false;
    		lazy[cur] = INF;
    	}
    	
    	public int query(int cur, int left, int right, int qLeft, int qRight) {
       		propagate(cur, left, right);
    		if(right < qLeft || qRight < left)
    			return INF;
       		
       		if(qLeft <= left && right <= qRight)
       			return tree[cur];
       		
       		int mid = (left + right ) / 2;
       		int leftValue = query(cur * 2, left, mid ,qLeft, qRight);
       		int rightValue = query(cur * 2 + 1, mid + 1, right ,qLeft, qRight);
       		
       		return Math.min(leftValue, rightValue);
    	}
    }
}