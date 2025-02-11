import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	n = Integer.parseInt(br.readLine());
    	arr = new int[2*n];
    	int total = 0;
    	for(int i = 0; i < n; i++) {
    		arr[i] = Integer.parseInt(br.readLine());
    		total += arr[i];
    		arr[i + n] = arr[i];
    	}
//    	System.out.println(Arrays.toString(arr));
    	int r = 0;
    	int sum = 0;
    	int ans = 0;
    	for(int l = 0; l < 2 * n; l++) {
    		while(r < 2 * n && sum + arr[r] <= total/2) {
    			sum += arr[r];
    			r++;
    		}
    		
    		if(sum <= total/2) {
    			ans = Math.max(ans,  Math.min(sum, total - sum));
    		}
    		
    		sum -= arr[l];
    	}
    	System.out.println(ans);
    }
    static int n;
    static int arr[];
}