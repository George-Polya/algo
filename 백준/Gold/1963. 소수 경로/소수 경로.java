import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        init();
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int t = 0; t<T; t++) {
        	st = new StringTokenizer(br.readLine());
        	String A = st.nextToken();
        	String B = st.nextToken();
        	
        	Queue<String> q = new ArrayDeque<>();
        	Map<String, Integer> visited = new HashMap<>();
        	visited.put(A, 0);
        	q.add(A);
//        	System.out.println(getNxt(1,4,A));
        	while(!q.isEmpty()) {
        		String cur = q.poll();
        		
        		if(visited.containsKey(B)) {
        			break;
        		}
        		
        		for(int i = 0; i <4 ;i++) {
        			for(int num = 0; num<10; num++) {
        				if(num ==0 && i == 0)
        					continue;
        				if(num == toInt(cur.charAt(i)))
        					continue;
        				
        				String nxt = getNxt(i, num, cur);
        				
        				if (primes.contains(nxt) && !visited.containsKey(nxt)) {
        					q.add(nxt);
        					visited.put(nxt, visited.get(cur) + 1);
        				}
        			}
        		}
        	}
        	sb.append(visited.containsKey(B) ? visited.get(B) : "Impossible").append('\n');
        }
        System.out.println(sb);
        
    }
    
    static String getNxt(int idx, int num, String str) {
    	return str.substring(0, idx) + String.valueOf(num) + str.substring(idx+1, str.length()); 
    }
    
    static int toChar(int num) {
    	return (char)(num+'0');
    }
    
    static int toInt(char ch) {
    	return ch - '0';
    }
    
    static StringTokenizer st;
    
    static void init() {
    	states = new boolean[MAX_R];
    	Arrays.fill(states, true);
    	states[1] = false;
    	
    	for(int i = 2; i*i<MAX_R;i++) {
    		if(!states[i])
    			continue;
    		
    		for(int j = i*i; j<MAX_R; j+=i)
    			states[j] = false;
    	}
    	
    	for(int i = 1000; i<MAX_R;i++) {
    		if(states[i])
    			primes.add(String.valueOf(i));
    	}
    
    }
    
    static Set<String> primes= new TreeSet<>();
    static int MAX_R = 10000;
    static boolean states[];
}