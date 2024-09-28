class Solution {
    int n;
    int arr[];
    int target;
    
    int dfs(int cur, int sum){
        if(cur == n ){
            if(sum == target)
                return 1;
            return 0;
        }
        
        int value = arr[cur];
        return dfs(cur +1 ,sum + value) + dfs(cur +1 ,sum - value);
    }
    
    public int solution(int[] numbers, int _target) {
        n = numbers.length;
        arr = numbers;
        target = _target;
        int answer = dfs(0,0);
        return answer;
    }
}