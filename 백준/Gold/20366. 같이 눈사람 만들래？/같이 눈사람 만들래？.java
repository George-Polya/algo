import java.io.*;
import java.util.*;

public class Main {
	static StringTokenizer st;
	static class SnowMan implements Comparable<SnowMan>{
		int hIdx, bIdx, height;
		
		public SnowMan(int hIdx, int bIdx, int height) {
			this.hIdx = hIdx;
			this.bIdx = bIdx;
			this.height = height;
		}
		
		public int compareTo(SnowMan man) {
			return height - man.height;
		}
	}
	
	static int snow[];
	static int n;
	static ArrayList<SnowMan> snowMans = new ArrayList<>();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		snow = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n ;i++) {
			snow[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i < n;i++) {
			for(int j = i + 1; j < n ; j++) {
				snowMans.add(new SnowMan(i,j, snow[i] + snow[j]));
			}
		}
		
		Collections.sort(snowMans);
		int ans = Integer.MAX_VALUE;
		for(int i = 0 ; i < snowMans.size() - 1; i++) {
			SnowMan man1 = snowMans.get(i);
			SnowMan man2 = snowMans.get(i+1);
			
			int snow1 = man1.bIdx;
			int snow2 = man1.hIdx;
			
			int snow3 = man2.bIdx;
			int snow4 = man2.hIdx;
			
			if(snow1 != snow3 && snow1 != snow4 && snow2 != snow3 && snow2 != snow4) {
				ans = Math.min(ans, man2.height - man1.height);
			}
		}
		
		System.out.println(ans);
	}
}