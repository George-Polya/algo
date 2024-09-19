import java.io.*;
import java.util.*;

public class Main {
	static int n,k;
	
	static boolean decide(int num) {
		int sum = 0;
		for(int i = 1; i<=n; i++) {
			sum += Math.min(num / i, n);
		}
		
		return sum >= k;
	}
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());
        
        int l = 1;
        int r = (int)1e9;
        int ans = -1;
        while(l<=r) {
        	int mid = (l+r)/2;
        	
        	if(decide(mid)) {
        		r = mid - 1;
        		ans = mid;
        	}else {
        		l = mid + 1;
        	}
        }
        System.out.println(ans);
    }
}