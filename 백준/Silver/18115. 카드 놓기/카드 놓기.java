import java.io.*;
import java.util.*;


public class Main {
	static Deque<Integer> deq = new ArrayDeque<>();
	static int n;
	static int A[];
	static int arr[];
	static StringTokenizer st;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		A = new int[n];
		arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i<n;i++) {
			A[i] = Integer.parseInt(st.nextToken());
			arr[i] = i + 1;
		}
		
		for(int i =0; i < n; i++) {
			int num = arr[i];
			switch(A[n -  1 - i]) {
			case 1:{
				deq.offerFirst(num);
				break;
			}
			case 2:{
				int temp = deq.pollFirst();
				deq.offerFirst(num);
				deq.offerFirst(temp);
				break;
			}
			case 3:{
				deq.offerLast(num);
				break;
			}
			}
		}
		StringBuilder sb = new StringBuilder();
		while(!deq.isEmpty()) {
			sb.append(deq.pollFirst()).append(' ');
		}
		System.out.println(sb);
	}
}