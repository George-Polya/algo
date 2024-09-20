import java.io.*;
import java.util.*;

public class Main {
	static int n,m;
	static int arr[],pSum[];
	static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        long sum = 0;
        arr = new int[n+1];
        st = new StringTokenizer(br.readLine());
        
        long cnt = 0;
        pSum = new int[m];
        for(int i = 1; i<=n; i++) {
        	arr[i] = Integer.parseInt(st.nextToken());
        	sum += arr[i];
        	
        	int mod = (int)(sum % m);
        	
        	if(mod == 0)
        		cnt++;
        	
        	cnt += pSum[mod];
        	pSum[mod]++;
        	
        }
        
        System.out.println(cnt);
        
    }
}