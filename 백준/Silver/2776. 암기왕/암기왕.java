import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        
        StringBuilder sb = new StringBuilder();
        for(int tc=1; tc<=T;tc++) {
        	set = new HashSet<>();
        	n = Integer.parseInt(br.readLine());
        	st = new StringTokenizer(br.readLine());
        	
        	for(int i = 0; i < n; i++) {
        		set.add(Integer.parseInt(st.nextToken()));
        	}
        	
        	m = Integer.parseInt(br.readLine());
        	st = new StringTokenizer(br.readLine());
        	for(int i = 0; i < m ;i++) {
        		sb.append(set.contains(Integer.parseInt(st.nextToken())) ? 1 : 0).append('\n');
        	}
        			
        }
        System.out.println(sb);
    }
    static int T, n, m;
    static StringTokenizer st;
    static HashSet<Integer> set;
}