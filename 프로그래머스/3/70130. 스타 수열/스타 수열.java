class Solution {
    public int solution(int[] a) {
        int n = a.length;
        int[] cnt = new int[n];  // a의 각 숫자(0~n-1)의 등장 횟수 카운트

        // 각 숫자의 등장 횟수 세기
        for (int i = 0; i < n; i++) {
            cnt[a[i]]++;
        }

        int maxPairCount = 0;
        // 후보 숫자 i를 스타 수열의 “교집합 원소”로 고려
        for (int i = 0; i < n; i++) {
            // 만약 해당 숫자의 등장 횟수가 이미 지금까지 구한 쌍 수(maxPairCount)보다 작거나 같다면
            // 해당 숫자로 만들 수 있는 스타 수열의 길이는 maxPairCount*2보다 작으므로 건너뛴다.
            if (cnt[i] <= maxPairCount) continue;

            int pairCount = 0;
            // 인접한 두 원소를 확인하며, 둘 중 하나가 i이고 두 값이 다른 경우 쌍으로 처리
            for (int j = 0; j < n - 1; j++) {
                if ((a[j] == i || a[j + 1] == i) && a[j] != a[j + 1]) {
                    pairCount++;
                    j++;  // 쌍으로 처리했으므로 다음 인덱스로 건너뛰기
                }
            }
            maxPairCount = Math.max(maxPairCount, pairCount);
        }

        // 스타 수열의 길이는 쌍의 수의 2배
        return maxPairCount * 2;
    }
}
