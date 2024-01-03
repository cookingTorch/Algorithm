import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        String str;

        str = br.readLine();
        if (str.equals("N") || str.equals("n")) {
            sb.append("Naver D2");
        } else {
            sb.append("Naver Whale");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}