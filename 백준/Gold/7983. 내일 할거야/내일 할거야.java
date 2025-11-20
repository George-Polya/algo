import java.io.*;
import java.util.*;

// 과제 정보를 담을 클래스
class Task implements Comparable<Task> {
    int d; // 소요 시간
    int t; // 마감일

    public Task(int d, int t) {
        this.d = d;
        this.t = t;
    }

    // 마감일(t)을 기준으로 내림차순 정렬 (늦은 날짜가 먼저 오도록)
    @Override
    public int compareTo(Task other) {
        return other.t - this.t;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        // 빠른 입출력을 위해 BufferedReader 사용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine()); // 과제의 수
        List<Task> tasks = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            tasks.add(new Task(d, t));
        }

        // 1. 정렬: 마감일이 가장 늦은 과제부터 처리하기 위해 내림차순 정렬
        Collections.sort(tasks);

        // 2. 그리디 알고리즘 적용
        // 초기값: 가장 늦은 마감일을 가진 과제의 마감일로 설정
        int current = tasks.get(0).t;

        for (Task task : tasks) {
            // 현재 시점(current)과 과제의 마감일(task.t) 중 더 이른 날짜에 끝내야 함
            // 예: current가 10일인데, 이 과제 마감일이 11일이면 -> 10일에 끝내야 함 (뒤에 과제가 밀려있으므로)
            // 예: current가 15일인데, 이 과제 마감일이 13일이면 -> 13일에 끝내야 함 (마감일 준수)
            int finishTime = Math.min(current, task.t);
            
            // 끝나는 시간에서 소요 시간을 빼면 시작 시간이 됨
            // 이 시작 시간은 다음(앞쪽) 과제의 '끝나는 시간' 한계선이 됨
            current = finishTime - task.d;
        }

        // 모든 과제를 배치하고 남은 시간(current)이 1일부터 놀 수 있는 최대 기간
        System.out.println(current);
    }
}