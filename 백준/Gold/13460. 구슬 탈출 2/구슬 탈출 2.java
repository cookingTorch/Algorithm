import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static final int LIMIT = 10;
	private static final int[] DX = {-1, 0, 1, 0};
	private static final int[] DY = {0, 1, 0, -1};
	private static final char RED = 'R';
	private static final char BLUE = 'B';
	private static final char EMPTY = '.';
	private static final char HOLE = 'O';
	
	private static class Node {
		int dir, dist;
		int[] red, blue;
		
		Node(int[] red, int[] blue, int dir, int dist) {
			this.red = red;
			this.blue = blue;
			this.dir = dir;
			this.dist = dist;
		}
	}
	
	private static char[][] map;
	
	private static int[] getClone(int[] origin) {
		int[] clone;
		
		clone = new int[2];
		clone[0] = origin[0];
		clone[1] = origin[1];
		return clone;
	}
	
	private static boolean move(int[] marble, int[] other, int dir) {
		int nx, ny;
		
		nx = marble[0] + DX[dir];
		ny = marble[1] + DY[dir];
		while (map[nx][ny] == EMPTY && (nx != other[0] || ny != other[1])) {
			marble[0] = nx;
			marble[1] = ny;
			nx = marble[0] + DX[dir];
			ny = marble[1] + DY[dir];
		}
		if (map[nx][ny] == HOLE) {
			marble[0] = nx;
			marble[1] = ny;
			return true;
		}
		return false;
	}
	
	private static char tilt(int[] red, int[] blue, int dir) {
		boolean flag;
		
		flag = false;
		if (move(red, blue, dir)) {
			flag = true;
		}
		if (move(blue, red, dir)) {
			return BLUE;
		}
		if (move(red, blue, dir)) {
			flag = true;
		}
		if (move(blue, red, dir)) {
			return BLUE;
		}
		if (flag) {
			return RED;
		}
		return EMPTY;
	}
	
	private static int bfs(int[] red, int[] blue) {
		int dir, dist;
		Node curr;
		Queue<Node> q;
		
		q = new ArrayDeque<>();
		q.add(new Node(getClone(red), getClone(blue), 0, 1));
		q.add(new Node(getClone(red), getClone(blue), 1, 1));
		q.add(new Node(getClone(red), getClone(blue), 2, 1));
		q.add(new Node(getClone(red), getClone(blue), 3, 1));
		while (!q.isEmpty()) {
			curr = q.poll();
			red = curr.red;
			blue = curr.blue;
			dir = curr.dir;
			dist = curr.dist;
			switch (tilt(red, blue, dir)) {
			case RED:
				return curr.dist;
			case BLUE:
				continue;
			case EMPTY:
				if (dist < LIMIT) {
					q.add(new Node(getClone(red), getClone(blue), (dir + 1) % 4, dist + 1));
					q.add(new Node(getClone(red), getClone(blue), (dir + 3) % 4, dist + 1));
				}
			}
		}
		return -1;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n, m, i, j;
		int[] red, blue;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new char[n][m];
		red = new int[2];
		blue = new int[2];
		for (i = 0; i < n; i++) {
			map[i] = br.readLine().toCharArray();
			for (j = 0; j < m; j++) {
				if (map[i][j] == RED) {
					red[0] = i;
					red[1] = j;
					map[i][j] = EMPTY;
				} else if (map[i][j] == BLUE) {
					blue[0] = i;
					blue[1] = j;
					map[i][j] = EMPTY;
				}
			}
		}
		System.out.print(bfs(red, blue));
	}
}
