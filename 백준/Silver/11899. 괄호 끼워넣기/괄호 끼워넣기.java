import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static final int OPEN = '(';
    private static final int CLOSE = ')';

    public static void main(String[] args) throws IOException {
        int cnt;
        int ans;
        BufferedReader br;

        br = new BufferedReader(new InputStreamReader(System.in));
        cnt = 0;
        ans = 0;
        for (;;) {
            switch (br.read()) {
                case OPEN:
                    cnt++;
                    continue;
                case CLOSE:
                    if (cnt == 0) {
                        ans++;
                    } else {
                        cnt--;
                    }
                    continue;
                default:
            }
            break;
        }
        System.out.print(cnt + ans);
    }
}
