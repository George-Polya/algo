import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
	
	static int T;
	static StringTokenizer st;
	
	static int toNum(char ch) {
		return ch - 'a';
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			st = new StringTokenizer(br.readLine());
			int alphabets[] = new int[26];
			
			while(st.hasMoreTokens()) {
				String str = st.nextToken();
				
				for(int i = 0; i < str.length();i++) {
					char ch = str.charAt(i);
					
					alphabets[toNum(ch)]++;
				}
			}
			
			int _max = 0;
			int idx = 0;
			for(int i = 0; i < 26; i++) {
				if(_max < alphabets[i]) {
					_max = alphabets[i];
					idx = i;
				}
			}
			
			int cnt = 0;
			for(int i = 0; i < 26; i++) {
				if(_max == alphabets[i])
					cnt++;
			}
			
			if(cnt == 1) {
				System.out.println((char)(idx+'a'));
			}else {
				System.out.println("?");
			}
			
		}
	}
}