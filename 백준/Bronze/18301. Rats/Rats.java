import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int n1, n2, n12;

        st = new StringTokenizer(br.readLine());
        n1 = Integer.parseInt(st.nextToken());
        n2 = Integer.parseInt(st.nextToken());
        n12 = Integer.parseInt(st.nextToken());
        sb.append((((n1 + 1) * (n2 + 1)) / (n12 + 1)) - 1);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}