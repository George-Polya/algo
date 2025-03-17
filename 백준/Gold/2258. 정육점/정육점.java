import java.util.*;
import java.io.*;

public class Main {
    static int n, k;
    static HashMap<Integer, Integer> hash, sum;
    static ArrayList<int[]> lst;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        hash = new HashMap<>();
        sum = new HashMap<>();
        lst = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            lst.add(new int[]{a, b});
        }

        lst.sort(new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if (a[1] != b[1]) {
                    return Integer.compare(a[1], b[1]);
                } else {
                    return Integer.compare(b[0], a[0]);
                }
            }
        });

        int tmp = 0;
        int prefix = 0;
        int prev = 0;
        long answer = Long.MAX_VALUE;

        for (int[] ls : lst) {
            int weight = ls[0];
            int price = ls[1];

            if (price > prev) {
                prev = price;
                tmp = price;
            } else if (price == prev) {
                tmp += price;
            }
            prefix += weight;

            if (prefix >= k) {
                answer = Math.min(answer, tmp);
            }
        }

        if (answer > Integer.MAX_VALUE) {
            answer = -1;
        }
        System.out.println(answer);
    }
}