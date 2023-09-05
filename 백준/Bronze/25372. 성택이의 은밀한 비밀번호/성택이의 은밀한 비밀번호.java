import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int n, i;
        String pw;

        n = Integer.parseInt(br.readLine());
        for (i = 0; i < n; i++) {
            pw = br.readLine();
            if (6 <= pw.length() && pw.length() <= 9)
                sb.append("yes\n");
            else
                sb.append("no\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}