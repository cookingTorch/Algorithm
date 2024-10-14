import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static final int WALL = -1;
    private static final int INITIAL_NUTRIENT = 5;
    private static final Tree NIL = new Tree();

    private static final class Tree implements Comparable<Tree> {
        int pos;
        int age;
        Tree next;

        Tree() {
        }

        Tree(int pos, int age) {
            this.pos = pos;
            this.age = age;
        }

        Tree(int pos, int age, Tree next) {
            this.pos = pos;
            this.age = age;
            this.next = next;
        }

        boolean grow() {
            if (nutrient[pos] < age) {
                return false;
            }
            nutrient[pos] -= age;
            if (++age % INITIAL_NUTRIENT == 0) {
                q.addLast(pos);
            }
            return true;
        }

        @Override
        public int compareTo(Tree o) {
            return age - o.age;
        }
    }

    private static int n;
    private static int col;
    private static int thr;
    private static int[] d;
    private static int[] humus;
    private static int[] nutrient;
    private static int[] increment;
    private static Tree trees;
    private static ArrayDeque<Integer> q;

    private static void spring() {
        Tree prev;
        Tree curr;

        prev = trees;
        for (curr = prev.next; curr != null; curr = prev.next) {
            if (curr.grow()) {
                prev = curr;
            } else {
                prev.next = curr.next;
                humus[curr.pos] += curr.age >> 1;
            }
        }
    }

    private static final void summerWinter() {
        int i;
        int pos;

        for (i = col; i < thr; i += col) {
            for (pos = i + 1; pos <= i + n; pos++) {
                nutrient[pos] += humus[pos] + increment[pos];
                humus[pos] = 0;
            }
        }
    }

    private static final void fall() {
        int i;
        int pos;
        int npos;

        while (!q.isEmpty()) {
            pos = q.pollFirst();
            for (i = 0; i < 8; i++) {
                npos = pos + d[i];
                if (nutrient[npos] != WALL) {
                    trees.next = new Tree(npos, 1, trees.next);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        int m;
        int k;
        int i;
        int j;
        int cnt;
        Tree tree;
        Tree[] initialTrees;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine(), " ", false);
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        col = n + 2;
        nutrient = new int[col * col];
        increment = new int[col * col];
        thr = (n + 1) * col;
        for (i = 0; i < col; i++) {
            nutrient[i] = WALL;
        }
        for (i = col; i < thr; i += col) {
            nutrient[i] = WALL;
            st = new StringTokenizer(br.readLine(), " ", false);
            for (j = 1; j <= n; j++) {
                nutrient[i + j] = INITIAL_NUTRIENT;
                increment[i + j] = Integer.parseInt(st.nextToken());
            }
            nutrient[i + n + 1] = WALL;
        }
        System.arraycopy(nutrient, 0, nutrient, thr, col);
        initialTrees = new Tree[m];
        for (i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ", false);
            initialTrees[i] = new Tree(Integer.parseInt(st.nextToken()) * col + Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(initialTrees);
        for (i = 1; i < m; i++) {
            initialTrees[i - 1].next = initialTrees[i];
        }
        (trees = NIL).next = initialTrees[0];
        humus = new int[col * col];
        d = new int[] {-col, -col + 1, 1, col + 1, col, col - 1, -1, -col - 1};
        q = new ArrayDeque<>();
        while (k-- > 0) {
            spring();
            summerWinter();
            fall();
        }
        cnt = 0;
        for (tree = trees.next; tree != null; tree = tree.next) {
            cnt++;
        }
        System.out.print(cnt);
    }
}