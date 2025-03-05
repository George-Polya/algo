import java.util.*;
import java.io.*;

public class Main {
 
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	N = Integer.parseInt(br.readLine());
    	st = new StringTokenizer(br.readLine());
    	arr = new int[N+1];
    	for(int i =1 ; i<=N;i++) {
    		int value = Integer.parseInt(st.nextToken());
    		arr[i] = value;
    		pq.add(new Pair(i, value));
    	}
    	
    	M = Integer.parseInt(br.readLine());
    	StringBuilder sb = new StringBuilder();
    	for(int i = 0; i< M;i++) {
    		st = new StringTokenizer(br.readLine());
    		int cmd= Integer.parseInt(st.nextToken());
    		
    		if(cmd == 1) {
    			int idx = Integer.parseInt(st.nextToken());
    			int value = Integer.parseInt(st.nextToken());
    			arr[idx] = value;
    			pq.add(new Pair(idx,value));
    		}else {
//    			sb.append(pq.poll().idx).append('\n');
//    			System.out.println("----");
    			while(true) {
    				Pair cur = pq.poll();
    				if(cur.value == arr[cur.idx]) {
    					sb.append(cur.idx).append('\n');
    					pq.add(cur);
    					break;
    				}
    			}
    			
    		}
    		
    	}
//    	System.out.println(Arrays.toString(arr));
    	System.out.println(sb);
    }
    

    
    static StringTokenizer st;
    
    static int N,M;
    static int arr[];
    static PriorityQueue<Pair> pq = new PriorityQueue<>();
    
    static class Pair implements Comparable<Pair>{
    	int idx, value;
    	
    	public Pair(int idx,int value) {
    		this.idx = idx;
    		this.value = value;
    	}
    	
    	public int compareTo(Pair o) {
    		if(value != o.value)
    			return value - o.value;
    		return idx - o.idx;
    	}
    	
    	public String toString() {
    		return idx+" "+value;    
    	}
    }
    
}