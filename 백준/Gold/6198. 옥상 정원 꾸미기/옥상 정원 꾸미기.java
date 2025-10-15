import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        // 입력을 받기 위한 BufferedReader 설정
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 빌딩의 수 N을 입력받음
        int n = Integer.parseInt(br.readLine());

        // 빌딩의 높이를 저장할 스택 선언 (Integer 타입을 저장)
        Stack<Integer> stack = new Stack<>();
        
        // 최종 결과(볼 수 있는 빌딩 쌍의 수)를 저장할 변수
        // n이 최대 80,000이므로 결과값이 int 범위를 초과할 수 있어 long 타입으로 선언
        long ans = 0;

        // n개의 빌딩에 대해 반복
        for (int i = 0; i < n; i++) {
            // 현재 빌딩의 높이를 입력받음
            int h = Integer.parseInt(br.readLine());

            // 스택이 비어있지 않고, 스택의 맨 위 빌딩이 현재 빌딩보다 작거나 같으면
            // 해당 빌딩은 더 이상 다른 빌딩을 볼 수 없으므로 스택에서 제거
            while (!stack.isEmpty() && stack.peek() <= h) {
                stack.pop();
            }

            // 위 과정을 거친 후 스택에 남아있는 빌딩들은 현재 빌딩보다 모두 높은 빌딩들임
            // 따라서 스택에 남아있는 빌딩들은 모두 현재 빌딩의 옥상을 볼 수 있음
            // 스택의 크기만큼 결과값에 더해줌
            ans += stack.size();

            // 현재 빌딩을 스택에 추가
            stack.push(h);
        }

        // 최종 결과 출력
        System.out.println(ans);
    }
}