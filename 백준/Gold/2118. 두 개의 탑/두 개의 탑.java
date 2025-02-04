import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	N = Integer.parseInt(br.readLine());
    	dist = new int[N];
    	pSum = new int[2*N+1];

    	int total = 0;
    	for(int i = 0; i<N; i++) {
    		dist[i] = Integer.parseInt(br.readLine());
    		total += dist[i];
    	}
    	
    	
    	for(int i = 0; i < 2*N;i++) {
    		pSum[i+1] = pSum[i] + dist[ i % N];
    	}
    	
    	
    	int e = 0;
    	int ans = 0;
    	for(int s = 0; s<N; s++) {
    		while(e < s + N && pSum[e+1] - pSum[s] <= total/2)
    			e++;
    		
    		ans = Math.max(ans, pSum[e] - pSum[s]);
    	}
    	System.out.println(ans);

    }
    
    static int N;
    static int pSum[],dist[];
    	
}