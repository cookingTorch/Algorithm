import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static final int OUT = 0;
    private static final int MOD = 15;
    private static final int SIZE = 9;
    private static final int DIFF = '0';
    private static final int CLEAN_UP = 3;
    private static final int MAX_OUT_CNT = 3;
    private static final int[] AND = {0, 8, 12, 14, 15};
    private static final int[] SCORES = {0, 1, 1, 2, 1, 2, 2, 3, 1, 2, 2, 3, 2, 3, 3, 4};

    private static int n;
    private static int max;
    private static int[] batters;
    private static int[][] results;
    private static boolean[] visited;

    private static final int play(int[] batters) {
        int score;
        int inning;
        int batter;
        int outCnt;
        int runners;
        int batterIdx;
        int[] result;

        score = 0;
        inning = 0;
        outCnt = 0;
        runners = 1;
        batterIdx = 0;
        result = results[0];
        for(batter = batters[0];; batter = batters[batterIdx = (batterIdx + 1) % SIZE]) {
            if (result[batter] == OUT) {
                if (++outCnt == MAX_OUT_CNT) {
                    if (++inning == n) {
                        break;
                    }
                    outCnt = 0;
                    runners = 1;
                    result = results[inning];
                }
            } else {
                score += SCORES[runners & AND[result[batter]]];
                runners = (runners << result[batter]) & MOD | 1;
            }
        }
        return score;
    }

    private static final void permu(int depth) {
        int i;

        if (depth == SIZE) {
            max = Math.max(max, play(batters));
            return;
        }
        if (depth == CLEAN_UP) {
            permu(depth + 1);
            return;
        }
        for (i = 1; i < SIZE; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            batters[depth] = i;
            permu(depth + 1);
            visited[i] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        int i;
        int j;
        int[] result;
        BufferedReader br;

        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        results = new int[n][SIZE];
        for (i = 0; i < n; i++) {
            result = results[i];
            for (j = 0; j < SIZE; j++) {
                result[j] = br.read() - DIFF;
                br.read();
            }
        }
        max = 0;
        batters = new int[SIZE];
        visited = new boolean[SIZE];
        permu(0);
        System.out.print(max);
    }
}
