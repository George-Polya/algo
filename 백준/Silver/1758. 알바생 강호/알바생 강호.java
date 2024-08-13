import java.util.*;
import java.io.*;

public class Main {
	static int n;
	static Integer arr[];
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new Integer[n+1];
        for(int i = 1; i<=n; i++) {
        	arr[i] = Integer.parseInt(br.readLine());
        }
        
        Arrays.sort(arr, 1, n+1, Collections.reverseOrder());
        
        long ans = 0;
        
        for(int i = 1; i<=n; i++) {
        	int value = arr[i] - (i - 1);
        	if(value <= 0)
        		continue;
        	ans += value;
        }
        System.out.println(ans);
   }
}