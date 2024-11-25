import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      n = Integer.parseInt(br.readLine());
      dfs(0,0);
      
      try {
    	  List<Long> list = new ArrayList<>(set);
    	  System.out.println(list.get(n-1));
      }catch(Exception e) {
    	  System.out.println(-1);
      }
    }
    
    static void dfs(long num, int idx) {
    	if(!set.contains(num)) {
    		set.add(num);
    	}
    	
    	if(idx >= 10)
    		return;
    	
    	dfs((num * 10) + arr[idx], idx+1);
    	dfs(num, idx + 1);
    }
    
    static int n;
    static int arr[] = {9,8,7,6,5,4,3,2,1,0};
    static TreeSet<Long> set = new TreeSet<>();
}