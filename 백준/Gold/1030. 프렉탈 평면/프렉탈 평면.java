import java.util.*;
import java.io.*;
public class Main {
   public static void main(String[] args) throws IOException{
    	// System.setIn(new FileInputStream("./input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        y1 = Integer.parseInt(st.nextToken());
        y2 = Integer.parseInt(st.nextToken());
        x1 = Integer.parseInt(st.nextToken());
        x2 = Integer.parseInt(st.nextToken());
        
        StringBuilder sb = new StringBuilder();
        for(int y = y1; y<=y2;y++) {
        	for(int x= x1;x<=x2;x++) {
        		sb.append(solve(y,x,s));
        	}
        	sb.append('\n');
        }
        System.out.println(sb);
    }
     
   static int solve(int y, int x,int depth) {
	   if(depth == 0)
		   return 0;
	   
	   int size = (int)Math.pow(n, depth-1);
	   int start = (n-k) / 2 * size;
	   int end = start + k * size;
	   
	   
	   if(start <= y && y  < end && start <= x && x  < end)
		   return 1;
	   
	   return solve(y % size, x % size,depth - 1);
   }
   static StringTokenizer st;
   static int s,n,k,y1,y2,x1,x2;
}