import java.io.*;
import java.util.*;

public class Solution {
	static int T,n;
	static StringTokenizer st;
	static TreeSet<Integer> set;
	public static void main(String[] args) throws IOException{
//		System.setIn(new FileInputStream("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int tc=1;tc<=T;tc++) {
			n = Integer.parseInt(br.readLine());
			set = new TreeSet<>();
			
			sb.append('#').append(tc).append(' ');
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				char oper = st.nextToken().charAt(0);
				
				switch(oper) {
				case '1':
					int value = Integer.parseInt(st.nextToken());
					set.add(value);
					break;
					
				case '2':
					if(set.isEmpty())
						sb.append(-1).append(' ');
					else {
						int last = set.pollLast();
						sb.append(last).append(' ');
					}
						
					
					break;
				}
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}
}