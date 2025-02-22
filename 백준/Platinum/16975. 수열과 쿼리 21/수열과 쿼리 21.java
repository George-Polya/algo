import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	N = Integer.parseInt(br.readLine());
    	st = new StringTokenizer(br.readLine());
    	arr = new int[N+1];
    	for(int i = 1; i <= N; i++) {
    		arr[i] = Integer.parseInt(st.nextToken());
    	}
    	
    	segTree = new SegTree(N);
    	
    	M = Integer.parseInt(br.readLine());
    	
    	StringBuilder sb = new StringBuilder();
    	for(int i = 0; i < M; i++) {
    		st = new StringTokenizer(br.readLine());
    		int cmd = Integer.parseInt(st.nextToken());
    		
    		if(cmd == 1) {
    			int qLeft = Integer.parseInt(st.nextToken());
    			int qRight = Integer.parseInt(st.nextToken());
    			int value = Integer.parseInt(st.nextToken());
    			segTree.updateRange(1, 1, N, value, qLeft, qRight);
    		}else{
    			int idx = Integer.parseInt(st.nextToken());
    			sb.append(segTree.query(1,1, N,idx,idx)).append('\n');
    		}
    	}
    	System.out.println(sb);
    }   
    static int N, M, arr[];
    static StringTokenizer st;
    static SegTree segTree;
    static class SegTree{
    	long tree[], lazyValue[];
    	boolean lazyExist[];
    	int size;
    	public SegTree(int n) {
    		int h = (int)Math.ceil(Math.log(n) / Math.log(2));
    		size = (1<<(h+1));
    		tree = new long[size];
    		lazyValue = new long[size];
    		lazyExist = new boolean[size];
    		
    		build(1, 1, n);
    	}
    	
    	public void build(int cur, int start, int end) {
    		if(start == end) {
    			tree[cur] = arr[start];
    			return;
    		}
    		
    		int mid = (start + end ) / 2;
    		build(cur * 2, start, mid);
    		build(cur * 2 + 1, mid + 1, end);
    		tree[cur] = tree[cur * 2] + tree[cur * 2 + 1];
    	}
    	
    	public void updateRange(int cur, int left, int right,int value, int qLeft, int qRight) {
    		if(qRight < left || right < qLeft)
    			return;
    		
    		if(left == right) {
    			tree[cur] += value;
    			return;
    		}
    		
    		if (qLeft <= left && right <= qRight) {
    			lazyExist[cur] = true;
    			lazyValue[cur] += value;
    			tree[cur] += value * (right - left + 1);
    			return;
    		}
    		
    		int mid = (left + right ) / 2;
    		if(lazyExist[cur]) {
    			lazyExist[cur] = false;
    			propgate(lazyValue[cur], cur * 2, left, mid);
    			propgate(lazyValue[cur], cur * 2 + 1, mid + 1, right);
    			lazyValue[cur] = 0;
    		}
    		
    		updateRange(cur * 2, left, mid, value, qLeft, qRight);
    		updateRange(cur * 2 + 1, mid + 1, right, value, qLeft, qRight);
    		tree[cur] = tree[cur * 2] + tree[cur * 2 + 1];
    	}
    	
    	private void propgate(long value, int cur, int left, int right) {
    		if(left == right) {
    			tree[cur] += value;
    			return;
    		}
    		
    		lazyExist[cur] = true;
    		lazyValue[cur] += value;
    		tree[cur] += value * (right - left + 1);
    	}
    	
    	public long query(int cur, int left, int right, int qLeft, int qRight) {
    		if(qRight < left || right < qRight)
    			return 0;
    		
    		if(qLeft <= left && right <= qRight)
    			return tree[cur];
    		
    		int mid = (left + right) / 2;
    		if(lazyExist[cur]) {
    			lazyExist[cur] = false;
    			propgate(lazyValue[cur], cur * 2, left, mid);
    			propgate(lazyValue[cur], cur * 2 + 1, mid + 1, right);
    			lazyValue[cur] = 0;
    		}
    		return query(cur * 2, left, mid , qLeft, qRight) + query(cur * 2 +1 , mid + 1, right ,qLeft, qRight);
    	}
    	
    }
}