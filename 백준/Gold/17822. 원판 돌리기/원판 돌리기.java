import java.util.*;
import java.io.*;

public class Main {
   static int n,m,q;
   static StringTokenizer st;
   static int board[][], nxtBoard[][];
   
   static void shift(int arr[], int d, int k) {
	   for(int i =0; i< k % m; i++) {
		   if(d == 0) {
			   int temp = arr[m];
			   for(int x = m; x>=2; x--)
				   arr[x] = arr[x-1];
			   arr[1] = temp;
		   }else {
			   int temp = arr[1];
			   for(int x = 1; x<=m-1; x++)
				   arr[x] = arr[x+1];
			   arr[m] = temp;
		   }
	   }
   }
   
   static void rotate(int mod, int d, int k) {
	   for(int y=1; y<=n; y++) {
		   if(y % mod == 0) {
			   shift(board[y], d,k);
		   }
	   }
   }
   
   static void copy(int dst[][], int src[][]) {
	   for(int y=1; y<=n; y++) {
		   for(int x=1; x<=m; x++)
			   dst[y][x] = src[y][x];
	   }
   }
   static boolean removed[][];
   static boolean remove() {
//	   copy(nxtBoard, board);
	   int cnt = 0;
	   
	   for(int y=1; y<=n; y++) {
		   for(int x=1; x<=m; x++)
			   removed[y][x] = false;
	   }
	   
	   for(int y=1; y<=n; y++) {
		   if(board[y][1] != 0 && (board[y][1] == board[y][2] || board[y][1] == board[y][m])) {
//			   nxtBoard[y][1] = 0;
//			   cnt++;
			   removed[y][1] = true;
		   }
		   
		   if(board[y][m] != 0 && (board[y][m] == board[y][m-1] || board[y][1] == board[y][m])) {
//			   nxtBoard[y][m] = 0;
//			   cnt++;
			   removed[y][m] = true;
		   }
		   
		   for(int x=2; x<=m-1; x++) {
			   if(board[y][x] != 0 && (board[y][x] == board[y][x-1] || board[y][x] == board[y][x+1])) {
//				   nxtBoard[y][x] = 0;
//				   cnt++;
				   removed[y][x] = true;
			   }
		   }
	   }
	   
	   for(int x=1; x<=m; x++) {
		   if(board[1][x] != 0 && board[1][x] == board[2][x] ) {
//			   if(nxtBoard[1][x] != 0) {
//				   nxtBoard[1][x] = 0;
//				   cnt++;
//			   }
			   removed[1][x] = true;
		   }
		   
		   if(board[n][x] != 0 && board[n][x] == board[n-1][x]) {
//			   if(nxtBoard[n][x] != 0) {
//				   nxtBoard[n][x] = 0;
//				   cnt++;
//			   }
			   removed[n][x] = true;
		   }
		   for(int y=2; y<=n-1; y++) {
			   if(board[y][x] != 0 && (board[y][x] == board[y-1][x] || board[y][x] == board[y+1][x])) {
//				   if(nxtBoard[y][x] != 0) {
//					   nxtBoard[y][x] = 0;
//					   cnt++;
//				   }
				   removed[y][x] = true;
			   }
		   }
	   }
	   
	   boolean isRemoved = false;
	   for(int y=1; y<=n; y++) {
		   for(int x=1; x<=m; x++) {
			   if(removed[y][x]) {
				   isRemoved = true;
				   board[y][x] = 0;
			   }
		   }
	   }
	   
	   return isRemoved;
	   
//	   copy(board,nxtBoard);
//	   return cnt;
   }
   
   static void normalize() {
	   double total = 0;
	   int cnt = 0;
	   for(int y=1; y<=n; y++) {
		   for(int x=1; x<=m; x++) {
			   if(board[y][x] == 0) 
				   continue;
			   total += board[y][x];
			   cnt++;
		   }
	   }
	   
	   total /= cnt;
	   
	   for(int y=1; y<=n; y++) {
		   for(int x=1; x<=m; x++) {
			   if(board[y][x] == 0)
				   continue;
			   if(board[y][x] > total) {
				   board[y][x] -= 1;
			   }else if(board[y][x] < total) {
				   board[y][x] += 1;
			   }
		   }
	   }
   }
   
   static boolean check() {
	   for(int y=1; y<=n; y++) {
		   for(int x=1; x<=m; x++) {
			   if(board[y][x] != 0)
				   return true;
		   }
	   }
	   return false;
   }
   
   static void simulate(int mod, int d, int k) {
	   rotate(mod,d,k);
	   boolean isDone = remove();
	   if(!isDone)
		   normalize();
   }
   
   static void printBoard(int board[][]) {
	   for(int y=1; y<=n; y++) {
		   for(int x=1; x<=m; x++)
			   System.out.print(board[y][x]+" ");
		   System.out.println();
	   }
	   System.out.println();
   }
   
   public static void main(String[] args) throws IOException{
	   BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	   st = new StringTokenizer(br.readLine());
	   n = Integer.parseInt(st.nextToken());
	   m = Integer.parseInt(st.nextToken());
	   q = Integer.parseInt(st.nextToken());
	   board = new int[n+1][m+1];
//	   nxtBoard = new int[n+1][m+1];
	   removed = new boolean[n+1][m+1];
	   for(int y=1; y<=n; y++) {
		   st = new StringTokenizer(br.readLine());
		   for(int x=1; x<=m; x++)
			   board[y][x] = Integer.parseInt(st.nextToken());
	   }
	   
	   for(int i = 0; i<q;i++) {
		   st = new StringTokenizer(br.readLine());
		   int x = Integer.parseInt(st.nextToken());
		   int d = Integer.parseInt(st.nextToken());
		   int k = Integer.parseInt(st.nextToken());
		   simulate(x,d,k);
	   }
	   
	   int ans = 0;
	   for(int y=1; y<=n; y++) {
		   for(int x=1; x<=m; x++)
			   ans += board[y][x];
	   }
	   
//	   printBoard(board);
	   
	   System.out.println(ans);
   }
}