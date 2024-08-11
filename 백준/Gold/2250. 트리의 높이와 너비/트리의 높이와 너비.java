import java.util.*;
import java.io.*;

public class Main {
	static int n;
	static int arr[];
	static StringTokenizer st;
	static int counts[];
	static int INT_MAX = Integer.MAX_VALUE;
	static int INT_MIN = Integer.MIN_VALUE;
	static int dfs(int root) {
		
		int left = nodes[root].left;
		int right = nodes[root].right;
		
		
		counts[root] = 1;
		if(left == -1 && right == -1) {
			return counts[root];
		}
		
		if(left != -1) {
			counts[root] += dfs(left);
		}
		
		if(right != -1) {
			counts[root] += dfs(right);
		}
		
		return counts[root];
		
	}
	static int depthMin[], depthMax[];
	static int maxDepth = 0;
	static void dfs(int root, int depth, int L, int R) {
		maxDepth = Math.max(maxDepth, depth);
		if(root == -1)
			return;
		if(L == R) {
			depthMin[depth] = Math.min(depthMin[depth], L);
			depthMax[depth] = Math.max(depthMax[depth], R);
			return;
		}
		
		int left = nodes[root].left;
		int right = nodes[root].right;
		
		
		int leftCnt = 0;
		int rightCnt = 0;
		if(left != -1) {
			leftCnt = counts[left];
		}
		
		if(right != -1) {
			rightCnt = counts[right];
		}
			
		int leftR = L + leftCnt - 1;
		int x = leftR + 1;
		depthMin[depth] = Math.min(depthMin[depth], x);
		depthMax[depth] = Math.max(depthMax[depth], x);
		int rightL = R - rightCnt + 1;
		dfs(left, depth+1, L, leftR);
		dfs(right,depth+1, rightL, R);
	}
	
	static class Node{
		int parent, left, right;
		public Node(int parent, int left, int right) {
			this.parent = parent;
			this.left = left;
			this.right = right;
		}
	}
	
	static Node nodes[];
	
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[2 * n + 2];
        counts = new int[n+1];
        depthMin = new int[n+1];
        depthMax = new int[n+1];
        Arrays.fill(depthMin, INT_MAX);
        Arrays.fill(depthMax, INT_MIN);
        nodes = new Node[n+1];
        for(int i = 1; i<=n; i++) {
        	nodes[i] = new Node(-1,-1,-1);
        }
        
        for(int i = 0; i < n;i++) {
        	st = new StringTokenizer(br.readLine());
        	int root = Integer.parseInt(st.nextToken());
        	int left = Integer.parseInt(st.nextToken());
        	int right = Integer.parseInt(st.nextToken());
        	nodes[root].left = left;
        	nodes[root].right = right;
        	if(nodes[root].left != -1)
        		nodes[left].parent = root;
        	if(nodes[root].right != -1)
        		nodes[right].parent = root;
        }
        
        int root = -1;
        for(int i = 1 ;i<=n;i++) {
        	if(nodes[i].parent == -1) {
        		root = i;
        		break;
        	}
        }
        
        dfs(root);
        dfs(root, 1, 1, n);
        
        int y = 1;
        int ans = depthMax[y] - depthMin[y] + 1;
        for(int i = 1; i<=n; i++) {
        	if(depthMin[i] == INT_MAX || depthMax[i] == INT_MIN )
        		continue;
        	int width = depthMax[i] - depthMin[i] + 1;
        	if(ans < width) {
        		ans = width;
        		y = i;
        	}
        }
        System.out.println(y + " "+ans);
        
    }
}