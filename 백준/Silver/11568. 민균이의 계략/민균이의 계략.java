import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n+1];
		st= new StringTokenizer(br.readLine());
		for(int i = 1; i<=n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
//		dp = new int[n+1];
//		for(int i = 1; i<=n; i++) {
//			for(int j = 0; j < i; j++) {
//				if(arr[j] < arr[i]) {
//					dp[i] = Math.max(dp[i],dp[j] + 1);
//				}
//			}
//		}
		
//		System.out.println(Arrays.toString(dp));

		ArrayList<Integer> dp2 = new ArrayList<>();
		
		dp2.add(arr[1]);
		for(int i =1;i<=n; i++) {
			if(arr[i] > dp2.get(dp2.size() - 1)) {
				dp2.add(arr[i]);
			}else {
				int idx = lowerBound(dp2, arr[i]);
				dp2.set(idx, arr[i]);
			}
		}
		
//		System.out.println(dp2);
		System.out.println(dp2.size());
	}	
	static int lowerBound(ArrayList<Integer> dp, int target) {
		int l = 0;
		int r = dp.size() - 1;
		
		int ret = last + 1;
		
		while(l<=r) {
			int mid = (l + r)/ 2;
			
			if(target <= dp.get(mid)) {
				r = mid - 1;
				ret = mid;
			}else {
				l = mid + 1;
			}
		}
		
		return ret;
		
	}
	static int last;
	static int n;
	static int arr[], dp[];
	static StringTokenizer st;
}