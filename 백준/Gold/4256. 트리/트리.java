import java.util.*;
import java.io.*;
public class Main {
	static int T,n, pre[], in[], tree[];
	static StringTokenizer st;
	static int root;
	
	static int findIdxFromIn(int value, int left, int right) {
		for(int i = left; i<=right; i++) {
			if(in[i] == value)
				return i;
		}
		return -1;
	}
	
	static void solve(int preL, int preR, int inL, int inR) {
		
		if( preL > preR)
			return;
		
		if(preL == preR) {
			tree[root++] = pre[preL];
			return;
		}
		
		int idx = 0;
		for(int i = inL; i <= inR; i++) {
			if(pre[preL] == in[i])
				idx = i;
		}
		
		int leftSize = idx - inL;
		int rightSize = inR - idx;
		
		solve(preL + 1, preL + leftSize, inL, idx-1);
		solve(preR - rightSize +1 ,preR, idx+1, inR);
		tree[root++] = pre[preL];
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int tc=1;tc<=T;tc++) {
			n = Integer.parseInt(br.readLine());
			pre = new int[n+1];
			in = new int[n+1];
			tree = new int[n+1];
			root = 0;
			
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i<=n; i++) {
				pre[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i<=n; i++) {
				in[i] = Integer.parseInt(st.nextToken());
			}
			
			root = 1;
			solve(1,n,1,n);
//			System.out.println(Arrays.toString(tree));
			for(int i = 1; i<=n;i++)
				sb.append(tree[i]).append(' ');
			sb.append('\n');
		}
		System.out.println(sb);
	}
}