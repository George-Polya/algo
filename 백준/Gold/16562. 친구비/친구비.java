import java.io.*;
import java.util.*;


public class Main {
	static StringTokenizer st;
	static int n,m,k;
	static int uf[], arr[];
	
	static int find(int x) {
		if(x == uf[x])
			return x;
		return uf[x] = find(uf[x]);
	}
	
	static void union(int a,int b) {
		int A = find(a);
		int B = find(b);
		
		if(A == B)
			return;
		
//		uf[A] = B;
		uf[B] = A;
		arr[A] = Math.min(arr[A], arr[B]);
		arr[B] = 0;
		
		
	}
	
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		
		uf = new int[n+1];
		arr = new int[n+1];
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i<=n; i++) {
			uf[i] = i;
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		
		for(int i = 0; i < m ;i++) {
			st= new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			union(a,b);
		}
//			System.out.println(Arrays.toString(uf));
		
		long ans = 0;
		for(int i = 1; i<=n; i++)
			ans += arr[i];
		System.out.println(ans <= k ? ans : "Oh no");
		
	}
}