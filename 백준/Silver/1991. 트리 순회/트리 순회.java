import java.io.*;
import java.util.*;
/**
 * 1.이진트리 구현 => 배열로 구현 
 * 2. 순회(분할정복)
 */
public class Main {
	static int n;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int lefts[], rights[];
	static void preOrder(int cur) {
		if(cur == -1)
			return;
		
		sb.append((char)(cur + 'A'));
		preOrder(lefts[cur]);
		preOrder(rights[cur]);
	}
	
	static void inOrder(int cur) {
		if(cur == -1)
			return;
		
		inOrder(lefts[cur]);
		sb.append((char)(cur + 'A'));
		inOrder(rights[cur]);
	}
	
	static void postOrder(int cur) {
		if(cur == -1)
			return;
		
		postOrder(lefts[cur]);
		postOrder(rights[cur]);
		sb.append((char)(cur + 'A'));
	}
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		lefts = new int[n];
		rights = new int[n];
		
		for(int i = 0; i< n;i++) {
			lefts[i] = rights[i] = -1;
		}
		for(int i = 1;i <= n;i++) {
			st = new StringTokenizer(br.readLine());
			char root = st.nextToken().charAt(0);
			char left = st.nextToken().charAt(0);
			char right = st.nextToken().charAt(0);
			if(left != '.')
				lefts[root-'A'] = left - 'A';
			if(right != '.')
				rights[root-'A'] = right-'A';
			
		}
		
		
		preOrder(0);
		sb.append('\n');
		inOrder(0);
		sb.append('\n');
		postOrder(0);
		sb.append('\n');
		System.out.println(sb);
	}
}