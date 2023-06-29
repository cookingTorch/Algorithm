import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int score, avg, i;

        avg = 0;
        for (i = 0; i < 5; i++) {
            score = Integer.parseInt(br.readLine());
            avg += Math.max(score, 40) / 5;
        }

        sb.append(avg);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}