import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // --- 입력 처리 ---
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        int S = Integer.parseInt(br.readLine());

        // --- 그리디 알고리즘 실행 ---
        // i: 현재 정렬하려는 배열의 위치 (0부터 N-1까지)
        for (int i = 0; i < N && S > 0; i++) {
            
            // 1. i번째 위치에 놓을 최댓값과 그 위치(maxIdx)를 찾는다.
            int maxVal = arr[i];
            int maxIdx = i;
            
            // 탐색 범위: 현재 위치(i)부터 교환 가능 횟수(S)를 고려한 끝까지
            // j-i는 i에서 j까지 원소를 옮기는 데 필요한 교환 횟수입니다.
            for (int j = i + 1; j < N && (j - i) <= S; j++) {
                if (arr[j] > maxVal) {
                    maxVal = arr[j];
                    maxIdx = j;
                }
            }
            
            // 2. 찾은 최댓값(arr[maxIdx])을 i 위치로 옮긴다.
            // maxIdx부터 i+1까지 한 칸씩 앞으로 당기는 연산 (버블 정렬의 일부와 유사)
            if (maxIdx != i) { // 더 큰 값을 찾았을 경우에만 실행
                for (int j = maxIdx; j > i; j--) {
                    // 인접한 두 원소 교환
                    int temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                }
                
                // 3. 사용한 교환 횟수를 S에서 차감한다.
                S -= (maxIdx - i);
            }
        }

        // --- 결과 출력 ---
        StringBuilder sb = new StringBuilder();
        for (int num : arr) {
            sb.append(num).append(" ");
        }
        System.out.println(sb.toString().trim());
    }
}