import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       k = Integer.parseInt(br.readLine());
       String str = Integer.toBinaryString(k+1);
       String ans = "";
       for(int i = 1; i < str.length();i++) {
    	   ans += str.charAt(i) == '0' ? '4' : '7';
       }
//       System.out.println(str+" "+ans);
       System.out.println(ans);
    		  
    }
    static int k;
}