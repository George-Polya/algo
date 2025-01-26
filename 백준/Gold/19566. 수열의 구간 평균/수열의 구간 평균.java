import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	st = new StringTokenizer(br.readLine());
    	N = Integer.parseInt(st.nextToken());
    	K = Integer.parseInt(st.nextToken());
    	st = new StringTokenizer(br.readLine());
    	arr = new int[N];
    	for(int i = 0; i < N;i++) {
    		arr[i] = Integer.parseInt(st.nextToken());
    	}
    	
    	long pSum[] = new long[N];
    	pSum[0] = arr[0];
    	for(int i = 1; i<N; i++) {
    		pSum[i] = pSum[i-1] + arr[i];
    	}
    	
    	Map<Long, Integer> map = new HashMap<>();
    	
    	long ans = 0;
    	long target = 0;
    	for(int i = 0; i < N ;i++) {
    		target += K;
//    		target = K * (i+1);
    		long diff = target - pSum[i];
//    		System.out.printf("%d | target : %d | diff : %d\n", i, target, diff);
    		if(diff == 0)
    			ans++;
    		
    		int cnt = map.getOrDefault(diff, 0);
    		ans += cnt;
    		map.put(diff, cnt+1);
    	}
    	
    	System.out.println(ans);
    }
    static int N,K;
    static int arr[];
    static StringTokenizer st;
}