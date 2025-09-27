import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        // --- 1. 입력 받기 ---
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken()); // 사대의 수
        int N = Integer.parseInt(st.nextToken()); // 동물의 수
        int L = Integer.parseInt(st.nextToken()); // 사정거리

        int[] shooters = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            shooters[i] = Integer.parseInt(st.nextToken());
        }

        // --- 2. 사대 위치 정렬 (계획의 핵심) ---
        // 이분 탐색을 사용하기 위한 필수 전처리 과정입니다.
        Arrays.sort(shooters);

        int huntCount = 0; // 사냥한 동물의 수를 저장할 변수

        // --- 3. 각 동물을 순회하며 사냥 가능 여부 확인 ---
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); // 동물의 x좌표
            int b = Integer.parseInt(st.nextToken()); // 동물의 y좌표

            // 최적화: 동물의 y좌표가 사정거리보다 크면 절대 잡을 수 없습니다.
            // |x-a| >= 0 이므로, |x-a| + b > L 이 항상 성립하기 때문입니다.
            if (b > L) {
                continue;
            }

            // --- 4. 사냥 가능한 사대 x좌표 범위 계산 ---
            // |x - a| + b <= L  =>  |x - a| <= L - b
            // a - (L - b) <= x <= a + (L - b)
            int minRange = a - (L - b);
            int maxRange = a + (L - b);

            // --- 5. 이분 탐색으로 범위 내 사대 존재 여부 확인 ---
            if (binarySearch(shooters, minRange, maxRange)) {
                huntCount++;
            }
        }

        // --- 6. 최종 결과 출력 ---
        System.out.println(huntCount);
    }

    /**
     * 정렬된 shooters 배열에서 [min, max] 범위에 해당하는 값이 있는지 확인하는 함수
     * @param shooters 정렬된 사대 위치 배열
     * @param min 찾고자 하는 범위의 최솟값
     * @param max 찾고자 하는 범위의 최댓값
     * @return 범위 내에 값이 존재하면 true, 아니면 false
     */
    private static boolean binarySearch(int[] shooters, int min, int max) {
        int left = 0;
        int right = shooters.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int currentPos = shooters[mid];

            if (currentPos >= min && currentPos <= max) {
                // 범위 내의 사대를 찾았으므로 즉시 true를 반환하고 탐색 종료
                return true;
            } else if (currentPos < min) {
                // 현재 사대 위치가 범위보다 왼쪽에 있으므로, 더 오른쪽을 탐색해야 함
                left = mid + 1;
            } else { // currentPos > max
                // 현재 사대 위치가 범위보다 오른쪽에 있으므로, 더 왼쪽을 탐색해야 함
                right = mid - 1;
            }
        }

        // 반복문이 끝날 때까지 찾지 못했다면 범위 내에 사대가 없는 것
        return false;
    }
}