import java.io.*;
import java.util.*;

public class Main {
	static int n,m,k,T;
	static int arr[];
	static String codes, inputs;
	static StringTokenizer st;
	static int MOD = 256;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int t=0; t<T; t++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());//배열(메모리) 크기
			m = Integer.parseInt(st.nextToken());//명령 = 코드 크기
			k = Integer.parseInt(st.nextToken());//입력 = 문자열
			arr = new int[n];//배열(메모리)
			int p = 0, inp=0;
			codes = br.readLine();
			inputs = br.readLine();
			
			//괄호 위치 저장
			int stoe[] = new int[m];
			int etos[] = new int[m];
			Stack<Integer> stk = new Stack<>();
			for(int i=0; i<m; i++) {
				if(codes.charAt(i) == '[') {
					stk.push(i);
				}
				else if(codes.charAt(i)==']') {
					int pos = stk.pop();
					stoe[pos] = i;
					etos[i] = pos;
				}
			}
			
			//명령어 실행
			int cnt = 0;//명령수행횟수
			int result = 0;
			for(int i=0; i<m; i++) {
				
				switch(codes.charAt(i)) {
				case '+':
					arr[p] = (arr[p]+1) % MOD;
					break;
				case '-':
					arr[p] = (arr[p]+255) % MOD;
					break;
				case '<':
					p = (p-1+n)%n;
					break;
				case '>':
					p = (p+1)%n;
					break;
				case '[':
					if(arr[p]==0) {//루프 안 해도된다.
						//']'찾기
						i = stoe[i];
					}
					break;
				case ']':
					if(arr[p]!=0) {
						if(cnt>50_000_000) {
							result = Math.max(result, i);
						}
						i = etos[i];//시작 위치로!
					}
					break;
				case ',':
					//입력이 더이상 없을 때
					arr[p] = (inp>=k) ? 255 : inputs.charAt(inp++);
					break;
				default:
					continue;
				}
				cnt++;
				if(cnt>100_000_000) {
					break;
				}
			}
			
			
			//출력
			if(cnt>50_000_000) {
				sb.append("Loops ").append(etos[result]).append(" ").append(result).append("\n");
			}
			else{
				sb.append("Terminates\n");
			}
		}
		System.out.println(sb.toString());
	}
}