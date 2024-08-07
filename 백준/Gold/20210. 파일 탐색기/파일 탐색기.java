import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main {
    static int n;
    static ArrayList<ArrayList<String>> info = new ArrayList<>();

    // 숫자인지 문자인지 확인
    public static int Number_Alpha(String check) {
        if (check.charAt(0) >= '0' && check.charAt(0) <= '9')
            return 0;
        return 1;
    }

    public static boolean cmp(ArrayList<String> a, ArrayList<String> b) {
        int len = Math.min(a.size(), b.size());

        for (int i = 0; i < len; i++) {
            // 앞에는 숫자 뒤에는 문자일때
            if (Number_Alpha(a.get(i)) == 0 && Number_Alpha(b.get(i)) == 1) {
                return true;
            }

            // 앞에는 문자 뒤에는 숫자일때
            else if (Number_Alpha(a.get(i)) == 1 && Number_Alpha(b.get(i)) == 0) {
                return false;
            }

            // 둘다 숫자일때
            else if (Number_Alpha(a.get(i)) == 0 && Number_Alpha(b.get(i)) == 0) {
                int a_front_zero_cnt = 0;
                int b_front_zero_cnt = 0;
                String a_real = "";
                String b_real = "";

                for (int j = 0; j < a.get(i).length(); j++) {
                    if (a.get(i).charAt(j) == '0')
                        a_front_zero_cnt++;
                    else {
                        a_real = a.get(i).substring(a_front_zero_cnt);
                        break;
                    }
                }

                for (int j = 0; j < b.get(i).length(); j++) {
                    if (b.get(i).charAt(j) == '0')
                        b_front_zero_cnt++;
                    else {
                        b_real = b.get(i).substring(b_front_zero_cnt);
                        break;
                    }
                }

                // 같은 값을 가지는 숫자일때
                if (a_real.equals(b_real)) {
                    // 앞의 0의 개수가 적은것이 앞에온다
                    if (a_front_zero_cnt < b_front_zero_cnt)
                        return true;
                    else if (a_front_zero_cnt > b_front_zero_cnt)
                        return false;
                } else {
                    if (a_real.length() < b_real.length())
                        return true;
                    else if (a_real.length() > b_real.length())
                        return false;
                    else {
                        for (int j = 0; j < a_real.length(); j++) {
                            if (a_real.charAt(j) < b_real.charAt(j))
                                return true;
                            else if (a_real.charAt(j) > b_real.charAt(j))
                                return false;
                        }
                    }
                }
            }

            // 둘다 문자일때
            else if (Number_Alpha(a.get(i)) == 1 && Number_Alpha(b.get(i)) == 1) {
                int i_len = Math.min(a.get(i).length(), b.get(i).length());

                for (int j = 0; j < i_len; j++) {
                    if (Character.toLowerCase(a.get(i).charAt(j)) < Character.toLowerCase(b.get(i).charAt(j)))
                        return true;
                    else if (Character.toLowerCase(a.get(i).charAt(j)) > Character.toLowerCase(b.get(i).charAt(j)))
                        return false;
                    else {
                        if ((a.get(i).charAt(j) >= 'A' && a.get(i).charAt(j) <= 'Z') &&
                            (b.get(i).charAt(j) >= 'a' && b.get(i).charAt(j) <= 'z'))
                            return true;
                        else if ((a.get(i).charAt(j) >= 'a' && a.get(i).charAt(j) <= 'z') &&
                                 (b.get(i).charAt(j) >= 'A' && b.get(i).charAt(j) <= 'Z'))
                            return false;
                    }
                }

                if (a.get(i).length() < b.get(i).length())
                    return true;
                else if (a.get(i).length() > b.get(i).length())
                    return false;
            }
        }

        if (a.size() < b.size())
            return true;
        else
            return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            info.add(new ArrayList<>());
            String input = br.readLine();
            String temp = "";
            boolean number = false;

            for (int j = 0; j < input.length(); j++) {
                char c = input.charAt(j);

                if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                    if (temp.equals("")) {
                        temp += c;
                    } else if (number) {
                        info.get(i).add(temp);
                        temp = "" + c;
                    } else {
                        temp += c;
                    }
                    number = false;
                } else if (c >= '0' && c <= '9') {
                    if (temp.equals("")) {
                        temp += c;
                    } else if (number) {
                        temp += c;
                    } else {
                        info.get(i).add(temp);
                        temp = "" + c;
                    }
                    number = true;
                }

                if (j == input.length() - 1 && !temp.equals("")) {
                    info.get(i).add(temp);
                }
            }
        }

        Collections.sort(info, new Comparator<ArrayList<String>>() {
            public int compare(ArrayList<String> a, ArrayList<String> b) {
                return cmp(a, b) ? -1 : 1;
            }
        });

        for (ArrayList<String> strings : info) {
            for (String s : strings) {
                System.out.print(s);
            }
            System.out.println();
        }
    }
}