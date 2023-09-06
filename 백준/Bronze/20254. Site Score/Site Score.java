import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int ur, tr, uo, to;

        st = new StringTokenizer(br.readLine());
        ur = Integer.parseInt(st.nextToken());
        tr = Integer.parseInt(st.nextToken());
        uo = Integer.parseInt(st.nextToken());
        to = Integer.parseInt(st.nextToken());
        sb.append((56 * ur) + (24 * tr) + (14 *  uo) + (6 * to));

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}