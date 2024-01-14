import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int n, m, k, l, r, i, j, o, start, end, block, back, front;
		int[] arr, cnt, bucket, ans;
		int[][] q;
		ArrayList<Deque<Integer>> dq = new ArrayList<>();
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for (i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		m = Integer.parseInt(br.readLine());
		q = new int[m][3];
		for (i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			q[i][0] = i;
			q[i][1] = Integer.parseInt(st.nextToken()) - 1;
			q[i][2] = Integer.parseInt(st.nextToken()) - 1;
		}
		block = (int) Math.sqrt(n);
		Arrays.sort(q, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				int a, b;
				
				a = o1[1] / block;
				b = o2[1] / block;
				return a == b ? o1[2] - o2[2] : a - b;
			}
		});
		cnt = new int[n];
		bucket = new int[(n / block) + 1];
		for (i = 0; i <= k; i++) {
			dq.add(new ArrayDeque<>());
		}
		start = q[0][1];
		end = q[0][1] - 1;
		ans = new int[m];
		for (i = 0; i < m; i++) {
			l = q[i][1];
			r = q[i][2];
			while (end < r) {
				if (dq.get(arr[++end]).isEmpty()) {
					dq.get(arr[end]).add(end);
					cnt[0]++;
					bucket[0]++;
				} else {
					back = dq.get(arr[end]).peekLast();
					front = dq.get(arr[end]).peek();
					cnt[back - front]--;
					bucket[(back - front) / block]--;
					dq.get(arr[end]).add(end);
					cnt[end - front]++;
					bucket[(end - front) / block]++;
				}
			}
			while (l < start) {
				if (dq.get(arr[--start]).isEmpty()) {
					dq.get(arr[start]).addFirst(start);
					cnt[0]++;
					bucket[0]++;
				} else {
					back = dq.get(arr[start]).peekLast();
					front = dq.get(arr[start]).peek();
					cnt[back - front]--;
					bucket[(back - front) / block]--;
					dq.get(arr[start]).addFirst(start);
					cnt[back - start]++;
					bucket[(back - start) / block]++;
				}
			}
			while (r < end) {
				front = dq.get(arr[end]).peek();
				cnt[end - front]--;
				bucket[(end - front) / block]--;
				dq.get(arr[end]).removeLast();
				if (dq.get(arr[end]).isEmpty()) {
					end--;
				} else {
					back = dq.get(arr[end--]).peekLast();
					cnt[back - front]++;
					bucket[(back - front) / block]++;
				}
			}
			while (start < l) {
				back = dq.get(arr[start]).peekLast();
				cnt[back - start]--;
				bucket[(back - start) / block]--;
				dq.get(arr[start]).remove();
				if (dq.get(arr[start]).isEmpty()) {
					start++;
				} else {
					front = dq.get(arr[start++]).peek();
					cnt[back - front]++;
					bucket[(back - front) / block]++;
				}
			}
			for (j = bucket.length - 1; j >= 0; j--) {
				if (bucket[j] > 0) {
					break;
				}
			}
			for (o = Math.min(n - 1, (j + 1) * block - 1); o >= j * block; o--) {
				if (cnt[o] > 0) {
					ans[q[i][0]] = o;
					break;
				}
			}
		}
		for (i = 0; i < m; i++) {
			sb.append(ans[i]).append('\n');
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}