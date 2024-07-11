import java.util.*;
import java.io.*;

public class Main {
	static int n;
	static int arr[];
	static boolean visited[];
	static List<Integer> result = new ArrayList<>();
	static void dfs(int cur, int target) {
		if(!visited[arr[cur]]) {
			visited[arr[cur]] = true;
			dfs(arr[cur], target);
			visited[arr[cur]] = false;
		}
		
		if(arr[cur] == target) {
			result.add(target);
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n+1];
		for(int i = 1; i<=n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		visited = new boolean[n+1];
		
		for(int i = 1; i<=n; i++) {
			visited[i] = true;
			dfs(i,i);
			visited[i] = false;
		}
		
		Collections.sort(result);
		StringBuilder sb = new StringBuilder();
		
		sb.append(result.size()).append('\n');
		for(int num : result)
			sb.append(num).append('\n');
		System.out.println(sb);
	}
}