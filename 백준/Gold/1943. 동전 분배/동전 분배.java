import java.io.*;
import java.util.*;

public class Main {
	
   public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        for(int tc=1; tc<=T; tc++) {
//        	System.out.println("----");
        	n = Integer.parseInt(br.readLine());
        	coins = new Pair[n+1];
        	sum = 0;
        	for(int i = 1; i<=n; i++) {
        		st = new StringTokenizer(br.readLine());
        		int coin = Integer.parseInt(st.nextToken());
        		int count = Integer.parseInt(st.nextToken());
        		sum += coin * count;
        		coins[i] = new Pair(coin, count);
        	}
        	
        	if(sum % 2 == 1) {
        		sb.append("0\n");
        		continue;
        	}
        	
        	half = sum / 2;
        	Arrays.sort(coins, 1,n+1);
        	dp = new boolean[half+1];
        	dp[0] = true;
        	for(int i = 1; i<=n; i++) {
        		int value = coins[i].value;
        		int cnt = coins[i].cnt;
        		for(int j = half; j>=value; j--) {
        			if(!dp[j - value])
        				continue;
        			
        			for(int c = 0; c< cnt; c++) {
        				if( j + value * c > half)
        					break;
        				dp[j + value * c] = true;
        			}
        		}
        	}
        	sb.append(dp[half] ? 1: 0).append('\n');
        	
        	
        }
        System.out.println(sb);
   }
   static int T = 3;
   static int n;
   static int MAX_R = (int)1e5;
   static int sum, half;
   static StringTokenizer st;
   static Pair coins[];
   static boolean dp[];
   
   static class Pair implements Comparable<Pair>{
	   int value, cnt;
	   public Pair(int value, int cnt) {
		   this.value = value;
		   this.cnt = cnt;
	   }
	   public int compareTo(Pair o) {
		   return value - o.value;
	   }
   }
   
   
}