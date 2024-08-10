import java.io.*;
import java.util.*;


public class Main {
	static StringTokenizer st;
	static class Time{
		int minute;
		public Time(String str) {
			StringTokenizer st = new StringTokenizer(str, ":");
			int time = Integer.parseInt(st.nextToken());
			int minute = Integer.parseInt(st.nextToken());
			this.minute = minute + 60 * time;
		}
		public String toString() {
			return minute+" ";
		}
		
		public boolean before(Time time) {
			return minute <= time.minute;
		}
		
		public boolean after(Time time) {
			return minute >= time.minute;
		}
	}
	
	static Time start, end, quit;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		start = new Time(st.nextToken());
		end = new Time(st.nextToken());
		quit = new Time(st.nextToken());
		
		HashSet<String> entrance = new HashSet<>();
		HashSet<String> exit = new HashSet<>();
		
		String str;
		while((str = br.readLine()) != null && !str.isEmpty()) {
			st = new StringTokenizer(str);
			String tmp = st.nextToken();
			Time time = new Time(tmp);
			String name = st.nextToken();
			if(time.before(start)) {
				entrance.add(name);
			}else if(time.after(end) && time.before(quit)) {
				exit.add(name);
			}
		}
//		System.out.println(entrance);
//		System.out.println(exit);
		
		int ans = 0;
		for(String name : entrance) {
			if(exit.contains(name))
				ans++;
		}
		System.out.println(ans);
	}
}