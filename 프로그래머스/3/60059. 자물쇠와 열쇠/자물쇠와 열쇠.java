class Solution {
    int board[][] = new int[60][60];
    int key[][], lock[][];
    int n,m;
    int cnt;
     void rotate(){
        int temp[][] = new int[m][m];
        for(int y = 0; y<m; y++){
            for(int x=0; x<m; x++){
                temp[y][x] = key[m-1-x][y];
            }
        }
        
        key = temp;
    }
    
     void printBoard(int board[][]){
        int len = board.length;

        for(int y=0 ; y<len;y++){
            for(int x=0; x<len;x++){
                System.out.printf("%3d", board[y][x]);
            }
            System.out.println();
        }
    }
    
    boolean inRange(int y,int x){
        return 20 <= y && y< n + 20 && 20<=x && x < n + 20;
    }
        
    boolean check(int sy,int sx){
        int count = 0;
        
        for(int y = sy; y < sy + m; y++){
            for(int x = sx; x<sx +m; x++){
                if(board[y][x] == 1 && key[y-sy][x-sx] == 1)
                    return false;
                
                if(inRange(y,x) && board[y][x] == 0 && key[y-sy][x-sx] == 1){
                    count++;
                }
            }            
        }
        
        
        return cnt == count;
    }
    
    public boolean solution(int[][] _key, int[][] _lock) {
        key = _key;
        lock = _lock;
        boolean answer = false;
        n = lock.length;
        m = key.length;

        
        for(int y= 20; y<20 + n; y++){
            for(int x=20; x<20 + n; x++){
                board[y][x] = lock[y-20][x-20];
                if(board[y][x] == 0)
                    cnt++;
            }
        }
        
        for(int i =0; i < 4; i++){
            // System.out.println("---");
            rotate();
            for(int y=1; y< 40;y++){
                for(int x = 1; x<40; x++){
                    if(check(y,x))
                        answer = true;
                }
            }
            // printBoard(key);
        }
        
        
        
        
        
        
        return answer;
    }
}