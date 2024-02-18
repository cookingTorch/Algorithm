import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Solution {
	private static final int SIZE = 14;
	private static final int MAX = SIZE * SIZE;
	
	private static int coreNum, minLen, maxConnected;
	private static int[] map, len;
	private static boolean[] visited;
	private static ArrayList<Integer> core;
	private static ArrayList<ArrayList<Integer>> wires, cross;
	
	private static void connect() {
		int idx, i, j;
		
		len = new int[coreNum * 4 + 1];
		idx = 1;
		for (i = 0; i < coreNum; i++) {
			for (j = core.get(i) - 1; map[j] != 1; j--) {
				if (map[j] == 2) {
					wires.get(i).add(idx);
					len[idx] = core.get(i) - j - 1;
					for (j = core.get(i) - 1; map[j] != 2; j--) {
						map[j] = -idx;
					}
					idx++;
					break;
				}
			}
			for (j = core.get(i) + 1; map[j] != 1; j++) {
				if (map[j] == 2) {
					wires.get(i).add(idx);
					len[idx] = j - core.get(i) - 1;
					for (j = core.get(i) + 1; map[j] != 2; j++) {
						map[j] = -idx;
					}
					idx++;
					break;
				}
			}
		}
		cross.clear();
		for (i = 0; i < coreNum * 4 + 1; i++) {
			cross.add(new ArrayList<>());
		}
		for (i = 0; i < coreNum; i++) {
			for (j = core.get(i) - SIZE; map[j] != 1; j -= SIZE) {
				if (map[j] == 2) {
					wires.get(i).add(idx);
					len[idx] = (core.get(i) - j) / SIZE - 1;
					for (j = core.get(i) - SIZE; map[j] != 2; j -= SIZE) {
						if (map[j] < 0) {
							cross.get(idx).add(-map[j]);
							cross.get(-map[j]).add(idx);
						}
					}
					idx++;
					break;
				}
			}
			for (j = core.get(i) + SIZE; map[j] != 1; j += SIZE) {
				if (map[j] == 2) {
					wires.get(i).add(idx);
					len[idx] = (j - core.get(i)) / SIZE - 1;
					for (j = core.get(i) + SIZE; map[j] != 2; j += SIZE) {
						if (map[j] < 0) {
							cross.get(idx).add(-map[j]);
							cross.get(-map[j]).add(idx);
						}
					}
					idx++;
					break;
				}
			}
		}
		visited = new boolean[idx];
	}
	
	private static void dfs(int sum, int depth, int connected) {
		if (depth == coreNum) {
			if (connected > maxConnected) {
				maxConnected = connected;
				minLen = sum;
			} else if (connected == maxConnected) {
				minLen = Math.min(minLen, sum);
			}
			return;
		}
		loop:
		for (int wire : wires.get(depth)) {
			for (int prev : cross.get(wire)) {
				if (visited[prev]) {
					continue loop;
				}
			}
			visited[wire] = true;
			dfs(sum + len[wire], depth + 1, connected + 1);
			visited[wire] = false;
		}
		dfs(sum, depth + 1, connected);
	}
	
	private static int solution(BufferedReader br, StringTokenizer st) throws IOException {
		int n, ans, pos, i, j;
		
		n = Integer.parseInt(br.readLine());
		for (i = 1; i < n + 2; i++) {
			map[i * SIZE + n + 1] = 2;
			map[(n + 1) * SIZE + i] = 2;
		}
		ans = 0;
		core.clear();
		wires.clear();
		for (i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (j = 1; j <= n; j++) {
				pos = i * SIZE + j;
				map[pos] = Integer.parseInt(st.nextToken());
				if (map[pos] == 1 && i != 1 && j != 1 && i != n && j != n) {
					if ((i == 2 && map[pos - SIZE] == 0) || (j == 2 && map[pos - 1] == 0)) {
						ans++;
					} else if (i != n - 1 && j != n - 1) {
						core.add(pos);
						wires.add(new ArrayList<>());
					}
				} else if (i == n && map[pos - SIZE] == 1 && j > 2 && j < n - 1) {
					if (map[pos] == 0) {
						ans++;
					} else {
						core.add(pos - SIZE);
						wires.add(new ArrayList<>());
					}
				} else if (j == n && map[pos - 1] == 1 && i > 2 && i < n - 1) {
					if (map[pos] == 0) {
						ans++;
					} else {
						core.add(pos - 1);
						wires.add(new ArrayList<>());
					}
				}
			}
		}
		if (map[pos = 2 * SIZE + n - 1] == 1 && map[pos - SIZE] == 1) {
			if (map[pos + 1] == 0) {
				ans++;
			} else {
				core.add(pos);
				wires.add(new ArrayList<>());
			}
		}
		if (map[pos = (n - 1) * SIZE + 2] == 1 && map[pos - 1] == 1) {
			if (map[pos + SIZE] == 0) {
				ans++;
			} else {
				core.add(pos);
				wires.add(new ArrayList<>());
			}
		}
		if (map[pos = (n - 1) * SIZE + n - 1] == 1) {
			if (map[pos + 1] == 0 || map[pos + SIZE] == 0) {
				ans++;
			} else {
				core.add(pos);
				wires.add(new ArrayList<>());
			}
		}
		coreNum = core.size();
		if (coreNum == 0) {
			return ans;
		}
		connect();
		maxConnected = 0;
		minLen = 0;
		dfs(0, 0, 0);
		return ans + minLen;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int t, testCase, i;
		
		map = new int[MAX];
		for (i = 0; i < SIZE; i++) {
			map[i] = 2;
			map[i * SIZE] = 2;
		}
		core = new ArrayList<>();
		wires = new ArrayList<>();
		cross = new ArrayList<>();
		t = Integer.parseInt(br.readLine());
		for (testCase = 1; testCase <= t; testCase++) {
			sb.append('#').append(testCase).append(' ').append(solution(br, st)).append('\n');
		}
		System.out.print(sb);
	}
}
