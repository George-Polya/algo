import java.util.*;
import java.io.*;

public class Main{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		
		states = new State[N];
		for(int i = 0; i < N ;i++) {
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			states[i] = new State(t,a,h);
		}
		
		long l = 1;
		long r = Long.MAX_VALUE - 1;
		long ans = 0;
		while(l<=r) {
			long mid = (l + r)/ 2;
			
			if(decide(mid)) {
				r = mid - 1;
				ans = mid;
			}else {
				l = mid + 1;
			}
		}
		System.out.println(ans);
	}
	
	static boolean decide(long maxHP) {
		long curHP = maxHP;
		long power = P;
		
		for(int i = 0 ; i < N; i++) {
			State state = states[i];
			
			if(state.t == 1) {
				long atkCnt = state.h / power;
				if(state.h % power != 0)
					atkCnt++;
				
				long defenseCnt = atkCnt - 1;
				if(curHP <= (long)state.a * defenseCnt) {
					return false;
				}else {
					curHP -= state.a * defenseCnt;
				}
				
			}else {
				curHP = Math.min(curHP + state.h, maxHP);
				power += state.a;
			}
			
		}
		return true;
		
	}
	
	
	static StringTokenizer st;
	static int N,P;
	static class State{
		int t,a,h;
		public State(int t, int a, int h) {
			this.t = t;
			this.a = a;
			this.h = h;
		}
	}
	static State states[]; 
	
}

