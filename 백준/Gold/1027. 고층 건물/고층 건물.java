import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        map = new int[N];
        for(int i=0; i<N; i++)
        {
            map[i] = Integer.parseInt(st.nextToken());
        }
        int ans=0;
        for(int i=0; i<N; i++)
        {
            ans = Math.max(ans, count(i));
        }
        System.out.println(ans);
    }
    public static int count(int index)
    {
        int cnt=0;
        double temp=0;
        for(int i=index-1; i>=0; i--)
        {
            double slope = (double)(map[index]-map[i])/(index-i);

            if(i==index-1 || temp>slope)
            {
                cnt++;
                temp = slope;
            }
        }

        for(int i=index+1; i<N; i++)
        {
            double slope = (double)(map[index]-map[i])/(index-i);

            if(i==index+1 || temp<slope)
            {
                cnt++;
                temp = slope;
            }
        }
        return cnt;
    }
}