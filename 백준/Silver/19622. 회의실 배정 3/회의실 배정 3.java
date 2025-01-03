import java.util.*;
import java.io.*;

public class Main {
    static class Meeting {
        int start, end, profit;
        
        public Meeting(int start, int end, int profit) {
            this.start = start;
            this.end = end;
            this.profit = profit;
        }
    }
    
    static int[] dp;
    static Meeting[] meetings;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        meetings = new Meeting[n];
        
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int profit = Integer.parseInt(st.nextToken());
            meetings[i] = new Meeting(start, end, profit);
        }
        
        // 종료 시간을 기준으로 정렬
        Arrays.sort(meetings, Comparator.comparingInt(m -> m.end));
        
        dp = new int[n];
        Arrays.fill(dp, -1);
        
        System.out.println(maxProfit(n - 1));
    }
    
    static int maxProfit(int idx) {
        if (idx < 0) return 0; // 기저 사례: 범위를 벗어난 경우
        
        if (dp[idx] != -1) return dp[idx]; // 메모이제이션
        
        // 이 회의를 선택하지 않은 경우
        int exclude = maxProfit(idx - 1);
        
        // 이 회의를 선택한 경우
        int include = meetings[idx].profit;
        int lastNonConflict = findLastNonConflict(idx);
        if (lastNonConflict != -1) {
            include += maxProfit(lastNonConflict);
        }
        
        // 최대값 저장
        return dp[idx] = Math.max(exclude, include);
    }
    
    // 현재 회의와 충돌하지 않는 가장 마지막 회의 찾기 (이분 탐색)
    static int findLastNonConflict(int idx) {
        int low = 0, high = idx - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (meetings[mid].end <= meetings[idx].start) {
                if (mid == idx - 1 || meetings[mid + 1].end > meetings[idx].start) {
                    return mid;
                }
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }
}