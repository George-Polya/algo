import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/21939
public class Main {
    static int n, m;

    static class Problem implements Comparable<Problem> {


        int num, difficulty;

        public Problem(int num, int difficulty) {
            this.num = num;
            this.difficulty = difficulty;
        }

        @Override
        public int compareTo(Problem o) {
            if (difficulty == o.difficulty)
                return num - o.num;
            return difficulty - o.difficulty;
        }
    }

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = null;

        TreeMap<Integer, Integer> map = new TreeMap<>();
        TreeSet<Problem> set = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int difficulty = Integer.parseInt(st.nextToken());
            set.add(new Problem(num, difficulty));
            map.put(num, difficulty);
        }
        m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            String oper = st.nextToken();
            if (oper.compareTo("add") == 0) {
                int id = Integer.parseInt(st.nextToken());
                int level = Integer.parseInt(st.nextToken());
                set.add(new Problem(id, level));
                map.put(id, level);
            } else if (oper.compareTo("recommend") == 0) {
                int x = Integer.parseInt(st.nextToken());
                if (x == 1) {
                    sb.append(set.last().num).append('\n');
//                    sb.append(map.lastKey()).append('\n');
                } else if (x == -1) {
                    sb.append(set.first().num).append('\n');
//                    sb.append(map.firstKey()).append('\n');
                }
            } else if (oper.compareTo("solved") == 0) {
                int id = Integer.parseInt(st.nextToken());
                int level = map.get(id);
                set.remove(new Problem(id, level));
                map.remove(id);
            }
        }
        System.out.println(sb);
    }
}
