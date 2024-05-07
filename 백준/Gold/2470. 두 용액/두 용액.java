import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2470
public class Main {
    static int n;
    static int arr[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr, 1, n + 1);

        int left = 1, right = n;
        int v1 = 0, v2 = 0;
        int best = Integer.MAX_VALUE;

        while (left < right) {
            int sum = arr[left] + arr[right];

            if (Math.abs(sum) < best) {
                best = Math.abs(sum);
                v1 = arr[left];
                v2 = arr[right];
            }

            if (sum > 0) {
                right -= 1;
            } else {
                left += 1;
            }
        }
        System.out.println(v1 + " " + v2);

    }
}
