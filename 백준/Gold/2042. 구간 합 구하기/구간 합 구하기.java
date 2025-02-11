import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	st = new StringTokenizer(br.readLine());
    	N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());
    	K = Integer.parseInt(st.nextToken());
    	
    	arr = new long[N+1];
    	for(int i = 1; i<=N;i++) {
    		arr[i] = Long.parseLong(br.readLine());
    	}
    	seg = new SegTree(N);
    	
    	StringBuilder sb = new StringBuilder();
    	for(int m = 0; m < M + K; m++) {
    		st = new StringTokenizer(br.readLine());
    		int cmd = Integer.parseInt(st.nextToken());
    		int b = Integer.parseInt(st.nextToken());
    		
    		if(cmd == 1) {
    			long c = Long.parseLong(st.nextToken());
    			seg.update(1, 1, N, b, c);
    		}else {
    			int c = Integer.parseInt(st.nextToken());
    			sb.append(seg.query(1,1,N,b,c)).append('\n');
    			seg.query(1, 1, N, b, c);
    		}
    	}
    	System.out.println(sb);
    	
    }
    static int N,M,K;
    static long arr[];
    static StringTokenizer st;
    static SegTree seg;
    
    static class SegTree{
    	long tree[];
    	int treeSize;
    	
    	public SegTree(int n) {
    		int h = (int)Math.ceil(Math.log(n) / Math.log(2));
    		this.treeSize = (1 << (h+1));
    		tree = new long[treeSize];
    		this.init(1, 1, N);
    	}
    	
    	public long init(int cur,int start, int end) {
    		if(start == end) {
    			return tree[cur] = arr[start];
    		}
    		
    		int mid = (start + end) / 2;
    		
    		tree[cur] = init(cur * 2, start, mid) + init(cur * 2 + 1, mid+1, end);
    		return tree[cur];
    	}
 
    	public void update(int cur, int start, int end, int idx, long value) {
    		if(idx < start || idx > end)
    			return;
    		if(start == end) {
    			tree[cur] = value;
    			return;
    		}
    		
    		int mid = (start + end) / 2;
    		update(cur * 2, start, mid, idx, value);
    		update(cur * 2 + 1, mid + 1, end, idx, value);
    		tree[cur] = tree[cur * 2] + tree[cur * 2 + 1];
    		
    	}
    	
    	public long query(int cur, int left, int right, int qLeft, int qRight) {
    		if(qRight < left || qLeft > right)
    			return 0;
    		
    		if(qLeft <= left && right <= qRight)
    			return tree[cur];
    		
    		int mid = (left + right ) / 2;
    		
    		long leftSum = query(cur * 2, left, mid, qLeft, qRight);
    		long rightSum = query(cur * 2 + 1, mid + 1, right, qLeft, qRight);
    		
    		return leftSum + rightSum;
    	}
    	
    }
}