import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       st = new StringTokenizer(br.readLine());
       n = Integer.parseInt(st.nextToken());
       d = Integer.parseInt(st.nextToken());
       k = Integer.parseInt(st.nextToken());
       c = Integer.parseInt(st.nextToken());
       
       arr = new int[n];
       for(int i = 0; i < n; i++) {
    	   arr[i] = Integer.parseInt(br.readLine());
       }
       
       
       sushi = new int[d+1];
       int cnt = 0;
       int maxCnt = 0;
       for(int i = 0; i < k; i++) {
    	   if(sushi[arr[i]] == 0)
    		   cnt++;
    	   sushi[arr[i]]++;
       }
       
       maxCnt = cnt + (sushi[c] == 0 ? 1 : 0);
       
//       System.out.println(map);
       for(int s = 1; s<n; s++) {
    	   int prv = arr[s-1];
    	   sushi[prv]--;
    	   if(sushi[prv] == 0)
    		   cnt--;
    	   
    	   int e = (s + k - 1) % n;
    	   int nxt = arr[e];
    	   if(sushi[nxt] == 0)
    		   cnt++;
    	   sushi[nxt]++;
    	   
    	   maxCnt = Math.max(maxCnt, cnt + (sushi[c] == 0 ? 1 : 0));
       }
       
       System.out.println(maxCnt);
       
       
       
       
    }
    static StringTokenizer st;
    static int n,d,k,c;
    static int arr[],sushi[];
}