import java.io.*;
import java.util.*;
public class Main {
	static int moveNums[] = new int[10];
	static int arr[] = new int[36];
	
	static final boolean ARRIVED = true;
	static final boolean NOT_ARRIVED = false;
	
	static class Horse{
		int pos;
		boolean state;
		public Horse() {
			this.pos = 0;
			this.state = NOT_ARRIVED;
		}
		
		public Horse(Horse h) {
			this.pos = h.pos;
			this.state = h.state;
		}
		
		public String toString() {
			return this.pos +" ";
		}
		
	}
	
	static Horse horses[];
	static StringTokenizer st;
	static void initialize() {
		horses = new Horse[5];
		for(int i = 0; i<5;i++)
			horses[i] = new Horse();
		arr = new int[]{
	        0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 0,
	        13, 16, 19, 0, 0,
	        22, 24, 0, 0, 0,
	        28, 27, 26, 0, 0,
	        0, 0, 0, 0, 0,
	        25, 30, 35, 40
	    };	
	}
	
	
	
	static int ans;
	static int END = 20;
	static int START = 0;
	
	static int getNextPos(int curPos, int moveNum) {
		if(curPos == END)
			return END;
		if(moveNum == 0)
			return curPos;
		int nxtPos = curPos + 1;
		if(curPos == 23 || curPos == 27 || curPos == 33)
			nxtPos = 41;
		else if(curPos == 19)
			nxtPos = 44;
		else if(curPos == 44)
			nxtPos = END;
		
		return getNextPos(nxtPos, moveNum - 1);
	}
	
	static boolean isBlue(int curPos) {
		return curPos != START && curPos % 5 == 0;
	}
	
	static boolean isOverlapped() {
		for(int i = 0; i<5;i++) {
			for(int j = i + 1; j<5;j++) {
				int pos1 = horses[i].pos;
				int pos2 = horses[j].pos;
				
				if(pos1 == pos2 && pos1 != START && pos1 != END)
					return true;
			}
		}
		return false;
	}
	
	static void solve(int cur, int sum) {
		if(cur == 10) {
			ans = Math.max(ans, sum);
			
			return;
		}
		
		for(int i = 0; i< 5; i++) {
			if(horses[i].pos == END)
				continue;
			Horse temp = new Horse(horses[i]);
			if(isBlue(horses[i].pos))
				horses[i].pos = getNextPos(horses[i].pos + 16, moveNums[cur] - 1);
			else
				horses[i].pos = getNextPos(horses[i].pos, moveNums[cur]);
			if(!isOverlapped())
				solve(cur + 1, sum + arr[horses[i].pos]);
			
			horses[i] = temp;
			
		
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i< 10;i++)
			moveNums[i] = Integer.parseInt(st.nextToken());
		initialize();

//		int ret = 0;
//		for(int i = 0; i< 10;i++) {
//			int move = move(moveNums[i], horses[0]);
//			ret += move;
//		}
//		System.out.println(ret);
		solve(0,0);
		System.out.println(ans);
		
		
	}
}