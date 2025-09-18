import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        // 0. 빠른 입력을 위한 설정
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 1. 데이터 저장 및 준비 단계
        // 친구 관계를 빠르게 확인하기 위한 인접 행렬
        boolean[][] areFriends = new boolean[N + 1][N + 1];
        // 각 사람의 친구 수를 저장하기 위한 배열
        int[] friendCount = new int[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            // 양방향 친구 관계 설정
            areFriends[u][v] = true;
            areFriends[v][u] = true;

            // 각자의 친구 수 증가
            friendCount[u]++;
            friendCount[v]++;
        }

        // 최솟값을 저장할 변수. 충분히 큰 값으로 초기화
        int minSum = Integer.MAX_VALUE;

        // 2. 탐색 및 계산 단계
        // 모든 사람 A, B, C의 조합을 확인
        for (int A = 1; A <= N; A++) {
            for (int B = A + 1; B <= N; B++) {
                // 먼저 A와 B가 친구인지 확인 (이것이 핵심!)
                if (areFriends[A][B]) {
                    // A와 B가 친구라면, 이 둘과 모두 친구인 C를 찾음
                    for (int C = B + 1; C <= N; C++) {
                        // C가 A와도 친구이고, B와도 친구인지 확인
                        if (areFriends[A][C] && areFriends[B][C]) {
                            // 세 친구(A, B, C)를 찾았음!
                            // 문제의 조건에 따라 친구 수의 합을 계산
                            int currentSum = (friendCount[A] - 2) + (friendCount[B] - 2) + (friendCount[C] - 2);
                            
                            // 현재 합계가 이전에 찾은 최솟값보다 작으면 갱신
                            minSum = Math.min(minSum, currentSum);
                        }
                    }
                }
            }
        }

        // 3. 결과 출력 단계
        // minSum이 초기값 그대로라면 세 친구 관계를 찾지 못한 것
        if (minSum == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(minSum);
        }
    }
}