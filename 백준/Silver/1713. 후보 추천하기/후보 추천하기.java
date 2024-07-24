import java.io.*;
import java.util.*;

public class Main {
	static class Pair implements Comparable<Pair>{
		int cnt, time;
		public Pair(int cnt, int time) {
			this.cnt = cnt;
			this.time = time;
		}
		
		public String toString() {
			return cnt+" "+time;
		}
		
		public boolean isHigher(Pair p) {
			if(cnt != p.cnt)
				return cnt < p.cnt;
		
			return time > p.time;
		}
		
		public int compareTo(Pair p) {
			if(cnt != p.cnt)
				return cnt - p.cnt;
			return p.time - time;
			
		}
	}
	static Map<Integer, Pair> map = new TreeMap<>();

	static void printMap() {
		for(int key : map.keySet()) {
			System.out.printf("id: %d, cnt: %d, time: %d\n", key,map.get(key).cnt, map.get(key).time);
		}
	}
	static int n,m;
	static StringTokenizer st;
	static void update() {
		for(int key : map.keySet()) {
			if(map.get(key).cnt != 0) {
				map.get(key).time++;
			}
		}
	}
	
	static Pair NO_PAIR = new Pair(Integer.MAX_VALUE, Integer.MIN_VALUE);
	
	static int findRemoveId() {
		int ret = -1;
		Pair best = NO_PAIR;
		for(int id : map.keySet()) {
			Pair pair = map.get(id);
			if(pair.cnt != 0) {
				if(pair.isHigher(best)) {
					best = pair;
					ret = id;
				}
			}
		}
		return ret;
	}
	
	static boolean isEmpty() {
//		System.out.println("size: "+map.size());
		if(map.size() < n)
			return true;
		
		int ret = 0;
		for(int id : map.keySet()) {
			if(map.get(id).cnt !=0)
				ret++;
		}
//		System.out.println("ret: "+ret);
		return ret >= m;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
//		boolean full = false;
		st = new StringTokenizer(br.readLine());
		for(int turn=1; turn<=m;turn++) {
//			System.out.println("-----");
//			System.out.println("turn: "+turn);
			update(); // 각 사진들이 사진틀에 남아있는 시간
			int id = Integer.parseInt(st.nextToken());
			boolean empty = isEmpty();
//			System.out.println("empty: "+empty);
			if(empty) { // 꽉차지 않은 경우 
				if(map.containsKey(id)) { // 이미 게시된 학생이 추천을 받았으면 
					Pair pair = map.get(id);
					pair.cnt++; // 추천받은 횟수만 증가시킴 
				}else {
					map.put(id, new Pair(1,1));
				}
				
			}else {
				if(map.containsKey(id)) {
					if(map.get(id).cnt != 0)
						map.get(id).cnt++;
					else {
						int removeId = findRemoveId();
						map.put(removeId, new Pair(0,0));
						map.put(id,  new Pair(1,1));
					}
				}else {
					int removeId = findRemoveId();
					map.put(removeId, new Pair(0,0));
					map.put(id,  new Pair(1,1));
				}
				
//				int removeId = findRemoveId();
//				System.out.println("removeId: "+removeId);
//				map.put(removeId, new Pair(0,0));
//				if(!map.containsKey(id)) {
//					map.put(id,  new Pair(1,1));
//				}else {
//					if(map.get(id).cnt == 0)
//						map.put(id,  new Pair(1,1));
//					else
//						map.get(id).cnt++;
//				}
			}
			
//			System.out.println(map);
//			System.out.println("full: "+full);
		}
//			printMap();
		StringBuilder sb= new StringBuilder();
		for(int id : map.keySet()) {
			if(map.get(id).cnt != 0) {
				sb.append(id).append(' ');
			}
		}
		System.out.println(sb);
	}
}