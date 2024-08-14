import java.util.*;
import java.io.*;

public class Main {
	static int n,d;
	static StringTokenizer st;
	static class Pair implements Comparable<Pair>{
		int start, end, len;
		public Pair(int start, int end) {
			this.start = start;
			this.end = end;
			this.len = end - start;
		}
		
		public int compareTo(Pair p) {
			return end - p.end;
		}
		
		public String toString() {
			return start+" "+end;
		}
	}
	
	static Pair arr[];
	
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       n = Integer.parseInt(br.readLine());
       arr = new Pair[n];
       for(int i = 0; i < n;i++) {
    	   st = new StringTokenizer(br.readLine());
    	   int start = Integer.parseInt(st.nextToken());
    	   int end = Integer.parseInt(st.nextToken());
    	   if(start > end)
    		   arr[i] = new Pair(end, start);
    	   else
    		   arr[i] = new Pair(start, end);
       }
        
       Arrays.sort(arr);
       
       d = Integer.parseInt(br.readLine());
       
       PriorityQueue<Integer> pq = new PriorityQueue<>();
       
       int ans = Integer.MIN_VALUE;
       for(int i = 0; i < n; i++) {
    	   pq.add(arr[i].start);
    	   while(!pq.isEmpty() && pq.peek() < arr[i].end - d)
    		   pq.poll();
    	   ans = Math.max(ans, pq.size());
       }
       System.out.println(ans);
       
    }
}