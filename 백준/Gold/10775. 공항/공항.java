import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        g = Integer.parseInt(br.readLine());
        p = Integer.parseInt(br.readLine());
        
        uf = new int[g+1];
        for(int i = 1; i<=g;i++)
        	uf[i] = i;
        
        int ans = 0;
        for(int i = 1; i<=p;i++) {
        	int gate = Integer.parseInt(br.readLine());
        	
        	int G = find(gate);
        	
        	if(G == 0)
        		break;
        	
        	union(G, G-1);
        	ans++;
        }
        System.out.println(ans);
    }
    
    static int find(int x) {
    	if(x == uf[x])
    		return x;
    	
    	return uf[x] = find(uf[x]);
    }
    
    static void union(int x,int y) {
    	x = find(x);
    	y = find(y);
    	if(x != y) {
    		uf[x] = y;
    	}
    }
    
    static int g,p;
    static int uf[];
}