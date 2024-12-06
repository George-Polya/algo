import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	m = Integer.parseInt(st.nextToken());
    	
    	arr = new int[n];
    	for(int i = 0;i <n; i++) {
    		arr[i] = Integer.parseInt(br.readLine());
    	}
    	
    	Arrays.sort(arr);
    	StringBuilder sb = new StringBuilder();
    	for(int q = 1; q<=m; q++) {
    		int d = Integer.parseInt(br.readLine());
    		
    		int lower = lowerBound(d);
    		int upper = upperBound(d);
//    		System.out.printf("lower: %d, upper: %d\n", lower, upper);
    		
    		sb.append(lower == upper ? -1 : lower).append('\n');
    	}
    	System.out.println(sb);
    }
    
    static int upperBound(int target) {
    	int l = 0;
    	int r = n - 1;
    	int ret = n;
    	
    	while(l<=r) {
    		int mid = (l + r)/2;
    		if(target < arr[mid]) {
    			r = mid - 1;
    			ret = mid;
    		}else
    			l = mid + 1;
    	}
    	return ret;
    }
    
    static int lowerBound(int target) {
    	int l = 0;
    	int r = n - 1;
    	int ret = n;
    	
    	while(l<=r) {
    		int mid = (l + r)/2;
    		if(target <= arr[mid]) {
    			r = mid - 1;
    			ret = mid;
    		}else
    			l = mid + 1;
    	}
    	
    	return ret;
    }
    
    static StringTokenizer st;
    static int n,m;
    static int arr[];
}