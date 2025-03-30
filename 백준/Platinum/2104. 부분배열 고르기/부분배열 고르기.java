import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Stack;

public class Main {

    static int n;
    static int[] a; // 입력 배열 (1-based index)
    static long[] prefixSum; // 누적 합 배열 (1-based index)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        a = new int[n + 1];
        prefixSum = new long[n + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
            prefixSum[i] = prefixSum[i - 1] + a[i];
        }

        Stack<Integer> stack = new Stack<>();
        long maxScore = 0;

        // 각 원소 A[i]에 대해 오른쪽 경계(R)를 찾으면서 최대 점수 계산
        for (int i = 1; i <= n; i++) {
            // 스택의 top에 있는 원소보다 현재 원소 A[i]가 작거나 같으면
            // 스택 top 원소의 오른쪽 경계는 i가 된다.
            while (!stack.isEmpty() && a[stack.peek()] >= a[i]) {
                int heightIndex = stack.pop(); // 현재 처리할 막대의 인덱스 (k)
                int height = a[heightIndex];   // A[k]

                // 왼쪽 경계(L) 찾기: 스택이 비어있으면 0, 아니면 스택의 새로운 top 인덱스
                int leftBoundary = stack.isEmpty() ? 0 : stack.peek();

                // 오른쪽 경계(R)는 현재 인덱스 i
                int rightBoundary = i;

                // 구간 [L+1, R-1]의 합 계산
                // 구간 [leftBoundary + 1, rightBoundary - 1]
                long currentSum = prefixSum[rightBoundary - 1] - prefixSum[leftBoundary];

                // 점수 계산 및 최대값 업데이트
                maxScore = Math.max(maxScore, currentSum * height);
            }
            // 현재 인덱스를 스택에 push
            stack.push(i);
        }

        // 스택에 남아있는 원소들 처리 (오른쪽 경계는 N+1)
        while (!stack.isEmpty()) {
            int heightIndex = stack.pop();
            int height = a[heightIndex];

            int leftBoundary = stack.isEmpty() ? 0 : stack.peek();
            int rightBoundary = n + 1; // 오른쪽 끝까지 확장 가능

            long currentSum = prefixSum[rightBoundary - 1] - prefixSum[leftBoundary];
            maxScore = Math.max(maxScore, currentSum * height);
        }

        System.out.println(maxScore);
    }
}