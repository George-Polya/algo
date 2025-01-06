import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Queue<Integer> q = new LinkedList<>();

        for (int i = 1; i <= n; i++) {
            q.add(i);
        }

        System.out.print("<");
        while (!q.isEmpty()) {
            for (int i = 0; i < k - 1; i++) {
                q.add(q.poll());
            }

            System.out.print(q.poll());
            if (!q.isEmpty()) {
                System.out.print(", ");
            }
        }
        System.out.print(">");
    }
}