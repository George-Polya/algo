import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	G = Integer.parseInt(br.readLine());
    	
    	long s = 1;
    	long e = 1;
    	boolean found = false;
    	StringBuilder sb = new StringBuilder();
    	while(true){
    		long diff = e * e - s * s;
    		
    		if(diff == G) {
    			sb.append(e).append(' ');
    			found = true;
    		}
    		
    		if(e - s == 1 && diff > G)
    			break;
    		
    		if(diff > G)
    			s++;
    		else 
    			e++;
    	}
    	
    	System.out.println(found ? sb : -1);
    	
    }
    static int G;
    	
}