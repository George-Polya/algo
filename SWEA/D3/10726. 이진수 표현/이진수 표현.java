import java.io.*;
import java.util.*;

public class Solution {
	static int T,n;
	static StringTokenizer st;
	static boolean isOn(int n, int m) {
		int bit = (1 <<n) - 1;
		
		return (m & bit) == bit;
	}
	 public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	T = Integer.parseInt(br.readLine());
    	StringBuilder sb = new StringBuilder();
    	for(int tc=1;tc<=T;tc++) {
    		st = new StringTokenizer(br.readLine());
    		int n = Integer.parseInt(st.nextToken());
    		int m = Integer.parseInt(st.nextToken());
    		
    		sb.append('#').append(tc).append(' ').append(isOn(n,m) ? "ON" : "OFF").append('\n');
    	}
    	System.out.println(sb);
    	
    }
}