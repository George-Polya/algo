import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        stk.push(0);
        int ans = 0;
        for(int i = 1; i<=n; i++) {
        	st = new StringTokenizer(br.readLine());
        	int x = Integer.parseInt(st.nextToken());
        	int y = Integer.parseInt(st.nextToken());
        	
        	while(stk.peek() > y)
        		stk.pop();
        	
        	if(stk.peek() < y) {
        		stk.push(y);
        		ans++;
        	}
        	
//        	int top = stk.peek();
//        	
//        	while(top > y)
//        		top = stk.pop();
        }
        System.out.println(ans);
    }
    static StringTokenizer st;
    static int n;
    static Stack<Integer> stk = new Stack<>();
}