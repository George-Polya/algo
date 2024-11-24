import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        board = new int[n][n]; // 0-index 기반으로 수정

        int cnt = 0;
        for (int y = 0; y < n; y++) { // 0부터 시작하도록 수정
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < n; x++) { // 0부터 시작하도록 수정
                board[y][x] = Integer.parseInt(st.nextToken());
                if(board[y][x] != 0)
                	cnt++;
            }
        }
        int turn = cnt % 2 == 0 ? 1 : 2;
        
        int ans = solve(turn);
        
        if(ans == 0) {
        	System.out.println("D");
        }else if(ans == 1) {
        	System.out.println("W");
        }else {
        	System.out.println("L");
        }
    }
    
    
    static int solve(int player) {
    	if(check(3 - player)) // 상대방이 이김 
    		return -1; // 나는 짐 
    	
    	int min = 2; // 상대방의 최선의 승패 
    	
    	for(int y=0; y<n; y++) {
    		for(int x=0; x<n; x++) {
    			if(board[y][x] == 0) {
    				board[y][x] = player; // (y,x)에 player가 두었음
    				
    				
    				// 동일하지 않다면 다음턴으로 넘김 
    				min = Math.min(min, solve(3 - player));
    				
    				board[y][x] = 0;
    			}
    		}
    	}
    	
    	
    	if(min == 0 || min == 2) // 이도저도 아님 
    		return 0;
    	else  // 상대방이 짐 
    		return -min; // 나는 이김 
    }
    
    static boolean check(int player) {
    	// 가로,세로
    	for(int i = 0; i < n; i++) {
    		// 가로
    		if(board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] == player) 
    			return true;
    		
    		
    		//세로
    		if(board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] == player)
    			return true;
    		
    	}
    	
    	
    	// 주대각선
    	if(board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] == player)
    		return true;
    	
    	// 부대각선
    	if(board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] == player)
    		return true;
    	
    	
    	return false; // 승자없음
    }


    static StringTokenizer st;
    static int n = 3;
    static int board[][];
}