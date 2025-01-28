import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	st = new StringTokenizer(br.readLine());
    	N = Integer.parseInt(st.nextToken());
    	Q = Integer.parseInt(st.nextToken());
    	arr = new int[N+1];
    	st = new StringTokenizer(br.readLine());
    	for(int i = 1; i<=N; i++) {
    		arr[i] = Integer.parseInt(st.nextToken());
    	}
    	
    	Arrays.sort(arr, 1,N+1);
    	pSum = new int[N+1];
    	for(int i = 1; i<=N; i++) {
    		pSum[i] = pSum[i-1] + arr[i];
    	}
    	
    	StringBuilder sb=  new StringBuilder();
    	for(int q = 0; q < Q; q++) {
    		st = new StringTokenizer(br.readLine());
    		int s = Integer.parseInt(st.nextToken());
    		int e = Integer.parseInt(st.nextToken());
    		sb.append(pSum[e] - pSum[s-1]).append('\n');
    	}
    	System.out.println(sb);
    }
    static int N,Q;
    static StringTokenizer st;
    static int arr[], pSum[];
}