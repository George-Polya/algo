import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int arr[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n+1];
        for(int i = 1; i<=n; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr,1,n+1);
        StringBuilder sb = new StringBuilder();

        for(int i=1;i<=n; i++){
            sb.append(arr[i]).append('\n');
        }
        System.out.println(sb);
    }
}