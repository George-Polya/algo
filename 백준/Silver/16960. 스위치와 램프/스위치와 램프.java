import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	m = Integer.parseInt(st.nextToken());
    	board = new int[n+1][];
    	
    	for(int id= 1; id<=n; id++) {
    		st = new StringTokenizer(br.readLine());
    		int size = Integer.parseInt(st.nextToken());
    		board[id] = new int[size];
    		for(int j = 0; j < size; j++) {
    			board[id][j] = Integer.parseInt(st.nextToken());
    		}
    	}
    	
    	
    	for(int id = 1; id<=n; id++) {
    		if(possible(id)) {
    			System.out.println(1);
    			return;
    		}
    	}
    	System.out.println(0);
    }
    
    static boolean possible(int idx) {
    	boolean isOn[] = new boolean[m+1];
    	int cnt = 0;
    	
    	for(int i = 1; i<=n; i++) {
    		if(i == idx)
    			continue;
    		for(int lamp : board[i]) {
    			if(isOn[lamp])
    				continue;
    			isOn[lamp] = true;
    			cnt++;
    		}
    	}
//    	System.out.println(Arrays.toString(isOn));
    	return cnt == m;
    }
    
    static int board[][];
    static StringTokenizer st;
    static int n,m;
}