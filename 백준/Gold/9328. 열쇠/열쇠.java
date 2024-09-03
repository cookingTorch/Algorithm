import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    private static final int KEYS = 26;
    private static final int KEY = 'a';
    private static final int DOOR = 'A';
    private static final int SIZE = 104;
    private static final int[] D = {1, SIZE, -1, -SIZE};
    private static final char LINE_BREAK = '\n';
    private static final char WALL = '*';
    private static final char FLOOR = '.';
    private static final char FILE = '$';
    private static final char EMPTY = '0';
    private static final boolean[] INITIAL_KEYS = new boolean[KEYS];

    private static char[] map;
    private static char[] floors;
    private static boolean[] keys;
    private static BufferedReader br;
    private static ArrayDeque<Integer> q;
    private static ArrayDeque<Integer>[] doors;

    private static int bfs() {
        int i;
        int key;
        int cnt;
        int pos;
        int npos;

        cnt = 0;
        q.clear();
        q.addLast(SIZE + 1);
        map[SIZE + 1] = WALL;
        while (!q.isEmpty()) {
            pos = q.pollFirst();
            for (i = 0; i < 4; i++) {
                npos = pos + D[i];
                switch (map[npos]) {
                    case WALL:
                        break;
                    case FILE:
                        cnt++;
                    case FLOOR:
                        q.addLast(npos);
                        map[npos] = WALL;
                        break;
                    default:
                        if (map[npos] >= KEY) {
                            if (!keys[key = map[npos] - KEY]) {
                                keys[key] = true;
                                while (!doors[key].isEmpty()) {
                                    q.addLast(doors[key].pollFirst());
                                }
                            }
                        } else if (map[npos] >= DOOR && !keys[map[npos] - DOOR]) {
                            doors[map[npos] - DOOR].addLast(npos);
                            map[npos] = WALL;
                            break;
                        }
                        q.addLast(npos);
                        map[npos] = WALL;
                }
            }
        }
        return cnt;
    }

    private static int solution() throws IOException {
        int h;
        int w;
        int i;
        char[] str;
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        System.arraycopy(floors, 1, map, SIZE + 1, w + 2);
        System.arraycopy(floors, 1, map, (h + 2) * SIZE + 1, w + 2);
        System.arraycopy(map, 1, map, (h + 3) * SIZE + 1, w + 2);
        map[SIZE + w + 3] = WALL;
        for (i = 2 * SIZE; i <= (h + 1) * SIZE; i += SIZE) {
            map[i + 1] = FLOOR;
            br.read(map, i + 2, w);
            br.read();
            map[i + w + 2] = FLOOR;
            map[i + w + 3] = WALL;
        }
        map[(h + 2) * SIZE + w + 3] = WALL;
        System.arraycopy(INITIAL_KEYS, 0, keys, 0, KEYS);
        str = br.readLine().toCharArray();
        if (str[0] != EMPTY) {
            for (char key : str) {
                keys[key - KEY] = true;
            }
        }
        for (i = 0; i < KEYS; i++) {
            doors[i].clear();
        }
        return bfs();
    }

    public static void main(String[] args) throws IOException {
        int t;
        int i;
        StringBuilder sb;

        map = new char[SIZE * SIZE];
        floors = new char[SIZE];
        for (i = 1; i < SIZE; i++) {
            map[i] = WALL;
            map[i * SIZE] = WALL;
            floors[i] = FLOOR;
        }
        keys = new boolean[KEYS];
        q = new ArrayDeque<>();
        doors = new ArrayDeque[KEYS];
        for (i = 0; i < KEYS; i++) {
            doors[i] = new ArrayDeque<>();
        }
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            sb.append(solution()).append(LINE_BREAK);
        }
        System.out.print(sb.toString());
    }
}
