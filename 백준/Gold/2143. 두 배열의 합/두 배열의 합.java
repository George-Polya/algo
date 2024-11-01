import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());
        int[] A = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        
        int m = Integer.parseInt(br.readLine());
        int[] B = new int[m];
        st = new StringTokenizer(br.readLine());
        
        for (int i = 0; i < m; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }
        
        // 부분 배열의 합을 저장할 리스트
        List<Integer> sumA = new ArrayList<>();
        List<Integer> sumB = new ArrayList<>();
        
        // A의 모든 부분 배열 합
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += A[j];
                sumA.add(sum);
            }
        }
        
        // B의 모든 부분 배열 합
        for (int i = 0; i < m; i++) {
            int sum = 0;
            for (int j = i; j < m; j++) {
                sum += B[j];
                sumB.add(sum);
            }
        }
        
        // B의 부분 배열 합을 정렬
        Collections.sort(sumB);
        
        long count = 0;
        
        // A의 각 부분 합에 대해 T - sumA의 개수를 sumB에서 찾기
        for (int num : sumA) {
            int target = T - num;
            int lowerBound = lowerBound(sumB, target);
            int upperBound = upperBound(sumB, target);
            count += (upperBound - lowerBound);
        }
        
        System.out.println(count);
    }
    
    // lowerBound: target 이상의 첫 위치 반환
    private static int lowerBound(List<Integer> list, int target) {
        int left = 0;
        int right = list.size();
        
        while (left < right) {
            int mid = (left + right) / 2;
            if (list.get(mid) >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
    
    // upperBound: target 초과의 첫 위치 반환
    private static int upperBound(List<Integer> list, int target) {
        int left = 0;
        int right = list.size();
        
        while (left < right) {
            int mid = (left + right) / 2;
            if (list.get(mid) > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}