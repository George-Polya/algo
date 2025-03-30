import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        pSum = new long[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
        	arr[i] = Integer.parseInt(st.nextToken());
        	pSum[i] = pSum[i-1] + arr[i];
        }
        
        segTree = new SegTree(arr);
        System.out.println(solve(1,N));
    }
    static int N;
    static int arr[];
    static long pSum[];
    static StringTokenizer st;
    static SegTree segTree; 
    
    static long solve(int left, int right) {
    	if(left > right)
    		return 0;
    	
    	if(left == right) {
    		return (long) arr[left] * arr[left];
    	}
    	
    	MinInfo minInfo = segTree.query(1, 1, N, left, right);
    	int minIdx = minInfo.idx;
    	int minValue = minInfo.value;
    	
    	long midSum = pSum[right] - pSum[left - 1];
    	long midScore = midSum * minValue;
    	
    	long leftScore = solve(left, minIdx - 1);
    	long rightScore = solve(minIdx + 1, right);
    	
    	return Math.max(midScore, Math.max(leftScore, rightScore));
    }
    
    static class MinInfo{
    	int value, idx;
    	public MinInfo(int value, int idx) {
    		this.value = value;
    		this.idx = idx;
    	}
    }
    
    static class SegTree{
    	MinInfo minTree[];
    	boolean isLazy[];
    	
    	public SegTree(int arr[]) {
    		int h = (int)(Math.ceil(Math.log(N) / Math.log(2)));
    		int size = 1 << (h + 1);
    		minTree = new MinInfo[size];
    		build(1, 1, N);
    	}
    	
    	private void build(int cur, int left, int right) {
    		if(left == right) {
    			minTree[cur] = new MinInfo(arr[left], left);
    			return;
    		}
    		
    		int mid = (left + right) / 2;
    		build(cur * 2, left, mid);
    		build(cur * 2 + 1, mid + 1, right);
    		
    		if(minTree[cur*2].value <= minTree[cur * 2 + 1].value)
    			minTree[cur] = minTree[cur * 2];
    		else
    			minTree[cur] = minTree[cur * 2 + 1];
    	}
    	
    	public MinInfo query(int cur, int left, int right, int qLeft, int qRight) {
    		
    		if(qRight < left || right < qLeft)
    			return new MinInfo(INF, -1);
    		
    		if(qLeft <= left && right <= qRight) {
    			return minTree[cur];
    		}
    		
    		int mid = (left + right ) / 2;
    		MinInfo leftInfo = query(cur * 2, left, mid, qLeft, qRight);
    		MinInfo rightInfo = query(cur * 2 + 1, mid + 1, right ,qLeft, qRight);
    		if(leftInfo.value <= rightInfo.value)
    			return leftInfo;
    		else
    			return rightInfo;
    	}
    	
   
    	
    }
    static int INF = Integer.MAX_VALUE / 2;
}