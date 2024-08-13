import java.util.*;
import java.io.*;

public class Main {
	static int n;
	static Integer arr[];
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new Integer[n+1];
        for(int i = 1; i <= n;i++) {
        	arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr,1,n+1,Collections.reverseOrder());
        ArrayList<ArrayList<Integer>> list = new ArrayList<>(); 
        
        for(int start = 1; start<=n;start+=3) {
        	int end = start + 2 > n ? n : start + 2;
        	ArrayList<Integer> temp = new ArrayList<>();
        	for(int i = start; i<=end;i++) {
        		temp.add(arr[i]);
        	}
        	list.add(temp);
        }
        int ans = 0;
        for(int i = 0; i < list.size();i++) {
        	ArrayList<Integer> temp = list.get(i);
//        	System.out.println(Arrays.toString(temp));
        	if(temp.size() > 2) {
        		int min = Integer.MAX_VALUE;
        		for(int j = 0; j<temp.size();j++) {
        			ans += temp.get(j);
        			min = Math.min(min, temp.get(j));
        		}
        		ans -= min;
        	}else {
        		for(int j = 0; j<temp.size();j++) {
        			ans += temp.get(j);
        		}
        	}
        }
        System.out.println(ans);
        
   }
}