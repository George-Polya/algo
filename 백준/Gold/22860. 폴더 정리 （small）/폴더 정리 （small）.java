import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	st = new StringTokenizer(br.readLine());
    	N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());

    	Folder main = new Folder("main");
    	folderList.add(main);
    	folderMap.put("main", 0);
    	
    	for(int i = 0; i < N+M;i++) {
    		st = new StringTokenizer(br.readLine());
    		String parent = st.nextToken();
    		String child = st.nextToken();
    		boolean isFolder = Integer.parseInt(st.nextToken()) == 1 ? true : false;
    		insert(parent, child, isFolder);
    	}
    	
    	Q = Integer.parseInt(br.readLine());
    	for(int q = 0; q < Q; q++) {
//    		fileCnt = 0;
    		String[] names = br.readLine().split("/");
    		String name = names[names.length - 1];
    		Folder now = null;
    		
    		for(Folder folder: folderList) {
    			if(folder.name.equals(name)) {
    				now = folder;
    				HashSet<String> hs = new HashSet<>();
//    				int cnt = findFile(now, hs);
    				int fileCnt = findFile(now, hs);
    				System.out.println(hs.size() +" "+fileCnt);
    			}
    		}
    	}
    	
    }
    
    static int findFile(Folder folder, HashSet<String> hs) {
    	int ret = folder.files.size();
    	
    	for(String s : folder.files)
    		hs.add(s);
    	
    	if(!folder.folders.isEmpty()) {
    		for(Folder child : folder.folders)
    			ret += findFile(child, hs);
    	}
    	
    	return ret;
    	
    }
    static List<Folder> folderList = new ArrayList<>();
    static Map<String, Integer> folderMap = new HashMap<>();
    static void insert(String parent, String child, boolean isFolder) {
    	Folder pNode = search(parent);
    	
    	if(pNode == null) {
    		pNode = new Folder(parent);
    		folderList.add(pNode);
    	}
    	
    	if(isFolder) {
    		Folder cNode = search(child);
    		
    		if(cNode == null) {
    			cNode = new Folder(child);
    			pNode.folders.add(cNode);
    			folderList.add(cNode);
    		}else
    			pNode.folders.add(cNode);
    		
    	}else {
    		pNode.files.add(child);
    	}
    	
    }
    
    static Folder search(String name) {
    	Folder ret = null;
    	
    	for(Folder folder : folderList) {
    		if(folder.name.equals(name)) {
    			ret = folder;
    			break;
    		}
    	}
    	return ret;
    }
    
    static StringTokenizer st;
    static int N,M, Q;
    static class Folder{
    	String name;
    	List<Folder> folders = new ArrayList<>();
    	List<String> files = new ArrayList<>();
    	public Folder(String name) {
    		this.name = name;
    	}

    }
}