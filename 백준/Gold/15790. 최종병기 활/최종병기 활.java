import java.util.*;
import java.io.*;

public class Main {
 
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	st = new StringTokenizer(br.readLine());
    	N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());
    	K = Integer.parseInt(st.nextToken());
    	arr = new int[M];
    	for(int i = 0; i < M;i++) {
    		arr[i] = Integer.parseInt(br.readLine());
    	}
    	
    	if(K == 1) {
    		System.out.println(N);
    		return;
    	}
    	
    	int l = 1;
    	int r = N;
    	int ret = -1;
    	
    	while(l<=r) {
    		int mid = (l + r) / 2;
    		if(decide(mid)) {
    			l = mid + 1;
    			ret = mid;
    		}else {
    			r = mid - 1;
    		}
    	}
    	
    	System.out.println(ret);
    	
    }
    
    static boolean decide(int len) {
    	for(int s = 0; s< M; s++) {
    		if(possible(s, len))
    			return true;
    	}
    	return false;
    }
    
    static boolean possible(int start, int len) {
    	int cnt = 1;
    	int cur = start;
    	
    	for(int i = 1; i < M;i++) {
    		int nxt = (start + i) % M;
    		
    		if(getLength(nxt, cur) >= len) {
    			cur = nxt;
    			cnt++;
    			if(cnt == K)
    				return getLength(start, cur) >= len;
    			
    		}
    	}
    	
    	
//    	int idx = (cur + 1) % M;
//    	
//    	while(idx != start) {
//    		if(getLength(idx, cur) >=len ) {
//    			cur = idx;
//    			cnt++;
//    			if(cnt == K)
//    				return getLength(start, cur) >= len; 
//    		}
//    		
//    		idx = (idx + 1) % M;
//    	}
    	
    	return false;
    	
    }
    
    static int getLength(int nxt, int cur) {
    	return Math.abs(arr[nxt] - arr[cur] + N) % N ;
    }
    
    static int N,M,K;
    static StringTokenizer st;
    static int arr[];
    
}