import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int h = sc.nextInt();
        int m = sc.nextInt();

        if (m < 45) {
            if (h == 0)
                h = 24;
            h -= 1;
            m = m + 60 - 45;
            System.out.println(h + " " + m);
        } else {
            m -= 45;
            System.out.println(h+" "+m);
        }

        sc.close();
    }
}
