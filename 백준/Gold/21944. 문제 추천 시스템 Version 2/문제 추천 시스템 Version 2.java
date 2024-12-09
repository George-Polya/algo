import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static int n, m;
    static class Pair implements Comparable<Pair>{
        int level, id;

        public Pair(int level, int id) {
        	this.level = level;
        	this.id = id;
        }
        
        public int compareTo(Pair o) {
        	if(level != o.level)
        		return level - o.level;
        	return id - o.id;
        }
        
        public boolean eqausl(Object o) {
        	if(this == o)
        		return true;
        	if(getClass() != o.getClass())
        		return false;
        	
        	Pair p = (Pair)o;
        	return this.level == p.level && this.id == p.id;
        }
        
        public int hashCode() {
        	return Objects.hash(level,id);
        }

    }

    static TreeSet<Pair> tree;
    static TreeSet<Pair> treeGroups[];
    static HashMap<Integer, Pair> problems;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        tree = new TreeSet<>();
        treeGroups = new TreeSet[101];
        for (int i = 0; i <= 100; i++) {
            treeGroups[i] = new TreeSet<>();
        }
        problems = new HashMap<>();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int id = Integer.parseInt(st.nextToken());
            int level = Integer.parseInt(st.nextToken());
            int group = Integer.parseInt(st.nextToken());

            tree.add(new Pair(level,id));
            treeGroups[group].add(new Pair(level, id));
            problems.put(id, new Pair(level, group));
        }

        m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String oper = st.nextToken();
            if (oper.equals("add")) {
                int id = Integer.parseInt(st.nextToken());
                int level = Integer.parseInt(st.nextToken());
                int group = Integer.parseInt(st.nextToken());

                tree.add(new Pair(level, id));
                treeGroups[group].add(new Pair(level, id));
                problems.put(id, new Pair(level, group));

            } else if (oper.equals("recommend")) {
                int group = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());
                if (x == 1) {
                    sb.append(treeGroups[group].last().id).append('\n');
                } else {
                    sb.append(treeGroups[group].first().id).append('\n');
                }
            }else if (oper.equals("recommend2")) {
                int x = Integer.parseInt(st.nextToken());
                if (x == 1) {
                    sb.append(tree.last().id).append('\n');
                } else {
                    sb.append(tree.first().id).append('\n');
                }
            }else if (oper.equals("recommend3")) {
                int x = Integer.parseInt(st.nextToken());
                int limit = Integer.parseInt(st.nextToken());
                if (x == 1) {
                    Pair now = tree.higher(new Pair(limit, -1));
                    if (now == null) {
                        sb.append(-1).append('\n');
                    } else {
                        sb.append(now.id).append('\n');
                    }
                } else {
                    Pair now = tree.lower(new Pair(limit, -1));
                    if (now == null) {
                        sb.append(-1).append('\n');
                    } else {
                        sb.append(now.id).append('\n');
                    }
                }
            }else if (oper.equals("solved")) {
                int id = Integer.parseInt(st.nextToken());
                Pair info = problems.get(id);
                int group = info.id;
                int level = info.level;

                treeGroups[group].remove(new Pair(level, id));
                tree.remove(new Pair(level, id));
                problems.remove(id);
            }
        }

        System.out.println(sb);
    }
}