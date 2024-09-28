class Solution {
    
    public int solution(int[] numbers, int target) {
        int n = numbers.length;
        int answer = 0;
        for(int bit = 0; bit < (1 << n); bit++){
            int sum = 0;
            
            for(int i = 0; i < n; i++){
                if( (bit & (1<< i)) != 0)
                   sum += numbers[i];
                else
                   sum -= numbers[i];
            }
                   
            if(sum == target)
                   answer++;
        }
                   
        return answer;
    }
}