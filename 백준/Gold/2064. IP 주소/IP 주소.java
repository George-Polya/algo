import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		ips = new int[N];
		for(int i = 0; i < N; i++) {
			ips[i] = ip2Int(br.readLine());
		}
		
		int common = 0;
		if(N == 1) {
			common = 32;
		}else {
			for(int bit = 31; bit>=0; bit--) {
				boolean allSame = check(bit);
				if(!allSame)
					break;
				common++;
			}
		}
		
		int subnetMask = (common == 0) ? 0 : (-1 << (32 - common));
		int networkAddress = ips[0] & subnetMask;
		
		System.out.println(int2Ip(networkAddress));
		System.out.println(int2Ip(subnetMask));
	}
	
	static String int2Ip(int ip) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < 4; i++) {
			int part = (ip >> (24 - i * 8)) & 0xFF;
			sb.append(part);
			if(i < 3)
				sb.append(".");
		}
		
		return sb.toString();
	}
	
	static boolean check(int bit) {
		int first = (ips[0] >> bit) & 1;
		for(int i = 1; i< N; i++) {
			int cur = (ips[i] >> bit) & 1;
			if(cur != first)
				return false;
		}
		return true;
	}
	
	static int ip2Int(String ip) {
		st = new StringTokenizer(ip, ".");
		int ret = 0;
		for(int i = 0; i <4; i++) {
			int part = Integer.parseInt(st.nextToken());
			ret = (ret << 8) | part;
		}
		
		return ret;
	}
	
	static int ips[];
	
	static StringTokenizer st;
	static int N;
}