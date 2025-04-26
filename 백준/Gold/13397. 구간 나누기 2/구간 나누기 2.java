import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i<=N;i++) {
        	arr[i] = Integer.parseInt(st.nextToken());
        }
        
        int L = 0;
        int R = 10001;
        int ret = R;
        
        while(L<=R) {
        	int mid = (L + R) / 2;
        	if(decide(mid)) {
        		R = mid - 1;
        		ret = mid;
        	}else {
        		L = mid + 1;
        	}
        }
        System.out.println(ret);
    }
    
    static boolean decide(int score) {
    	int cnt = 1;
    	int min = arr[1];
    	int max = arr[1];
    	
    	for(int i = 2; i<=N; i++) {
    		int curMax = Math.max(max, arr[i]);
    		int curMin = Math.min(min, arr[i]);
    		
    		if(curMax - curMin > score) {
    			cnt++;
    			max = arr[i];
    			min = arr[i];
    		}else {
    			max = curMax;
    			min = curMin;
    		}
    	}
    	
    	return cnt <= M;
    }
    
    static int N,M;
    static StringTokenizer st;
    static int arr[];
}