import java.io.*;

public class Main {
    public static void main(String[] args)  throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int l, a, b, c, d, korean, math;

        l = Integer.parseInt(br.readLine());
        a = Integer.parseInt(br.readLine());
        b = Integer.parseInt(br.readLine());
        c = Integer.parseInt(br.readLine());
        d = Integer.parseInt(br.readLine());

        korean = a / c;
        if (a % c != 0) {
            korean++;
        }
        math = b / d;
        if (b % d != 0) {
            math++;
        }

        sb.append(Math.max(0, l - Math.max(korean, math)));

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}