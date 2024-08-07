import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        ArrayList<Integer>[] graph = new ArrayList[N+1];
        int[] target = new int[M];
        int[] inputCount = new int[M];
        Boolean[] visited = new Boolean[N + 1];
        for(int i=0; i<=N;i++){
            visited[i] = false;
            graph[i] = new ArrayList<>();
        }


        for(int i= 0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            inputCount[i] = k;
            for (int j=0; j<k; j++){
                graph[Integer.parseInt(st.nextToken())].add(i);
            }
            target[i] = Integer.parseInt(st.nextToken());
        }
        int L = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0 ; i<L; i++){
            int y = Integer.parseInt(st.nextToken());
            queue.add(y);
            visited[y]= true;
        }
        while (!queue.isEmpty()){
            int temp =queue.poll();
            for(int next : graph[temp]){
                if( --inputCount[next] ==0 && (!visited[target[next]])){
                    visited[target[next]] = true;
                    queue.add(target[next]);
                }
            }
        }
        int count = 0;
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i<=N;i++){
            if(visited[i]){
                count++;
                sb.append(i).append(" ");
            }
        }
        System.out.println(count);
        System.out.println(sb);
    }
}