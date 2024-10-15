import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	n = Integer.parseInt(br.readLine());
    	
    	PriorityQueue<Soil> stations = new PriorityQueue<>();
    	
    	for(int i = 0 ;i <n ;i++) {
    		st = new StringTokenizer(br.readLine());
    		int idx = Integer.parseInt(st.nextToken());
    		int fuel = Integer.parseInt(st.nextToken());
    		stations.add(new Soil(idx, fuel));
    	}
    	
    	st = new StringTokenizer(br.readLine());
    	l = Integer.parseInt(st.nextToken());
    	p = Integer.parseInt(st.nextToken());
    	
    	PriorityQueue<Integer> fuels = new PriorityQueue<>(Collections.reverseOrder());
    	
    	int ans = 0;
    	
    	while(p < l) {
    		while(!stations.isEmpty() && stations.peek().idx <= p)
    			fuels.add(stations.poll().fuel);
    		
    		if(fuels.isEmpty()) {
    			System.out.println(-1);
    			return;
    		}
    		
    		ans++;
    		p += fuels.poll();
    		
    	}
    	
    	System.out.println(ans);
    }
    
    static StringTokenizer st;
    
    static int n, l, p;
    static class Soil implements Comparable<Soil>{
    	int idx, fuel;
    	
    	public Soil(int idx ,int fuel) {
    		this.idx = idx;
    		this.fuel = fuel;
    	}
    	
    	public int compareTo(Soil o) {
    		return idx - o.idx;
    	}
    }
}