import java.util.*;
import java.io.*;

public class Main {
	static int c,n,q;
	static StringTokenizer st;
	static Set<String> nicknames = new HashSet<>();
	static Trie root = new Trie('S', false);
	static class Trie{
		char name;
		Trie[] nxt = new Trie[(int)'z' - (int)'a' + 1];
		boolean isEnd;
		
		public Trie(char name, boolean isEnd) {
			this.name = name;
			this.isEnd = isEnd;
		}
		
		public void add(Trie trie) {
			int nxtIdx = (int)trie.name - (int)'a';
			if(nxt[nxtIdx] == null) {
				nxt[nxtIdx] = trie;
			}
			
			nxt[nxtIdx].isEnd = nxt[nxtIdx].isEnd == true ? true : trie.isEnd;
		}
		
		public Trie nextTrie(char nextName) {
			int nxtIdx = (int)nextName - (int)'a';
			return nxt[nxtIdx];
		}
	}
	
	static void add(String color, Trie cursor) {
		for(int i = 0; i < color.length();i++) {
			Trie trie = new Trie(color.charAt(i), (i == color.length() - 1));
			cursor.add(trie);
			cursor = cursor.nextTrie(color.charAt(i));
		}
	}
	
	static void markColorPoint(String team, Trie cursor, List<Integer> matchedPoints){
		for(int i = 0; i < team.length();i++) {
			cursor = cursor.nextTrie(team.charAt(i));
			if(cursor == null)
				return;
			if(cursor.isEnd && team.length() - (i+1) <= 1000)
				matchedPoints.add(i);
		}
	}
	
	static String matchNicknamePoint(String team, List<Integer> matchedPoints) {
		for(int point : matchedPoints) {
			if(nicknames.contains(team.substring(point+1)))
				return "Yes";
		}
		return "No";
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		c = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < c;i++) {
			String color = br.readLine();
			add(color,root);
		}
		
		for(int i = 0; i < n; i++) {
			String nickname = br.readLine();
			nicknames.add(nickname);
		}
		
		q = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < q; i++) {
			String team = br.readLine();
			List<Integer> matchedPoints = new ArrayList<>();
			markColorPoint(team, root, matchedPoints);
			sb.append(matchNicknamePoint(team, matchedPoints)).append('\n');
		}
		System.out.println(sb);
	}
}