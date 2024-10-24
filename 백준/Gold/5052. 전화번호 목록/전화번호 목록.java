import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	T = Integer.parseInt(br.readLine());
    	
    	for(int tc=1;tc<=T; tc++) {
    		n = Integer.parseInt(br.readLine());
    		arr = new String[n];
    		for(int i = 0; i <n ;i++) {
    			arr[i] = br.readLine();
    		}
    		
    		Arrays.sort(arr);
    		
    		root = new TrieNode();
    		boolean flag = true;
    		
    		for(int i = 0; i < n;i++) {
//    			String str = br.readLine();
    			boolean inserted = insert(arr[i]);
    			if(!inserted)
    				flag = false;
    		}
    		System.out.println(flag ? "YES" : "NO");
    	}
    	
    }
    
    static String arr[];
    
    static boolean insert(String str) {
    	TrieNode t = root;
    	
    	for(int i = 0; i < str.length(); i++) {
    		int idx = str.charAt(i) - '0';
    		
    		if(t.children[idx] == null)
    			t.children[idx] = new TrieNode();
    		t = t.children[idx];
    		if(t.isEnd)
    			return false;
    	}
    	
    	t.isEnd = true;
    	return true;
    }
    
    static TrieNode root;
    static class TrieNode{
    	TrieNode children[] = new TrieNode[10];
    	
    	boolean isEnd;
    	
    	public TrieNode() {
    		isEnd = false;
    		for(int i = 0; i < 10; i++)
    			children[i] = null;
    	}
    }
    
    static int T,n;
}