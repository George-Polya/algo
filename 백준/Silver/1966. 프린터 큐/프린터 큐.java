import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();


        int case_number = sc.nextInt();


        while (case_number-- > 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();

            LinkedList<int[]> q = new LinkedList<>();

            for (int i = 0; i < n; i++) {
                q.add(new int[]{i, sc.nextInt()});
            }

            int count = 0;

            while (!q.isEmpty()) {
                int[] front = q.poll();
                boolean isMax = true;

                for (int i = 0; i < q.size(); i++) {
                    if (front[1] < q.get(i)[1]) {
                        q.add(front);
                        for (int j = 0; j < i; j++) {
                            q.add(q.poll());
                        }

                        isMax = false;
                        break;
                    }
                }

                if(isMax==false)
                    continue;

                count++;
                if(front[0] == m)
                    break;
            }

            sb.append(count).append("\n");
        }
        System.out.println(sb);


    }




}
