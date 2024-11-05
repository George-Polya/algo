import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new double[n + 1];
		for(int i = 1; i <=n; i++) {
			arr[i] = Double.parseDouble(br.readLine());
		}
		
		
		
		double ans = arr[1];
		double cur = arr[1];
		for(int i = 2; i<=n; i++) {
			cur = Math.max(arr[i], cur * arr[i]);
			ans = Math.max(ans,  cur);
		}
		
		System.out.printf("%.3f\n", ans);
	}
	static int n;
	static double  arr[];
}