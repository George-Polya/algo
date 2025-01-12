import java.io.*;
import java.util.*;

public class Main {

    static int[][] board = new int[151][151]; // 전체 액자 크기
    static int[][] board1 = new int[51][51]; // 퍼즐 1
    static int[][] board2 = new int[51][51]; // 퍼즐 2
	static StringTokenizer st;

    static int n1, n2, m1, m2;
    static int ans = Integer.MAX_VALUE;

    public static void rotate() {
        int[][] tmp = new int[51][51];
        for (int i = m1 - 1; i >= 0; i--) {
            for (int j = 0; j < n1; j++) {
                tmp[m1 - 1 - i][j] = board1[j][i];
            }
        }

        for (int i = 0; i <= 50; i++) {
            for (int j = 0; j <= 50; j++) {
                board1[i][j] = tmp[i][j];
            }
        }

        int temp = n1;
        n1 = m1;
        m1 = temp;
    }
    
    static boolean check(int sy,int sx) {
    	for(int y = sy; y < sy + n1; y++) {
    		for(int x=sx; x < sx + m1; x++) {
    			if(board[y][x] ==1 && board1[y-sy][x-sx] == 1)
    				return false;
    		}
    	}
    	return true;
    }

    public static void go(int sy, int sx) {
        

        if (check(sy,sx)) {
            // 넓이 계산
            int minY = Math.min(sy, 50);
            int maxY = Math.max(sy + n1 - 1, 49 + n2);
            int minX = Math.min(sx, 50);
            int maxX = Math.max(sx + m1 - 1, 49 + m2);

            int area = (maxY - minY + 1) * (maxX - minX + 1);
            ans = Math.min(ans, area);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        n1 = Integer.parseInt(st.nextToken());
        m1 = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n1; i++) {
            String line = br.readLine();
            for (int j = 0; j < m1; j++) {
                board1[i][j] = line.charAt(j) - '0';
            }
        }

        st = new StringTokenizer(br.readLine());
        n2 = Integer.parseInt(st.nextToken());
        m2 = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n2; i++) {
            String line = br.readLine();
            for (int j = 0; j < m2; j++) {
                board2[i][j] = line.charAt(j) - '0';
            }
        }

        // 퍼즐 2는 가운데 고정시켜두기
        for (int i = 50; i < 50 + n2; i++) {
            for (int j = 50; j < 50 + m2; j++) {
                board[i][j] = board2[i - 50][j - 50];
            }
        }

        // 퍼즐을 돌리면서 확인 (총 4번)
        for (int k = 0; k < 4; k++) {
            rotate();
            for (int i = 0; i < 100; i++) {
                for (int j = 0; j < 100; j++) {
                    go(i, j);
                }
            }
        }

        System.out.println(ans);
    }
}
