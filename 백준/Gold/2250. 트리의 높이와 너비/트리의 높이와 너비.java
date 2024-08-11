import java.util.*;
import java.io.*;

public class Main {
	static int n;
	static int arr[];
	static StringTokenizer st;
	
	static class Node{
		int parent, left, right;
		public Node(int parent, int left, int right) {
			this.parent = parent;
			this.left = left;
			this.right = right;
		}
		
		public String toString() {
			return parent +" "+left+" "+right;
		}
	}
	
	static Node nodes[];
	static int levelMin[], levelMax[];
	static int maxLevel = 0;
	static int x = 1;
	static void inOrder(int cur, int level) {
		Node root = nodes[cur];
		if(maxLevel < level)
			maxLevel = level;
		
		if(root.left != -1)
			inOrder(root.left, level+1);
		
		levelMin[level] = Math.min(levelMin[level], x);
		levelMax[level] = x;
		x++;
		
		if(root.right != -1)
			inOrder(root.right, level + 1);
		
	}
	
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        nodes = new Node[n+1];
        levelMin = new int[n+1];
        levelMax = new int[n+1];
        Arrays.fill(levelMin, Integer.MAX_VALUE);
        for(int i = 0 ;i <=n;i++) {
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
        for(int i = 1; i<=n;i++) {
        	if(nodes[i].parent == -1) {
        		root = i;
        		break;
        	}
        }
        
        inOrder(root, 1);
        
        int y = 1;
        int ans = levelMax[y] - levelMin[y] + 1;
        for(int i = 2; i<=maxLevel;i++) {
        	int width = levelMax[i] - levelMin[i] + 1;
        	if(ans < width) {
        		ans = width;
        		y = i;
        	}
        }
        System.out.println(y+" "+ans);
    }
}