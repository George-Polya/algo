import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 입력 받기
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        // LIS 알고리즘 적용
        int[] dp = new int[N];  // 각 위치에서의 LIS 길이
        int[] lis = new int[N]; // LIS를 구성하는 수들
        int[] idx = new int[N]; // lis 배열에서의 인덱스 추적
        int len = 0;
        
        Arrays.fill(dp, -1);
        
        for (int i = 0; i < N; i++) {
            int pos = Arrays.binarySearch(lis, 0, len, arr[i]);
            if (pos < 0) pos = -pos - 1;
            lis[pos] = arr[i];
            dp[i] = pos;
            if (pos == len) len++;
        }
        
        // 부분 수열 복원
        int[] result = new int[len];
        int idxToFind = len - 1;
        for (int i = N - 1; i >= 0; i--) {
            if (dp[i] == idxToFind) {
                result[idxToFind] = arr[i];
                idxToFind--;
            }
        }
        
        // 결과 출력
        StringBuilder sb = new StringBuilder();
        sb.append(len).append('\n');
        for (int i = 0; i < len; i++) {
            sb.append(result[i]).append(' ');
        }
        System.out.println(sb.toString());
    }
}