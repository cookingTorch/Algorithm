import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int i;
        int[] nums;

        nums = new int[3];
        st = new StringTokenizer(br.readLine());
        for (i = 0; i < 3; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(nums);
        for (i = 0; i < 3; i++) {
            sb.append(nums[i]).append(" ");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}