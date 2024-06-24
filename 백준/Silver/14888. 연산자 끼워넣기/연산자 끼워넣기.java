import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14888

public class Main{

    static int n, max,min;
    static int[] nums, operators, order;

    static int calculate(int operand1, int operator, int operand2){
        int ret = 0;
        if(operator == 0)
            ret = operand1 + operand2;
        if(operator == 1)
            ret = operand1 - operand2;
        if(operator == 2)
            ret = operand1 * operand2;
        if(operator == 3)
            ret =  operand1 / operand2;
        return ret;
    }

    static void solve(int k, int value){
        if(k == n-1){
            // 완성된 식에 맞게 계산해서 정답 갱신
            max = Math.max(max, value);
            min = Math.min(min, value);
        }else{
            for(int cand = 0; cand < 4; cand++){
                if(operators[cand] >= 1){
                    operators[cand] -= 1;
                    order[k] = cand;
                    int new_value = calculate(value, cand, nums[k+1]);
                    
                    solve(k+1, new_value);
                    operators[cand] += 1;
                    order[k] = 0;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        nums = new int[n];
        operators = new int[4];
        order = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<n;i++)
            nums[i] = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < 4;i++)
            operators[i] = Integer.parseInt(st.nextToken());

        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;
        solve(0,nums[0]);
        System.out.println(max+"\n"+min);
    }
}