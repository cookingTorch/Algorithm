import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        int sum1 = 0, sum2 = 0, i;
        st = new StringTokenizer(br.readLine());
        for(i = 0; i < 4; i++) {
            sum1 += Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for(i = 0; i < 4; i++) {
            sum2 += Integer.parseInt(st.nextToken());
        }
        sb.append(Math.max(sum1, sum2));
        
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
