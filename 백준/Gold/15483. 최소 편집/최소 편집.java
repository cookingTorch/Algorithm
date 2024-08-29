import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static final int EOF = -1;
    private static final int LINE_BREAK = '\n';

    public static void main(String[] args) throws IOException {
        int i;
        int ch;
        int len;
        int[] prev;
        int[] curr;
        int[] temp;
        String str;
        BufferedReader br;

        br = new BufferedReader(new InputStreamReader(System.in));
        len = (str = br.readLine()).length();
        prev = new int[len + 1];
        curr = new int[len + 1];
        for (i = 0; i <= len; i++) {
            prev[i] = i;
        }
        while ((ch = br.read()) != EOF && ch != LINE_BREAK) {
            curr[0] = prev[0] + 1;
            for (i = 1; i <= len; i++) {
                if (ch == str.codePointAt(i - 1)) {
                    curr[i] = prev[i - 1];
                } else {
                    curr[i] = Math.min(prev[i - 1], Math.min(prev[i], curr[i - 1])) + 1;
                }
            }
            temp = prev;
            prev = curr;
            curr = temp;
        }
        System.out.print(prev[len]);
    }
}
