import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int r1, r2, s;

        st = new StringTokenizer(br.readLine());
        r1 = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        r2 = s + (s - r1);
        sb.append(r2);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}