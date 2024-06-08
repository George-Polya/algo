import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str;
		StringBuilder result = new StringBuilder();
		while(!(str = br.readLine()).equals("END") ) {
//			System.out.println(str);
			StringBuilder sb = new StringBuilder();
			sb.append(str);
//			System.out.println(sb.reverse());
			result.append(sb.reverse()).append('\n');
		}
		System.out.println(result);
	}
}