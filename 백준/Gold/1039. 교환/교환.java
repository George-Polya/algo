import java.util.*;
import java.io.*;
public class Main {
   public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        str = st.nextToken();
        n = Integer.parseInt(str);
        k = Integer.parseInt(st.nextToken());
        m = calc(n);
        selected = new int[2];
        combination(0,0, 0);
        
        Queue<Integer> q = new ArrayDeque<>();
        q.add(n);
        
       

        visited = new int[MAX_R+1];
        while(!q.isEmpty() && k > 0) {
        	int size = q.size();
        	
        	for(int s = 0; s < size; s++) {
        		int cur = q.poll();
        		
        		ArrayList<Integer> nxts  = getNxts(String.valueOf(cur));
        		
        		for(int nxt : nxts) {
        			if(nxt == -1 || visited[nxt] == k)
        				continue;
        			
        			q.add(nxt);
        			visited[nxt] = k;
        		}
        	}
        	
        	k--;
       }
        
        if(q.isEmpty()) {
        	System.out.println(-1);
        }else {
            int ans = Integer.MIN_VALUE;
        	while(!q.isEmpty())
        		ans = Math.max(ans, q.poll());
        	System.out.println(ans);
        }
        
        
   }
   
   static int visited[];
   
   static ArrayList<Integer> getNxts(String str){
	   ArrayList<Integer> ret = new ArrayList<>();
	   
	   for(int[] s : selecteds) {
		   int i = s[0];
		   int j = s[1];
		   
		   
		   int swapped = swap(i,j,str);
		   
		   if(swapped != -1)
			   ret.add(swapped);
		   
	   }
	   
	   return ret;
   }
   
   static int swap(int i, int j, String str) {
	   if(str == null)
		   return -1;
	   char chars[] = str.toCharArray();
	   
	   char temp = chars[i];
	   chars[i] = chars[j];
	   chars[j] = temp;
	   
	   if(chars[0] == '0')
		   return -1;
	   
	
	   
	   return Integer.valueOf(new String(chars));
   }
   
   static String str;
   static int MAX_R = (int)1e6;
   static void combination(int cur, int cnt, int bit) {
	   if(cnt == 2) {
		   int temp[] = new int[2];
		   for(int i = 0; i < 2; i++)
			   temp[i] = selected[i];
		   selecteds.add(temp);
		   return;
	   }
	   
	   
	   for(int i = cur; i < m; i++) {
		   selected[cnt] = i;
		   combination(i + 1, cnt + 1, bit);
	   }
   }
   
   static int selected[];
   
   static int calc(int num) {
	   int ret = 0;
	   
	   while(num != 0) {
		   num /= 10;
		   ret++;
	   }
	   return ret;
   }
   
   static int n,m,k;
   static StringTokenizer st;
   static ArrayList<int[]> selecteds = new ArrayList<>();
}