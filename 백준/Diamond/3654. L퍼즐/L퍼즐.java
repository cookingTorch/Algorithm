import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	private static final String DELIM = " ";
	private static final char BLACK = 'B';
	private static final char WHITE = 'W';
	private static final int VERTICAL = 0;
	private static final int HORIZONTAL = 1;
	private static final int UP = 0;
	private static final int DOWN = 1;
	private static final int LEFT = 0;
	private static final int RIGHT = 1;

	private static int edgeCnt;
	private static char[][] board;
	private static int[][] blackId;
	private static int[] head;
	private static int[] to;
	private static int[] next;
	private static int[] reverseHead;
	private static int[] reverseTo;
	private static int[] reverseNext;

	private static final int getLiteral(int id, int axis, int value) {
		return ((((id << 1) | axis) << 1) | value);
	}

	private static final boolean isWhite(int r, int c, int n, int m) {
		return r >= 0 && r < n && c >= 0 && c < m && board[r][c] == WHITE;
	}

	private static final boolean isBlack(int r, int c, int n, int m) {
		return r >= 0 && r < n && c >= 0 && c < m && board[r][c] == BLACK;
	}

	private static final void addEdge(int from, int target) {
		to[++edgeCnt] = target;
		next[edgeCnt] = head[from];
		head[from] = edgeCnt;
		reverseTo[edgeCnt] = from;
		reverseNext[edgeCnt] = reverseHead[target];
		reverseHead[target] = edgeCnt;
	}

	private static final void addForbidden(int literal) {
		addEdge(literal, literal ^ 1);
	}

	private static final void addNotBoth(int a, int b) {
		addEdge(a, b ^ 1);
		addEdge(b, a ^ 1);
	}

	private static final boolean isSatisfiable(int variableCnt) {
		int i;
		int curr;
		int edge;
		int nodeCnt;
		int nextNode;
		int top;
		int orderCnt;
		int componentCnt;
		boolean[] visited;
		int[] iter;
		int[] stack;
		int[] order;
		int[] component;

		nodeCnt = variableCnt << 1;
		visited = new boolean[nodeCnt];
		iter = new int[nodeCnt];
		stack = new int[nodeCnt];
		order = new int[nodeCnt];
		component = new int[nodeCnt];
		orderCnt = 0;
		for (i = 0; i < nodeCnt; i++) {
			if (visited[i]) {
				continue;
			}
			top = 0;
			stack[top] = i;
			iter[i] = head[i];
			visited[i] = true;
			while (top >= 0) {
				curr = stack[top];
				edge = iter[curr];
				while (edge != 0 && visited[to[edge]]) {
					edge = next[edge];
				}
				iter[curr] = edge;
				if (edge == 0) {
					order[orderCnt++] = curr;
					top--;
				} else {
					nextNode = to[edge];
					iter[curr] = next[edge];
					visited[nextNode] = true;
					stack[++top] = nextNode;
					iter[nextNode] = head[nextNode];
				}
			}
		}
		Arrays.fill(component, -1);
		componentCnt = 0;
		for (i = orderCnt - 1; i >= 0; i--) {
			if (component[order[i]] != -1) {
				continue;
			}
			top = 0;
			stack[top] = order[i];
			component[order[i]] = componentCnt;
			while (top >= 0) {
				curr = stack[top--];
				for (edge = reverseHead[curr]; edge != 0; edge = reverseNext[edge]) {
					nextNode = reverseTo[edge];
					if (component[nextNode] == -1) {
						component[nextNode] = componentCnt;
						stack[++top] = nextNode;
					}
				}
			}
			componentCnt++;
		}
		for (i = 0; i < variableCnt; i++) {
			if (component[i << 1] == component[(i << 1) | 1]) {
				return false;
			}
		}
		return true;
	}

	private static final boolean solve(int n, int m, int blackCnt, int whiteCnt) {
		int i;
		int j;
		int k;
		int l;
		int id;
		int cnt;
		int nodeCnt;
		int maxEdgeCnt;
		int variableCnt;
		int[] literals;

		if (whiteCnt != (blackCnt << 1)) {
			return false;
		}
		variableCnt = blackCnt << 1;
		nodeCnt = variableCnt << 1;
		maxEdgeCnt = (blackCnt << 2) + whiteCnt * 12 + 5;
		head = new int[nodeCnt];
		to = new int[maxEdgeCnt];
		next = new int[maxEdgeCnt];
		reverseHead = new int[nodeCnt];
		reverseTo = new int[maxEdgeCnt];
		reverseNext = new int[maxEdgeCnt];
		edgeCnt = 0;
		for (i = 0; i < n; i++) {
			for (j = 0; j < m; j++) {
				if (board[i][j] != BLACK) {
					continue;
				}
				id = blackId[i][j];
				if (!isWhite(i - 1, j, n, m)) {
					addForbidden(getLiteral(id, VERTICAL, UP));
				}
				if (!isWhite(i + 1, j, n, m)) {
					addForbidden(getLiteral(id, VERTICAL, DOWN));
				}
				if (!isWhite(i, j - 1, n, m)) {
					addForbidden(getLiteral(id, HORIZONTAL, LEFT));
				}
				if (!isWhite(i, j + 1, n, m)) {
					addForbidden(getLiteral(id, HORIZONTAL, RIGHT));
				}
			}
		}
		literals = new int[4];
		for (i = 0; i < n; i++) {
			for (j = 0; j < m; j++) {
				if (board[i][j] != WHITE) {
					continue;
				}
				cnt = 0;
				if (isBlack(i - 1, j, n, m)) {
					literals[cnt++] = getLiteral(blackId[i - 1][j], VERTICAL, DOWN);
				}
				if (isBlack(i + 1, j, n, m)) {
					literals[cnt++] = getLiteral(blackId[i + 1][j], VERTICAL, UP);
				}
				if (isBlack(i, j - 1, n, m)) {
					literals[cnt++] = getLiteral(blackId[i][j - 1], HORIZONTAL, RIGHT);
				}
				if (isBlack(i, j + 1, n, m)) {
					literals[cnt++] = getLiteral(blackId[i][j + 1], HORIZONTAL, LEFT);
				}
				for (k = 0; k < cnt; k++) {
					for (l = k + 1; l < cnt; l++) {
						addNotBoth(literals[k], literals[l]);
					}
				}
			}
		}
		return isSatisfiable(variableCnt);
	}

	public static void main(String[] args) throws IOException {
		int t;
		int n;
		int m;
		int i;
		int j;
		int tc;
		int blackCnt;
		int whiteCnt;
		String line;
		StringBuilder sb;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		t = Integer.parseInt(br.readLine());
		for (tc = 0; tc < t; tc++) {
			st = new StringTokenizer(br.readLine(), DELIM, false);
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			board = new char[n][];
			blackId = new int[n][m];
			blackCnt = 0;
			whiteCnt = 0;
			for (i = 0; i < n; i++) {
				line = br.readLine();
				board[i] = line.toCharArray();
				for (j = 0; j < m; j++) {
					if (board[i][j] == BLACK) {
						blackId[i][j] = blackCnt++;
					} else if (board[i][j] == WHITE) {
						whiteCnt++;
					}
				}
			}
			if (solve(n, m, blackCnt, whiteCnt)) {
				sb.append("YES\n");
			} else {
				sb.append("NO\n");
			}
		}
		System.out.print(sb);
	}
}