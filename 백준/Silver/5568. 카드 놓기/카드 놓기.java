import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
public class Main {
	static int n,k;
	static String arr[];
	static boolean visited[];
	static String selected[];
	static HashSet<String> set = new HashSet<>();
	
	static void solve(int cnt) {
		if(cnt == k) {
			
//			System.out.println(Arrays.toString(selected));
			
			String ret = "";
			for(int i = 0; i < k ;i++)
				ret += selected[i];
			
			set.add(ret);
			
			return;
		}
		
		for(int i = 0; i < n ;i++) {
			if(visited[i])
				continue;
			selected[cnt] = arr[i];
			visited[i] = true;
			solve(cnt + 1);
			visited[i] = false;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		k = Integer.parseInt(br.readLine());
		arr = new String[n];
		selected = new String[k];
		
		for(int i = 0; i <n ;i++) {
			arr[i] = br.readLine();
		}
		visited = new boolean[n];
		
//		System.out.println(Arrays.toString(arr));
		
		solve(0);
		System.out.println(set.size());
		
	}
}