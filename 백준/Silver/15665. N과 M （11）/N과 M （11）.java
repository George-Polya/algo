import java.io.*;
import java.util.*;

public class Main {
	static int n,m;
	static StringTokenizer st;
	static TreeSet<Integer> set = new TreeSet<>();
	static int selected[];
	static void solve(int cur, int cnt) {
		if(cnt == m) {
			
			StringBuilder sb= new StringBuilder();
			for(int i = 0; i <m ;i++)
				sb.append(selected[i]).append(' ');
			System.out.println(sb);
			return;
		}
		
		for(int num : set) {
			selected[cur] = num;
			solve(cur + 1, cnt + 1);
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		selected = new int[m];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			set.add(Integer.parseInt(st.nextToken()));
		}
		
		solve(0,0);
	}
}