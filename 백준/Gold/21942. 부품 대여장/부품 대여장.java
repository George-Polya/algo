import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/21942
public class Main {
    static int N, fine;
    static String L;

    static int days[] = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    static int getTimestamp(String date, String time) {
        String[] inputs = date.split("-");
        int month = Integer.parseInt(inputs[1]);
        int day = Integer.parseInt(inputs[2]);

        for (int i = 0; i < month; i++) {
            day += days[i];
        }

        inputs = time.split(":");
        int hour = Integer.parseInt(inputs[0]);
        int minute = Integer.parseInt(inputs[1]);
        return ((day - 1) * 24 + hour) * 60 + minute;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = st.nextToken();
        fine = Integer.parseInt(st.nextToken());

        String[] inputs = L.split("/");
        int day = Integer.parseInt(inputs[0]);
        int hour = Integer.parseInt(inputs[1].split(":")[0]);
        int minute = Integer.parseInt(inputs[1].split(":")[1]);
        int limit = (day * 24 + hour) * 60 + minute;

        HashMap<String, HashMap<String, Integer>> map = new HashMap<>();

        HashMap<String, Long> penalty = new HashMap<>();

        for (int i = 0; i < N; i++) {
            inputs = br.readLine().split(" ");
            int timestamp = getTimestamp(inputs[0], inputs[1]);
            String item = inputs[2];
            String name = inputs[3];

            if (!map.containsKey(name)) { // 해당사람의 정보가 처음 들어오는 경우
                map.put(name, new HashMap<>());
                map.get(name).put(item, timestamp);
            } else { // 해당 사람의 정보가 이미 있는 경우
                if (!map.get(name).containsKey(item)) { // 해당 사람이 해당 물건을 빌리는 경우
                    map.get(name).put(item, timestamp);
                } else { // 반납하는 경우
                    int prev = map.get(name).get(item);
                    long diff = Math.max(0, timestamp - prev - limit);

                    // 벌금 기록 정보
                    if (!penalty.containsKey(name)) {
                        penalty.put(name, diff * fine);
                    } else {
                        long update = penalty.get(name) + diff * fine;
                        penalty.put(name, update);
                    }
                    map.get(name).remove(item);
                }
            }


        }



        List<String> keyList = new ArrayList<>(penalty.keySet());
        Collections.sort(keyList);

        boolean found = false;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < keyList.size(); i++) {
            String name = keyList.get(i);
            if(penalty.get(name) == 0)
                continue;
            sb.append(name + " " + penalty.get(name) + "\n");
            found = true;
        }

        if (!found) {
            System.out.println(-1);
        } else {
            System.out.println(sb);
        }
    }
}
