import java.io.*;
import java.util.*;

public class Main{
	static int sum;
	static int n = 14;
	static int arr[] = new int[n+1];
	static StringTokenizer st;
	static int calc(int cash, int value, int cnt) {
		return cash + value * cnt;
	}
	
	static int getFlag(int idx) {
		if(arr[idx-3] < arr[idx-2] && arr[idx-2] < arr[idx-1]) {
			return 1;
		}else if(arr[idx-3] > arr[idx-2] && arr[idx-2] > arr[idx-1]) {
			return -1;
		}
		return 0;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sum = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i<=n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int bnpCnt = 0;
		int bnpSum = sum;
		
		
		for(int i = 1; i<=n; i++) {
			if(bnpSum / arr[i] != 0) {
				int cnt = bnpSum / arr[i];
				bnpCnt += cnt;
				bnpSum -= cnt * arr[i];
			}
		}
		
		int bnp = calc(bnpSum, arr[n], bnpCnt);

		int timingCnt = 0;
		int timingSum = sum;
		
		for(int i = 4; i<=n; i++) {
			int flag = getFlag(i);
			if(flag == 1) {
				if(timingCnt != 0) {
					timingSum += timingCnt * arr[i];
					timingCnt = 0;	
				}
			}else if(flag == -1) {
			
				if(timingSum / arr[i] != 0) {
					int cnt = timingSum / arr[i];
					timingCnt += cnt;
					timingSum -= cnt * arr[i];
//					System.out.printf("value: %d, cnt: %d, cash: %d\n", arr[i], timingCnt, timingSum);
				}
			}
		}
		int timing = calc(timingSum,arr[n], timingCnt);
		
		if(bnp > timing) {
			System.out.println("BNP");
		}else if(bnp < timing) {
			System.out.println("TIMING");
		}else {
			System.out.println("SAMESAME");
		}
		
	}
}