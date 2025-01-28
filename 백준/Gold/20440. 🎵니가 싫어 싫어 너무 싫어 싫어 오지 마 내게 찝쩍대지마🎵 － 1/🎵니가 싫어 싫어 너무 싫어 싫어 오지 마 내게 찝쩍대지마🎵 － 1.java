import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	N = Integer.parseInt(br.readLine());
    	for(int i = 0; i < N; i++) {
    		st = new StringTokenizer(br.readLine());
    		int start = Integer.parseInt(st.nextToken());
    		int end = Integer.parseInt(st.nextToken());
    		map.put(start, map.getOrDefault(start, 0) + 1);
    		map.put(end, map.getOrDefault(end, 0) - 1);
    	}
    	
    	int max = 0;
    	int cur = 0;
    	int prev = 0;
    	boolean flag = false;
    			
    	int start = -1;
    	int end = -1;
    	
    	for(int time : map.keySet()) {
    		prev = cur;
    		cur += map.get(time);
    		
    		if(cur > max) {
    			max = cur;
    			flag = true;
    			start = time; 
    		}
    		
    		if(flag && prev == max && cur < max) {
    			end = time;
    			flag = false;
    		}
    	}
    	
    	System.out.println(max+"\n"+start+" "+end);
    }
    static Map<Integer, Integer> map = new TreeMap<>();
    static int N;
    static StringTokenizer st;
}