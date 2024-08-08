import java.io.*;
import java.util.*;


public class Solution {
	static int T,n, arr[];
	static StringTokenizer st;
	public static void main(String[] args) throws IOException{
//		System.setIn(new FileInputStream("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int tc=1;tc<=T;tc++) {
			n = Integer.parseInt(br.readLine());
			arr = new int[n];
			
			int aIdx = -1;
			int bIdx = -1;
			int idx = -1;
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i<n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				if(arr[i] > 0) {
					if(aIdx <0)
						aIdx = i;
					else if(bIdx < 0)
						bIdx = i;
				}
				
				if(arr[i] == 1 && idx <0)
					idx = i;
			}
			
			sb.append("#").append(tc).append(' ');
			
			if(bIdx < 0 ) {
				for(int i = 0; i <arr[aIdx]; i++) {
					sb.append(aIdx+1).append(' ').append(-aIdx - 1).append(' ');
				}
			}else if(idx >= 0) {
				
				sb.append(idx+1).append(' ');
				for(int i = 0; i <n;i++) {
					if(i == idx)
						continue;
					for(int j = 0; j < arr[i];j++)
						sb.append(i+1).append(' ').append(-i-1).append(' ');
				}
				sb.append(-idx - 1).append(' ');
				
			}else {
				sb.append(aIdx + 1).append(' ');
				for(int i =1; i< arr[bIdx];i++)
					sb.append(bIdx + 1).append(' ').append(-bIdx-1).append(' ');
				
				sb.append(-aIdx - 1).append(' ');
				
				arr[aIdx]--;
				sb.append(bIdx + 1).append(' ');
				
				for(int i = 0; i < n ;i++) {
					if(i == bIdx)
						continue;
					
					for(int j = 0; j < arr[i];j++)
						sb.append(i+1).append(' ').append(-i-1).append(' ');
						
				}
				sb.append(-bIdx - 1).append(' ');
				
			}
			
			sb.append('\n');
		}
		System.out.println(sb);
	}
}