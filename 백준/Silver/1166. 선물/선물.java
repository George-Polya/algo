import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Long.parseLong(st.nextToken());
        w = Long.parseLong(st.nextToken());
        h = Long.parseLong(st.nextToken());
//        System.out.println(l+" "+w+" "+h);
        double min = l;
        min = Math.min(min, w);
        min = Math.min(min, h);
        double l = 0.0;
        double r = min;
//        System.out.println(r);
        for(int i = 0; i < 10000;i ++) {
        	double mid = (l + r) / 2;
        	
        	if(decide(mid)) {
        		l = mid;
        	}else {
        		r = mid;
        	}
        }
        System.out.println(l);
    }
    
    static boolean decide(double len) {
    	long cnt = (long)(l / len) * (long)(w / len) * (long)(h / len);
    	return cnt >= n;
    	
    }
    static int n;
    static long l,w,h;
     static StringTokenizer st;
}