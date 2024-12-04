import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	m = Integer.parseInt(st.nextToken());
    	
    	uf = new int[n+1];
    	for(int i = 1; i<=n; i++) {
    		uf[i] = -1;
    	}
    	
    	int cnt = 0;
    	for(int i = 0 ; i < m ;i++) {
    		st = new StringTokenizer(br.readLine());
    		int u = Integer.parseInt(st.nextToken());
    		int v = Integer.parseInt(st.nextToken());
    		if(!union(u,v)) // 사이클 발생 
    			cnt++;
    	}
    	
//    	System.out.println(Arrays.toString(uf));
    	for(int i = 1; i<=n; i++) {
    		if(uf[i] < 0)
    			cnt++;
    	}
    	
    	cnt--;
    	System.out.println(cnt);
    }
    
    static int find(int x) {
    	if(uf[x] < 0)
    		return x;
    	
    	return uf[x] = find(uf[x]);
    }
    
    static boolean union(int x,int y) {
    	x = find(x);
    	y = find(y);
    	
    	if(x == y)
    		return false;
    	uf[y] = x;
    	return true;
    }
    static StringTokenizer st;
    static int n,m;
    static int uf[];
}