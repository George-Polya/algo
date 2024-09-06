import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static final int MXN = 4000002;
    static boolean[] state = new boolean[MXN];
    static ArrayList<Integer> primes = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        Arrays.fill(state, true);

        for (int i = 2; i * i < MXN; i++) {
            if (!state[i]) continue;
            for (int j = i * i; j < MXN; j += i)
                state[j] = false;
        }

        for (int i = 2; i < MXN; i++)
            if (state[i])
                primes.add(i);

        Collections.reverse(primes);
        primes.add(0);

        int st = 0, en = 1, cnt = 0, sum = primes.get(0);
        while (true) {
            if (sum == n)
                cnt += 1;
            if (sum <= n) {
                sum += primes.get(en);
                en += 1;
            }
            if (sum > n) {
                sum -= primes.get(st);
                st += 1;
            }
            if (en == primes.size())
                break;
        }

        System.out.println(cnt);
    }
}