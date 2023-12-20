import java.io.*;
import java.util.*;

public class Main {
	static int n,m;
	static StringTokenizer st;
	static HashMap<String, Integer> name2id = new HashMap<>();
	static HashMap<Integer, String> id2name = new HashMap<>();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		for(int id = 1; id<=n; id++) {
			String name = br.readLine();
			name2id.put(name, id);
			id2name.put(id, name);
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < m ;i++) {
			String str = br.readLine();
			int flag = str.charAt(0) - '0';
//			System.out.println(str);
			if(0<=flag && flag <= 9) {
//				System.out.println(flag);
//				System.out.println(str);
				sb.append(id2name.get(Integer.valueOf(str))).append('\n');
			}else {
				sb.append(name2id.get(str)).append('\n');
			}
		}
		System.out.println(sb);
		
	}
}