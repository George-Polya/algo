import java.io.*;
/**
 * 1) HashSet
 * 2) 이분탐색 << 이걸로 해보자 
 */
import java.util.*;
public class Main{
	static int n,m;
	static HashSet<Integer> set = new HashSet<>();
	static StringTokenizer st;
	public static void main(String[] args) throws IOException{
	 	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	 	n = Integer.parseInt(br.readLine());
	 	st = new StringTokenizer(br.readLine());
	 	for(int i = 0; i < n ;i++) {
	 		int num = Integer.parseInt(st.nextToken());
	 		set.add(num);
	 	}
	 	
	 	m = Integer.parseInt(br.readLine());
	 	st = new StringTokenizer(br.readLine());
	 	StringBuilder sb = new StringBuilder();
	 	for(int i = 0; i < m ;i++) {
	 		int num = Integer.parseInt(st.nextToken());
	 		sb.append(set.contains(num) ? 1 : 0).append(' ');
	 	}
	 	System.out.println(sb);
	}
}