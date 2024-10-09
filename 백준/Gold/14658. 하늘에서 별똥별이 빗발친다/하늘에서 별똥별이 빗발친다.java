import java.util.*;
import java.io.*;

public class Main {
    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static ArrayList<Node> stars;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        stars = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            stars.add(new Node(x, y));
        }

        int maxStars = 0;
        for (Node a : stars) {
            for (Node b : stars) {
                maxStars = Math.max(maxStars, countStarsInBounds(a.x, b.y, L));
            }
        }

        System.out.println(K - maxStars);
    }

    private static int countStarsInBounds(int x, int y, int l) {
        int count = 0;
        for (Node s : stars) {
            if (s.x >= x && s.x <= x + l && s.y >= y && s.y <= y + l) {
                count++;
            }
        }
        return count;
    }
}