import java.io.*;
import java.util.*;

public class Main {
    static int n, k;
    static int[] arr;
    static boolean[] visited;
    static int cnt;
    static final int INF = 0x7f7f7f7f;
    static StringTokenizer st;
    
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        k = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        visited = new boolean[n + 1];
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (visited[arr[i]]) {
                continue;
            }

            if (list.size() == k) {
                int last = 0;
                int pos = -1;

                for (int e : list) {
                    int here = INF;

                    for (int j = i + 1; j < n; j++) {
                        if (e == arr[j]) {
                            here = j;
                            break;
                        }
                    }

                    if (last < here) {
                        last = here;
                        pos = e;
                    }
                }

                visited[pos] = false;
                cnt += 1;
                list.remove(Integer.valueOf(pos));  
            }

            list.add(arr[i]);
            visited[arr[i]] = true;
        }

        System.out.println(cnt);
    }
}