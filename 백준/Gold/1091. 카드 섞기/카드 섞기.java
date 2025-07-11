import java.util.*;
import java.io.*;

public class Main{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		target = new int[N];
		shift = new int[N];
		arr = new int[N];
		original = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i< N; i++) {
			target[i] = Integer.parseInt(st.nextToken());
			arr[i] = original[i] = i;
		}
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i< N; i++) {
			shift[i] = Integer.parseInt(st.nextToken());
		}
		
		solve(0);
	}
	static int N;
	static StringTokenizer st;
	static int target[], shift[], arr[], original[];

	
	static void solve(int cnt) {
		if(isAnswer()) {
			System.out.println(cnt);
			System.exit(0);
		}
		
		if(impossible(cnt)) {
			System.out.println(-1);
			System.exit(0);
		}
		
		mix();
		solve(cnt + 1);
	}
	
	static boolean isAnswer() {
		for(int i = 0; i < N; i++) {
			if(target[i] != (arr[i] % 3))
				return false;
		}
		return true;
	}
	
	static boolean impossible(int cnt) {
		if(cnt == 0)
			return false;
		for(int i = 0 ;i < N; i++) {
			if(arr[i] != original[i])
				return false;
		}
		
		return true;
	}
	
	static void mix() {
		int temp[] = new int[N];
		for(int i = 0; i < N; i++) {
			temp[i] = shift[arr[i]];
		}
		
		arr = temp;
 	}
	
}

