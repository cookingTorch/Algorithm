import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		String str;
		StringTokenizer st;
		
		int n, m, i, j, left, right, now, max = 0, k;
		int[] arr, cnt, table, ans;
		int[][] query;
		
		// 수열 입력
		str = br.readLine();
		n = Integer.parseInt(str);
		arr = new int[n + 1];
		str = br.readLine();
		st = new StringTokenizer(str);
		for (k = 1; k <= n; k++) {
			arr[k] = Integer.parseInt(st.nextToken());
		}
		
		// 쿼리 입력
		str = br.readLine();
		m = Integer.parseInt(str);
		query = new int[m][];
		for (k = 0; k < m; k++) {
			str = br.readLine();
			st = new StringTokenizer(str);
			i = Integer.parseInt(st.nextToken());
			j = Integer.parseInt(st.nextToken());
			query[k] = new int[] {k, i, j};
		}
		
		// 정렬
		Arrays.sort(query, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				int sqrtM = (int) Math.sqrt(m), a = o1[1] / sqrtM, b = o2[1] / sqrtM;
				if (a == b) {
					return o1[2] - o2[2];
				}
				else {
					return a - b;
				}
			}
		});
		
		// 첫 쿼리 수행
		cnt = new int[100001];
		table = new int[100001];
		table[0] = 100000;
		ans = new int[m];
		left = query[0][1];
		right = query[0][1] - 1;
		while (right < query[0][2]) {
			now = ++cnt[arr[++right]];
			table[now]++;
			table[now - 1]--;
			max = Math.max(max, now);
		}
		ans[query[0][0]] = max;
		
		// 나머지 쿼리 수행
		for (k = 1; k < m; k++) {
			while (left > query[k][1]) {
				now = ++cnt[arr[--left]];
				table[now - 1]--;
				table[now]++;
				max = Math.max(max, now);
			}
			while (right < query[k][2]) {
				now = ++cnt[arr[++right]];
				table[now - 1]--;
				table[now]++;
				max = Math.max(max, now);
			}
			while (left < query[k][1]) {
				now = --cnt[arr[left++]];
				if (--table[now + 1] == 0 && now + 1 == max) {
					max--;
				}
				table[now]++;
			}
			while (right > query[k][2]) {
				now = --cnt[arr[right--]];
				if (--table[now + 1] == 0 && now + 1 == max) {
					max--;
				}
				table[now]++;
			}
			ans[query[k][0]] = max;
		}
		
		// 출력
		for (k = 0; k < m; k++) {
			sb.append(ans[k]).append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}