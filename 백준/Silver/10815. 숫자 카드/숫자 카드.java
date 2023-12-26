import java.io.*;
/**
 * 1) HashSet
 * 2) 이분탐색 << 이걸로 해보자 
 */
import java.util.*;
public class Main {
	static int arr[];
	static int n,m;
	static StringTokenizer st;
	
	static boolean binary_search(int num) {
		int l = 1;
		int r = n;
		
		while(l<=r) {
			int mid = (l + r)/2;
			
			if(arr[mid]== num)
				return true;
			else if(arr[mid] < num) {
				l = mid + 1;
			}else if(arr[mid] > num) {
				r = mid - 1;
			}
		}
		return false;
	}
	
	public static void main(String[] args) throws IOException{
	 	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	 	n = Integer.parseInt(br.readLine());
	 	arr = new int[n+1];
	 	st = new StringTokenizer(br.readLine());
	 	for(int i = 1; i<=n; i++) {
	 		arr[i] = Integer.parseInt(st.nextToken());
	 	}
	 	
	 	Arrays.sort(arr,1,n+1);
	 	
	 	m = Integer.parseInt(br.readLine());
	 	st = new StringTokenizer(br.readLine());
	 	StringBuilder sb = new StringBuilder();
	 	for(int i = 0; i < m; i++) {
	 		int num = Integer.parseInt(st.nextToken());
	 		sb.append(binary_search(num) ? 1 : 0).append(' ');
	 	}
	 	System.out.println(sb);
	}
}