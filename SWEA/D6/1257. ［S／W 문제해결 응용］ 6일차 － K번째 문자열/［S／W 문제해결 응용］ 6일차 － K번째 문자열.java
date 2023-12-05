import java.io.*;
import java.util.*;

public class Solution {
	static int T;
	static int k;
	static String str;
	static class TrieNode{
		boolean isEnd;
		int cnt;
		char ch;
		Map<Character, TrieNode> children = new HashMap<>();
		
		public TrieNode() {
			
		}
		
		public TrieNode(char ch) {
			this.ch = ch;
		}
	}
	
	static TrieNode root;
	
	static void insertWord(String s) {
		TrieNode t = root;
		for(int i = 0; i < s.length();i++) {
			char ch = s.charAt(i);
			if(!t.children.containsKey(ch)) {
				t.children.put(ch, new TrieNode(ch));
			}
			t = t.children.get(ch);
			t.cnt++;
		}
		t.isEnd = true;
	}
	
	
	static void dfs(TrieNode cur, String ret, int tc) {
		if(k == 0)
			return;
		
		if(cur.isEnd) {
			k--;
			if(k == 0) {
				sb.append('#').append(tc).append(' ').append(ret).append('\n');
				return;
			}
		}
		
		for(char i = 'a'; i <= 'z'; i++) {
			if(cur.children.containsKey(i)) {
				TrieNode nxt = cur.children.get(i);
				
				if(nxt.cnt < k) {
					k -= nxt.cnt;
					continue;
				}
				
				dfs(nxt, ret + nxt.ch, tc);
			}
		}
	}
	
	static StringBuilder sb = new StringBuilder();
	static HashSet<String> set;
	public static void main(String[] args) throws IOException{
//		System.setIn(new FileInputStream("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for(int tc= 1; tc<=T;tc++) {
			root = new TrieNode('-');
			set = new HashSet<>();
			k = Integer.parseInt(br.readLine());
			str = br.readLine();
			
			
			
			for(int len = 1; len <= str.length();len++) {
				for(int start = 0; start < str.length();start++) {
					int end = start + len;
					if(end > str.length())
						continue;
					set.add(str.substring(start,end));
				}
			}
			
			for(String text : set) {
//				System.out.println(text);
				insertWord(text);
			}
			
			dfs(root, "", tc);
			if(k > 0) {
				sb.append('#').append(tc).append(' ').append("none").append('\n');
			}
		}
		System.out.println(sb);
	}
}