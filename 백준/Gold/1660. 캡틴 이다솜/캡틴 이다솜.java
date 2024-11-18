import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        sc.close();

        // 사면체 수 리스트 생성
        ArrayList<Integer> tetrahedralNumbers = new ArrayList<>();
        int num = 1;
        int i = 1;
        while (num <= N) {
            tetrahedralNumbers.add(num);
            i++;
            num = i * (i + 1) * (i + 2) / 6;
        }

        // DP 배열 초기화
        int[] dp = new int[N + 1];
        for (i = 1; i <= N; i++) {
            dp[i] = Integer.MAX_VALUE;
        }

        // DP를 이용한 최소 개수 계산
        for (int t : tetrahedralNumbers) {
            for (i = t; i <= N; i++) {
                if (dp[i - t] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i - t] + 1);
                }
            }
        }

        System.out.println(dp[N]);
    }
}