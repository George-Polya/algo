import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	N = Integer.parseInt(br.readLine());
    	st = new StringTokenizer(br.readLine());
    	arr = new int[N];
    	for(int i = 0; i < N; i++) {
    		arr[i] = Integer.parseInt(st.nextToken());
    	}
    	
    	seg = new SegTree(N);
    	M = Integer.parseInt(br.readLine());
    	
    	StringBuilder sb = new StringBuilder();
    	for(int i = 0; i < M; i++) {
    		st = new StringTokenizer(br.readLine());
    		int cmd = Integer.parseInt(st.nextToken());
    		int qLeft = Integer.parseInt(st.nextToken());
    		int qRight = Integer.parseInt(st.nextToken());
    		if(cmd == 1) {
    			int value = Integer.parseInt(st.nextToken());
    			seg.rangeUpdate(1,0, N - 1, value, qLeft, qRight);
    		}else {
    			sb.append(seg.query(1, 0, N-1, qLeft, qRight)).append('\n');
    		}
    	}
    	System.out.println(sb);
    }
    static SegTree seg;
    static int N,M;
    static int arr[];
    static StringTokenizer st;
    static class SegTree{
    	int size, tree[], lazyValue[];
    	boolean isLazy[];
    	int DEFAULT = 0;
    	public SegTree(int n) {
    		int h = (int)Math.ceil(Math.log(n) / Math.log(2)) + 1;
    		int size = (1 << h);
    		tree = new int[size];
    		lazyValue = new int[size];
    		isLazy = new boolean[size];
    		this.build(1, 0, n - 1);
    	}
    	
    	private void build(int cur, int left, int right) {
    		if(left == right) {
    			tree[cur] = arr[left];
    			return;
    		}
    		
    		int mid = (left + right ) / 2;
    		build(cur * 2, left, mid);
    		build(cur * 2 + 1, mid + 1, right);
    		tree[cur] = tree[cur * 2] ^ tree[cur * 2 + 1];
    	}
    	
    	public int rangeUpdate(int cur, int left, int right, int value, int qLeft, int qRight) {
    		if(qRight < left || right < qLeft)
    			return tree[cur];
    		
    		if(left == right) {
    			return tree[cur] ^= value;
    		}
    		
    		if(qLeft <= left && right <= qRight) {
    			isLazy[cur] = true;
    			lazyValue[cur] ^= value;
    			int len = right - left + 1;
    			if(len % 2 != 0)
    				tree[cur] ^= value;
    			return tree[cur];
    		}
    		
    		int mid = (left + right) / 2;
    		if(isLazy[cur]) {
    			isLazy[cur] = false;
    			propgate(lazyValue[cur], cur * 2, left, mid);
    			propgate(lazyValue[cur], cur * 2 + 1, mid + 1, right);
    			lazyValue[cur] = DEFAULT;
    		}
    		int leftValue = rangeUpdate(cur * 2, left, mid, value, qLeft, qRight);
    		int rightValue = rangeUpdate(cur * 2 +1 ,mid +1, right ,value, qLeft, qRight);
    		
    		return tree[cur] = leftValue ^ rightValue;
    				
    	}
    	
    	private void propgate(int value, int cur, int left, int right) {
    		if(left == right) {
    			tree[cur] ^= value;
    			return;
    		}
    		isLazy[cur] = true;
    		lazyValue[cur] ^= value;
    		int len = right - left + 1;
    		if(len % 2 != 0)
    			tree[cur] ^= value;
    		return;
    	}
    	
    	public int query(int cur, int left, int right, int qLeft, int qRight) {
    		if(qRight < left || qLeft > right)
    			return DEFAULT;
    		
    		if(qLeft <= left && right <= qRight)
    			return tree[cur];
    		
    		int mid = (left + right ) / 2;
    		if(isLazy[cur]) {
    			isLazy[cur] = false;
    			propgate(lazyValue[cur], cur * 2, left, mid);
    			propgate(lazyValue[cur], cur * 2 + 1, mid + 1, right);
    			lazyValue[cur] = DEFAULT;
    		}
    		
    		int leftValue = query(cur * 2, left, mid, qLeft, qRight);
    		int rightValue = query(cur * 2 + 1, mid + 1, right , qLeft ,qRight);
    		
    		return leftValue ^ rightValue;
    	}
    }
}