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
		int left = 0;
		int right = 0;
		int size = list.size();
		while(right < size) {
			if(right - left + 1 == k) {
				int len = list.get(right) - list.get(left) + 1;
				min = Math.min(min, len);
				max = Math.max(max, len);
			}
			
			right++;
			while(right - left + 1 > k) {
				left++;
			}
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