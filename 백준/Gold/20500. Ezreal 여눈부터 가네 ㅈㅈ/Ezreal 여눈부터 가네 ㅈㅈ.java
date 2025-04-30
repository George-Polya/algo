import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static final long MOD = 1000000007L; // 모듈러 상수 (10^9 + 7)
    static long[] fact; // 팩토리얼 값을 저장할 배열 (모듈러 연산 적용)

    // 모듈러 거듭제곱 함수 (a^b % mod)
    // 분할 정복을 이용한 효율적인 계산
    static long power(long base, long exp) {
        long res = 1L;
        base %= MOD;
        while (exp > 0) {
            if (exp % 2 == 1) res = (res * base) % MOD; // 홀수 지수면 결과에 base 곱하기
            base = (base * base) % MOD; // base 제곱
            exp /= 2; // 지수 절반으로 줄이기
        }
        return res;
    }

    // 모듈러 곱셈 역원 함수 (n^-1 % mod)
    // 페르마의 소정리 이용: n^(mod-2) % mod
    static long modInverse(long n) {
        return power(n, MOD - 2);
    }

    // 조합 함수 (nCr % mod)
    // nCr = n! / (r! * (n-r)!) % mod
    // nCr = n! * (r!)^(-1) * ((n-r)!)^(-1) % mod
    static long nCr(int n, int r) {
        if (r < 0 || r > n) {
            return 0; // 범위를 벗어나면 0
        }
        if (r == 0 || r == n) {
            return 1; // nC0 = nCn = 1
        }
        if (r > n / 2) {
            r = n - r; // nCr = nC(n-r) 이용 최적화
        }

        // 분자: fact[n]
        // 분모: (fact[r] * fact[n-r]) % MOD
        long denominator = (fact[r] * fact[n - r]) % MOD;

        // 분자 * (분모의 모듈러 역원) % MOD
        return (fact[n] * modInverse(denominator)) % MOD;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // N이 1515까지 가능하므로 팩토리얼 배열 크기 설정
        // N-1 까지 필요하므로 N 크기로 충분
        fact = new long[N + 1];
        fact[0] = 1;
        // 팩토리얼 미리 계산 (모듈러 연산 적용)
        for (int i = 1; i <= N; i++) {
            fact[i] = (fact[i - 1] * i) % MOD;
        }

        long totalCount = 0;

        // j는 1부터 N까지 (5의 개수)
        for (int j = 1; j <= N; j++) {
            // 조건 확인: N + j 가 3의 배수인가?
            if ((N + j) % 3 == 0) {
                // 조합 계산: nCr(N-1, j-1)
                // N-1개의 자리에 j-1개의 5를 배치하는 경우의 수
                long ways = nCr(N - 1, j - 1);
                // 총 개수에 더하기 (모듈러 연산)
                totalCount = (totalCount + ways) % MOD;
            }
        }

        // 최종 결과 출력
        System.out.println(totalCount);
    }
}