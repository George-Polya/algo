import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i<=n; i++) {
        	arr[i] = Integer.parseInt(st.nextToken());
        }
        
        int L = 1;
        int R = n;
        int ans = Integer.MAX_VALUE;
        while(L<R) {
        	int sum = arr[L] + arr[R];
        	
        	if(Math.abs(ans) > Math.abs(sum)) {
        		ans = sum;
        	}
        	
        	
        	if(sum < 0)
        		L++;
        	else
        		R--;
        }
        
        System.out.println(ans);
    }
    static StringTokenizer st;
    static int n;
    static int arr[];
}