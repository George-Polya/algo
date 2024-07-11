import java.util.*;
import java.io.*;

public class Main {
	static StringTokenizer st;
	static int arr[];
	static int n;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr= new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i <n ;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int l = 0;
		int r = n - 1;
		int ans = 0;
		while(l<=r) {
			int score = (r - l -1) * Math.min(arr[l], arr[r]);
			ans = Math.max(ans,  score);
			if(arr[l] < arr[r])
				l++;
			else
				r--;
		}
		System.out.println(ans);
	}
}