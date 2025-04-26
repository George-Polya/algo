import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        
        List<Apart> leftAparts = new ArrayList<>();
        List<Apart> rightAparts = new ArrayList<>();
        
        for(int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
        	int L = Integer.parseInt(st.nextToken());
        	int students = Integer.parseInt(st.nextToken());
        	
        	int relative = L - S;
        	if(relative < 0) {
        		leftAparts.add(new Apart(Math.abs(relative), students));
        	}else if(relative > 0) {
        		rightAparts.add(new Apart(Math.abs(relative), students));
        	}
        }
        
        long leftDist = calc(leftAparts, K);
        long rightDist = calc(rightAparts, K);
        
        System.out.println(leftDist + rightDist);
    }
    
    static long calc(List<Apart> aparts, int limit) {
    	Collections.sort(aparts);
    	
    	long total = 0;
    	int idx = 0;
    	
    	while(idx < aparts.size()) {
    		while(idx < aparts.size() && aparts.get(idx).students == 0)
    			idx++;
    		if(idx == aparts.size())
    			break;
    		
    		Apart farthest = aparts.get(idx);
    		int location = farthest.location;
    		int students = farthest.students;
    		
    		long numTrips = (students + limit - 1) / limit;
    		total += numTrips * 2L * location;
    		
    		long capacity = numTrips * K;
    		capacity -= students;
    		aparts.get(idx).students = 0;
    		
    		int fill = idx + 1;
    		while(fill < aparts.size() && capacity > 0) {
    			Apart apt = aparts.get(fill);
    			long take = Math.min((long)apt.students, capacity);
    			
    			apt.students -= (int)take;
    			capacity -= take;
    			fill++;
    		}
    	}
    	
    	return total;
    }
    
    static int N,K,S;
    static StringTokenizer st;
    static class Apart implements Comparable<Apart>{
    	int location, students;
    	public Apart(int location, int students) {
    		this.location = location;
    		this.students = students;
    	}
    	
    	public int compareTo(Apart o) {
    		return o.location - location;
    	}
    }
}