import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력 받기
        int N = sc.nextInt(); // 배열 크기
        int M = sc.nextInt(); // 나눌 값

        long[] arr = new long[N]; // 배열 선언
        long[] prefixSumMod = new long[M]; // 나머지 카운트를 저장할 배열
        long count = 0;
        long sum = 0;

        // 입력 배열을 처리하며 누적합 계산
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
            sum += arr[i]; // 누적 합
            int mod = (int)(sum % M); // M으로 나눈 나머지 계산

            // 나머지가 음수인 경우 보정
            if (mod < 0) mod += M;

            // 나머지가 0인 경우 해당 구간 자체가 조건을 만족하므로 count 증가
            if (mod == 0) count++;

            // 이전에 같은 나머지를 가진 누적합이 있는지 확인
            count += prefixSumMod[mod];

            // 현재 나머지를 prefixSumMod 배열에 저장
            prefixSumMod[mod]++;
        }

        // 결과 출력
        System.out.println(count);
    }
}