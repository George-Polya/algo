import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static List<Edge>[] adj;
    static int start, end;
    static long maxWeight = 0;

    static class Edge {
        int to;
        long weight;

        Edge(int to, long weight){
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adj = new ArrayList[N+1];
        for(int i=1;i<=N;i++) adj[i] = new ArrayList<>();

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            long C = Long.parseLong(st.nextToken());
            adj[A].add(new Edge(B, C));
            adj[B].add(new Edge(A, C));
            if(C > maxWeight) maxWeight = C;
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        long left = 1;
        long right = maxWeight;
        long result = 0;

        while(left <= right){
            long mid = left + (right - left) / 2;
            if(canTransport(mid)){
                result = mid;
                left = mid + 1;
            }
            else{
                right = mid -1;
            }
        }

        System.out.println(result);
    }

    static boolean canTransport(long weight){
        boolean[] visited = new boolean[N+1];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;

        while(!queue.isEmpty()){
            int current = queue.poll();
            if(current == end) return true;
            for(Edge edge : adj[current]){
                if(!visited[edge.to] && edge.weight >= weight){
                    visited[edge.to] = true;
                    queue.add(edge.to);
                }
            }
        }

        return false;
    }
}