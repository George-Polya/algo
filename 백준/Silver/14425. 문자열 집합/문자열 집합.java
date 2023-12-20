import java.io.*;
import java.util.*;

public class Main {
	static StringTokenizer st;
	static int n,m;
	static HashSet<String> set = new HashSet<>();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		for(int i = 0; i < n; i++) {
			String str = br.readLine();
			set.add(str);
		}
		
		int ans = 0;
		for(int i =0; i <m ;i++) {
			String string = br.readLine();
			if(set.contains(string))
				ans++;
		}
		System.out.println(ans);
	}
}