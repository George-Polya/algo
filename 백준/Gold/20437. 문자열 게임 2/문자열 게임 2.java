import java.util.*;
import java.io.*;

public class Main {
	static int T;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			String str = br.readLine();
			int k = Integer.parseInt(br.readLine());
			if(k == 1) {
				sb.append("1 1").append('\n');
				continue;
			}
			
			int count[] = new int[26];
			int n = str.length();
			for(int i =0; i < n ;i++) {
				count[str.charAt(i) - 'a']++;
			}
			
			int min = Integer.MAX_VALUE;
			int max = -1;
			
			for(int i = 0; i < n ;i++) {
				if(count[str.charAt(i) - 'a'] < k)
					continue;
				int cnt = 1;
				for(int j = i + 1; j < n; j++) {
					if(str.charAt(i) == str.charAt(j))
						cnt++;
					if(cnt == k) {
						min = Math.min( min, j - i  + 1);
						max = Math.max(max, j -i + 1);
						break;
					}
				}
			}
			
			if(min == Integer.MAX_VALUE || max == -1)
				sb.append("-1\n");
			else
				sb.append(min).append(' ').append(max).append('\n');
			
		}
		System.out.println(sb);
	}
}