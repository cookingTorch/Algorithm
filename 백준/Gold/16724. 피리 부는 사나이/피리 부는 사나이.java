import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int[] roots;
	
	private static int find(int v) {
		if (roots[v] <= 0) {
			return v;
		}
		return roots[v] = find(roots[v]);
	}
	
	private static void union(int u, int v) {
		int ru, rv;
		
		ru = find(u);
		rv = find(v);
		if (ru == rv) {
			return;
		}
		if (roots[ru] > roots[rv]) {
			roots[ru] = rv;
		} else {
			if (roots[ru] == roots[rv]) {
				roots[ru]--;
			}
			roots[rv] = ru;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n, m, size, max, pos, next, root, cnt, i, j;
		int[] map;
		String str;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		size = m + 2;
		max = (n + 2) * size;
		map = new int[max];
		for (i = 1; i <= n; i++) {
			str = br.readLine();
			for (j = 1; j <= m; j++) {
				pos = i * size + j;
				switch (str.charAt(j - 1)) {
				case 'U':
					map[pos] = -size;
					break;
				case 'R':
					map[pos] = 1;
					break;
				case 'D':
					map[pos] = size;
					break;
				case 'L':
					map[pos] = -1;
					break;
				}
			}
		}
		roots = new int[max];
		for (i = 1; i <= n; i++) {
			for (j = 1; j <= m; j++) {
				pos = i * size + j;
				next = pos + map[pos];
				if (map[next] != 0) {
					union(pos, next);
				}
			}
		}
		cnt = 0;
		for (i = 1; i <= n; i++) {
			for (j = 1; j <= m; j++) {
				pos = i * size + j;
				root = find(pos);
				if (map[root] != 0) {
					cnt++;
					map[root] = 0;
				}
			}
		}
		System.out.print(cnt);
	}
}
