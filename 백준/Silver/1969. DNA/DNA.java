import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
public class Main {
	static StringTokenizer st;
	static int n,m;
	static HashMap<Character, int[]> table = new HashMap<>();
	
	static char findBest(int max, int idx) {
		char ret = 'Z';
		
		for(char ch : new char[] {'T','G','C','A'}) {
			if(max == table.get(ch)[idx]) {
				ret = ch;
			}
		}
		return ret;
	}
	
	static String dnas[];
	
	static int hamming(String dna, String ans) {
		int ret = 0;
		for(int i = 0; i < m;i++) {
			if(dna.charAt(i) != ans.charAt(i))
				ret++;
		}
		return ret;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		for(char ch : new char[] {'A','G','C','T'}) {
			table.put(ch, new int[m]);
		}
		
		dnas = new String[n];
		
		for(int i = 0; i <n;i++) {
			String dna = br.readLine();
			dnas[i] = dna;
			for(int j = 0; j < dna.length();j++) {
				char ch = dna.charAt(j);
				
				int arr[] = table.get(ch);
				
				arr[j]++;
			}
		}
//		for(char ch : new char[] {'A','G','C','T'}) {
//			System.out.println(Arrays.toString(table.get(ch)));
//		}
		
		String ans = "";
		
		for(int i = 0; i < m ;i++) {
			int max = 0;
			for(char ch : new char[] {'A','G','C','T'}) {
//				System.out.println(Arrays.toString(table.get(ch)));
				max = Math.max(max, table.get(ch)[i]);
			}
			
			char best = findBest(max, i);
			ans += best;
		}
		
		int distance = 0;
		for(int i = 0; i < n; i++) {
			distance += hamming(dnas[i], ans);
		}
	
		
		System.out.println(ans+'\n'+distance);
		
		
	}
}