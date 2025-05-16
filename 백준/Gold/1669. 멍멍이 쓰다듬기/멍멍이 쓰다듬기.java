import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		end -= start;
		start -= start;
		
		long cnt = 0;
		long speed = 0;
		
				
		while(start < end) {
			for(int s = 1; s>=-1; s--) {
				long nxt = speed + s;
				if( (nxt * (nxt + 1) / 2) + start <= end) {
					speed = nxt;
					start += speed;
					cnt++;
					break;
							
				}
						
			}
		}
		System.out.println(cnt);
				
				
	}
	static StringTokenizer st;
	static int start, end;
	
}