import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        arr = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i<=N; i++) {
        	arr[i] = Integer.parseInt(st.nextToken());
        }
        
        costs = new int[N+1];
        st = new StringTokenizer(br.readLine());
        int total = 0;
        for(int i = 1; i<=N; i++) {
        	costs[i] = Integer.parseInt(st.nextToken());
        	total += costs[i];
        }
        
        dp = new int[total+1];
        
        for(int i = 1; i<=N; i++) {
        	for(int c = total ; c >=0; c--) {
        		if(c - costs[i] >=0 ) {
        			dp[c] = Math.max(dp[c], dp[c - costs[i]] + arr[i]);
        		}
        	}
        }
        
//        System.out.println(Arrays.toString(dp));
        
//        int ans = -1;
//        for(int i = 0; i<= total;i++) {
//        	if(dp[i] >= M) {
//        		ans = i;
//        		break;
//        	}
//        }
        
        int l = 0;
        int r = total;
        int ans = -1;
        while(l<=r) {
        	int mid = (l + r) / 2;
        	
        	if(M <= dp[mid]) {
        		r = mid - 1;
        		ans = mid;
        	}else {
        		l = mid + 1;
        	}
        }
        
        System.out.println(ans);
    }
    static int N,M;
    static StringTokenizer st;
    static int arr[], costs[], dp[];

}