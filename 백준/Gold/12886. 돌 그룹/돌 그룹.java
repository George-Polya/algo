import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        
        System.out.println(solve(A,B,C) ? 1 : 0);
    }
    
    static int[] parse(String str) {
    	st = new StringTokenizer(str,"#");
    	int a = Integer.parseInt(st.nextToken());
    	int b = Integer.parseInt(st.nextToken());
    	int c = Integer.parseInt(st.nextToken());
    	return new int[] {a,b,c};
    }
    
    static boolean solve(int a, int b, int c) {
    	Queue<String> q = new ArrayDeque<>();
    	Set<String> visited =  new HashSet<>();
    	String state = makeState(a,b,c);
    	visited.add(state);
    	q.add(state);
    	
    	while(!q.isEmpty()) {
    		String cur = q.poll();
    		int parsed[] = parse(cur);
    		
    		a = parsed[0];
    		b = parsed[1];
    		c = parsed[2];
    		if(a == b && b == c)
    			return true;
    		
    		for(String nxt : getNxts(parsed[0], parsed[1], parsed[2])) {
    			if(visited.contains(nxt))
    				continue;
    			visited.add(nxt);
    			q.add(nxt);
    		}
    	}
    	
    	return false;
    }
    
    static String makeState(int a, int b,int c) {
    	return a+"#"+b+"#"+c;
    }
    
    static List<String> getNxts(int a,int b, int c){
    	List<String> ret = new ArrayList<>();
    	
    	if(a > b)
    		ret.add(makeState(a-b, b+b, c));
    	if(a < b)
    		ret.add(makeState(a+a,b-a,c));
    	
    	if(b > c)
    		ret.add(makeState(a,b-c,c+c));
    	if(b < c)
    		ret.add(makeState(a,b+b,c-b));
    	
    	if(c > a)
    		ret.add(makeState(a+a,b,c-a));
    	if(c < a)
    		ret.add(makeState(a-c,b,c+c));
    	
    	return ret;
    	
    }
    

    static StringTokenizer st;
    static int A,B,C;
}