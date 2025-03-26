import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.ArrayList;

public class Main {
    static class Student implements Comparable<Student> {
        int h, k;
        public Student(int h, int k) {
            this.h = h;
            this.k = k;
        }
        // 키가 큰 순으로 내림차순 정렬
        @Override
        public int compareTo(Student other) {
            return other.h - this.h;
        }
    }
    
    public static void main(String[] args) throws IOException {
        // 빠른 입력 처리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        Student[] students = new Student[n];
        
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            students[i] = new Student(h, k);
        }
        
        // 키가 큰 학생부터 처리하기 위해 내림차순 정렬
        Arrays.sort(students);
        
        // teams 리스트: 각 원소는 해당 팀의 현재 인원 수.
        // 리스트는 내림차순(즉, 가장 큰 팀원 수부터)로 유지합니다.
        ArrayList<Integer> teams = new ArrayList<>();
        
        for (Student s : students) {
            // 조건: 현재 팀에 속한 인원수가 s.k 미만이어야 함.
            // teams는 내림차순이므로, 이분탐색(upperBound)을 통해
            // "첫 번째로 teams[i] < s.k"인 인덱스를 찾습니다.
            int idx = upperBound(teams, s.k);
            if (idx == teams.size()) {
                // 조건에 맞는 팀이 없으면, 새로운 팀을 생성(팀원 1명)
                teams.add(1);
            } else {
                // 찾은 팀에 학생 배정: 팀원 수를 1 증가시킴
                teams.set(idx, teams.get(idx) + 1);
                // 인원수가 증가했으므로, 내림차순을 유지하기 위해 위치 재조정
                int i = idx;
                while (i > 0 && teams.get(i) > teams.get(i - 1)) {
                    int tmp = teams.get(i);
                    teams.set(i, teams.get(i - 1));
                    teams.set(i - 1, tmp);
                    i--;
                }
            }
        }
        
        // 최종 팀의 총 개수 출력
        System.out.println(teams.size());
    }
    
    // 내림차순으로 정렬된 teams 리스트에서,
    // target(학생의 k 값)보다 작은 값이 처음 등장하는 인덱스를 반환하는 이분탐색 함수
    // 만약 모든 팀원 수가 target 이상이면 teams.size()를 반환합니다.
    static int upperBound(ArrayList<Integer> teams, int target) {
        int lo = 0, hi = teams.size();
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            // teams.get(mid) < target 인 경우, 후보로 잡을 수 있으므로 hi를 mid로
            if (teams.get(mid) < target) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }
}