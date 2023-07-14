import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int dist;

        dist = Integer.parseInt(br.readLine());
        sb.append((dist + 4) / 5);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}