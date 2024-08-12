import java.util.*;
import java.io.*;

public class Main {
	static String str1, str2;
	static StringTokenizer st;
	
	static class Tuple{
		long x;
		int a,b;
		public Tuple(long x,int a, int b) {
			this.x = x;
			this.a = a;
			this.b = b;
		}
	}
	
	static int getNum(char ch) {
		if('a' <= ch && ch <='z') {
			return ch - 'a' + 10;
		}
		return ch - '0';
	}
	
	static long convert(String str, int bit) {
		for(int i = 0; i < str.length();i++) {
			char ch = str.charAt(i);
			int num = getNum(ch);
			if(bit <= num)
				return -1;
		}
		
		long ret = 0;
		for(int i = 0; i < str.length();i++) {
			char ch = str.charAt(i);
			int num = getNum(ch);
			ret = ret * bit + num;
		}
		
		return ret;
	}
	
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        str1 = st.nextToken();
        str2 = st.nextToken();
        ArrayList<Tuple> result = new ArrayList<>();
        for(int a = 2; a<=36;a++) {
        	for(int b = 2; b<=36; b++) {
        		if(a == b)
        			continue;
        		
        		long A = convert(str1, a);
        		long B = convert(str2, b);
        		if(A == -1 || B == -1)
        			continue;
        		if(A == B) {
        			result.add(new Tuple(A, a,b));
        		}
        	}
        }
        
        if(result.size() == 1) {
        	Tuple tuple = result.get(0);
        	System.out.println(tuple.x+" "+tuple.a+" "+tuple.b);
        }else if(result.isEmpty()) {
        	System.out.println("Impossible");
        }else if(result.size() > 1) {
        	System.out.println("Multiple");
        }
    }
}