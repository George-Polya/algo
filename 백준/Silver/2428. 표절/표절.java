import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       n = Integer.parseInt(br.readLine());
       arr = new int[n];
       st = new StringTokenizer(br.readLine());
       for(int i = 0; i < n ;i++) {
    	   arr[i] = Integer.parseInt(st.nextToken());
       }
       Arrays.sort(arr);
       
       int left = 0;
       int right = 0;
       
       long ans = 0;
       while(left < n) {
    	   int a = arr[left];
    	   
    	   while(true) {
    		   
    		   if(right >= n-1)
    			   break;
    		   
    		   int b = arr[right + 1];
    		   
    		   if(a < b * 0.9) {
    			   break;
    		   }else {
    			   right++;
    		   }
    	   }
    	   
    	   ans += right - left;
    	   left++;
       }
       System.out.println(ans);
    }
    static int n;
    static int arr[];
    static StringTokenizer st;
}