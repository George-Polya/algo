import java.util.*;
import java.io.*;



public class Main {
	static int n,k;
	static int arr[];
	static StringTokenizer st;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		arr = new int[n];
		st = new StringTokenizer(br.readLine());
		
		HashMap<Integer,Integer> map = new HashMap<>();
		for(int i = 0; i < n ;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			map.put(arr[i], 0);
		}
		
		
		int answer = 0;
		int len = 0;
		int r = 0;
		
		
		
		for(int l = 0; l<n;l++) {
			
			while(r < n && map.get(arr[r]) < k) {
				map.put(arr[r], map.getOrDefault(arr[r], 0) + 1);
				len++;
				r++;
			}
			
//			System.out.println(map);
			answer = Math.max(answer, len);
			
			map.put(arr[l], map.get(arr[l]) - 1);
			len--;
		}
		System.out.println(answer);
		
	}
}