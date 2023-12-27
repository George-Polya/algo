import java.io.*;
import java.util.*;
/**
 * 중복없이 m개를 골라서 줄을 세운다 
 * nCm * m! = 순
 * 비트마스킹을 이용해서 풀어보자 
 */
public class Main {
	static int n,m;
	static int arr[];
	static StringTokenizer st;
	static int selected[];
	static StringBuilder sb = new StringBuilder();
	static void solve(int cnt, int bit) {
		if(cnt == m) {
			for(int i = 0; i < m ;i++) {
				sb.append(selected[i]).append(' ');
			}
			sb.append('\n');
			return;
		}
		
		for(int i =0; i < n ;i++) {
			if(((bit >> i) & 1) == 1)
				continue;
			selected[cnt] = arr[i];
			solve(cnt + 1, bit | (1 << i));
		}
		
	}
	public static void main(String[] args) throws IOException{
	 	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	 	st = new StringTokenizer(br.readLine());
	 	n = Integer.parseInt(st.nextToken());
	 	m = Integer.parseInt(st.nextToken());
	 	arr = new int[n];
	 	for(int i = 0;i < n ;i++) {
	 		arr[i] = i + 1;
	 	}
	 	selected = new int[m];
	 	solve(0, 0);
	 	System.out.println(sb);
	}
}