import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 1. 입력값이 10101인 경우 -> 1의 개수는 45개
 * 2. 10000보다 작은 수의 1 개수 카운트 -> dp[4] = 32
 * 3. 000~101 개수 카운트 -> 해당 값은 맨 앞자리 1의 개수 -> 0~ 5이므로 -> 6
 * 4. xx101에 대한 계산 -> 101에 대한 카운트 시작
 * 5. 100보다 작은 수의 1 개수 카운트 -> dp[2] = 4
 * 6. 00~01 개수 -> 2
 * 7. 1에 대해 카운트 -> 1
 */

public class Main {
    static long[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long A, B;
        String[] str = br.readLine().split(" ");
        A = Long.parseLong(str[0]);
        B = Long.parseLong(str[1]);

        int size = Long.toBinaryString(B).length();
        dp = new long[size + 1];
        dp[1] = 1; // 2 보다 작은 수들 중 1의 개수
        for (int i = 2; i <= size; i++) {
            dp[i] = dp[i - 1] * 2 + (1L << (i - 1));
        }

        System.out.println(calc(B) - calc(A - 1));
    }

    public static long calc(long num) {
        if (num == 0L) return 0;
        else if (num == 1L) return 1;

        int size = Long.toBinaryString(num).length();
        long next = num - (1L << (size - 1));
        return dp[size - 1] + next + 1 + calc(next);
    }
}