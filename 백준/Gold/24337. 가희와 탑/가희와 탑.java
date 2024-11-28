import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        
        
        Deque<Integer> deq = new ArrayDeque<>();
        
        StringBuilder sb = new StringBuilder();
        
        for(int i = 1; i< a; i++)
        	deq.add(i);
        
        deq.add(Math.max(a, b));
        
        for(int i = b-1; i>0; i--)
        	deq.add(i);
        
        
        if(deq.size() > n) {
        	System.out.println(-1);
        	return;
        }
        
        int first = deq.pollFirst();
        int size = deq.size();
        
        for(int i = 1; i<= n - size - 1; i++)
        	deq.addFirst(1);
        
        deq.addFirst(first);
        
        for(int i = 1; i<=n; i++)
        	sb.append(deq.pollFirst()).append(' ');
        System.out.println(sb);
   }
    static StringTokenizer st;
    static int n,a,b;
}