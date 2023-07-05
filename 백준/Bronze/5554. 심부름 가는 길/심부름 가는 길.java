import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int sec, min, i;
        sec = 0;
        for (i = 0; i < 4; i++) {
            sec += Integer.parseInt(br.readLine());
        }
        min = sec / 60;
        sec %= 60;
        sb.append(min).append("\n").append(sec);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}