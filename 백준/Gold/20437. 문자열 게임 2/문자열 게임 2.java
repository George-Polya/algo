import java.util.*;
import java.io.*;

public class Main {
	static int T;
	static StringBuilder sb = new StringBuilder();
	static List<Integer> alpha[] = new ArrayList[26];
	static int min, max;
	static String str;
	static int k;
	
	static void solve(int idx) {
		List<Integer> list = alpha[idx];
	
		int size = list.size();
		if(size < k)
			return;
		
		for(int i = 0; i <= size - k; i++) {
			int len = list.get(i + k - 1) - list.get(i) + 1;
			max = Math.max(max, len);
			min = Math.min(min, len);
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			str = br.readLine();
			k = Integer.parseInt(br.readLine());
			
			
			for(int i = 0; i < 26;i++) {
				alpha[i] = new ArrayList<>();
			}
			
			min = 10001;
			max = -1;
			int n = str.length();
			for(int i = 0; i < n;i++) {
				char ch = str.charAt(i);
				
				alpha[ch - 'a'].add(i);
			}
			
			for(int i = 0; i < 26;i++) {
//				System.out.printf("%c: %s\n", (char)(i + 'a'), alpha[i]);
				solve(i);
			}
			
			if(min == 10001)
				sb.append(-1).append('\n');
			else
				sb.append(min).append(' ').append(max).append('\n');
		}
		System.out.println(sb);
	}
}