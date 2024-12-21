// https://www.acmicpc.net/problem/1806
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }


        int right = 0, sum = arr[0], ans = INF;
        for (int left = 0; left< n; left++) {
            while (right < n - 1 && sum < s) {
                right += 1;
                sum += arr[right];
            }

            //  [left, ... , right]의 합, 즉 sum이 조건을 만족하면 정답갱신
            if(sum >= s)
                ans = Math.min(ans, right-left+1);
            
            sum -= arr[left];
        }

        System.out.println(ans == INF ? 0 : ans);
    }
    
    static int n, s;
    static int arr[];

    static int INF = Integer.MAX_VALUE;


}