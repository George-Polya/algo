import java.io.*;
import java.util.*;
public class Main {
    		
	static int n;
    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        
        int ans = -1;
        for(int i = 0; i < n; i++) {
        	int number = i;
        	int sum = 0;
        	
        	while(number != 0) {
        		sum += number % 10;
        		number /= 10;
        	}
        	
        	if(sum + i == n) {
        		ans = i;
        		break;
        	}
        	
        }
        
        System.out.println(ans == -1 ? 0 : ans);
    }
}