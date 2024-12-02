import java.util.*;

public class Main {
    static int n, k;
    static int[] arr = new int[104];
    static int[] visited = new int[104];
    static int cnt = 0;
    static final int INF = 0x7f7f7f7f;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        k = sc.nextInt();
        n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        List<Integer> v = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (visited[arr[i]] == 1) {
                continue;
            }

            if (v.size() == k) {
                int last_idx = 0;
                int pos = -1;

                for (int a : v) {
                    int here_pick = INF;

                    for (int j = i + 1; j < n; j++) {
                        if (a == arr[j]) {
                            here_pick = j;
                            break;
                        }
                    }

                    if (last_idx < here_pick) {
                        last_idx = here_pick;
                        pos = a;
                    }
                }

                visited[pos] = 0;
                cnt += 1;
                v.remove(Integer.valueOf(pos));  // List에서 해당 값 제거
            }

            v.add(arr[i]);
            visited[arr[i]] = 1;
        }

        System.out.println(cnt);
    }
}