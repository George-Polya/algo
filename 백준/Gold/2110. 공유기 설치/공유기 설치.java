// https://www.acmicpc.net/problem/2110

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n, c;
    static int arr[];

      static boolean check(int dist) {
        int cnt = 1;
        int cur = arr[1];
        for (int i = 2; i <= n; i++) {
            if (cur + dist <= arr[i]) {
                cnt += 1;
                cur = arr[i];
            }
        }

        return c <= cnt;
    }


    static int lower_bound(int arr[], int target) {
        int st = 1, en = 1000000000;
        int result = 0;
        while (st <= en) {
            int mid = (st+en)/ 2;

            if (check(mid)) {
                result = mid;
                st = mid + 1;
            } else {
                en = mid-1;
            }
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        arr = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr, 1, n + 1);
        int result = lower_bound(arr, c);
        System.out.println(result);
    }
}
