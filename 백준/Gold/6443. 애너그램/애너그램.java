import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=T;tc++) {
        	counts = new int[26];
        	
        	String str = br.readLine();
        	len = str.length();
        	for(int i = 0; i < len;i++) {
        		counts[str.charAt(i) - 'a']++;
        	}
        	
        	solve("");
        }
        System.out.println(sb);
    }
    static void solve(String str) {
    	
    	if(str.length() == len) {
    		sb.append(str).append('\n');
    		return;
    	}
    	
    	
    	for(int i = 0; i < 26; i++) {
    		if(counts[i] == 0)
    			continue;
    		counts[i]--;
    		solve(str + (char)(i + 'a'));
    		counts[i]++;
    	}
    }
    
    static int counts[];
    
    static StringBuilder sb = new StringBuilder();
    static int T,n, len;
    static char arr[];
}