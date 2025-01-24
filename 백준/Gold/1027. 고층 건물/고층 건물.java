import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	n = Integer.parseInt(br.readLine());
    	st = new StringTokenizer(br.readLine());
    	arr = new int[n+1];
    	for(int i =1; i<=n; i++) {
    		arr[i] = Integer.parseInt(st.nextToken());
    	}
    	
    	int ans = 0;
    	for(int i = 1; i<=n; i++) {
    		ans = Math.max(ans, calc(i));
    	}
    	System.out.println(ans);
    }   
    
    static int calc(int idx) {
    	int cnt = 0;
    	double temp = 0;
    	
    	double minG = Double.POSITIVE_INFINITY;
    	double maxG = Double.NEGATIVE_INFINITY;
    	
    	for(int l = idx-1; l>=1; l--) {
    		double slope = (double)(arr[idx] - arr[l]) / (idx - l);
    		
    		if(l == idx - 1|| minG > slope) {
//    			temp = slope;
    			minG = slope;
    			cnt++;
    		}
    	}
    	
    	for(int r = idx + 1; r<=n; r++) {
    		double slope = (double)(arr[idx] - arr[r]) / (idx - r);
    		if(r == idx + 1 || slope > maxG) {
//    			temp = slope;
    			maxG = slope;
    			cnt++;
    		}
    	}
    	return cnt;
    }
    
    static int n;
    static int arr[];
    static StringTokenizer st;
}