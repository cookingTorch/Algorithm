import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        int i;
        int j;
        int len1;
        int len2;
        int[] prev;
        int[] curr;
        int[] temp;
        char ch;
        String str1;
        String str2;
        BufferedReader br;

        br = new BufferedReader(new InputStreamReader(System.in));
        len1 = (str1 = br.readLine()).length();
        len2 = (str2 = br.readLine()).length();
        prev = new int[len1 + 1];
        curr = new int[len1 + 1];
        for (i = 0; i <= len1; i++) {
            prev[i] = i;
        }
        for (i = 0; i < len2; i++) {
            ch = str2.charAt(i);
            curr[0] = prev[0] + 1;
            for (j = 1; j <= len1; j++) {
                if (ch == str1.charAt(j - 1)) {
                    curr[j] = prev[j - 1];
                } else {
                    curr[j] = Math.min(prev[j - 1], Math.min(prev[j], curr[j - 1])) + 1;
                }
            }
            temp = prev;
            prev = curr;
            curr = temp;
        }
        System.out.print(prev[len1]);
    }
}
