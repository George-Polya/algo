import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       k = Integer.parseInt(br.readLine());
       String s = Integer.toBinaryString(k + 1).replace('0', '4').replace('1', '7');
       String ans="";
       for(int i = 1; i < s.length();i++)
    	   ans += s.charAt(i);
       System.out.println(ans);
    }
    static int k;
}