import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Solution {
	private static final int INF = Integer.MAX_VALUE;
	private static final int NUM = 12;
	private static final int SIZE = NUM + 2;
	private static final char POWER = '\0';
	private static final char CORE = '1';
	private static final char EMPTY = '0';
	
	private static int max, ans, coresSize, lenSize;
	private static int[] cores, temp, len;
	private static char[] map;
	private static boolean[] visited;
	private static ArrayList<LinkedList<Character>> lines, cross;
	
	private static void select(int core, int cnt, int sum) {
		if (core == coresSize) {
			if (cnt > max) {
				max = cnt;
				ans = sum;
			} else if (cnt == max) {
				ans = Math.min(ans, sum);
			}
			return;
		}
		loop:
		for (char line : lines.get(core)) {
			for (char prev : cross.get(line)) {
				if (visited[prev]) {
					continue loop;
				}
			}
			visited[line] = true;
			select(core + 1, cnt + 1, sum + len[line]);
			visited[line] = false;
		}
		select(core + 1, cnt, sum);
	}
	
	private static void connect() {
		int curr, core, num, i, j;
		char idx;
		LinkedList<Character> list;
		
		lenSize = coresSize * 4 + 1;
		lines = new ArrayList<>();
		idx = 0;
		for (i = 0; i < coresSize; i++) {
			core = cores[i];
			list = new LinkedList<>();
			idx++;
			curr = core - 1;
			for (j = 0; map[curr] != CORE && map[curr] != POWER; j++) {
				map[curr] = idx;
				curr--;
			}
			if (map[curr] == POWER) {
				len[idx] = j;
				list.add(idx);
			}
			idx++;
			curr = core + 1;
			for (j = 0; map[curr] != CORE && map[curr] != POWER; j++) {
				map[curr] = idx;
				curr++;
			}
			if (map[curr] == POWER) {
				len[idx] = j;
				list.add(idx);
			}
			lines.add(list);
		}
		cross = new ArrayList<>();
		for (j = 0; j < lenSize; j++) {
			cross.add(new LinkedList<>());
		}
		num = 0;
		for (i = 0; i < coresSize; i++) {
			core = cores[i];
			list = lines.get(num++);
			idx++;
			curr = core - SIZE;
			for (j = 0; map[curr] != CORE && map[curr] != POWER; j++) {
				if (map[curr] != EMPTY) {
					cross.get(map[curr]).add(idx);
					cross.get(idx).add(map[curr]);
				}
				curr -= SIZE;
			}
			if (map[curr] == POWER) {
				len[idx] = j;
				list.add(idx);
			}
			idx++;
			curr = core + SIZE;
			for (j = 0; map[curr] != CORE && map[curr] != POWER; j++) {
				if (map[curr] != EMPTY) {
					cross.get(map[curr]).add(idx);
					cross.get(idx).add(map[curr]);
				}
				curr += SIZE;
			}
			if (map[curr] == POWER) {
				len[idx] = j;
				list.add(idx);
			}
		}
	}
	
	private static int solution(BufferedReader br, StringTokenizer st) throws IOException {
		int n, pos, add, x, y, tempSize, core, i, j;
		
		n = Integer.parseInt(br.readLine());
		for (i = 1; i <= n; i++) {
			map[(n + 1) * SIZE + i] = POWER;
			map[i * SIZE + n + 1] = POWER;
		}
		coresSize = 0;
		tempSize = 0;
		for (i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (j = 1; j <= n; j++) {
				pos = i * SIZE + j;
				map[pos] = st.nextToken().charAt(0);
				if (map[pos] == CORE && i != 1 && i != n && j != 1 && j != n) {
					if (i == 2 || i == n - 1 || j == 2 || j == n - 1) {
						temp[tempSize++] = pos;
					} else {
						cores[coresSize++] = pos;
					}
				}
			}
		}
		add = 0;
		for (i = 0; i < tempSize; i++) {
			core = temp[i];
			x = core / SIZE;
			y = core % SIZE;
			if ((x == 2 && map[core - SIZE] == EMPTY) || (x == n - 1 && map[core + SIZE] == EMPTY) || (y == 2 && map[core - 1] == EMPTY) || (y == n - 1 && map[core + 1] == EMPTY)) {
				add++;
			} else {
				cores[coresSize++] = core;
			}
		}
		connect();
		max = 0;
		ans = INF;
		select(0, 0, 0);
		return ans + add;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int t, testCase;
		
		map = new char[SIZE * SIZE];
		cores = new int[NUM];
		temp = new int[NUM];
		len = new int[NUM * 4 + 1];
		visited = new boolean[NUM * 4 + 1];
		t = Integer.parseInt(br.readLine());
		for (testCase = 1; testCase <= t; testCase++) {
			sb.append('#').append(testCase).append(' ').append(solution(br, st)).append('\n');
		}
		System.out.print(sb);
	}
}
