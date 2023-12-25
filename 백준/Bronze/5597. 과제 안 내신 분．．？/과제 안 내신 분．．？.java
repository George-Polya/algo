import java.io.*;
/**
 * dp
 */
import java.util.*;
public class Main {
	static int MAX_N = 30;
	static boolean submitted[] = new boolean[MAX_N+1];
	public static void main(String[] args) throws IOException{
	 	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	 	for(int i = 1; i<=28;i++) {
	 		int id = Integer.parseInt(br.readLine());
	 		submitted[id] = true;
	 	}
	 	
	 	StringBuilder sb = new StringBuilder();
	 	for(int id = 1; id <= MAX_N; id++) {
	 		if(submitted[id])
	 			continue;
//	 		sb.append(id).append('\n');
	 		System.out.println(id);
	 	}
	 	System.out.println(sb);
	}
}