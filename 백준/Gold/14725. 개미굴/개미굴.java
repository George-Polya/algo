import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	n = Integer.parseInt(br.readLine());
    	
    	for(int i = 0; i < n;i++) {
    		st = new StringTokenizer(br.readLine());
    		int size = Integer.parseInt(st.nextToken());
    		String arr[] = new String[size];
    		for(int j = 0; j < size;j++) {
    			arr[j] = st.nextToken();
    		}
    		
    		insert(arr);
    		
    	}
    	
    	solve(root, 0);
    	System.out.println(sb);
    }
    static StringBuilder sb = new StringBuilder();
    static void solve(TrieNode t, int depth) {
    	
    	for(String str : t.children.keySet()) {
    		for(int i = 0; i < depth;i++) {
    			sb.append("--");
    		}
    		sb.append(str).append('\n');
    		solve(t.children.get(str), depth + 1);
    	}
    	
    }
    
    
    static void insert(String arr[]) {
    	TrieNode t = root;
    	
    	for(String str : arr) {
    		if(!t.children.containsKey(str))
    			t.children.put(str,  new TrieNode());
    		
    		t = t.children.get(str);
    	}
    }
    
    static int n;
    static StringTokenizer st;
    static TrieNode root = new TrieNode();
    
    static class TrieNode{
    	Map<String, TrieNode> children;
    	public TrieNode() {
    		this.children = new TreeMap<>();
    	}
    }
}