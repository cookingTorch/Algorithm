import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	private static int[][] dir = {
			{-1, 0},
			{-1, 1},
			{0, 1},
			{1, 1},
			{1, 0},
			{1, -1},
			{0, -1},
			{-1, -1}};

	private static final class FireBall {
		private static ArrayDeque<FireBall> bin = new ArrayDeque<>();

		int x;
		int y;
		int m;
		int s;
		int d;

		FireBall(int x, int y, int m, int s, int d) {
			this.x = x;
			this.y = y;
			this.m = m;
			this.s = s;
			this.d = d;
		}

		static FireBall getFireBall(int x, int y, int m, int s, int d) {
			if (bin.isEmpty()) {
				return new FireBall(x, y, m, s, d);
			}
			return bin.pollFirst().setFireBall(x, y, m, s, d);
		}

		FireBall setFireBall(int x, int y, int m, int s, int d) {
			this.x = x;
			this.y = y;
			this.m = m;
			this.s = s;
			this.d = d;
			return this;
		}

		void move() {
			map[x][y].remove(this);
			x = ((x + (dir[d][0] * s)) % n + n) % n;
			y = ((y + (dir[d][1] * s)) % n + n) % n;
			map[x][y].add(this);
			if (!mergeMap[x][y]) {
				mergeMap[x][y] = true;
				mergeQ.addLast(new int[] {x, y});
			}
		}

		void delete() {
			bin.addLast(this);
		}
	}

	private static int n;
	private static boolean[][] mergeMap;
	private static ArrayDeque<int[]> mergeQ;
	private static ArrayDeque<FireBall> q;
	private static LinkedList<FireBall>[][] map;

	private static void merge(int x, int y) {
		int i;
		int d;
		int mod;
		int mSum;
		int sSum;
		int size;
		boolean flag;
		FireBall newFireBall;

		mSum = 0;
		sSum = 0;
		mod = map[x][y].getFirst().d & 1;
		flag = true;
		size = map[x][y].size();
		for (FireBall fireBall : map[x][y]) {
			mSum += fireBall.m;
			sSum += fireBall.s;
			if ((fireBall.d & 1) != mod) {
				flag = false;
			}
			fireBall.delete();
		}
		map[x][y].clear();
		if (mSum / 5 == 0) {
			return;
		}
		d = flag ? 0 : 1;
		for (i = 0; i < 4; i++, d += 2) {
			newFireBall = FireBall.getFireBall(x, y, mSum / 5, sSum / size, d);
			map[x][y].add(newFireBall);
			q.addLast(newFireBall);
		}
	}

	private static void moveAll() {
		int x;
		int y;
		int[] pos;

		while (!q.isEmpty()) {
			q.pollFirst().move();
		}
		while (!mergeQ.isEmpty()) {
			pos = mergeQ.pollFirst();
			x = pos[0];
			y = pos[1];
			if (map[x][y].size() > 1) {
				merge(x, y);
			} else {
				q.addLast(map[x][y].getFirst());
			}
			mergeMap[x][y] = false;
		}
	}

	public static void main(String[] args) throws IOException {
		int m;
		int k;
		int x;
		int y;
		int i;
		int j;
		int ans;
		FireBall fireBall;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		map = new LinkedList[n][n];
		for (i = 0; i < n; i++) {
			for (j = 0; j < n; j++) {
				map[i][j] = new LinkedList<>();
			}
		}
		q = new ArrayDeque();
		for (i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken()) - 1;
			y = Integer.parseInt(st.nextToken()) - 1;
			fireBall = new FireBall(x, y, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			map[x][y].add(fireBall);
			q.addLast(fireBall);
		}
		mergeMap = new boolean[n][n];
		mergeQ = new ArrayDeque<>();
		for (i = 0; i < k; i++) {
			moveAll();
		}
		ans = 0;
		while (!q.isEmpty()) {
			ans += q.pollFirst().m;
		}
		System.out.print(ans);
	}
}
