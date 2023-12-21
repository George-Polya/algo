import java.io.*;
import java.util.*;
/**
 * 에라토스테네스의 체 
 */
public class Main {
	static int n,m;
	static boolean states[];
	
	
	static int[] initialize(int left,int right) {
		Arrays.fill(states, true);
		states[1] = false;
		
		for(int i = 2; i * i <=n; i++) {
			if(!states[i])
				continue;
			for(int j = i * i ; j<=n; j+=i)
				states[j] = false;
		}
		
		int sum = 0;
		int min = Integer.MAX_VALUE;
		
		for(int i = m; i<=n;i++) {
			if(states[i]) {
				sum += i;
				min = Math.min(min, i);
			}
		}
		if(sum != 0)
			return new int[] {sum, min};
		else {
			return new int[] {-1,-1};
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		m = Integer.parseInt(br.readLine());
		n = Integer.parseInt(br.readLine());
		states = new boolean[n+1];
		int ret[] = initialize(n,m);
		if(ret[0] == -1)
			System.out.println(-1);
		else {
			System.out.println(ret[0]);
			System.out.println(ret[1]);
		}
	}
}