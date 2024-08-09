import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int n = scanner.nextInt();
        int k = scanner.nextInt();

        List<long[]> jewels = new ArrayList<>();
        long[] bags = new long[k];

        for (int i = 0; i < n; i++) {
            long weight = scanner.nextLong();
            long value = scanner.nextLong();
            jewels.add(new long[]{weight, value});
        }

        for (int i = 0; i < k; i++) {
            bags[i] = scanner.nextLong();
        }

        // 보석과 가방을 각각 정렬
        jewels.sort(Comparator.comparingLong(a -> a[0]));
        Arrays.sort(bags);

        PriorityQueue<Long> pq = new PriorityQueue<>(Collections.reverseOrder());

        int st = 0;
        long ret = 0;

        for (int i = 0; i < k; i++) {
            while (st < n && jewels.get(st)[0] <= bags[i]) {
                pq.add(jewels.get(st)[1]);
                st++;
            }
            if (!pq.isEmpty()) {
                ret += pq.poll();
            }
        }

        System.out.println(ret);
    }
}