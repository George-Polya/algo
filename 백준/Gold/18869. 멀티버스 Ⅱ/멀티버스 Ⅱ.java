import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		arr = new int[m][n];
		
		for(int y = 0; y<m; y++) {
			st = new StringTokenizer(br.readLine());
			for(int x = 0; x<n; x++) {
				arr[y][x] = Integer.parseInt(st.nextToken());
			}
			
			compress(arr[y]);
		}
		
		int ans = 0;
		
		for(int i = 0; i < m - 1; i++) {
			for(int j = i + 1; j < m; j++) {
				if(isEqual(arr[i], arr[j]))
					ans++;
			}
		}
		System.out.println(ans);
	}
	
	static boolean isEqual(int a[], int b[]) {
		for(int k = 0; k < n; k++) {
			if(a[k] != b[k])
				return false;
		}
		return true;
	}
	
	static void compress(int arr[]) {
		List<Integer> v = new ArrayList<>();
		for(int value : arr)
			v.add(value);
		
		Collections.sort(v);
		v = new ArrayList<>(new LinkedHashSet<>(v));
		
		for(int i = 0; i < n; i++) {
			arr[i] = Collections.binarySearch(v, arr[i]);
		}
//		System.out.println(Arrays.toString(arr));
	}
	
	static StringTokenizer st;
	static int n,m;
	static int arr[][];
}