import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	n = Integer.parseInt(br.readLine());
    	for(int y= 0; y<52; y++) {
    		for(int x=0; x<52; x++) {
    			dist[y][x] = INF;
    			if(y == x)
    				dist[y][x] = 0;
    		}
    	}
    	for(int i = 0; i < n; i++) {
    		String line[] = br.readLine().split(" ");
    		char u = line[0].charAt(0);
    		char v = line[line.length - 1].charAt(0);
    		dist[toInt(u)][toInt(v)] = 1;
    	}
    	
    	for(int k = 0; k < 52; k++) {
    		for(int a= 0; a<52; a++) {
    			for(int b=0; b<52; b++) {
    				dist[a][b] = Math.min(dist[a][b], dist[a][k] + dist[k][b]);
    			}
    		}
    	}
    	
    	List<String> ans = new ArrayList<>();
    	for(int y=0;y < 52; y++) {
    		for(int x=0; x< 52; x++) {
    			if(y != x && dist[y][x] != INF) {
    				ans.add(toChar(y)+" => "+toChar(x));
    			}
    		}
    	}
    	StringBuilder sb = new StringBuilder();
    	sb.append(ans.size()).append('\n');
    	
    	for(String str : ans) {
    		sb.append(str).append('\n');
    	}
    	System.out.println(sb);

    }
    
    static int n;
    static int dist[][] = new int[52][52];
    
    static char toChar(int num) {
    	if(num >= 26)
    		num = num - 26 + 'a';
    	else
    		num += 'A';
    	return (char)num;
    }
    
    static int toInt(char ch) {
    	int idx = ch - 'A';
    	if(idx > 26)
    		idx = idx - 32 + 26;
    	
    	return idx;
    }
    static int INF = (int)1e9;
}