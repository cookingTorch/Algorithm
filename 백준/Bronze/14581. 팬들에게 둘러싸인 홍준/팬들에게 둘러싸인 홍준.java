import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        sb.append(":fan::fan::fan:\n");
        sb.append(":fan::").append(br.readLine()).append("::fan:\n");
        sb.append(":fan::fan::fan:");

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}