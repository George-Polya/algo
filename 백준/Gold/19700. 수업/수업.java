import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.TreeMap;

public class Main {
    static class Student implements Comparable<Student> {
        int height, k;
        public Student(int height, int k) {
            this.height = height;
            this.k = k;
        }
        // 내림차순 정렬 (키가 큰 순으로)
        public int compareTo(Student other) {
            return other.height - this.height;
        }
    }
    
    public static void main(String[] args) throws IOException {
        // 빠른 입력을 위한 BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        Student[] students = new Student[n];
        
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            students[i] = new Student(h, k);
        }
        
        // 키가 큰 순서대로 정렬
        Arrays.sort(students);
        
        // 팀의 인원수를 key, 해당 팀의 개수를 value로 저장
        TreeMap<Integer, Integer> teamSizes = new TreeMap<>();
        
        for (Student s : students) {
            // 학생 s는 자신보다 키가 큰 학생들이 이미 배정된 팀에서
            // 현재 팀원 수가 s.k - 1 이하인 팀에 들어갈 수 있음.
            // 따라서, key <= s.k - 1 인 팀들 중 최대 key값을 찾는다.
            Integer key = teamSizes.floorKey(s.k - 1);
            
            if (key == null) {
                // 적합한 팀이 없으면 새로운 팀 생성 (팀원 1명)
                teamSizes.put(1, teamSizes.getOrDefault(1, 0) + 1);
            } else {
                // 해당 팀에 학생을 배정 -> 기존 팀원 수 key를 제거하고 key+1로 업데이트
                int count = teamSizes.get(key);
                if (count == 1) {
                    teamSizes.remove(key);
                } else {
                    teamSizes.put(key, count - 1);
                }
                teamSizes.put(key + 1, teamSizes.getOrDefault(key + 1, 0) + 1);
            }
        }
        
        // 만들어진 팀의 총 개수는 팀Sizes에 저장된 모든 값의 합
        int teams = 0;
        for (int cnt : teamSizes.values()) {
            teams += cnt;
        }
        System.out.println(teams);
    }
}