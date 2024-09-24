import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        d = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        oven = new int[d+1];
        for(int idx = 1; idx <= d; idx++) {
        	int radius = Integer.parseInt(st.nextToken());
        	oven[idx] = Math.min(prev,  radius);
        	prev = oven[idx];
        	
        }
        arr = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i<=n; i++) {
        	arr[i] = Integer.parseInt(st.nextToken());
        }
        
        int last = d;
        for(int i = 1; i<=n; i++) {
        	int cur = arr[i];
        	while(last >=0 && cur > oven[last--]);
        }
        System.out.println(last + 1);
    }
    
    
    static int d,n;
    static StringTokenizer st;
    static int arr[];
    static int oven[];
    static int prev = Integer.MAX_VALUE;
}