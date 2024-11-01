import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n+1];
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i<=n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Stack<Building> stk = new Stack<>();
		cnt = new int[n+1];
		near = new int[n+1][2];
		for(int row[] : near) {
			Arrays.fill(row, Integer.MAX_VALUE / 2);
		}
		for(int i = 1; i<=n; i++) {
			while(!stk.isEmpty() && stk.peek().height <= arr[i])
				stk.pop();
			
			cnt[i] += stk.size();
			
			if(!stk.isEmpty()) {
				Building peek = stk.peek();
				int gap = Math.abs(peek.idx - i);
				
				if(gap < near[i][0]) {
					near[i][0] = gap;
					near[i][1] = peek.idx;
				}else if(gap == near[i][0] && peek.idx < near[i][1]) {
					near[i][1] = peek.idx;
				}
			}
			
			stk.push(new Building(i, arr[i]));
		}
		
		stk = new Stack<>();
		
		for(int i = n; i>= 1; i--) {
			while(!stk.isEmpty() && stk.peek().height <= arr[i])
				stk.pop();
			
			cnt[i] += stk.size();
			if(!stk.isEmpty()) {
				Building peek = stk.peek();
				int gap = Math.abs(peek.idx - i);
				
				if(gap < near[i][0]) {
					near[i][0] = gap;
					near[i][1] = peek.idx;
				}else if(gap == near[i][0] && peek.idx < near[i][1]) {
					near[i][1] = peek.idx;
				}
			}
			
			stk.push(new Building(i, arr[i]));
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i<=n; i++) {
			if(cnt[i] == 0)
				sb.append(0).append('\n');
			else {
				sb.append(cnt[i]).append(' ').append(near[i][1]).append('\n');
			}
		}
		System.out.println(sb);
		
	}
	static StringTokenizer st;
	static int arr[],n, cnt[], near[][];
	static class Building{
		int idx, height;
		public Building(int idx, int height) {
			this.idx = idx;
			this.height = height; 
		}
	}
}