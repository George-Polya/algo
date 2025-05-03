import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		lefts = new PriorityQueue<>(Collections.reverseOrder());
		rights = new PriorityQueue<>(Collections.reverseOrder());
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i<N;i++) {
			int pos = Integer.parseInt(st.nextToken());
			if(pos < 0)
				lefts.add(Math.abs(pos));
			else
				rights.add(pos);
		}
			
		int ans = 0;
		
		if(! lefts.isEmpty() && !rights.isEmpty()) {
			if(lefts.peek() > rights.peek()) {
				ans += lefts.peek();
				for(int i = 0; i < M;i++) {
					lefts.poll();
				}
			}else {
				ans += rights.peek();
				for(int i = 0; i < M; i++) {
					rights.poll();
				}
			}
			ans += solve(lefts) + solve(rights);
		}else if(lefts.isEmpty()) {
			ans += rights.peek();
			for(int i = 0; i < M; i++)
				rights.poll();
			
			ans += solve(rights);
		}else {
			ans += lefts.peek();
			for(int i = 0; i < M; i++)
				lefts.poll();
			ans += solve(lefts);
		}
		
		
		
		System.out.println(ans);
		
	}
	static StringTokenizer st;
	static int N,M;
	static PriorityQueue<Integer> lefts, rights;
	
	static int solve(PriorityQueue<Integer> pq) {
		int ret = 0;
		
		while(!pq.isEmpty()) {
			int farthest = pq.peek();
			for(int i= 0; i<M;i++)
				pq.poll();
			
			ret += 2 * farthest;
	}
		
		return ret;
	}
	
}