import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * [문제 해석]
 * D : 가로선 중심 아랫 면으로 접기
 * U : 가로선 중심 윗면으로 접기
 * R : 세로선 중심 오른쪽 면으로 접기
 * L : 세로선 중심 왼쪽 면으로 접기
 * 
 * k : 접는 횟수
 * 2k개의 문자가 주어짐
 * 
 * h구멍을 뚫는 위치 h가 주어짐
 * 
 * [문제 해결에 대한 고민]
 * 
 * [시작 위치(sr,sc) 찾기]
 * 
 * k=2인 경우,
 * 
 * rowLength=2^k = 1<<k
 * colLength=2^k = 1<<k
 * 
 * 시작 위치 인덱스를
 * sr = 0
 * sc = 0
 * 으로 두고
 * 
 * D - 아래로 접힐 때, 현재 종이의 세로 크기의 반만큼 인덱스 증가
 * rowLength /=2;
 * sr += rowLength;
 * 
 * U - 위로 접힐 때, 시작인덱스는 변하지 않으나 세로 크기만 반 줆.
 * rowLength /=2;
 * 
 * R - 오른쪽으로 접힐 때, 현재 종이의 가로 크기의 반만큼 인덱스 증가
 * colLength /= 2;
 * sc += colLength;
 * 
 * L - 왼쪽으로 접힐 때, 시작인덱스는 변하지 않으나 가로 크기만 반 줆.
 * colLength /=2;
 * 
 * 입력 값에 따라 위 계산을 반복하면
 * 시작인덱스 (sr, sc)를 찾을 수 있음
 * 
 * [대칭 규칙]
 * 0인 경우
 * 상하 2
 * 좌우 1
 * 
 * 1인 인우
 * 상하 3
 * 좌우 0
 * 
 * 2인 경우
 * 상하 0
 * 좌우 3
 * 
 * 3인 경우
 * 상하 1
 * 좌우 2
 * 
 * 위 정보를 통해 현재 위치의 번호가 num인 경우 아래와 같은 식을 적용할 수 있음
 * 상하
 * num = (num + 2)%4
 * 좌우
 * num = num%2==0? num+1 : num-1;
 * 
 * 
 * 
 * [문제 해결 프로세스]
 * 1. 접었을 때 시작 면(sr,sc)을 찾기
 * 2. 시작 위치(sr, sc)에 숫자값(h) 담기
 * 3. 기준점(r,c)으로부터 대칭 규칙을 이용해 배열에 값을 채워 넣기
 * 		=> (sr,sc)좌표를 처음기준점으로 사방씩 채워나가는 과정을 반복(재귀)
 * 4. 출력
 * @author SSAFY
 *
 */
public class Main {
	//상하좌우
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	static int k;	//접는 횟수
	static int h;	//구멍을 뚫는 위치
	
	static int length;	//종이의 크기
	static int[][] result;	//결과값
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		k = Integer.parseInt(br.readLine());
		
		length = 1<<k;
		
		//step 01. 접었을 때 시작 면(sr,sc)을 찾기
		int rowLength = length;	//현재까지 접은 종이의 세로의 크기
		int colLength = length;	//현재까지 접은 종이의 가로 크기
		int sr = 0;
		int sc = 0;
		StringTokenizer st= new StringTokenizer(br.readLine());
		for(int i=0; i<2*k; i++) {
			char cmd = st.nextToken().charAt(0);
			switch (cmd) {
			case 'D':
				rowLength /=2;
				sr += rowLength;
				break;
			case 'U':
				rowLength /=2;
				break;
			case 'R':
				colLength /=2;
				sc += colLength;
				break;
			default:
				colLength /=2;
				break;
			}
			//체크 용도
//			System.out.printf("가로길이:%d, 세로길이:%d%n",rowLength,colLength);
//			System.out.printf("시작 좌표 (sr,sc):(%d,%d)%n",sr,sc);
		}
		
		//결과배열에 의미없는 -1로 채워넣기(값을 채워넣을 때 반복처리 방지 위함)
		result = new int[length][length];
		for(int i=0; i<length; i++) Arrays.fill(result[i], -1);

		//step 02. 시작 위치에 숫자값 넣기
		h = Integer.parseInt(br.readLine());
		result[sr][sc] = h;
		 
		//step 03. 대칭 규칙을 사용해 채워 넣기
		fill(sr,sc);
		
		//step 04. 출력
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<length; i++) {
			for(int j=0; j<length; j++) {
				sb.append(result[i][j]).append(' ');
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}

	/**
	 * (r,c) 좌표 기준 4방에 값 채워넣고 다음 좌표(nr,nc)기준 4방
	 * @param r
	 * @param c
	 */
	private static void fill(int r, int c) {
		for(int d=0; d<4; d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			
			//경계를 벗어나거나 이미 채워진 경우, 다음 방향
			if(nr<0 || nr>=length || nc<0 || nc>=length) continue;
			if(result[nr][nc]!=-1) continue;	
			
			//상하 채우기
			if(d<=1) {
				result[nr][nc] = (result[r][c]+2)%4;
			}
			//좌우 채우기
			else {
				result[nr][nc] = result[r][c]%2==0? result[r][c]+1 : result[r][c]-1 ;
			}
			//(nr,nc) 기준 4방 채우기는 재귀로 넘김
			fill(nr,nc);
		}
	}
}