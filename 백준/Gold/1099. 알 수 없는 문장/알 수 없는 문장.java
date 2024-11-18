import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String sentence = br.readLine();
        int N = Integer.parseInt(br.readLine());
        List<String> dictionary = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            dictionary.add(br.readLine());
        }
        br.close();

        int len = sentence.length();
        int[] dp = new int[len + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 1; i <= len; i++) {
            for (int j = 0; j < i; j++) {
                String sub = sentence.substring(j, i);
                for (String word : dictionary) {
                    if (canForm(sub, word)) {
                        int cost = calculateCost(sub, word);
                        if (dp[j] != Integer.MAX_VALUE) {
                            dp[i] = Math.min(dp[i], dp[j] + cost);
                        }
                    }
                }
            }
        }

        System.out.println(dp[len] == Integer.MAX_VALUE ? -1 : dp[len]);
    }

    private static boolean canForm(String sub, String word) {
        if (sub.length() != word.length()) return false;
        int[] count = new int[26];
        for (char c : sub.toCharArray()) count[c - 'a']++;
        for (char c : word.toCharArray()) count[c - 'a']--;
        for (int i : count) if (i != 0) return false;
        return true;
    }

    private static int calculateCost(String sub, String word) {
        int cost = 0;
        for (int i = 0; i < sub.length(); i++) {
            if (sub.charAt(i) != word.charAt(i)) cost++;
        }
        return cost;
    }
}