import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        d = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        oven = new int[d];
        for(int idx = 0; idx < d; idx++) {
        	int radius = Integer.parseInt(st.nextToken());
        	oven[idx] = Math.min(prev,  radius);
        	prev = oven[idx];
        	
        }
        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<n; i++) {
        	arr[i] = Integer.parseInt(st.nextToken());
        }
        
        depth = d - 1;
        for(int i = 0; i<n; i++) {
        	int cur = arr[i];
        	
        	int idx = lowerBound(cur, 1, depth);
        	depth = idx - 1;
        	min = Math.min(min, idx);
        }
        
        
        
        System.out.println(min+1);
    }
    static int depth;
    static int min = Integer.MAX_VALUE;
    static int lowerBound(int target, int l, int r) {
    	int ret = -1;
    	while(l<=r) {
    		int mid = (l+r)/2;
//    		System.out.printf("%d %d %d\n", l,r,mid);
    		if(target <= oven[mid]) {
    			l = mid + 1;
    			ret = mid;
    		}else {
    			r = mid - 1;
    		}
    	}
    	return ret;
    }
    
    static int d,n;
    static StringTokenizer st;
    static int arr[];
    static int oven[];
    static int prev = Integer.MAX_VALUE;
}