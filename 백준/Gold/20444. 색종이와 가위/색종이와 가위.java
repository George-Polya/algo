import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        long k = sc.nextLong();
        sc.close();

        // (h+1)(v+1) = k, h+v = n
        // x = h+1 => v = n-h, (h+1)(n-h+1) = k => x(n+2-x) = k
        // x^2 - (n+2)x + k = 0 이차방정식의 해를 찾아야 함.

        // D = (n+2)^2 - 4k
        // 오버플로우 방지:
        // 1. (n+2)^2는 최대 (2^31+1)^2 ~ 4.611686e18 정도, long 범위 내.
        // 2. k가 매우 클 수 있으므로 4*k 계산 전 비교.
        
        // 먼저 (n+2)^2 계산
        long n2 = n + 2; 
        // (n+2)^2 계산
        // n 최대 2^31-1 -> (n+2)^2 < 2^63 범위 내 (안전)
        long sq = (long)n2 * (long)n2;
        
        // 4*k <= sq ? 확인
        // k <= sq/4
        // sq/4 연산: sq는 long 범위 내, 나눗셈 안전.
        long limit = sq / 4;
        if (k > limit) {
            // k가 너무 커서 D<0
            System.out.println("NO");
            return;
        }

        // 여기까지 왔으면 4*k <= sq
        long fourK = 4 * k; // 오버플로우 없음 (4*k <= sq <= 약 4.6e18 < 2^63)
        long D = sq - fourK;

        if (D < 0) {
            System.out.println("NO");
            return;
        }

        // D의 제곱근 검사
        long d = (long)Math.floor(Math.sqrt(D));
        if (d * d != D) {
            // 제곱근이 정수가 아님
            System.out.println("NO");
            return;
        }

        // x = ((n+2) ± d) / 2 두 해를 확인
        // 해가 정수인지(2로 나누어 떨어지는지), 1 ≤ x ≤ n+1인지 확인
        // 첫 번째 해: (n+2 + d)/2
        // 두 번째 해: (n+2 - d)/2
        
        if (checkSolution(n, n2, d)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    private static boolean checkSolution(long n, long n2, long d) {
        // 해1
        if ((n2 + d) % 2 == 0) {
            long x = (n2 + d) / 2;
            if (x >= 1 && x <= n+1) return true;
        }
        // 해2
        if ((n2 - d) % 2 == 0) {
            long x = (n2 - d) / 2;
            if (x >= 1 && x <= n+1) return true;
        }
        return false;
    }
}