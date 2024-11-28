import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        
        towers = new ArrayList<>();
        
        if(a + b > n + 1) {
        	System.out.println(-1);
        	return;
        }
        
        for(int i = 1; i< a; i++)
        	towers.add(i);
        
        towers.add(Math.max(a, b));
        for(int i = b-1; i>=1; i--)
        	towers.add(i);
        
        
        while(towers.size() < n) {
        	if(a == 1)
        		towers.add(1,1);
        	else
        		towers.add(0,1);
        }
        
        StringBuilder sb = new StringBuilder();
        for(int height : towers)
        	sb.append(height).append(' ');
        System.out.println(sb);
   }
    static StringTokenizer st;
    static ArrayList<Integer> towers;
    static int n,a,b;
}