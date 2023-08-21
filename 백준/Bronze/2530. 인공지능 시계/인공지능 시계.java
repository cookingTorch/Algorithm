import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        long a, b, c, d;

        st = new StringTokenizer(br.readLine());
        a = Long.parseLong(st.nextToken());
        b = Long.parseLong(st.nextToken());
        c = Long.parseLong(st.nextToken());
        d = Long.parseLong(br.readLine());
        c = ((a * 3600) + (b * 60) + c + d) % (24 * 3600);
        a = c / 3600;
        c %= 3600;
        b = c / 60;
        c %= 60;
        sb.append(a).append(" ").append(b).append(" ").append(c);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}