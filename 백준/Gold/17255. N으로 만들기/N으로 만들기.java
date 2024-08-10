import java.io.*;
import java.util.*;


public class Main {
	static char[] arr;
	static Set<String> set = new HashSet<>();
	static void dfs(int left, int right, String s, String path) {
		if(left == 0 && right == arr.length-1) {
			set.add(path);
			return;
		}
		
		if(left - 1 >=0) {
			dfs(left - 1, right, arr[left-1] + s, path+" "+arr[left-1] + s);
		}
		
		if(right + 1< arr.length) {
			dfs(left, right + 1, s + arr[right+1], path+" "+s+arr[right+1]);
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		arr = br.readLine().toCharArray();
		
		for(int i = 0; i < arr.length;i++) {
			dfs(i,i,""+arr[i], ""+arr[i]);
		}
//		System.out.println(set);
		System.out.println(set.size());
	}
}