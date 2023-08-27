import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int a, b;

        a = Integer.parseInt(br.readLine());
        b = Integer.parseInt(br.readLine());
        sb.append(a * b);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}