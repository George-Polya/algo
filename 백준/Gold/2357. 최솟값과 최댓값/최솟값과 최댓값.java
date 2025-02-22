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
    		int result[] = seg.query(1, 1, N, a, b);
    		sb.append(result[0]+" "+result[1]).append('\n');
    	}
    	System.out.println(sb);
    }
    
    static class SegTree{
    	int size, minTree[], maxTree[];
    	boolean isLazy[];
    	int MIN_DEFAULT = INT_MAX;
    	int MAX_DEFAULT = Integer.MIN_VALUE / 2;
    	
    	public SegTree(int n) {
    		int h = (int)Math.ceil(Math.log(n) / Math.log(2));
    		int size = (1 << h+1);
    		minTree = new int[size];
    		maxTree = new int[size];
    		isLazy = new boolean[size];
//    		System.out.println(size);
    		this.build(1, 1, N);
    	}
    	
    	private void build(int cur, int left, int right) {
    		if(left == right) {
    			minTree[cur] = arr[left];
    			maxTree[cur] = arr[left];
    			return;
    		}
    		
    		int mid = (left + right ) / 2;
    		build(cur * 2, left, mid);
    		build(cur * 2 + 1, mid + 1, right);
    		
    		minTree[cur] = Math.min(minTree[cur*2],minTree[cur*2+1]);
    		maxTree[cur] = Math.max(maxTree[cur*2],maxTree[cur*2+1]);
    	}
    	
    	public int[] query(int cur, int left, int right, int qLeft, int qRight) {
    		if(qRight < left || right < qLeft)
    			return new int[] {MIN_DEFAULT, MAX_DEFAULT};
    		
    		if(qLeft <= left && right <= qRight)
    			return new int[] {minTree[cur], maxTree[cur]};
    		
    		int mid = (left + right ) / 2;
    		
    		int[] leftValues = query(cur * 2, left, mid, qLeft, qRight);
    		int[] rightValues = query(cur * 2 + 1, mid + 1, right ,qLeft, qRight);
    		
    		return new int[] {Math.min(leftValues[0], rightValues[0]) , Math.max(leftValues[1], rightValues[1])};
    	}
    	
    }
    
    static int INT_MAX = Integer.MAX_VALUE / 2;
    
    static int N,M;
    static StringTokenizer st;
    static int arr[];
}