import java.util.ArrayDeque;

class Solution {
    private static final int NIL = -1;
    private static final int NUM = 6;
    private static final int SIZE = 4;
    private static final int EMPTY = 0;
    private static final int INF = Integer.MAX_VALUE;

    private static final class Card {
        private static final int[] dx = {-1, 0, 1, 0};
        private static final int[] dy = {0, 1, 0, -1};

        private static boolean[][] visited;
        private static boolean[][] initialVisited;
        private static ArrayDeque<int[]> q;

        int x1;
        int y1;
        int x2;
        int y2;
        int idx;

        Card(int idx) {
            this.idx = idx;
            x1 = NIL;
        }

        static void init() {
            q = new ArrayDeque<>();
            initialVisited = new boolean[SIZE][SIZE];
            visited = new boolean[SIZE][SIZE];
        }

        private static int bfs(int x1, int y1, int x2, int y2) {
            int i;
            int x;
            int y;
            int nx;
            int ny;
            int cnt;
            int[] cur;

            if (x1 == x2 && y1 == y2) {
                return 0;
            }
            q.clear();
            for (i = 0; i < SIZE; i++) {
                System.arraycopy(initialVisited[i], 0, visited[i], 0, SIZE);
            }
            q.addLast(new int[] {x1, y1, 0});
            visited[x1][y1] = true;
            while (!q.isEmpty()) {
                cur = q.pollFirst();
                x = cur[0];
                y = cur[1];
                cnt = cur[2] + 1;
                for (i = 0; i < 4; i++) {
                    nx = x + dx[i];
                    ny = y + dy[i];
                    if (nx < 0 || nx >= SIZE || ny < 0 || ny >= SIZE) {
                        continue;
                    }
                    if (!visited[nx][ny] && map[nx][ny] == EMPTY) {
                        if (nx == x2 && ny == y2) {
                            return cnt;
                        }
                        q.addLast(new int[] {nx, ny, cnt});
                        visited[nx][ny] = true;
                    }
                    while (map[nx][ny] == EMPTY) {
                        nx += dx[i];
                        ny += dy[i];
                        if (nx < 0 || nx >= SIZE || ny < 0 || ny >= SIZE) {
                            nx -= dx[i];
                            ny -= dy[i];
                            break;
                        }
                    }
                    if (!visited[nx][ny]) {
                        if (nx == x2 && ny == y2) {
                            return cnt;
                        }
                        q.addLast(new int[] {nx, ny, cnt});
                        visited[nx][ny] = true;
                    }
                }
            }
            return NIL;
        }

        void add(int x, int y) {
            if (x1 == NIL) {
                x1 = x;
                y1 = y;
            } else {
                x2 = x;
                y2 = y;
            }
        }

        int deleteForward(int x, int y) {
            int cnt;

            cnt = bfs(x, y, x1, y1) + bfs(x1, y1, x2, y2) + 2;
            map[x1][y1] = EMPTY;
            map[x2][y2] = EMPTY;
            return cnt;
        }

        int deleteBackward(int x, int y) {
            int cnt;

            cnt = bfs(x, y, x2, y2);
            cnt += bfs(x2, y2, x1, y1);
            cnt += 2;
            map[x1][y1] = EMPTY;
            map[x2][y2] = EMPTY;
            return cnt;
        }

        void restore() {
            map[x1][y1] = idx;
            map[x2][y2] = idx;
        }
    }

    private static int min;
    private static int size;
    private static int[] arr;
    private static int[][] map;
    private static boolean[] visited;
    private static Card[] cards;

    private static void dfs(int x, int y, int cnt, int depth) {
        int i;
        int move;
        Card card;

        if (depth == size) {
            min = Math.min(min, cnt);
            return;
        }
        depth++;
        for (i = 0; i < size; i++) {
            if (visited[i]) {
                continue;
            }
            card = cards[arr[i]];
            visited[i] = true;
            move = card.deleteForward(x, y);
            dfs(card.x2, card.y2, cnt + move, depth);
            card.restore();
            move = card.deleteBackward(x, y);
            dfs(card.x1, card.y1, cnt + move, depth);
            card.restore();
            visited[i] = false;
        }
    }

    public int solution(int[][] board, int r, int c) {
        int i;
        int j;
        int idx;

        map = board;
        size = 0;
        cards = new Card[NUM + 1];
        for (i = 0; i < SIZE; i++) {
            for (j = 0; j < SIZE; j++) {
                if ((idx = board[i][j]) != EMPTY) {
                    if (cards[idx] == null) {
                        cards[idx] = new Card(idx);
                        size++;
                    }
                    cards[idx].add(i, j);
                }
            }
        }
        arr = new int[size];
        visited = new boolean[size];
        idx = 0;
        for (i = 1; i <= NUM; i++) {
            if (cards[i] != null) {
                arr[idx++] = i;
            }
        }
        min = INF;
        Card.init();
        dfs(r, c, 0, 0);
        return min;
    }
}
