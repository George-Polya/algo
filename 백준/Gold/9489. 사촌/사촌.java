import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringBuilder sb = new StringBuilder();
        while(true) {
        	st = new StringTokenizer(br.readLine());
        	n = Integer.parseInt(st.nextToken());
        	k = Integer.parseInt(st.nextToken());
        	if(n == 0 && k == 0)
        		break;
        	
        	arr = new int[n+1];
        	parent = new int[n+1];
        	arr[0] = -1;
        	parent[0] = -1;
        	int idx = -1;
        	
        	int target = -1;
        	st = new StringTokenizer(br.readLine());
        	for(int i = 1; i<=n; i++) {
        		arr[i] = Integer.parseInt(st.nextToken());
        		if(arr[i] == k)
        			target = i;
        		
        		if(arr[i] != arr[i-1] + 1)
        			idx++;
        		
        		parent[i] = idx;
        	}
        	
        	int cnt = 0;
        	
        	for(int i = 1; i<=n; i++) {
        		if(i == target)
        			continue;
        		if(parent[target] != parent[i] && parent[parent[target]] == parent[parent[i]])
        			cnt++;
        	}
        	
        	sb.append(cnt).append('\n');
        	
        }
        System.out.println(sb);
    }
    static int n,k;
    static StringTokenizer st;
    static int arr[], parent[];
}