import java.io.*;
import java.util.*;
public class Main {
	
	static int n,m,k;
	static StringTokenizer st;
	static int nutrition[][], plus[][], temp[][];
	
	static class Virus implements Comparable<Virus>{
		int y,x,age;
		public Virus(int y,int x,int age) {
			this.y = y;
			this.x = x;
			this.age = age;
		}
		
		public int compareTo(Virus v) {
			return age - v.age;
		}
		
		public String toString() {
			return String.format("y: %d x: %d age: %d ", y,x,age);
		}
	}
	
	static void initialize() {
		for(int y=1;y<=n;y++) {
			for(int x=1; x<=n; x++)
				temp[y][x] = 0;
		}
	}
	
	static ArrayList<Virus> viruses = new ArrayList<>();
	
	static void grow() {
		PriorityQueue<Virus> pq = new PriorityQueue<>(viruses);
		viruses.clear();
		
		initialize();
		while(!pq.isEmpty()) {
			Virus cur = pq.poll();
			
			if(cur.age <= nutrition[cur.y][cur.x]) {
				viruses.add(new Virus(cur.y,cur.x,cur.age+1));
				nutrition[cur.y][cur.x] -= cur.age;
			}else
				temp[cur.y][cur.x] += cur.age/2;
		}
		
		for(int y=1; y<=n; y++) {
			for(int x=1; x<=n; x++)
				nutrition[y][x] += temp[y][x];
		}
		
	}
	
	
	static void printBoard(int board[][]) {
		StringBuilder sb = new StringBuilder();
		for(int y=1; y<=n; y++) {
			for(int x=1; x<=n; x++)
				sb.append(board[y][x]).append(' ');
			sb.append('\n');
		}
		System.out.println(sb);
	}
	
	static int dy[] = {-1,-1,0,1,1,1,0,-1};
	static int dx[] = {0,1,1,1,0,-1,-1,-1};
	static boolean OOB(int y,int x) {
		return y<= 0|| y>n || x<=0 || x>n;
	}
	
	static void breed(Virus virus) {
		for(int dir = 0; dir<8;dir++) {
			int ny = virus.y + dy[dir];
			int nx = virus.x + dx[dir];
			if(OOB(ny,nx))
				continue;
			viruses.add(new Virus(ny,nx,1));
		}
	}
	
	static void breedAll() {
		int size = viruses.size();
		for(int i = 0; i < size;i++) {
			Virus virus = viruses.get(i);
			if(virus.age % 5 == 0)
				breed(virus);
		}
		
	}
	
	static void allPlus() {
		for(int y=1; y<=n; y++) {
			for(int x=1; x<=n; x++)
				nutrition[y][x] += plus[y][x];
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		plus = new int[n+1][n+1];
		nutrition = new int[n+1][n+1];
		temp = new int[n+1][n+1];
		for(int y=1; y<=n; y++) {
			st = new StringTokenizer(br.readLine());
			for(int x=1; x<=n; x++) {
				plus[y][x] = Integer.parseInt(st.nextToken());
				nutrition[y][x] = 5;
			}				
		}
		
		
		for(int i = 0; i<m;i++) {
			st = new StringTokenizer(br.readLine());
			int y= Integer.parseInt(st.nextToken());
			int x= Integer.parseInt(st.nextToken());
			int age= Integer.parseInt(st.nextToken());
			viruses.add(new Virus(y,x,age));
		}

		
//		grow();
//		breedAll();
//		allPlus();
//		System.out.println(viruses.size());
//		printBoard(nutrition);
		
		for(int i = 0; i<k;i++) {
			grow();
			breedAll();
			allPlus();
		}
		System.out.println(viruses.size());
	}
}