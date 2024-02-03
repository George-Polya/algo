import java.io.*;
import java.util.*;
public class Solution {
	static int T = 10;
	static int n;
	static int MAX_N = 100;
	static char arr[] = new char[MAX_N+5];
	static int node[] = new int[2 * MAX_N + 5];
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static void inOrder(int cur) {
		if(node[cur * 2] != 0){
			inOrder(cur * 2);
		}
		
		sb.append(arr[cur]);
		
		if(node[cur * 2 + 1] != 0) {
			inOrder(cur * 2 + 1);
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int tc=1;tc<=T;tc++) {
			n = Integer.parseInt(br.readLine());
			
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				int id = Integer.parseInt(st.nextToken());
				node[id * 2] = 0;
				node[id * 2+1] = 0;
				char ch = st.nextToken().charAt(0);
				arr[id] = ch;
				if(st.hasMoreTokens())
					node[id * 2] = Integer.parseInt(st.nextToken());
				if(st.hasMoreTokens())
					node[id * 2 + 1] = Integer.parseInt(st.nextToken());
			}
			
			sb.append("#").append(tc).append(' ');
			inOrder(1);
			sb.append('\n');
		}
		System.out.println(sb);
	}
	
}