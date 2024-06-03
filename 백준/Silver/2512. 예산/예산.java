import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n,m;
    static int arr[];

    static boolean check() {
        int total = 0;
        for (int i = 1; i <= n; i++) {
            total += arr[i];
        }

        return total < m;
    }

    static StringBuilder sb = new StringBuilder();

    static boolean decide(int limit) {
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            if (limit > arr[i]) {
                sum += arr[i];
            } else {
                sum += limit;
            }
        }

        return sum > m;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        m = Integer.parseInt(br.readLine());

        Arrays.sort(arr, 1, n + 1);

        if (check()) {
            sb.append(arr[n]);
        } else {
            int left = 1;
            int right = arr[n];
            int result = 0;
            while (left <= right) {
                int mid = (left + right) / 2;

                if (decide(mid)) {
                    right = mid - 1;
                } else {
                    result = mid;
                    left = mid + 1;
                }

            }

            sb.append(result);

        }
        System.out.println(sb);
    }

}
