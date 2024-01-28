import java.io.*;
import java.util.*;

public class Solution {
    static int T = 10;
    static int n,m;
    static int MAX_N = 5000;
    static StringTokenizer st;
    
    static class Node{
        int value;
        Node nxt;
        public Node(int value) {
            this.value = value;
            this.nxt = nxt;
        }
        
        public Node() {
            
        }
    }
    
    static class LinkedList{
        Node head, tail;
        Node nodeArr[];
        int nodeCnt;
        
        public LinkedList() {
            head = null;
            nodeArr = new Node[MAX_N];
            nodeCnt = 0;
        }
        
        public Node createNode(int value) {
            Node node = new Node(value);
            nodeArr[nodeCnt] = node;
            
            return nodeArr[nodeCnt++];
        }
        
        public void insert(int idx, int arr[]) {
            int start = 0;
            if(idx == 0) {
                if(head != null) {
                    Node node = createNode(arr[0]);
                    node.nxt = head;
                    head = node;
                    
                }else {
                    head = createNode(arr[0]);
                }
                idx = 1;
                start = 1;

            }
            
            
            Node cur = head;
            for(int i = 1; i < idx;i++) {
                cur = cur.nxt;
            }
            
            for(int i = start; i < arr.length;i++) {
                Node node = createNode(arr[i]);
                node.nxt = cur.nxt;
                cur.nxt = node;
                cur = node;
            }
            
            if(cur.nxt == null)
                tail = cur;
        }
        
        public void delete(int idx ,int cnt) {
            Node cur = head;
            
            if(idx == 0) {
                for(int i = 0; i < cnt;i++)
                    cur = cur.nxt;
                head = cur;
                return;
            }
            
            for(int i = 1; i < idx; i++)
                cur = cur.nxt;
            
            Node anchor = cur;
            for(int i = 0; i < cnt;i++)
                cur = cur.nxt;
            
            anchor.nxt = cur.nxt;
            
            if(anchor.nxt == null)
            	tail = anchor;
            
        }
        
        public void add(int value) {
        	Node node = createNode(value);
        	tail.nxt = node;
        	tail = node;
        }
    }
    
     public static void main(String[] args) throws IOException{
//          System.setIn(new FileInputStream("./input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        for(int tc =1; tc<=T;tc++) {
            
            
            n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            LinkedList list = new LinkedList();
                    
            int arr[] = new int[n];
            for(int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            list.insert(0, arr);
            
            
            m = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i< m;i++) {
                char cmd = st.nextToken().charAt(0);
                int x,y;
                
                if(cmd == 'I') {
                    x = Integer.parseInt(st.nextToken());
                    y = Integer.parseInt(st.nextToken());
                    int temp[] = new int[y];
                    for(int j = 0; j<y;j++)
                        temp[j] = Integer.parseInt(st.nextToken());
                    
                    list.insert(x, temp);
                }else if(cmd == 'D') {
                    x = Integer.parseInt(st.nextToken());
                    y = Integer.parseInt(st.nextToken());
                    list.delete(x,y);
                }else if(cmd == 'A') {
                    y = Integer.parseInt(st.nextToken());
                    for(int j = 0; j < y; j++)
                        list.add(Integer.parseInt(st.nextToken()));
                }
            }
            
            sb.append('#').append(tc).append(' ');
            Node cur = list.head;
//            System.out.println(cur.value);
            for(int i = 0; i< 10;i++) {
            	sb.append(cur.value).append(' ');
            	cur = cur.nxt;
            }
           
            
          sb.append('\n');

        }
        System.out.println(sb);
    
    }
}