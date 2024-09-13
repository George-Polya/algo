import java.io.*;
import java.util.*;
public class Main {
	static int n;
	static int arr[];
	static StringTokenizer st;
	static int c[];
	static int INT_MAX = Integer.MAX_VALUE;
	static int lowerBound(int target,int L, int R) {
		int minIdx = n + 1;
		
		while(L<=R) {
			int mid = (L+R) / 2;
			if(target <= c[mid]) {
				R = mid - 1;
				minIdx = Math.min(minIdx, mid);
			}else {
				L = mid + 1;
			}
		}
		return minIdx;
	}
	static int indexArr[];
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n+1];
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i<=n; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		c = new int[n+1];
		Arrays.fill(c, INT_MAX);
//		c[1] = arr[1];
		indexArr = new int[n+1];
		int length = 1;
		
		for(int i = 1; i<=n; i++) {
			int target = arr[i];
			
			if(c[length] < target) {
				length++;
				c[length] = target;
				indexArr[i] = length;
			}else {
				int minIdx = lowerBound(target, 1, length + 1);
				
				c[minIdx] = target;
				indexArr[i] = minIdx;
			}
		}
		StringBuilder sb = new StringBuilder();
		sb.append(length).append('\n');

		int idx = length;
		Stack<Integer> stk = new Stack<>();
		for(int i = n; i>0;i--) {
			if(indexArr[i] == idx) {
				idx--;
				stk.push(arr[i]);
			}
		}
		
		while(!stk.isEmpty()) {
			sb.append(stk.pop()).append(' ');
		}
		
		
		System.out.println(sb);
	}

}