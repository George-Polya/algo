import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = br.readLine().toCharArray();
		
		int bCnt = 0;
		int rCnt = 0;
		char cur = 'x';
		
		for(int i = 0; i < n; i++) {
			if(cur != arr[i]) {
				if(arr[i] == 'R') {
					rCnt++;
				}else {
					bCnt++;
				}
			}
			
			cur = arr[i];
		}
		System.out.println(Math.min(rCnt, bCnt) + 1);
	}
	static int n;
	static char arr[];
}