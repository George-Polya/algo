import java.io.*;
import java.util.*;

public class Solution {
	static int T;
	static StringTokenizer st;
	
	static long get2Power(int cnt, int mod) {
		long res = 1;
		long num = 2;
		
		while(cnt > 0) {
			if(cnt % 2 == 1) {
				res = (res * num) % mod;
			}
			
			num = (num * num) % mod;
			cnt /= 2;
		
		}
		return res;
	}
	
	static long solution(int num1, int num2, int cnt) {
		int sum = num1 + num2;
		int max = sum / 2;
		
		long result = (get2Power(cnt, sum) * (long)num1) % sum;
		
		return result > max ? sum - result : result;
	}
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int tc=1;tc<=T;tc++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			sb.append('#').append(tc).append(' ').append(solution(a,b,k)).append('\n');
		}
		System.out.println(sb);

	}

}