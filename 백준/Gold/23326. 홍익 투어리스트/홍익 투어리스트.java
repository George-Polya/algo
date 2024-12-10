import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        for(int i=  1; i<=N; i++) {
        	boolean flag = Integer.parseInt(st.nextToken()) == 1 ? true : false;
        	if(flag) {
        		ts.add(i);
        		ts.add(i + N);
        	}
        }
        
        for(int q = 1; q<=Q; q++) {
        	st = new StringTokenizer(br.readLine());
        	int cmd = Integer.parseInt(st.nextToken());
        	switch(cmd) {
        	case 1:{
        		int idx = Integer.parseInt(st.nextToken());
        		oper1(idx);
        	}
        		break;
        	case 2:{
        		int x = Integer.parseInt(st.nextToken());
        		oper2(x);
        	}
        		break;
        	case 3:
        		oper3();
        		break;
        	}
        }
        
        System.out.println(sb);
        
    }
    static int cur = 1;
    static StringBuilder sb = new StringBuilder();
    static void oper3() {
    	Integer upper = ts.ceiling(cur);
    	if(upper == null) {
    		sb.append(-1).append('\n');
    		return;
    	}
    
//    	System.out.printf("cur: %d, upper: %d\n", cur, upper);
    	int diff = upper - cur;
    	sb.append(diff % N == 0 ? 0 : diff).append('\n');
    }
    
    static void oper2(int x) {
    	cur = (cur - 1 + x) % N + 1;
//    	System.out.println("after oper2");
//    	System.out.println("cur: "+cur);
    }
    
    static void oper1(int idx) {
    	if(ts.contains(idx)) {
    		ts.remove(idx);
    		ts.remove(idx + N);
    	}else {
    		ts.add(idx);
    		ts.add(idx + N);
    	}
//    	System.out.println("after oper1");
//    	System.out.println(ts);
    }
    static StringTokenizer st;
    static int N,Q;
    static TreeSet<Integer> ts = new TreeSet<>();
}