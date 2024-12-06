import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        long k = sc.nextLong();
        sc.close();

        // f(h) = (h+1)*(n-h+1)
        // 0 <= h <= n
        // f(h)는 0에서 시작해 h ~ n/2 부근에서 최대, 이후 감소하는 형태

        // peak를 찾는다.
        // 정수 n에 대해 f(h) 최대값은 h = n/2 또는 (n/2)+1 근처에 나타난다.
        // 두 점을 비교해 더 큰 값을 가지는 h를 peak로 한다.
        long h1 = n/2;
        long h2 = h1 + 1;
        long val1 = f(h1, n);
        long val2 = (h2 <= n) ? f(h2, n) : Long.MIN_VALUE;

        long peak = (val1 >= val2) ? h1 : h2;

        // 만약 k가 f(peak)보다 크다면 불가능
        long maxPieces = f(peak, n);
        if (k > maxPieces) {
            System.out.println("NO");
            return;
        }

        // 1. 0 ~ peak 구간 이분 탐색 (f(h) 증가)
        if (binarySearchIncreasing(0, peak, n, k)) {
            System.out.println("YES");
            return;
        }

        // 2. peak ~ n 구간 이분 탐색 (f(h) 감소)
        if (binarySearchDecreasing(peak, n, n, k)) {
            System.out.println("YES");
            return;
        }

        // 못찾으면 NO
        System.out.println("NO");
    }

    // f(h) = (h+1)*(n-h+1), long 범위 내 계산 가능
    private static long f(long h, long n) {
        return (h+1) * (n - h + 1);
    }

    // f(h)가 단조 증가하는 구간 [L..R]에서 k 탐색
    // f(mid) == k => true
    // f(mid) < k => L = mid+1
    // f(mid) > k => R = mid-1
    private static boolean binarySearchIncreasing(long L, long R, long n, long k) {
        while (L <= R) {
            long mid = (L + R) >>> 1;
            long val = f(mid, n);
            if (val == k) return true;
            if (val < k) {
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }
        return false;
    }

    // f(h)가 단조 감소하는 구간 [L..R]에서 k 탐색
    // f(mid) == k => true
    // 단조 감소이므로 f(mid) < k이면 더 큰 값을 찾아야 하므로 왼쪽으로 이동(R = mid-1)
    // f(mid) > k이면 더 작은 값을 찾아야 하므로 오른쪽으로 이동(L = mid+1)
    private static boolean binarySearchDecreasing(long L, long R, long n, long k) {
        while (L <= R) {
            long mid = (L + R) >>> 1;
            long val = f(mid, n);
            if (val == k) return true;
            if (val < k) {
                // 감소하는 구간에서 val < k면 왼쪽 탐색
                R = mid - 1;
            } else {
                // val > k면 오른쪽 탐색
                L = mid + 1;
            }
        }
        return false;
    }
}