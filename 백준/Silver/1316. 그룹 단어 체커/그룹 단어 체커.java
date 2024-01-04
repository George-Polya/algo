import java.util.*;
import java.io.*;
public class Main{
	static int n;
	static boolean used[] = new boolean[26];
	static int toInt(char ch) {
		return ch - 'a';
	}
	static boolean isGroup(String str) {
		char ch = str.charAt(0);
		used[toInt(ch)] = true;
		
		int idx = 0;
		for(int i =1; i< str.length();i++) {
			if(ch != str.charAt(i)) {
//				System.out.print(ch);
				if(used[toInt(str.charAt(i))])
					return false;
				ch = str.charAt(i);
				idx = i;
				used[toInt(ch)] = true;
			}
		}
		return true;
	}
	
    public static void main(String[] args) throws IOException{
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       // Please Enter Your Code Here
       n = Integer.parseInt(br.readLine());
       int cnt = 0;
       for(int i = 0; i < n; i++) {
    	   String str = br.readLine();
    	   Arrays.fill(used, false);
    	   if(isGroup(str))
    		   cnt++;
//    	   System.out.println();
       }
       System.out.println(cnt);
    }
}