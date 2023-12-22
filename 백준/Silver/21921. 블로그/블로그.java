import java.io.*;
/**
 * dp
 */
import java.util.*;
public class Main {
	static int n,x;
	static StringTokenizer st;
	static int arr[];
	public static void main(String[] args) throws IOException{
	 	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	 	st = new StringTokenizer(br.readLine());
	 	n = Integer.parseInt(st.nextToken());
	 	x = Integer.parseInt(st.nextToken());
	 	arr = new int[n+1];
	 	st = new StringTokenizer(br.readLine());
	 	for(int i = 1; i<=n; i++) {
	 		arr[i] = Integer.parseInt(st.nextToken());
	 	}
	 	
	 	int sum = 0;
	 	for(int i = 1; i<= x; i++) {
	 		sum += arr[i];
	 	}
	 	
	 	int ans = sum;
	 	ArrayList<Integer> list = new ArrayList<>();
	 	list.add(sum);
	 	int l = 1;
	 	for(int r = x + 1; r <=n; r++) {
	 		sum += arr[r];
	 		sum -= arr[l];
	 		list.add(sum);
	 		ans = Math.max(ans, sum);
	 		l++;
	 	}
	 	if(ans == 0) {
	 		System.out.println("SAD");
	 	}else {
	 		int cnt = 0;
	 		for(int num : list) {
	 			if(num == ans)
	 				cnt++;
	 		}
	 		System.out.println(ans+"\n"+cnt);
	 	}
	}
}