import java.io.*;
import java.util.*;

public class Main {
	static int a,t, flag;
	static StringTokenizer st;
	
	static void add(List<Integer> sentence, int ch, int step) {
		for(int i = 0; i < step; i++)
			sentence.add(ch);
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		a = Integer.parseInt(br.readLine());
		t = Integer.parseInt(br.readLine());
		flag = Integer.parseInt(br.readLine());
		
		int step = 1;
		int idx = 0;
//		ArrayList<Integer> sentence = new ArrayList<>(Arrays.asList(0,1,0,1));
//		step += 1;
//		add(sentence,0,step);
//		add(sentence,1,step);

//		System.out.println(sentence);
		
		int cnt0 = 0;
		int cnt1 = 0;
		while(true) {
			ArrayList<Integer> sentence = new ArrayList<>(Arrays.asList(0,1,0,1));
			step += 1;
			add(sentence,0,step);
			add(sentence,1,step);
			
			for(int num : sentence) {
				if(num == 0)
					cnt0+=1;
				else
					cnt1 += 1;
				if(flag == 0 && cnt0 == t) {
					System.out.println(idx);
					return;
				}else if(flag == 1 && cnt1 == t) {
					System.out.println(idx);
					return;
				}
				idx = (idx + 1) % a;
			}
		}
	}
}