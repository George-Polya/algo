import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        c = Long.parseLong(st.nextToken());
        arr = new long[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n;i++) {
        	arr[i] = Long.parseLong(st.nextToken());
        	
        	if(arr[i] == c) {
        		System.out.println(1);
        		System.exit(0);
        	}
        }
        
        
        Arrays.sort(arr);
        
        int l = 0;
        int r = n - 1;
        
        while(l < r) {
        	
        	long sum = arr[l] + arr[r];
        	
        	if(sum == c) {
        		System.out.println(1);
        		System.exit(0);
        	}else if(sum < c) {
        		if(binarySearch(l,r,c - sum)) {
        			System.out.println(1);
            		System.exit(0);
        		}
        		l++;
        	}else {
        		r--;
        	}
        }
        System.out.println(0);
    }
    
    static boolean binarySearch(int l, int r, long value) {
    	int left = l;
    	int right = r;
    	
    	
    	while(left <= right) {
    		int mid = (left+right) / 2;
    		
    		if(arr[mid] == value) {
    			if(mid != l && mid != r)
    				return true;
    			return false;
    		}
    		else if(arr[mid] > value) {
    			right = mid - 1;
    		}else {
    			left = mid + 1;
    		}
    		
    	}
    	
    	return false;
    }
    
    static StringTokenizer st;
    static int n;
    static long c, arr[];
}