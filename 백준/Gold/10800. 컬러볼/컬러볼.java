import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	
    	n = Integer.parseInt(br.readLine());
    	balls = new Ball[n+1];
    	for(int i = 1; i <= n;i++) {
    		st = new StringTokenizer(br.readLine());
    		int color = Integer.parseInt(st.nextToken());
    		int size = Integer.parseInt(st.nextToken());
    		balls[i] = new Ball(i, color ,size);
    	}
    	
    	Arrays.sort(balls, 1, n+1);
    	
    	int result[] = new int[n+1];
    	int pSum[] = new int[n+1];
    	
    	int idx = 1;
    	int sum = 0;
    	for(int i = 1; i<=n; i++) {
    		Ball cur = balls[i];
    		Ball target = balls[idx];
    		
    		while(target.size < cur.size) {
    			sum += target.size;
    			pSum[target.color] += target.size;
    			
    			target = balls[++idx];
    		}
    		
    		result[cur.id] = sum - pSum[cur.color];
    		
    	}
    	
    	
    	StringBuilder sb= new StringBuilder();
    	
    	for(int id = 1; id<=n; id++) {
    		sb.append(result[id]).append('\n');
    	}
    	
    	System.out.println(sb);
    	
    	
    }   
  
    static Ball balls[];
    static int n;
    static StringTokenizer st;
    static class Ball implements Comparable<Ball>{
    	int id, color, size;
    	public Ball(int id,int color, int size) {
    		this.id = id;
    		this.color = color;
    		this.size = size;
    	}
    	
    	public int compareTo(Ball o) {
    		return size - o.size;
    	}
    }
}