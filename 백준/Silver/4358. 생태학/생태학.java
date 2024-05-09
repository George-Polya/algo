import java.util.*;
import java.io.*;



public class Main {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = null;
		TreeMap<String, Integer> map = new TreeMap<>();
		int cnt = 0;
		while((line = br.readLine()) != null) {
			map.put(line, map.getOrDefault(line, 0)+1);
			cnt++;
		}
		

		
		StringBuilder sb = new StringBuilder();
		for(String key : map.keySet()) {
			double ratio = (double)(map.get(key) * 100.0) / cnt;
			sb.append(key).append(' ').append(String.format("%.4f", ratio)).append('\n');
		}
		System.out.println(sb);
	}
}