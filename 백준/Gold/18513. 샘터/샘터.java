import java.util.*;
import java.io.*;


public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		Queue<int[]> q = new ArrayDeque<>();
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n ;i++) {
			int value = Integer.parseInt(st.nextToken());
//			long value = Long.parseLong(st.nextToken());
			q.add(new int[] {value, 0});
			visited.add(value);
		}
		
		long total = 0;
		int cnt = 0;
		
		while(!q.isEmpty() && cnt != k) {
			int cur[] = q.poll();
			
			for(int dir : new int[] {-1,1}) {
				int nxt = cur[0] + dir;
				if(visited.contains(nxt))
					continue;
				visited.add(nxt);
				int nxtDist = cur[1] + 1;
				total += nxtDist;
				cnt++;
				if(cnt == k) {
					System.out.println(total);
					return;
				}
				q.add(new int[] {nxt, nxtDist});
			}
		}
		
	}
	static Set<Integer> visited = new HashSet<>();
	static boolean OOB(long value) {
		return value < -MAX_R || value > MAX_R;
	}
	static int n,k;
	static int MAX_R = 100_000_000;
	static StringTokenizer st;
}