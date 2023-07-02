import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int i;
        int[] cnt;
        String str;

        str = br.readLine();
        cnt = new int[26];
        for (i = 0; i < str.length(); i++) {
            cnt[str.charAt(i) - 'a']++;
        }
        for (i = 0; i < 26; i++) {
            sb.append(cnt[i]).append(" ");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}