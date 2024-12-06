import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	str = br.readLine();
    	
    	int cnt = 0;
    	ArrayList<Integer> left = new ArrayList<>();
    	for(int i = 0; i < str.length();i++) {
    		if(str.charAt(i) == 'K')
    			cnt++;
    		else
    			left.add(cnt);
    		
    	}
    	ArrayList<Integer> right = new ArrayList<>();
    	cnt = 0;
    	for(int i = str.length() - 1; i>=0 ;i--) {
    		if(str.charAt(i) == 'K')
    			cnt++;
    		else
    			right.add(cnt);
    	}
    	Collections.sort(right, Collections.reverseOrder());
    	
    	
    	int l = 0;
    	int r = right.size() - 1;
    	int ans = 0;
    	
    	while(l<=r) {
    		ans = Math.max(ans, (r - l + 1) + Math.min(left.get(l), right.get(r)) * 2);
    		
    		
    		if(left.get(l) < right.get(r))
    			l++;
    		else
    			r--;
    	}
    	System.out.println(ans);
    }
    static String str;
}