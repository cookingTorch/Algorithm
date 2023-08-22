import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int n, i, j;

        n = Integer.parseInt(br.readLine());
        for (i = 0; i < n; i++) {
            for (j = 0; j < i; j++)
                sb.append(" ");
            for (j = 0; j < n - i; j++)
                sb.append("*");
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}