import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new Pair[n+1];
        for(int i = 0; i < n; i++) {
        	st = new StringTokenizer(br.readLine());
        	double x = Double.parseDouble(st.nextToken());
        	double y = Double.parseDouble(st.nextToken());
        	
        	arr[i] = new Pair(x,y);
        }
        
        arr[n] = new Pair(arr[0].x, arr[0].y);
        
        double first = 0;
        for(int i = 0; i<n; i++) {
        	double x = arr[i].x;
        	double y = arr[i+1].y;
        	first += x * y;
        }
        double second = 0;
        for(int i = 0; i < n; i++) {
        	double y = arr[i].y;
        	double x = arr[i+1].x;
        	
        	second += x * y;
        }
//        System.out.printf("%f %f\n", first, second);
        
        System.out.printf("%.1f\n",Math.abs(first - second) / 2);
        
    }
    static StringTokenizer st;
    static int n;
    static Pair arr[];
    static class Pair{
    	double x,y;
    	public Pair(double x,double y) {
    		this.x = x;
    		this.y = y;
    	}
    }
}