import java.util.*;
import java.io.*;
public class Main {
//	static TreeSet<String> set = new TreeSet<>();
	static TreeMap<String, Integer> map = new TreeMap<>();
	static int n ;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		for(int i = 0; i < n;i++) {
			String str = br.readLine();
			map.put(str, map.getOrDefault(str, 0) + 1);
		}
		
		
		int maxCnt = 0;
		String str="";
		for(String key : map.keySet()) {
			int cnt = map.get(key);
			if(maxCnt < cnt) {
				maxCnt = cnt;
				str = key;
			}else if(str.compareTo(key) > 0)
				str = key;
		}
		System.out.println(str);
		
	}
}