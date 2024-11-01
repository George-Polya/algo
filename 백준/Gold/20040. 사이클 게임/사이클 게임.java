import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		arr = new int[m+1][2];
		for(int turn = 1; turn<=m; turn++) {
			st = new StringTokenizer(br.readLine());
			arr[turn][0] = Integer.parseInt(st.nextToken());
			arr[turn][1] = Integer.parseInt(st.nextToken());
		}
		uf = new int[n];
		for(int i = 0; i<n;i++) {
			uf[i] = i;
		}
		
		
		for(int turn = 1; turn<=m; turn++) {
			int i = arr[turn][0];
			int j = arr[turn][1];
			
			i = find(i);
			j = find(j);
			
			if(i == j) {
				System.out.println(turn);
				return;
			}
			
			uf[i] = j;
		}
		System.out.println(0);
	}
	
	static int find(int x) {
		if(x == uf[x])
			return x;
		return uf[x] = find(uf[x]);
	}
	
	static int uf[];
	static int n,m;
	static int arr[][];
	static StringTokenizer st;
	
}