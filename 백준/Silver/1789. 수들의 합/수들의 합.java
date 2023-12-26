import java.io.*;
/**
 * S = 200이면 
 * n = 1 -> true
 * n = 2 -> true
 * ...
 * n = 19 -> true
 * n = 20 -> false
 * ..
 * 
 * 뒤집은 문제 : 서로다른 n개의 자연수로 S를 만들 수 있는가? 
 * 1~n까지의 합은 분명 서로다른 n개의 자연수의 합 중에서 최솟값이다
 * 만약 sum([1...n])이 S보다 작거나 같으면 S - sum([1..n])을 1~n중에 아무곳에나 더해도 
 * 그 n개의 수는 서로다른 n개의 수이다. 
 * 따라서 뒤집은 문제는 sum([1..n]) <= S인가로 바뀔 수 있다 
 */
import java.util.*;
public class Main {
	static long INT_MAX = Integer.MAX_VALUE;
	static long s;
	static boolean decide(long target) {
		return target * (target + 1) / 2 <= s;
	}
	public static void main(String[] args) throws IOException{
	 	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//	 	System.out.println((long)Integer.MAX_VALUE * 2);
	 	s = Long.parseLong(br.readLine());
	 	
	 	long ans = -1;
	 	long l = 1;
	 	long r = INT_MAX;
	 	
	 	while(l<=r) {
	 		long mid = (l + r) / 2;
	 		
	 		if(decide(mid)) {
	 			l = mid + 1;
	 			ans = mid;
	 		}else {
	 			r = mid - 1;
	 		}
	 	}
	 	System.out.println(ans);
	}
}