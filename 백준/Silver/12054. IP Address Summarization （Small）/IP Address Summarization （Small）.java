import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final int START = 1 << 31;
    private static final int MOD = (1 << 8) - 1;
    private static final char DOT = '.';
    private static final char SLASH = '/';
    private static final char LINE_BREAK = '\n';
    private static final char[] PREFIX = "Case #".toCharArray();
    private static final char[] SUFFIX = {':', LINE_BREAK};
    private static final String DELIM = "./";

    private static final class Trie {
        private int time;
        private int output;
        private Trie zero;
        private Trie one;

        final void insert(int addr, int bit, int end) {
            this.time = testCase;
            if (bit == end) {
                output = testCase;
                return;
            }
            if ((addr & bit) == 0) {
                if (zero == null) {
                    zero = new Trie();
                }
                zero.insert(addr, bit >>> 1, end);
            } else {
                if (one == null) {
                    one = new Trie();
                }
                one.insert(addr, bit >>> 1, end);
            }
            if (zero != null && one != null && zero.output == testCase && one.output == testCase) {
                output = testCase;
            }
        }

        final void print(int num, int depth) {
            if (output == testCase) {
                num <<= 32 - depth;
                sb.append((num >>> 24) & MOD).append(DOT)
                        .append((num >>> 16) & MOD).append(DOT)
                        .append((num >>> 8) & MOD).append(DOT)
                        .append(num & MOD).append(SLASH)
                        .append(depth).append(LINE_BREAK);
                return;
            }
            depth++;
            if (zero != null && zero.time == testCase) {
                zero.print(num << 1, depth);
            }
            if (one != null && one.time == testCase) {
                one.print(num << 1 | 1, depth);
            }
        }
    }

    private static int testCase;
    private static Trie trie;
    private static StringBuilder sb;
    private static BufferedReader br;

    private static final void solution() throws IOException {
        int n;
        int p;
        int addr;
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        while (n-- > 0) {
            st = new StringTokenizer(br.readLine(), DELIM, false);
            addr = Integer.parseInt(st.nextToken()) << 24
                    | Integer.parseInt(st.nextToken()) << 16
                    | Integer.parseInt(st.nextToken()) << 8
                    | Integer.parseInt(st.nextToken());
            p = Integer.parseInt(st.nextToken());
            trie.insert(addr, START, START >>> p);
        }
        trie.print(0, 0);
    }

    public static void main(String[] args) throws IOException {
        int t;

        trie = new Trie();
        br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());
        sb = new StringBuilder();
        for (testCase = 1; testCase <= t; testCase++) {
            sb.append(PREFIX).append(testCase).append(SUFFIX);
            solution();
        }
        System.out.print(sb.toString());
    }
}
