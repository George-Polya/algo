import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	k = Integer.parseInt(st.nextToken());
    	
    	arr = new int[MAX_X+1];
    	
    	for(int i = 0; i < n; i++) {
    		st = new StringTokenizer(br.readLine());
    		int g = Integer.parseInt(st.nextToken());
    		int x = Integer.parseInt(st.nextToken());
    		
    		arr[x] = g;
    	}
    	
    	int sum = 0;
    	for(int x = 0; x<=Math.min(MAX_X, k); x++)
    		sum += arr[x];
    	
//    	System.out.println(sum);
    	
    	int ans= -1;
    	for(int x = 1; x<=MAX_X; x++) {
    		if(x - 1 - k >= 0)
    			sum -= arr[x-1-k];
    		
    		if(x + k <= MAX_X)
    			sum += arr[x + k];
    		
    		ans = Math.max(ans, sum);
    	}
    		
    	System.out.println(ans);
    	
    }
    
    static int arr[];
    static StringTokenizer st;
    static int MAX_X = 1_000_000;
    static int n,k;
}