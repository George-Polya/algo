import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class Main {


    public static void main(String[] args)  throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] cnt = new int[10001];


        for (int i = 0; i < n; i++) {
            cnt[Integer.parseInt(br.readLine())]++;
        }

        br.close();

        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < cnt.length; i++) {
            while (cnt[i] > 0) {
                sb.append(i).append("\n");
                
                cnt[i]--;
            }
        }
        System.out.println(sb);
    }


}
