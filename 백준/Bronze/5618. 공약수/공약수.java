import java.util.*;
import java.io.*;



public class Main {
	static int n;
	static int arr[];
	static StringTokenizer st;
	
	static int gcd(int a, int b) {
		if(b == 0)
			return a;
		
		return gcd(b, a % b);
	}
	
	static int GCD(int arr[]) {
		if(arr.length == 1)
			return arr[0];
		
		int temp[] = new int[arr.length-1];
		for(int i = 0; i < temp.length;i++) {
			temp[i] = arr[i+1];
		}
		
		return gcd(arr[0], GCD(temp));
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i <n;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int gcd = GCD(arr);
		
//		System.out.print(gcd);
		
		for(int i = 1; i<= gcd;i++) {
			if(gcd % i == 0)
				System.out.println(i);
		}
	}
}