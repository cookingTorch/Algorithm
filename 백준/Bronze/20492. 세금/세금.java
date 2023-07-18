import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int n1, n2;

        n1 = Integer.parseInt(br.readLine());
        n2 = n1;
        n1 -= (n1 / 100) * 22;
        n2 -= (((n2 / 100) * 20) / 100) * 22;
        sb.append(n1).append(" ").append(n2);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}