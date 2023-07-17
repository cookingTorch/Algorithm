import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int time, drink;

        st = new StringTokenizer(br.readLine());
        time = Integer.parseInt(st.nextToken());
        drink = Integer.parseInt(st.nextToken());
        if (drink == 1 || !(12 <= time && time <= 16)) {
            sb.append(280);
        } else {
            sb.append(320);
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}