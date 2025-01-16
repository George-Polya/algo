import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1253
public class Main {
    static int n;
    static int arr[];

    // targtIdx번째 원소가 서로 다른 두 수의 합으로 표현되는가?
    static boolean check(int targetIdx) {
        int left = 1, right = n;
        int target = arr[targetIdx];

        while (left < right) {
            if(left == targetIdx)
                left += 1;
            else if(right == targetIdx)
                right -= 1;
            else{
                if(arr[left] + arr[right] == target)
                    return true;
                if (arr[left] + arr[right] > target) {
                    right -= 1;
                } else {
                    left += 1;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        // 최소, 최대를 빠르게 알기 위해 정렬
        Arrays.sort(arr, 1, n + 1);
        int cnt = 0;
        for (int idx = 1; idx <= n; idx++) {
            if(check(idx))
                cnt+= 1;
        }
        System.out.println(cnt);
    }
}
