import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/11728
public class Main {
    static int n,m;
    static int[] a;
    static int[] b;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        a = new int[n + 1];
        b = new int[m + 1];

        st = new StringTokenizer(br.readLine());
        for(int i =1 ;i<=n;i++)
            a[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i =1 ; i<=m;i++)
            b[i] = Integer.parseInt(st.nextToken());

        int left = 1, right = 1;// 두 포인터 초기화, left는 a에서, right는 b에서
        while (left <= n && right <= m) { // left와 right가 모두 범위내에 있다면
            if (a[left] <= b[right]) { // a의 현재값이 b의 현재값보다 작다면
                sb.append(a[left] + " ");
                left += 1;
            } else {
                sb.append(b[right] + " ");
                right += 1;
            }
        }


        while (left <= n) { // right가 범위밖이지만 left는 범위안일 때
            sb.append(a[left] + " ");
            left += 1;
        }

        while (right <= m) {
            sb.append(b[right] + " ");
            right += 1;
        }

        System.out.println(sb);

    }
}
