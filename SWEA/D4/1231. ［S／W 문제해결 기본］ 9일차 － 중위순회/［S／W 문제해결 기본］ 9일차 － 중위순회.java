import java.util.*;
import java.io.*;

public class Solution {
	static int T = 10;
	static StringTokenizer st;
	static int n;
	static int lefts[], rights[];
	static char tree[];
	static StringBuilder order;
	
	static void inOrder(int cur) {
		int left = lefts[cur];
		int right = rights[cur];
		
		if(tree[left] != '#')
			inOrder(left);
		order.append(tree[cur]);
		
		if(tree[right]!='#')
			inOrder(right);
		
	}
	public static void main(String[] args) throws IOException{
//		System.setIn(new FileInputStream("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for(int tc=1; tc<=T;tc++) {
			n = Integer.parseInt(br.readLine());
			lefts = new int[n + 1];
			rights = new int[n+1];
			tree = new char[2 * n +3];
			Arrays.fill(tree, '#');
			for(int i = 1; i<=n; i++) {
				st = new StringTokenizer(br.readLine());
				int cur = Integer.parseInt(st.nextToken());
				char ch = st.nextToken().charAt(0);
				tree[cur] = ch;
				if(st.hasMoreTokens())
					lefts[cur] = Integer.parseInt(st.nextToken());
				if(st.hasMoreTokens())
					rights[cur] = Integer.parseInt(st.nextToken());
			}
			order = new StringBuilder();
			inOrder(1);
			sb.append("#").append(tc).append(' ').append(order).append('\n');
		}
		System.out.println(sb);
	}
}