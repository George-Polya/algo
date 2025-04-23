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
    
    static boolean solve(int a, int b, int c) {
    	Queue<Tuple> q = new ArrayDeque<>();
    	Tuple tuple = new Tuple(a,b,c);
    	visited.add(tuple);
    	q.add(tuple);
    	
    	while(!q.isEmpty()) {
    		Tuple cur = q.poll();
    		if(cur.a == cur.b && cur.b == cur.c)
    			return true;
    		
    		for(Tuple nxt : getNxts(cur)) {
    			if(visited.contains(nxt))
    				continue;
    			visited.add(nxt);
    			q.add(nxt);
    		}
    	}
    	
    	return false;
    }
    
    static List<Tuple> getNxts(Tuple cur){
    	List<Tuple> ret = new ArrayList<>();
    	int a = cur.a;
    	int b = cur.b;
    	int c = cur.c;
    	
    	if(a > b)
    		ret.add(new Tuple(a-b, b+b, c));
    	if(a < b)
    		ret.add(new Tuple(a+a,b-a,c));
    	
    	if(b > c)
    		ret.add(new Tuple(a,b-c,c+c));
    	if(b < c)
    		ret.add(new Tuple(a,b+b,c-b));
    	
    	if(c > a)
    		ret.add(new Tuple(a+a,b,c-a));
    	if(c < a)
    		ret.add(new Tuple(a-c,b,c+c));
    	
    	return ret;
    	
    }
    
    static class Tuple{
    	int a,b,c;
    	
    	public Tuple(int a,int b, int c) {
    		this.a = a;
    		this.b = b;
    		this.c = c;
    	}
    	
    	public boolean equals(Object o) {
    		if(this == o)
    			return true;
    		
    		if(o == null)
    			return false;
    		if(getClass() != o.getClass())
    			return false;
    		
    		Tuple t = (Tuple)o;
    		return this.a == t.a && this.b == t.b && this.c == t.c;
    	}
    	
    	public int hashCode() {
    		return Objects.hash(a,b,c);
    	}
    }
    static Set<Tuple> visited = new HashSet<>();
    static StringTokenizer st;
    static int A,B,C;
}