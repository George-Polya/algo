import java.util.*;
import java.io.*;



public class Main {
	static int k;
	static int list[], arr[];
	static StringTokenizer st;
	static int size;
	
	static void restore(int left, int right, int list[], int idx) {
		if(idx > size)
			return;
		
		int mid = (left+ right) / 2;
		arr[idx] = list[mid];
		
		restore(left, mid - 1, list, idx * 2);
		restore(mid+1, right, list, idx * 2 + 1);
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		k = Integer.parseInt(br.readLine());
		size = (1<<k) - 1 ;
		list = new int[size+1];
		arr = new int[size+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i<=size;i++) {
			list[i] = Integer.parseInt(st.nextToken());
		}
		
		restore(1,size,list,1);
		StringBuilder sb = new StringBuilder();
		
		for(int level = 1; level<=k; level++) {
			int start = 1<<(level-1);
			int end = (1<<level) - 1;
			
			for(int i =start; i<=end;i++) {
				sb.append(arr[i]).append(' ');
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}
}