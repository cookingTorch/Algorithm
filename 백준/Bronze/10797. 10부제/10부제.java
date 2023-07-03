import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        int num, cnt, i;
        
        num = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        cnt = 0;
        for (i = 0; i < 5; i++) {
            if (Integer.parseInt(st.nextToken()) == num) {
                cnt++;
            }
        }
        sb.append(cnt);
        
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
