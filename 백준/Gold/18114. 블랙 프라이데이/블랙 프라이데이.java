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
        
        for(int i = 0; i < n ;i++) {
        	for(int j = i + 1; j <n ;j++) {
        		long sum = arr[i] + arr[j];
        		
        		if(sum == c) {
        			System.out.println(1);
            		System.exit(0);
        		}
        		
        		int l = 0;
        		int r = n - 1;
        		
        		while(l<=r) {
        			int mid = (l + r) / 2;
        			
        			if(sum + arr[mid] == c) {
        				if(mid !=i && mid != j) {
        					System.out.println(1);
        	        		System.exit(0);
        				}
        			}
        			
        			if(sum + arr[mid] > c) {
        				r = mid - 1;
        			}else 
        				l = mid + 1;
        		}
        	}
        }
        
        
        System.out.println(0);
    }
    
    static StringTokenizer st;
    static int n;
    static long c, arr[];
}