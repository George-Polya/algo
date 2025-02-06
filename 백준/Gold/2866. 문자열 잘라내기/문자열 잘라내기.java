import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	st = new StringTokenizer(br.readLine());
    	R = Integer.parseInt(st.nextToken());
    	C = Integer.parseInt(st.nextToken());
    	
    	board = new char[R][C];
    	
    	for(int y=0;y <R ;y++) {
    		board[y] = br.readLine().toCharArray();
    	}
    	
    	int l = 0;
    	int r = R;
    	int ans = -1;
    	while(l<=r) {
    		int mid = (l+r)/2;
    		
    		if(decide(mid)) {
    			l = mid + 1;
    			ans = mid;
    		}else {
    			r = mid - 1;
    		}
    	}
    	
    	System.out.println(ans);
    	
    }
    
    static boolean decide(int cnt) {
    	Set<String> hs = new HashSet<>();
//    	System.out.println("----");
//    	System.out.println("cnt: "+cnt);
    	for(int x = 0; x< C;x++) {
    		String str = "";
    		for(int y=cnt; y < R; y++)
    			str += board[y][x];
//    		System.out.println(str);
    		if(hs.contains(str))
    			return false;
    		
    		hs.add(str);
    	}
    	return true;
    }
    
    static int R,C;
    static StringTokenizer st;
    static char board[][];
}