import java.util.*;
import java.io.*;


public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		A = new int[n];
		B = new int[n];
		C = new int[n];
		D = new int[n];
		
		for(int y =0; y<n; y++) {
			st = new StringTokenizer(br.readLine());
			A[y] = Integer.parseInt(st.nextToken());
			B[y] = Integer.parseInt(st.nextToken());
			C[y] = Integer.parseInt(st.nextToken());
			D[y] = Integer.parseInt(st.nextToken());
		}
	
//		Arrays.sort(A);
//		Arrays.sort(B);
//		Arrays.sort(C);
//		Arrays.sort(D);
//		System.out.println(Arrays.toString(A));
//		System.out.println(Arrays.toString(B));
//		System.out.println(Arrays.toString(C));
//		System.out.println(Arrays.toString(D));
		
		AB = new int[n*n];
		CD = new int[n*n];
		
		int idx = 0;
		for(int a =0; a<n; a++) {
			for(int b= 0; b<n ;b++) {
				AB[idx] = A[a] + B[b];
				CD[idx] = C[a] + D[b];
				idx++;
			}
		}
		
		Arrays.sort(AB);
		Arrays.sort(CD);
		
		int l = 0;
		int r = n * n - 1;
		long ans = 0;
		while(l < n * n && r >= 0) {
			int sum = AB[l] + CD[r];
			
			
			if(sum == 0) {
				long cnt1 = 0;
				long cnt2 = 0;
				
				int ab = AB[l];
				int cd = CD[r];
				
				while(l < n * n && AB[l] == ab) {
					cnt1++;
					l++;
				}
				
				while(r >=0 && CD[r] == cd) {
					cnt2++;
					r--;
				}
				ans += cnt1 * cnt2;
			}else if(sum < 0)
				l++;
			else
				r--;
		}
		System.out.println(ans);
		
	};
	static int n;
	static int[] A,B,C,D, AB, CD;
	static StringTokenizer st;
}