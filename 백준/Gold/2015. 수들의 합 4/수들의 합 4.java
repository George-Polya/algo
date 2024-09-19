import java.io.*;
import java.util.*;

public class Main {
	static int n,k;
	static int pSum[];
	static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        pSum = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i<=n; i++) {
        	pSum[i] = pSum[i-1] + Integer.parseInt(st.nextToken());
        }
        
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        long ans = 0;
        
        for(int i = 1; i<=n; i++) {
        	ans += map.getOrDefault(pSum[i] - k, 0);
        	map.put(pSum[i], map.getOrDefault(pSum[i], 0) + 1);
        }
        
        System.out.println(ans);
    }
}