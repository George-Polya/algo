import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	st = new StringTokenizer(br.readLine());
    	N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());
    	arr = new int[N+1];
    	
    	for(int i = 1; i <= N; i++) {
    		arr[i] = Integer.parseInt(br.readLine());
    	}
    	
    	SegTree seg = new SegTree(N);
    	
    	StringBuilder sb = new StringBuilder();
    	for(int i = 0; i < M; i++) {
    		st = new StringTokenizer(br.readLine());
    		int a = Integer.parseInt(st.nextToken());
    		int b = Integer.parseInt(st.nextToken());
    		sb.append(seg.query(1, 1, N, a, b)).append('\n');
    	}
    	System.out.println(sb);
    }
    
    static class SegTree{
    	int size, tree[], lazy[];
    	boolean isLazy[];
    	int DEFAULT = INT_MAX;
    	
    	public SegTree(int n) {
    		int h = (int)Math.ceil(Math.log(n) / Math.log(2));
    		int size = (1 << h+1);
    		tree = new int[size];
    		lazy = new int[size];
    		isLazy = new boolean[size];
//    		System.out.println(size);
    		this.build(1, 1, n);
    	}
    	
    	private void build(int cur, int left, int right) {
    		if(left == right) {
    			tree[cur] = arr[left];
    			return;
    		}
    		
    		int mid = (left + right ) / 2;
    		build(cur * 2, left, mid);
    		build(cur * 2 + 1, mid + 1, right);
    		
    		tree[cur] = Math.min(tree[cur*2],tree[cur*2+1]);
    	}
    	
    	public int query(int cur, int left, int right, int qLeft, int qRight) {
    		if(qRight < left || right < qLeft)
    			return DEFAULT;
    		
    		if(qLeft <= left && right <= qRight)
    			return tree[cur];
    		
    		int mid = (left + right ) / 2;
    		
    		int leftValue = query(cur * 2, left, mid, qLeft, qRight);
    		int rightValue = query(cur * 2 + 1, mid + 1, right ,qLeft, qRight);
    		
    		return Math.min(leftValue, rightValue);
    	}
    }
    
    static int INT_MAX = Integer.MAX_VALUE / 2;
    
    static int N,M;
    static StringTokenizer st;
    static int arr[];
}