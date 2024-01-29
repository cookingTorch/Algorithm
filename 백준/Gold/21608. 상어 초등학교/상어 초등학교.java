import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	private static int n;
	private static int[][] seat;
	private static ArrayList<ArrayList<Integer>> adj;
	
	private static int like(int num, int i, int j) {
		if (i < 0 || j < 0 || i >= n || j >= n) {
			return 0;
		}
		if (adj.get(num).contains(seat[i][j])) {
			return 1;
		}
		return 0;
	}
	
	private static int zero(int i, int j) {
		if (i < 0 || j < 0 || i >= n || j >= n) {
			return 0;
		}
		if (seat[i][j] == 0) {
			return 1;
		}
		return 0;
	}
	
	private static int getSatisfaction(int num, int i, int j) {
		int cnt;
		
		cnt = like(num, i - 1, j)
				+ like(num, i + 1, j)
				+ like(num, i, j - 1)
				+ like(num, i, j + 1);
		if (cnt == 0) {
			return 0;
		}
		return (int) Math.pow(10, cnt - 1);
	}
	
	private static int getEmpty(int i, int j) {
		return zero(i - 1, j)
				+ zero(i + 1, j)
				+ zero(i, j - 1)
				+ zero(i, j + 1);
	}
	
	private static void arrange(int num) {
		int satisfaction, empty, maxS, maxE, i, j, x, y;
		
		x = 0;
		y = 0;
		maxS = -1;
		maxE = -1;
		for (i = 0; i < n; i++) {
			for (j = 0; j < n; j++) {
				if (seat[i][j] != 0) {
					continue;
				}
				satisfaction = getSatisfaction(num, i, j);
				if (satisfaction > maxS) {
					x = i;
					y = j;
					maxS = satisfaction;
					maxE = getEmpty(i, j);
				} else if (satisfaction == maxS && (empty = getEmpty(i, j)) > maxE) {
					x = i;
					y = j;
					maxE = empty;
				}
			}
		}
		seat[x][y] = num;
	}
	
	private static int getMax() {
		int ans, i, j;
		
		ans = 0;
		for (i = 0; i < n; i++) {
			for (j = 0; j < n; j++) {
				ans += getSatisfaction(seat[i][j], i, j);
			}
		}
		return ans;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int size, student, i, j;
		int[] order;
		
		n = Integer.parseInt(br.readLine());
		size = n * n;
		order = new int[size];
		adj = new ArrayList<>();
		for (i = 0; i <= size; i++) {
			adj.add(new ArrayList<>());
		}
		for (i = 0; i < size; i++) {
			st = new StringTokenizer(br.readLine());
			student = Integer.parseInt(st.nextToken());
			order[i] = student;
			for (j = 0; j < 4; j++) {
				adj.get(student).add(Integer.parseInt(st.nextToken()));
			}
		}
		seat = new int[n][n];
		for (int num : order) {
			arrange(num);
		}
		System.out.println(getMax());
	}
}
