import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int n, q, num, block, left, right, start, end, i;
		int[] arr, unique, cnt, ans;
		int[][] query;
		HashMap<Integer, Integer> map = new HashMap<>();
		
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for (i = 0; i < n; i++) {
			num = Integer.parseInt(st.nextToken());
			arr[i] = num;
		}
		unique = Arrays.stream(arr).distinct().sorted().toArray();
		for (i = 0; i < unique.length; i++) {
			map.put(unique[i], i);
		}
		for (i = 0; i < n; i++) {
			arr[i] = map.get(arr[i]);
		}
		
		q = Integer.parseInt(br.readLine());
		query = new int[q][3];
		for (i = 0; i < q; i++) {
			st = new StringTokenizer(br.readLine());
			query[i][0] = i;
			query[i][1] = Integer.parseInt(st.nextToken()) - 1;
			query[i][2] = Integer.parseInt(st.nextToken()) - 1;
		}
		
		block = (int) Math.sqrt(n);
		Arrays.sort(query, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				int a, b;
				
				a = o1[1] / block;
				b = o2[1] / block;
				if (a == b) {
					return o1[2] - o2[2];
				}
				return a - b;
			}
		});
		
		cnt = new int[unique.length];
		ans = new int[q];
		left = query[0][1];
		right = query[0][1] - 1;
		end = query[0][2];
		while (right < end) {
			if (cnt[arr[++right]]++ == 0) {
				ans[query[0][0]]++;
			}
		}
		for (i = 1; i < q; i++) {
			start = query[i][1];
			end = query[i][2];
			ans[query[i][0]] = ans[query[i - 1][0]];
			while (start < left) {
				if (cnt[arr[--left]]++ == 0) {
					ans[query[i][0]]++;
				}
			}
			while (left < start) {
				if (--cnt[arr[left++]] == 0) {
					ans[query[i][0]]--;
				}
			}
			while (right < end) {
				if (cnt[arr[++right]]++ == 0) {
					ans[query[i][0]]++;
				}
			}
			while (end < right) {
				if (--cnt[arr[right--]] == 0) {
					ans[query[i][0]]--;
				}
			}
		}
		
		for (i = 0; i < q; i++) {
			sb.append(ans[i]).append('\n');
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}