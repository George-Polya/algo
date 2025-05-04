import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		for(int i = 0; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			int key = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			tm.put(key,  value);
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			int key = Integer.parseInt(st.nextToken());
			Integer floor = tm.floorKey(key);
			Integer ceil = tm.ceilingKey(key);
			
			Integer findKey = null;
			boolean flag = false;
			boolean floorValid = (floor != null && Math.abs(key - floor) <= K);
			boolean ceilValid = (ceil != null && Math.abs(ceil - key) <= K);
			
			if(floorValid && ceilValid) {
				int diffFloor = Math.abs(key - floor);
				int diffCeil = Math.abs(ceil - key);
				
				if(diffCeil < diffFloor) {
					findKey = ceil;
				}else if(diffCeil > diffFloor) {
					findKey = floor;
				}else {
					flag = true;
				}
			}else if(floorValid) {
				findKey = floor;
			}else if(ceilValid) {
				findKey = ceil;
			}
			
			
			if(cmd==1) {
				int value = Integer.parseInt(st.nextToken());
				tm.put(key, value);
			}else if(cmd == 2) {
				int value = Integer.parseInt(st.nextToken());
				
				if(tm.containsKey(key)) {
					tm.put(key, value);
				}else {
					if(!flag && findKey != null)
						tm.put(findKey, value);
				}
					
				
			}else if(cmd == 3) {
				if(tm.containsKey(key)) {
					sb.append(tm.get(key)+"\n");
				}else {
					if(flag) {
						sb.append("?\n");
					}else if(findKey == null) {
						sb.append("-1\n");
					}else {
						sb.append(tm.get(findKey)+"\n");
					}
				}
			}
		}
		System.out.println(sb);
	}
	static StringTokenizer st;
	static int N,M,K;
	static TreeMap<Integer, Integer> tm = new TreeMap<>();
	
}