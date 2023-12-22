import java.io.*;
/**
 * dp
 */
import java.util.*;
public class Main {
	static int n,m;
	static StringTokenizer st;
	static int a[], b[], arr[];
	public static void main(String[] args) throws IOException{
	 	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	 	st = new StringTokenizer(br.readLine());
	 	n = Integer.parseInt(st.nextToken());
	 	m = Integer.parseInt(st.nextToken());
	 	a = new int[n+1];
	 	b = new int[m+1];
	 	st = new StringTokenizer(br.readLine());
	 	for(int i = 1;i<=n; i++) {
	 		a[i] = Integer.parseInt(st.nextToken());
	 	}
	 	
	 	st = new StringTokenizer(br.readLine());
	 	for(int i = 1;i<=m; i++) {
	 		b[i] = Integer.parseInt(st.nextToken());
	 	}
	 	Arrays.sort(a,1,n+1);
	 	Arrays.sort(b,1,m+1);
	 	
	 	arr = new int[n+m+1];
	 	int r = 1;
	 	int idx = 1;
	 	int l = 1;
	 	for(l =1; l<=n; l++) {
	 		while(r <= m && b[r] < a[l]) {
	 			arr[idx] = b[r];
	 			idx++;
	 			r++;
	 		}
	 		
	 		arr[idx] = a[l];
	 		idx++;
	 	}
//	 	System.out.printf("l: %d, r: %d, idx: %d\n", l,r,idx);
//	 	System.out.println(Arrays.toString(arr));
	 	if( r <= m) {
	 		for(int i = idx; i<=n+m;i++) {
	 			arr[i] = b[r];
	 			r++;
	 		}
	 	}
//	 	System.out.println("----");
//	 	System.out.printf("l: %d, r: %d, idx: %d\n", l,r,idx);
//	 	System.out.println(Arrays.toString(arr));
	 	StringBuilder sb = new StringBuilder();
	 	for(int i = 1; i<=n+m;i++) {
	 		sb.append(arr[i]).append(' ');
	 	}
	 	System.out.println(sb);
	}
}