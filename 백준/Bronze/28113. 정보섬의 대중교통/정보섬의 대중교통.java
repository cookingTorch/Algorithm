import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int n, a, b;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        if (b >= n && b < a) {
            sb.append("Subway");
        } else if (b >= n && b == a) {
            sb.append("Anything");
        } else {
            sb.append("Bus");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}