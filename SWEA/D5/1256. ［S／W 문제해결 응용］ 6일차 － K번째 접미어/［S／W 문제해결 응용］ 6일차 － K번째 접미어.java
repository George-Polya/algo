import java.io.*;
import java.util.*;

public class Solution {
	static int T = 10;
	static int k;
	static String str;
	static class TrieNode{
		boolean isEnd;
		int cnt;
		char ch;
		TrieNode children[] = new TrieNode[26];
		
		public TrieNode(char ch) {
			this.ch = ch;
			for(int i = 0; i < 26;i++) {
				children[i] = null;
			}
		}
	}
	
	static TrieNode root;
	
	static void insertWord(String s) {
		TrieNode t = root;
		for(int i = 0; i < s.length();i++) {
			int idx = s.charAt(i) - 'a';
			
			if(t.children[idx] == null)
				t.children[idx] = new TrieNode(s.charAt(i));
			t = t.children[idx];
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
		
		for(int i = 0; i < 26;i++) {
			if(cur.children[i] == null)
				continue;
			
			TrieNode nxt = cur.children[i];
			if(nxt.cnt < k) {
				k -= nxt.cnt;
				continue;
			}
			
			dfs(nxt, ret + nxt.ch, tc);
		}
		
		
		
		
		
	}
	
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException{
//		System.setIn(new FileInputStream("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for(int tc= 1; tc<=T;tc++) {
			root = new TrieNode('-');
			k = Integer.parseInt(br.readLine());
			str = br.readLine();
			
			if(k > str.length()) {
				sb.append('#').append(tc).append(' ').append("none").append('\n');
			}
			
			for(int i = 0; i<str.length();i++) {
				insertWord(str.substring(i));
			}
			
			
			dfs(root, "", tc);
		}
		System.out.println(sb);
	}
}