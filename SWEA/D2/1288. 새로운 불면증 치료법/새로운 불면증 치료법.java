import java.io.*;
import java.util.*;

public class Solution {
	static int T,n;
	static int calc(int bit, int value) {
		while(value != 0) {
			int mod = value % 10;
			value/= 10;
			bit |= (1<<mod);
		}
		return bit;
	}
	
	static boolean check(int bit) {
		return bit == (1 << 10) - 1;
	}
	
	static int getAnswer(int number) {
		int bit = 0 ;
		int k = 1;
		while(true) {
			bit = calc(bit, number * k);
			if(check(bit)) {
				return number * k;				
			}
			k++;
		}
	}
	 public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	T = Integer.parseInt(br.readLine());
    	StringBuilder sb = new StringBuilder();
    	for(int tc=1;tc<=T;tc++) {
    		n = Integer.parseInt(br.readLine());
    		
    		int ans = getAnswer(n);
    		sb.append('#').append(tc).append(' ').append(ans).append('\n');
    	}
    	System.out.println(sb);
    }
}