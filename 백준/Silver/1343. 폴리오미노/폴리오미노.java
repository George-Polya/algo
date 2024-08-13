import java.util.*;
import java.io.*;

public class Main {
	static String str;
	static int n;
	static boolean hasDot(int start, int end) {
		if(end >= n)
			return true;
		for(int i = start; i<=end;i++) {
			if(str.charAt(i) == '.')
				return true;
		}
		return false;
	}
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine();   
        n = str.length();
        
        int idx = 0;
        String ret = "";
        
        while(idx < n) {
        	if(str.charAt(idx) == 'X') {
        		if(!hasDot(idx, idx+3)) {
        			ret += "AAAA";
        			idx = idx + 4;
        		}else if(!hasDot(idx, idx+1)) {
        			ret += "BB";
        			idx = idx + 2;
        		}else {
        			idx++;
        		}
        	}else {
        		ret += ".";
        		idx++;
        	}
        }
        
        System.out.println(ret.length() != str.length() ? -1 : ret);
    }
}