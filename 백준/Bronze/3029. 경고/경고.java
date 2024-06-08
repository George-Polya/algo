import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
	
	static int toSeconds(String time) {
	    String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        int seconds = Integer.parseInt(parts[2]);
        return hours * 3600 + minutes * 60 + seconds;
	}
	
	   private static String toTimeString(int totalSeconds) {
	        int hours = totalSeconds / 3600;
	        int minutes = (totalSeconds % 3600) / 60;
	        int seconds = totalSeconds % 60;
	        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
	    }
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String current = br.readLine();
		String target = br.readLine();
		
		int currentTime = toSeconds(current);
		int targetTime = toSeconds(target);

		if(targetTime <= currentTime) {
			targetTime += 86400;
		}
		
		int diff = targetTime - currentTime;
		System.out.println(toTimeString(diff));
	}
}