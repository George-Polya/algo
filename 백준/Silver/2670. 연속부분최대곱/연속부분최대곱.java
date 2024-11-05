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
		
		double ans = -1;
		for(int s = 1; s<=n; s++) {
			double p = arr[s];
			ans = Math.max(ans, p);
			
			for(int e = s + 1; e <= n; e++) {
				p *= arr[e];
				ans = Math.max(ans,p);
			}
		}
		System.out.printf("%.3f\n", ans);
	}
	static int n;
	static double  arr[];
}