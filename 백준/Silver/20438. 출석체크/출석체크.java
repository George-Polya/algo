import java.io.*;
import java.util.*;

public class Main {
	static int n,k,q,m;
	static StringTokenizer st;
	static int arr[], pSum[];
	static Set<Integer> sleeps = new HashSet<>();
	static int visited[];
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		q = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < k;i++) {
			sleeps.add(Integer.parseInt(st.nextToken()));
		}
		
		visited = new int[n+3];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i<q;i++) {
			int student = Integer.parseInt(st.nextToken()); // 출석코드를 받은 학생 
			
			if(sleeps.contains(student)) // 조는 학생은 코드를 보내지 않는다 
				continue;
			
			if(visited[student] != 0) // 이미 출석코드를 받은 학생 
				continue;
			
			for(int j = student; j<=n+2;j++) {
				if(j % student == 0) {
					if(sleeps.contains(j))
						continue;
					visited[j] = 1;
				}
			}
			
			
		}
		
		pSum = new int[n+3];
		for(int i =3 ; i<=n+2;i++) {
			pSum[i] = pSum[i-1] + ((visited[i] == 0) ? 1 : 0);
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i<m;i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			sb.append(pSum[end] - pSum[start-1]).append('\n');
		}
		System.out.println(sb);
		
		
	}
}