import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		PriorityQueue<Person> waitQ = new PriorityQueue<>();
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			waitQ.add(new Person(s,e));
		}
		
		PriorityQueue<Player> playerQ = new PriorityQueue<>();
		PriorityQueue<Integer> comQ = new PriorityQueue<>();
		
		TreeMap<Integer, Integer> map = new TreeMap<>();
		
		int cnt = 1;
		while(!waitQ.isEmpty()) {
			Person person = waitQ.poll();
			
			while(!playerQ.isEmpty()) {
				if(person.s >= playerQ.peek().e)
					comQ.add(playerQ.poll().cIdx);
				else
					break;
			}
			
			if(comQ.isEmpty()) {
				playerQ.add(new Player(person.e, cnt));
				map.put(cnt, map.getOrDefault(cnt,0) + 1);
				cnt++;
			}else {
				int cIdx = comQ.poll();
				map.put(cIdx, map.getOrDefault(cIdx, 0) + 1);
				playerQ.add(new Player(person.e, cIdx));
			}
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(map.size()).append('\n');
		for(int key : map.keySet()) {
			sb.append(map.get(key)).append(' ');
		}
		System.out.println(sb);
		
	}
	static int n;
	static StringTokenizer st;
	static class Person implements Comparable<Person>{
		int s,e;
		public Person(int s, int e) {
			this.s = s;
			this.e = e;
		}
		
		public int compareTo(Person o) {
			return s - o.s;
		}
	}
	
	static class Player implements Comparable<Player>{
		int e, cIdx;
		
		public Player(int e, int cIdx) {
			this.e = e;
			this.cIdx = cIdx;
		}
		
		public int compareTo(Player o) {
			return e - o.e;
		}
	}
}