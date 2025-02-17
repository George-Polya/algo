import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	target = Integer.parseInt(br.readLine());
    	st = new StringTokenizer(br.readLine());
    	N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());
    	A = new int[N+1];
    	B = new int[M+1];
    	
    	for(int i = 1; i<=N; i++) {
    		A[i] = Integer.parseInt(br.readLine());
    	}
    	
    	for(int i = 1; i<=M; i++) {
    		B[i] = Integer.parseInt(br.readLine());
    	}
    	
    	
    	TreeMap<Integer,Integer> mapA = init(A);
    	TreeMap<Integer,Integer> mapB = init(B);
    	
    	
    	int maxA = mapA.lastKey();
    	int maxB = mapB.lastKey();
    	
    	mapA.put(maxA, 1);
    	mapB.put(maxB, 1);
    	
    	int ans = 0;
    	
    			
    			
    	for(int key : mapA.keySet()) {
    		if(key < target && mapB.containsKey(target - key)) {
    			ans += mapA.get(key) * mapB.get(target - key);
    		}
    	}
    	
    	
    	if (mapA.containsKey(target))
    		ans += mapA.get(target);
    	
    	if(mapB.containsKey(target))
    		ans += mapB.get(target);
    		
    	
    	System.out.println(ans);
    }
    
    
    static TreeMap<Integer, Integer> init(int arr[]) {
    	TreeMap<Integer, Integer> ret = new TreeMap<>();
    	int len = arr.length - 1;
    	
    	for(int y=1; y<=len; y++) {
    		int sum = 0;
    		for(int x= 0; x < len; x++) {
    			int nxt = (y + x) % len == 0 ? len : (y + x) % len;
    			sum += arr[nxt];
    			ret.put(sum, ret.getOrDefault(sum, 0) + 1);
    		}
    	}
    	
    	return ret;
    	
    }
//    
    static int target, N, M;
    static StringTokenizer st;
    static int A[], B[];
}