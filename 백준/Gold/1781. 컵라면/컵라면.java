import java.io.*;
import java.util.*;



public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<Problem> problems = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            problems.add(new Problem(d, c));
        }

        // 마감일을 기준으로 정렬합니다.
        Collections.sort(problems);

        // 우선순위 큐를 사용하여 최소 힙을 만듭니다.
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (Problem problem : problems) {
            pq.offer(problem.cupNoodles);
            if (pq.size() > problem.deadline) {
                pq.poll(); // 현재까지 푼 문제 중 가장 작은 컵라면 수를 제거합니다.
            }
        }

        int result = 0;
        while (!pq.isEmpty()) {
            result += pq.poll();
        }

        System.out.println(result);
    }
    
 // Comparable 인터페이스를 구현하여 정렬 기준을 커스터마이징합니다.
    static class Problem implements Comparable<Problem> {
        int deadline, cupNoodles;

        public Problem(int deadline, int cupNoodles) {
            this.deadline = deadline;
            this.cupNoodles = cupNoodles;
        }

        // 마감일 기준으로 오름차순 정렬합니다.
        @Override
        public int compareTo(Problem other) {
            return this.deadline - other.deadline;
        }
    }
}