import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new String[N];
        for (int i = 0; i < N; i++) {
        	arr[i] = br.readLine();
        	addWeight(arr[i]);
        }
        
        
        for(int i = 0; i < 26; i++) {
        	if(weights[i] != 0) {
        		char ch = (char)(i+'A');
        		pq.add(new Pair(ch, weights[i]));
        	}
        }
        
        int num = 9;
        
        while(!pq.isEmpty()) {
        	Pair cur = pq.poll();
        	weights[toInt(cur.ch)] = num;
        	num--;
        }
        
        long ans = 0;
        
        for(int i = 0; i < N; i++) {
        	ans += convert(arr[i]);
        }
        System.out.println(ans);
    }
    
    static long convert(String str) {
    	long ret = 0;
    	
    	for(int i = 0; i < str.length();i++) {
    		char ch = str.charAt(i);
    		
    		ret = ret * 10 + weights[toInt(ch)];
    	}
    	
    	return ret;
    }
    
    static PriorityQueue<Pair> pq = new PriorityQueue<>();
    
    static class Pair implements Comparable<Pair>{
    	char ch;
    	long value;
    	
    	public Pair(char ch,long value) {
    		this.ch = ch;
    		this.value = value;
    	}
    	
    	public int compareTo(Pair o) {
    		return Long.compare(o.value, value);
    	}
    }
    
    static long weights[] = new long[26];
    
    static int toInt(char ch) {
    	return ch - 'A';
    }
    
    static void addWeight(String str) {
    	int len = str.length();
    	for(int i = 0; i < len;i++) {
    		char ch = str.charAt(i);
    		int pos = len - i;
    		weights[toInt(ch)] += (long)Math.pow(10, pos);
    	}
    }
    
    static int N;
    static String arr[];
}