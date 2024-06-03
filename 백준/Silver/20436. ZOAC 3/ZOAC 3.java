import java.io.*;
import java.util.*;

public class Main {
	
	static class Pair{
		int y,x;
		public Pair(int y,int x) {
			this.y = y;
			this.x = x;
		}
		
		public String toString() {
			return y+" "+x;
		}
	}
	static Map<Character, Pair> map = new HashMap<>();
	
	static HashSet<Character> lefts = new HashSet<>();
	static HashSet<Character> rights = new HashSet<>();
	static StringTokenizer st;
	
	static void init() {
		int y = 1;
		int x = 1;
		for(char ch : new char[] {'q','w','e','r','t','y','u','i','o','p'}) {
			map.put(ch, new Pair(y,x));
			x++;
		}
		
		y++;
		x = 1;
		for(char ch : new char[] {'a','s','d','f','g','h','j','k','l'}) {
			map.put(ch, new Pair(y,x));
			x++;
		}
		
		y++;
		x = 1;
		for(char ch : new char[] {'z','x','c','v','b','n','m'}) {
			map.put(ch, new Pair(y,x));
			x++;
		}
		
		
		for(char ch : "qwertasdfgzxcv".toCharArray())
			lefts.add(ch);
		
		for(char ch : "yuiophjklbnm".toCharArray())
			rights.add(ch);
	}
	
	static boolean isLeft(char ch) {
		return lefts.contains(ch);
	}
	
	static boolean isRight(char ch) {
		return rights.contains(ch);
	}
	
	static int calc(Pair pair1, Pair pair2) {
		return Math.abs(pair1.y - pair2.y) + Math.abs(pair1.x - pair2.x);
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		init();
		st = new StringTokenizer(br.readLine());
		char sl = st.nextToken().charAt(0);
		char sr = st.nextToken().charAt(0);
		String str = br.readLine();
		
		Pair slPair = map.get(sl);
		Pair srPair = map.get(sr);
		
		int time = 0;
		for(char ch : str.toCharArray()) {
			if(isLeft(ch)) {
				Pair pair = map.get(ch);
				time += calc(slPair, pair);
				slPair = pair;
			}else if(isRight(ch)) {
				Pair pair = map.get(ch);
				time += calc(srPair, pair);
				srPair = pair;
			}
			time++;
		}
		System.out.println(time);
	}
}