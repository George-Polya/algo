import java.util.*;
import java.io.*;

public class Main {
	static int n,d,k,c;
	static StringTokenizer st;
	static int arr[];
	static ArrayDeque<Integer> deq = new ArrayDeque<>();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		arr = new int[n+1];
		for(int i = 1; i<=n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		int end = k;
		Map<Integer,Integer> map = new HashMap<>();
		
		for(int i = 1; i<=end;i++) {
			map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
		}
		
		map.put(c, map.getOrDefault(c,0)+1);
		
		int ans = 0;
		for(int start = 1; start<=n; start++) {
//			System.out.println(map);
			ans = Math.max(ans, map.size());
			end++;
			if(end > n)
				end = 1;
			
			map.put(arr[start], map.get(arr[start]) - 1);
			if(map.get(arr[start]) == 0)
				map.remove(arr[start]);
			map.put(arr[end], map.getOrDefault(arr[end], 0)+1);
			
		}
		
		System.out.println(ans);
	}
}