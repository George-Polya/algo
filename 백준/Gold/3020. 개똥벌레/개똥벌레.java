import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()) / 2;
        H = Integer.parseInt(st.nextToken());
        up = new int[n];
        down = new int[n];
        
        for(int i = 0; i < n * 2; i++) {
        	if(i % 2 == 0)
        		down[i / 2] = Integer.parseInt(br.readLine());
        	else
        		up[i / 2] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(down);
        Arrays.sort(up);
        
        int count = 1;
        int ans = Integer.MAX_VALUE;
        for(int h = 1; h<=H; h++) {
        	int cnt = lowerBound(h, down) + lowerBound(H - h + 1, up);
        	if(ans > cnt) {
        		ans = cnt;
        		count = 1;
        	}else if(ans == cnt)
        		count++;
        }
        
        System.out.println(ans+" "+count);
    }
    
    static int lowerBound(int height, int arr[]) {
    	int l = 0;
    	int r = n - 1;
    	int ret = n;
    	
    	while(l<=r) {
    		int mid = (l + r) / 2;
    		
    		if(height <= arr[mid]) {
    			r = mid - 1;
    			ret = mid;
    		}else
    			l = mid + 1;
    	}
    	return n - ret;
    }
    
    static StringTokenizer st;
    static int n,H;
    static int up[], down[];
}