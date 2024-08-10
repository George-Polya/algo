import java.io.*;
import java.util.*;


public class Main {
	static class Pair{
		int start, end;
		public Pair(int start,int end) {
			this.start = start;
			this.end = end;
		}
		public String toString() {
			return start+" "+end;
		}
	}
	static String str;
	static int n;
	static Stack<Integer> stk = new Stack<>();
	static List<Pair> list = new ArrayList<>();
	static List<String> temp = new ArrayList<>();
	
	static String remove(int idx, String temp) {
		String ret = "";
		Pair pair = list.get(idx);
		for(int i = 0; i < temp.length();i++) {
			if(i == pair.start || i == pair.end)
				ret += "&";
			else
				ret += temp.charAt(i);
		}
		return ret;
	}
	
	static void solve(int cur, String str) {
		if(cur == list.size()) {
			temp.add(str);
//			System.out.printf("%d: %s\n", cur, str);
			return;
		}
		
		// cur에 해당하는거 제거 
		String str2 = remove(cur, str);
		solve(cur + 1, str2);
		
		// 제거 안함 
		solve(cur + 1, str);
	}
	
	
	static String make(String str) {
		String ret = "";
		for(int i = 0; i < str.length();i++) {
			char ch = str.charAt(i);
			if(ch == '&')
				continue;
			ret += ch;
		}
		return ret;
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		str = br.readLine();
		n = str.length();
		
		for(int i = 0; i<n;i++) {
			char ch = str.charAt(i);
			if(ch == '(') {
				stk.push(i);
			}else if(ch == ')') {
				int start = stk.pop();
				list.add(new Pair(start, i));
			}
		}
		
		solve(0, str);
//		System.out.println(temp);
		TreeSet<String> result = new TreeSet<>();
		for(int i = 0; i < temp.size() - 1;i++) {
			String str = temp.get(i);
			String ret = make(str);
			result.add(ret);
		}
		
		for(String str : result)
			System.out.println(str);
	}
}