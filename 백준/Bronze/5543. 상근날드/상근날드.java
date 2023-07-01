import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int burger, soda;

        burger = Integer.parseInt(br.readLine());
        burger = Math.min(burger, Integer.parseInt(br.readLine()));
        burger = Math.min(burger, Integer.parseInt(br.readLine()));
        soda = Integer.parseInt(br.readLine());
        soda = Math.min(soda, Integer.parseInt(br.readLine()));

        sb.append(burger + soda - 50);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}