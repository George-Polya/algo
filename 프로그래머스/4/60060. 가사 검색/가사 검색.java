import java.util.*;
class Solution {
    static int n,m;
    static String reverseString(String str){
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        return sb.reverse().toString();
    }
    static String words[], queries[];
    static Map<Integer, List<String>> map = new HashMap<>();
    static Map<Integer, List<String>> rMap = new HashMap<>();
    
    static int binarySearch(String target, List<String> list){
        int l = 0;
        int r = list.size() - 1;
        int ret = r + 1;
        
        while(l<=r){
            int mid = (l + r)/2;
            
            if(target.compareTo(list.get(mid)) < 0){
                r = mid - 1;
                ret = mid;
            }else{
                l = mid + 1;
            }
        }
        
        return ret;
        
    }
    
    public int[] solution(String[] _words, String[] _queries) {
        words = _words;
        queries = _queries;
        n = words.length;
        m = queries.length;
        
        for(int i = 0; i < n ;i++){
            String word = words[i];
            
            int len = word.length();
            if(!map.containsKey(len)){
                List<String> list = new ArrayList<>();
                List<String> reverse = new ArrayList<>();
                list.add(word);
                reverse.add(reverseString(word));
                
                map.put(len, list);
                rMap.put(len, reverse);
                
            }else{
                map.get(len).add(word);
                rMap.get(len).add(reverseString(word));
            }
            
              // System.out.printf("%s : %s\n", word, map.get(len));
        }
        
        for(int len : map.keySet()){
            Collections.sort(map.get(len));
            Collections.sort(rMap.get(len));
        }
        
//         System.out.println(map);
//         System.out.println(rMap);
        
        int answer[] = new int[m];
        for(int i = 0; i < m; i++){
            String target = queries[i];
            int len = target.length();
            List<String> list = map.get(len);
            if(target.charAt(0) == '?'){
                target = reverseString(target);
                list = rMap.get(len);
            }
            
            if(!map.containsKey(len)){
                continue;
            }
        
            String minQuery = target.replace("?", "a");
            String maxQuery = target.replace("?", "z");
            
            int s = binarySearch(minQuery, list);
            int e = binarySearch(maxQuery, list); 
            // System.out.printf("%s : %d %d\n", minQuery, s,e);
            
            answer[i] = e - s;
            
        }
        
        
        return answer;
    }
}